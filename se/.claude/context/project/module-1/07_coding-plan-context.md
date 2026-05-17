# Module 1 Coding Context — Register for Next Week

Use this file when implementing Module 1 code. It adapts the teacher's Java Swing / DAO coding pattern to the Parttime Employee Management project.

## Boundary

The teacher code under `se/demo CNPM hotel/` is methodology-only. Module 1 code must use the Parttime Employee Management domain from the current report.

Do not use hotel names such as Room, Booking, Client, Manager, Seller, SearchRoomFrm, EditRoomFrm, or tblRoom in Module 1 implementation.

## Target package organization

Use the teacher's package layering, adapted to Module 1:

```text
model
  User.java
  Restaurant.java
  Employee.java
  Shift.java
  RegistrationShift.java

dao
  DAO.java
  UserDAO.java
  EmployeeDAO.java
  ShiftDAO.java
  RegistrationShiftDAO.java

view.user
  LoginFrm.java
  HomeFrm.java

view.registration
  SearchEmployeeFrm.java
  RegisterShiftFrm.java

test.unit
  UserDAOTest.java
  EmployeeDAOTest.java
  ShiftDAOTest.java
  RegistrationShiftDAOTest.java
```

If the actual source project already has a different package structure, inspect it first and preserve it unless the user asks to reorganize.

## Entity classes

Entity classes should be simple JavaBeans in `model`.

### User

Fields:

- `id: int`;
- `username: String`;
- `password: String`;
- `role: String`;
- `description: String`.

### Restaurant

Fields:

- `id: int`;
- `restaurantName: String`;
- `address: String`;
- `description: String`;
- optional `ArrayList<Employee> employees` if needed by the UI or DAO.

### Employee

Fields:

- `id: int`;
- `code: String`;
- `phoneNumber: String`;
- `fullName: String`;
- `email: String`;
- `dob: Date`;
- `contractDate: Date`;
- optional `Restaurant restaurant` or `int restaurantID` depending on existing code style.

### Shift

Fields:

- `id: int`;
- `date: Date`;
- `shiftNumber: int`;
- `description: String`;
- `startDate: Date`;
- `endDate: Date`.

### RegistrationShift

Fields:

- `id: int`;
- `registeredTime: Date` or `Timestamp`;
- `status: String`;
- `description: String`;
- `employee: Employee`;
- `shift: Shift`;
- `user: User`.

## Database tables and column names

Use the design-phase table names:

- `tblUser`;
- `tblRestaurant`;
- `tblEmployee`;
- `tblShift`;
- `tblRegistrationShift`.

Use these design-phase column names unless the actual database schema differs and the user confirms the schema:

- `tblUser`: `ID`, `username`, `password`, `role`, `description`;
- `tblRestaurant`: `ID`, `restaurantName`, `address`, `description`;
- `tblEmployee`: `ID`, `code`, `fullName`, `phoneNumber`, `email`, `dob`, `contractDate`, `restaurantID`;
- `tblShift`: `ID`, `date`, `shiftNumber`, `description`, `startDate`, `endDate`;
- `tblRegistrationShift`: `ID`, `registeredTime`, `status`, `description`, `employeeID`, `shiftID`, `userID`.

## DAO methods for Module 1

DAO methods should match the design report and scenario v.3.

### UserDAO

```java
public boolean checkLogin(User user)
```

Behavior:

- uses username and password from the input `User` object;
- queries `tblUser`;
- if found, fills `id`, `role`, and `description` into the same `User` object;
- returns `true` if login succeeds, otherwise `false`.

### EmployeeDAO

```java
public ArrayList<Employee> searchEmployee(String key)
```

Behavior:

- searches employees by `fullName LIKE ?`;
- returns an empty `ArrayList<Employee>` if no result;
- packs every row into an `Employee` object.

### ShiftDAO

```java
public ArrayList<Shift> getShiftNextWeek()
```

Behavior:

- calculates next week's date range internally;
- queries `tblShift` for shifts in that range;
- returns fourteen shifts for seven days and two shifts per day in the standard case;
- packs every row into a `Shift` object.

### RegistrationShiftDAO

```java
public ArrayList<RegistrationShift> getRegistrationByEmployee(Employee employee)
public boolean saveRegistration(ArrayList<RegistrationShift> list)
```

`getRegistrationByEmployee` behavior:

- receives the selected employee;
- queries next-week registrations for that employee;
- packs each record with `Employee`, `Shift`, and `User` object references as needed by the UI.

`saveRegistration` behavior:

- saves selected shift registrations;
- use a transaction if inserting multiple rows;
- rollback on failure;
- return `true` only if the whole save operation succeeds.

If implementing overwrite behavior, delete or replace existing next-week registrations for the employee inside the same transaction before inserting the new selected registrations.

## Swing view flow

Follow the report's design flow:

```text
LoginFrm -> HomeFrm -> SearchEmployeeFrm -> RegisterShiftFrm -> HomeFrm
```

### LoginFrm

- package: `view.user`;
- extends `JFrame` and implements `ActionListener`;
- visible fields: username input, password input, login button;
- creates `User` from input;
- calls `UserDAO.checkLogin(user)`;
- on success and correct role, opens `HomeFrm(user)`.

### HomeFrm

- package: `view.user`;
- hidden attribute: `User user`;
- constructor: `HomeFrm(User user)`;
- contains Register for next week button;
- opens `SearchEmployeeFrm(user)`.

### SearchEmployeeFrm

- package: `view.registration`;
- hidden attributes: `User user`, `ArrayList<Employee> listEmployee`;
- constructor: `SearchEmployeeFrm(User user)`;
- search button calls `EmployeeDAO.searchEmployee(key)`;
- displays `ID`, `code`, `fullName`, `phoneNumber`, and possibly `email` in a non-editable `JTable`;
- mouse click on a result row opens `RegisterShiftFrm(selectedEmployee, user)`.

### RegisterShiftFrm

- package: `view.registration`;
- hidden attributes: `Employee employee`, `User user`, `ArrayList<Shift> listShift`, existing registrations if needed;
- constructor: `RegisterShiftFrm(Employee employee, User user)`;
- during constructor initialization, call `ShiftDAO.getShiftNextWeek()`;
- if needed, call `RegistrationShiftDAO.getRegistrationByEmployee(employee)`;
- display selected employee info and a 7 x 2 shift checkbox table;
- save button validates the minimum shift threshold;
- create one `RegistrationShift` object per selected shift;
- call `RegistrationShiftDAO.saveRegistration(list)`;
- show success message and return to `HomeFrm(user)`.

## JUnit testing targets

Create DAO tests in `test.unit` following the teacher's JUnit style.

Recommended tests:

- `UserDAOTest`: valid login and invalid login;
- `EmployeeDAOTest`: search with matching keyword, no matching keyword, empty keyword behavior if DAO is expected to handle it;
- `ShiftDAOTest`: next-week shifts are not null and contain the expected count/date range;
- `RegistrationShiftDAOTest`: save standard registration list, verify rows were inserted, then rollback.

For write tests, use transaction rollback to keep the test database stable.

## Implementation guardrails

- Match the current LaTeX design before coding.
- Keep code close to the teacher's Java Swing / DAO style unless the user asks for modernization.
- Prefer `PreparedStatement` for all SQL parameters.
- Do not add unrelated modules.
- Do not invent extra entities beyond `User`, `Restaurant`, `Employee`, `Shift`, and `RegistrationShift` for Module 1.
- Keep UI simple and traceable to the sequence diagram.
- Do not use hotel data or hotel class/table names.
