# Teacher Coding Reference — Java Swing / DAO Pattern

This file summarizes the teacher-provided source code under `se/demo CNPM hotel/`.

## Boundary

The source code is an unrelated hotel management demo. Use it only as a coding methodology reference.

Do not copy hotel-specific names into the Parttime Employee Management module:

- packages such as `view.room`, `view.booking`, or `view.stat.room`;
- classes such as `Room`, `RoomDAO`, `SearchRoomFrm`, `EditRoomFrm`, `BookingDAO`, `ClientDAO`;
- tables such as `tblRoom`, `tblClient`, `tblBooking`, `tblBookedRoom`;
- users/roles such as Manager and Seller unless the real project independently defines equivalent roles.

## Project organization pattern

The teacher's Java project is organized in Eclipse/IntelliJ with these conceptual packages:

- `model`: entity classes;
- `dao`: DAO classes and shared database connection;
- `test.unit`: JUnit tests for DAO classes;
- `view.<module>`: Swing boundary/view classes grouped by functional module;
- `view.user`: login and home screens.

For the hotel demo, examples include:

- `model.Room`, `model.User`, `model.Booking`;
- `dao.DAO`, `dao.UserDAO`, `dao.RoomDAO`;
- `test.unit.RoomDaoTest`;
- `view.user.LoginFrm`, `view.user.ManagerHomeFrm`;
- `view.room.ManageRoomFrm`, `view.room.SearchRoomFrm`, `view.room.EditRoomFrm`.

## Entity class pattern

Entity classes in `model` are simple JavaBeans:

- package declaration: `package model;`;
- usually implement `Serializable`;
- private fields;
- no database logic;
- default constructor;
- optional parameterized constructor;
- getters and setters for every attribute;
- object relationships represented by object fields or lists when needed.

Teacher example method style:

```java
public class Room implements Serializable{
    private int id;
    private String name;

    public Room() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
```

## DAO base class pattern

The shared `DAO` class owns the static database connection:

- package declaration: `package dao;`;
- imports `java.sql.Connection` and `java.sql.DriverManager`;
- static `Connection con` shared by DAO subclasses;
- constructor initializes the connection once if `con == null`;
- subclasses call `super()` in their constructors.

The teacher sample hardcodes local database configuration. For the real project, database URL, database name, username, and password must be adapted to the local Parttime Employee Management database, not the hotel database.

## DAO subclass pattern

DAO classes extend `DAO` and contain SQL operations for one entity or one aggregate operation.

Typical rules:

- package declaration: `package dao;`;
- import `PreparedStatement`, `ResultSet`, and needed model classes;
- constructor calls `super()`;
- use `PreparedStatement` with `?` parameters;
- pack each database row into model objects by calling setters;
- return `ArrayList<Entity>` for search/list methods;
- return `boolean` for update/save operations when the caller needs success/failure;
- use transactions for multi-row operations that must succeed or fail together.

Search method pattern:

```java
public ArrayList<Room> searchRoom(String key){
    ArrayList<Room> result = new ArrayList<Room>();
    String sql = "SELECT * FROM tblRoom WHERE name LIKE ?";
    try{
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + key + "%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Room rm = new Room();
            rm.setId(rs.getInt("id"));
            result.add(rm);
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    return result;
}
```

Login method pattern:

- receive a partially filled `User` object containing username and password;
- query `tblUser` with username/password;
- if found, fill remaining user fields and return `true`;
- otherwise return `false`.

Multi-row transaction pattern:

- call `con.setAutoCommit(false)`;
- perform parent insert and child inserts;
- rollback if a validation query fails or an exception occurs;
- commit in normal application mode;
- tests may comment out commit and use rollback.

## Swing view pattern

View classes are Swing `JFrame` classes:

- package by module, such as `view.user` or `view.room`;
- extend `JFrame`;
- implement `ActionListener`;
- private Swing component attributes such as `JTextField`, `JButton`, `JTable`;
- hidden object attributes such as logged-in `User` and selected entity;
- constructor builds the UI and registers listeners;
- `actionPerformed(ActionEvent e)` dispatches button events;
- successful operations navigate by opening the next form and disposing the current form.

Search form pattern:

- constructor receives the logged-in `User`;
- hidden list stores the current search result objects;
- `btnSearch` calls a DAO search method;
- results are displayed in a non-editable `JTable` using `DefaultTableModel`;
- mouse click on a row opens the next form with the selected object and logged-in user.

Edit/save form pattern:

- constructor receives `User` and selected object;
- form fields are initialized from the selected object;
- save/update button writes UI input back into the object;
- DAO method saves data;
- success message is shown using `JOptionPane`;
- after success, the form navigates back to a home/manage form.

## JUnit DAO testing pattern

JUnit tests live in `test.unit` and test DAO methods directly.

Common patterns:

- instantiate the DAO as a field;
- call DAO method with controlled input;
- assert result is not null;
- assert expected result size or field values;
- for update/save methods, disable auto-commit, execute the method, assert the database result, then rollback and re-enable auto-commit.

Example testing intentions:

- exception/no-result search case returns an empty list, not null;
- standard search case returns expected rows and each row matches the keyword;
- update/save case changes data as expected, then rollback restores the test database.

## What to imitate

For the Parttime Employee Management implementation, imitate:

- Java package layering: `model`, `dao`, `view.<module>`, `view.user`, `test.unit`;
- entity classes as JavaBeans;
- DAO classes as database access classes;
- Swing forms as boundary classes;
- constructor-based passing of hidden context objects like `User` and selected entity;
- DAO methods matching design sequence diagrams;
- JUnit tests focused on DAO behavior and database state.

## What not to imitate blindly

Do not blindly copy:

- hotel names, roles, tables, or database URL;
- typo-level text such as `Loged` or `succeffully`;
- obsolete driver class names if the actual JDBC driver requires a newer class name;
- comments that are not useful for the current code;
- unrelated modules such as room statistics or hotel booking.
