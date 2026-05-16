# Module 1 UI and Design Context

## Phase naming distinction

The project uses different names between analysis and design phases.

Analysis phase tends to use `View` class names:

- LoginView;
- HomepageView;
- SearchEmployeeView;
- RegisterShiftView.

Design phase uses Swing-style `Frm` class names:

- LoginFrm;
- HomeFrm;
- SearchEmployeeFrm;
- RegisterShiftFrm.

Use the names that match the phase being written.

## View / boundary classes in design phase

### LoginFrm

Purpose:

- First interface of the application.
- Allows Registration Staff to enter authentication information.

Visible elements:

- `txtUserName: JTextField`;
- `txtPassword: JTextField`;
- `btnLogin: JButton`.

Hidden attributes:

- None.

Constructor:

```text
LoginFrm()
```

Main behavior:

- `actionPerformed(e: ActionEvent): void` receives UI events.
- `btnLoginClick(): void` handles login.
- Creates a User object from username and password.
- Calls `UserDAO.checkLogin(user: User): boolean`.
- If login succeeds, opens HomeFrm and passes the authenticated User object forward.

### HomeFrm

Purpose:

- Staff homepage after login.
- Provides access to Register for next week.

Visible elements:

- `btnRegister: JButton`.

Hidden attributes:

- `user: User`.

Constructor:

```text
HomeFrm(u: User)
```

Main behavior:

- `actionPerformed(e: ActionEvent): void` dispatches events.
- `btnRegisterClick(): void` opens SearchEmployeeFrm and passes the User object.

### SearchEmployeeFrm

Purpose:

- Search for the part-time employee requesting shift registration.

Visible elements:

- `txtName: JTextField`;
- `btnSearch: JButton`;
- `tblResult: JTable`.

Hidden attributes:

- `user: User`.

Constructor:

```text
SearchEmployeeFrm(u: User)
```

Main behavior:

- `btnSearchClick(): void` gets keyword from `txtName`.
- Calls `EmployeeDAO.searchEmployee(key: String): ArrayList<Employee>`.
- Displays returned employees in `tblResult`.
- `btnResultClick(): void` handles selecting an employee row.
- Opens RegisterShiftFrm with selected Employee and hidden User.

### RegisterShiftFrm

Purpose:

- Main interface for selecting and saving next week's shifts for the chosen employee.

Visible elements:

- employee information display: employee code, name, and phone number;
- `tblShift: JTable` for 7-day x 2-shift checkbox table;
- `btnSave: JButton`.

Hidden attributes:

- `employee: Employee`;
- `user: User`.

Constructor:

```text
RegisterShiftFrm(e: Employee, u: User)
```

Main behavior:

- During initialization:
  - calls `ShiftDAO.getShiftNextWeek(): ArrayList<Shift>`;
  - calls `RegistrationShiftDAO.getRegistrationByEmployee(e: Employee): ArrayList<RegistrationShift>` if existing registrations need to be shown.
- `btnSaveClick(): void` collects selected shifts.
- Creates RegistrationShift objects for selected shifts.
- Calls `RegistrationShiftDAO.saveRegistration(list: ArrayList<RegistrationShift>): boolean`.
- Displays success or validation/error message.

## DAO / control classes in design phase

### DAO

Common base class for DAO classes.

Attribute:

- `con: Connection`.

Constructor:

- `DAO()` connects to the database.

### UserDAO

Purpose:

- Database operations related to User.

Method:

```text
checkLogin(User u): boolean
```

Input:

- User object containing username and password.

Output:

- boolean indicating login success.

Behavior:

- Checks credentials against database.
- Can populate additional User fields if a matching account is found.

### EmployeeDAO

Purpose:

- Database operations related to Employee.

Method:

```text
searchEmployee(String key): ArrayList<Employee>
```

Input:

- employee name keyword.

Output:

- list of Employee objects whose fullName contains the keyword.

### ShiftDAO

Purpose:

- Database operations related to Shift.

Method:

```text
getShiftNextWeek(): ArrayList<Shift>
```

Input:

- none; next week is calculated internally.

Output:

- list of next-week Shift objects.

### RegistrationShiftDAO

Purpose:

- Database operations related to RegistrationShift.

