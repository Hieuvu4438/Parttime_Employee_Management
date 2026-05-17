# Module 1 Data Model Context

## Entity classes

The selected entity classes for Module 1 are:

- Restaurant;
- Employee;
- Shift;
- RegistrationShift;
- User.

These are the real project entities for the Register for next week module.

## Entity class purposes

### Restaurant

Represents a restaurant branch that manages part-time employees.

Known attributes from the report:

- `ID: int` in design phase;
- `restaurantName: String`;
- `address: String`;
- `description: String`;
- `employees: Employee[]` as an object attribute in design phase.

Purpose:

- Provides the organizational parent for employees.
- One Restaurant has many Employees.

### Employee

Represents the part-time employee whose shifts can be registered.

Known attributes from the report:

- `ID: int` in design phase;
- `code: String`;
- `phoneNumber: String`;
- `fullName: String`;
- `email: String`;
- `dob: Date`;
- `contractDate: Date`.

Some earlier analysis text also mentions `description`; verify current diagram before using it in final table.

Purpose:

- Search target in Module 1.
- Selected employee is passed to RegisterShiftFrm.
- One Employee may have many RegistrationShift records.

### Shift

Represents one working shift in the next week.

Known attributes from the report:

- `ID: int` in design phase;
- `date: Date`;
- `shiftNumber: int`;
- `description: String`;
- `startDate: Date`;
- `endDate: Date`.

Purpose:

- Provides selectable next-week shift slots.
- One Shift may have many RegistrationShift records.

Shift structure:

- Shift 1: 8:00 AM to 4:00 PM.
- Shift 2: 4:00 PM to 12:00 AM.

### RegistrationShift

Represents the registration record linking one Employee to one selected Shift and the User who created the registration.

Known attributes from the report:

- `ID: int` in design phase;
- `registeredTime: DateTime`;
- `status: String`;
- `description: String`;
- `employee: Employee` object attribute;
- `shift: Shift` object attribute;
- `user: User` object attribute.

Purpose:

- Resolves the many-to-many relationship between Employee and Shift.
- Stores each concrete selected shift registration.
- Records who created the registration and when.

### User

Represents a staff account that logs in and operates the module.

Known attributes from the report:

- `ID: int` in design phase;
- `username: String`;
- `password: String`;
- `role: String`;
- `description: String`.

Some later design summary mentions `fullName` and `position`; verify current diagram before using those names in final content.

Purpose:

- Supports login.
- Represents the Registration Staff account that creates RegistrationShift records.
- One User may create many RegistrationShift records.

## Noun extraction decisions

Accepted entity classes:

| Noun | Classification | Reason |
| --- | --- | --- |
| Restaurant | Entity class | Employees belong to a restaurant. |
| Employee | Entity class | Core searched and registered object. |
| Shift | Entity class | Concrete working slot selected by the employee. |
| Registration | Entity class as RegistrationShift | Registration record linking Employee and Shift. |
| User / Registration staff | Entity class via User | Staff account that logs in and performs registration. |

Rejected or non-entity concepts:

| Noun | Classification | Reason |
| --- | --- | --- |
| System | Rejected | Too abstract. |
| Restaurant chain | Rejected for Module 1 | Too broad for the selected module. |
| Working day | Rejected | Represented by `Shift.date`. |
| Shift 1 / Shift 2 | Attribute value | Values of `Shift.shiftNumber`. |
| Hourly rate | Rejected for Module 1 | System-wide configuration, not an entity here. |
| Contract | Attribute | Represented by `Employee.contractDate` in this module. |
| Available working time | Rejected | Represented by selected checkboxes and RegistrationShift records. |
| Week | Rejected | Time period derived from current date. |
| Minimum threshold | Rejected | Configuration/validation rule, not an entity. |
| Registration table | Rejected | UI element, not entity. |
| Login | Rejected | Action, not entity. |
| Username/password | Attributes | Attributes of User. |
| Employee code/phone/name | Attributes | Attributes of Employee. |

