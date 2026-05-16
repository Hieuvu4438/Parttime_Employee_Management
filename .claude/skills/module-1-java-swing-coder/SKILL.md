---
name: module-1-java-swing-coder
description: Use this skill when implementing Java Swing, DAO, model, or JUnit code for Module 1 Register for next week in the Parttime Employee Management project using the teacher's coding pattern as methodology only.
---

# Module 1 Java Swing Coder

Use this skill when the user asks to code, generate, revise, or test Java source files for Module 1: Register for next week.

## Non-negotiable boundary

The teacher code under `se/demo CNPM hotel/` is an unrelated hotel management demo. It is a reference for coding structure only.

Never copy hotel domain names into Module 1 code unless the current Parttime Employee Management project independently defines them.

Do not use:

- `Room`, `Booking`, `BookedRoom`, `Client`, `Hotel`, `RoomStat`, `Service`, `UsedService`;
- `RoomDAO`, `BookingDAO`, `ClientDAO`, `RoomStatDAO`;
- `SearchRoomFrm`, `EditRoomFrm`, `ManageRoomFrm`, `ManagerHomeFrm`, `SellerHomeFrm`;
- `tblRoom`, `tblBooking`, `tblBookedRoom`, `tblClient`;
- hotel roles such as Manager or Seller.

## Required workflow before coding

1. Inspect the current source tree first. Do not assume files are absent or packages are empty.
2. Read the real Module 1 context:
   - `.claude/context/project/module-1/03_data-model.md`;
   - `.claude/context/project/module-1/04_ui-and-design.md`;
   - `.claude/context/project/module-1/07_coding-plan-context.md`.
3. Read the teacher coding pattern only if needed:
   - `.claude/context/teacher-reference/06_coding-java-swing-dao.md`.
4. Verify current LaTeX design if accuracy matters:
   - `chapters/04_design_phase.tex`;
   - `chapters/05_testing_phase.tex` for test data.
5. Implement only the requested code. Do not add unrelated modules.
6. Prefer the teacher's simple Java Swing / DAO style over modern frameworks unless the user asks otherwise.
7. After coding, run available compile/tests if the environment supports it. If not, report the limitation clearly.

## Target architecture

Use the teacher's package layering adapted to Module 1:

```text
model
  User
  Restaurant
  Employee
  Shift
  RegistrationShift

dao
  DAO
  UserDAO
  EmployeeDAO
  ShiftDAO
  RegistrationShiftDAO

view.user
  LoginFrm
  HomeFrm

view.registration
  SearchEmployeeFrm
  RegisterShiftFrm

test.unit
  UserDAOTest
  EmployeeDAOTest
  ShiftDAOTest
  RegistrationShiftDAOTest
```

If the existing codebase already uses a different source root or package layout, preserve the existing layout unless the user explicitly asks to reorganize.

## Model coding rules

Entity classes should be simple JavaBeans:

- `package model;`;
- private attributes;
- default constructor;
- getters and setters;
- no SQL or Swing code;
- object relationships represented by object fields where needed.

Use current Module 1 fields:

- `User`: `id`, `username`, `password`, `role`, `description`;
- `Restaurant`: `id`, `restaurantName`, `address`, `description`;
- `Employee`: `id`, `code`, `phoneNumber`, `fullName`, `email`, `dob`, `contractDate`;
- `Shift`: `id`, `date`, `shiftNumber`, `description`, `startDate`, `endDate`;
- `RegistrationShift`: `id`, `registeredTime`, `status`, `description`, `employee`, `shift`, `user`.

## DAO coding rules

DAO classes should follow the teacher pattern:

- `DAO` owns a shared database connection;
- DAO subclasses extend `DAO` and call `super()`;
- use `PreparedStatement` for all dynamic SQL;
- pack `ResultSet` rows into model objects;
- return `ArrayList<T>` for search/list methods;
- return `boolean` for operations that the UI needs to treat as success/failure;
- use transactions for multi-row save operations.

Required Module 1 DAO methods:

```java
public boolean checkLogin(User user)
public ArrayList<Employee> searchEmployee(String key)
public ArrayList<Shift> getShiftNextWeek()
public ArrayList<RegistrationShift> getRegistrationByEmployee(Employee employee)
public boolean saveRegistration(ArrayList<RegistrationShift> list)
```

Use design table names and columns:

- `tblUser(ID, username, password, role, description)`;
- `tblRestaurant(ID, restaurantName, address, description)`;
- `tblEmployee(ID, code, fullName, phoneNumber, email, dob, contractDate, restaurantID)`;
- `tblShift(ID, date, shiftNumber, description, startDate, endDate)`;
- `tblRegistrationShift(ID, registeredTime, status, description, employeeID, shiftID, userID)`.

## Swing coding rules

Swing forms should follow the teacher's boundary class style:

- extend `JFrame`;
- implement `ActionListener`;
- define private UI components as attributes;
- store hidden context objects such as `User` and selected `Employee`;
- build UI in the constructor;
- register listeners in the constructor;
- dispatch events in `actionPerformed`;
- open the next form with required objects and dispose the current form when navigation succeeds.

Flow to implement:

```text
LoginFrm -> HomeFrm -> SearchEmployeeFrm -> RegisterShiftFrm -> HomeFrm
```

Important constructor behavior:

- `SearchEmployeeFrm(User user)` receives the logged-in user.
- `RegisterShiftFrm(Employee employee, User user)` receives selected employee and logged-in user.
- `RegisterShiftFrm` constructor calls `ShiftDAO.getShiftNextWeek()` during initialization.
- `RegisterShiftFrm` may call `RegistrationShiftDAO.getRegistrationByEmployee(employee)` to show existing registrations.

## JUnit testing rules

JUnit tests should follow the teacher's `test.unit` style:

- instantiate DAO classes directly;
- use `Assert.assertNotNull`, `Assert.assertEquals`, `Assert.assertTrue`, and `Assert.assertFalse`;
- for write tests, use transaction rollback to avoid permanently changing the test database;
- test standard and exception cases from the testing phase.

Recommended tests:

- valid/invalid login;
- employee search with matching and no-result keywords;
- next-week shift retrieval;
- standard registration save with inserted rows verified and rolled back.

## Quality bar

When coding for this project:

- keep implementation traceable to the design sequence diagram;
- keep names consistent with the report;
- avoid speculative abstractions;
- do not modernize into MVC frameworks, ORMs, or dependency injection unless requested;
- do not add comments except when a constraint is non-obvious;
- do not mix teacher hotel code with project code;
- keep the code simple enough for an Intro to Software Engineering course submission.