Methods:

```text
saveRegistration(ArrayList<RegistrationShift> list): boolean
getRegistrationByEmployee(Employee e): ArrayList<RegistrationShift>
```

`saveRegistration`:

- Saves all selected shift registrations in one operation.
- Returns boolean success/failure.

`getRegistrationByEmployee`:

- Retrieves existing registration records of an employee for next week.
- Used to pre-populate or avoid duplicate registration choices.

## Design entity classes

### User

Attributes:

- `ID: int`;
- `username: String`;
- `password: String`;
- `role: String`;
- `description: String`.

### Restaurant

Attributes:

- `ID: int`;
- `restaurantName: String`;
- `address: String`;
- `description: String`;
- `employees: Employee[]`.

### Employee

Attributes:

- `ID: int`;
- `code: String`;
- `phoneNumber: String`;
- `fullName: String`;
- `email: String`;
- `dob: Date`;
- `contractDate: Date`.

### Shift

Attributes:

- `ID: int`;
- `date: Date`;
- `shiftNumber: int`;
- `description: String`;
- `startDate: Date`;
- `endDate: Date`.

### RegistrationShift

Attributes:

- `ID: int`;
- `registeredTime: DateTime`;
- `status: String`;
- `description: String`;
- `employee: Employee`;
- `shift: Shift`;
- `user: User`.

## Navigation flow

Design navigation:

```text
LoginFrm -> HomeFrm -> SearchEmployeeFrm -> RegisterShiftFrm -> HomeFrm
```

Flow details:

1. LoginFrm opens HomeFrm after successful login.
2. HomeFrm opens SearchEmployeeFrm when staff chooses Register for next week.
3. SearchEmployeeFrm opens RegisterShiftFrm after staff selects the correct employee.
4. RegisterShiftFrm returns to HomeFrm after successful save.

## Scenario v.3 design sequence summary

1. Part-time employee requests next-week shift registration.
2. Registration Staff enters username and password on LoginFrm and clicks Login.
3. `LoginFrm.actionPerformed()` is called.
4. LoginFrm creates a User object.
5. LoginFrm calls `UserDAO.checkLogin(user)`.
6. UserDAO checks login and returns boolean.
7. LoginFrm opens `HomeFrm(u: User)`.
8. Staff clicks Register for next week.
9. HomeFrm opens `SearchEmployeeFrm(u: User)`.
10. Staff asks employee for name.
11. Employee provides name keyword.
12. Staff searches.
13. SearchEmployeeFrm calls `EmployeeDAO.searchEmployee(key)`.
14. EmployeeDAO returns Employee objects.
15. SearchEmployeeFrm displays matched employees.
16. Staff selects correct employee.
17. SearchEmployeeFrm obtains next-week shifts and opens `RegisterShiftFrm(e: Employee, u: User)`.
18. RegisterShiftFrm displays employee information and 7-day x 2-shift table.
19. Staff asks employee for desired shifts.
20. Employee provides desired shifts.
21. Staff ticks checkboxes and clicks Save.
22. RegisterShiftFrm creates RegistrationShift objects.
23. RegisterShiftFrm calls `RegistrationShiftDAO.saveRegistration(list)`.
24. DAO saves data and returns boolean.
25. RegisterShiftFrm displays success message.
26. Staff informs employee that registration is successful.
27. RegisterShiftFrm opens HomeFrm again.

## Method naming across phases

Analysis phase method names:

- `checkLogin()` assigned to User;
- `searchEmployee()` assigned to Employee;
- `getUnregistrationShift()` assigned to Shift;
- `saveRegistration()` assigned to RegistrationShift.

Design phase DAO method names:

- `UserDAO.checkLogin(User u): boolean`;
- `EmployeeDAO.searchEmployee(String key): ArrayList<Employee>`;
- `ShiftDAO.getShiftNextWeek(): ArrayList<Shift>`;
- `RegistrationShiftDAO.saveRegistration(ArrayList<RegistrationShift> list): boolean`;
- `RegistrationShiftDAO.getRegistrationByEmployee(Employee e): ArrayList<RegistrationShift>`.

For future writing, prefer DAO methods in design/testing content and entity-assigned methods in analysis content.