## Relationships

### Restaurant to Employee

Relationship:

```text
Restaurant 1 --- n Employee
```

Meaning:

- One Restaurant manages many Employees.
- Each Employee belongs to one Restaurant.

Design representation:

- Restaurant has `employees: Employee[]`.
- Database representation likely uses `tblEmployee.restaurantID` as a foreign key to `tblRestaurant.ID`.

### Employee to RegistrationShift

Relationship:

```text
Employee 1 --- n RegistrationShift
```

Meaning:

- One Employee can create many shift registration records.
- Each RegistrationShift belongs to one selected Employee.

Design representation:

- RegistrationShift has `employee: Employee`.
- Database representation uses `tblRegistrationShift.employeeID`.

### Shift to RegistrationShift

Relationship:

```text
Shift 1 --- n RegistrationShift
```

Meaning:

- One Shift can be registered by many Employees.
- Each RegistrationShift refers to one selected Shift.

Design representation:

- RegistrationShift has `shift: Shift`.
- Database representation uses `tblRegistrationShift.shiftID`.

### User to RegistrationShift

Relationship:

```text
User 1 --- n RegistrationShift
```

Meaning:

- One User / Registration Staff can create many registration records.
- Each RegistrationShift is created by one User.

Design representation:

- RegistrationShift has `user: User`.
- Database representation uses `tblRegistrationShift.userID`.

### Employee to Shift via RegistrationShift

Conceptual relationship:

```text
Employee n --- n Shift
```

This is resolved by RegistrationShift:

```text
Employee 1 --- n RegistrationShift n --- 1 Shift
```

## Database tables

The design phase maps entity classes to these tables:

- `tblUser`;
- `tblRestaurant`;
- `tblEmployee`;
- `tblShift`;
- `tblRegistrationShift`.

## Database columns from design phase

### tblUser

Columns:

- `ID` primary key;
- `username`;
- `password`;
- `role`;
- `description`.

### tblRestaurant

Columns:

- `ID` primary key;
- `restaurantName`;
- `address`;
- `description`.

### tblEmployee

Columns:

- `ID` primary key;
- `code`;
- `fullName`;
- `phoneNumber`;
- `email`;
- `dob`;
- `contractDate`;
- `restaurantID` foreign key to `tblRestaurant.ID`.

### tblShift

Columns:

- `ID` primary key;
- `date`;
- `shiftNumber`;
- `description`;
- `startDate`;
- `endDate`.

### tblRegistrationShift

Columns:

- `ID` primary key;
- `registeredTime`;
- `status`;
- `description`;
- `employeeID` foreign key to `tblEmployee.ID`;
- `shiftID` foreign key to `tblShift.ID`;
- `userID` foreign key to `tblUser.ID`.

## Persistence behavior in standard case

In the standard Register for next week case:

- `tblUser`, `tblRestaurant`, `tblEmployee`, and `tblShift` provide existing input data.
- `tblRegistrationShift` is the main table changed after saving.
- For each selected shift, one registration row should be inserted or saved.

If the report states that an employee has exactly one weekly registration schedule and updates overwrite old data, clarify whether the design stores each selected shift as separate RegistrationShift rows or treats the week as a registration set. Current design diagrams and DAO methods favor separate RegistrationShift records per selected shift.

## Verification notes

Some terminology differs between analysis and design chapters:

- Analysis uses `HomepageView`, design uses `HomeFrm`.
- Analysis mentions `getUnregistrationShift()`, design uses `getShiftNextWeek()` and `getRegistrationByEmployee()`.
- Analysis assigns methods directly to entity classes; design assigns database operations to DAO classes.

For final report writing, prefer the phase-appropriate terms:

- analysis phase: `View` classes and entity methods;
- design phase/testing phase: `Frm` classes and DAO methods.
