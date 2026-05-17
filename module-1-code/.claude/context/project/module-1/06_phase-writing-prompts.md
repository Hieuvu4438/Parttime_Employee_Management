# Module 1 Phase-Writing Prompts and Guardrails

Use these prompts and guardrails when writing future report content for Module 1.

## General guardrail

Always separate:

- project facts from `.claude/context/project/module-1/` and current LaTeX chapters;
- methodology/style from `.claude/context/teacher-reference/`.

Never copy teacher hotel sample domain content into Module 1.

## Business model prompt

When asked to write or revise natural-language business model content:

1. Explain the system scope as a restaurant-chain part-time employee management system.
2. State that Module 1 handles next-week shift registration.
3. Identify Registration Staff and Parttime Employee as the main participants.
4. Describe the flow from login to search employee to shift selection to save.
5. Include Minimum Shift Threshold validation.
6. List needed information: employee profile, shift data, registration records, user account.
7. List relationships: Restaurant-Employee, Employee-RegistrationShift, Shift-RegistrationShift, User-RegistrationShift.

## Use case prompt

When asked to write use case content:

1. General system use cases include Register Next Week, Schedule Next Week, Calculate Weekly Wages, and Generate Employee Statistics.
2. For Module 1 detailed use case, include:
   - search employee;
   - select employee;
   - select shifts;
   - save registration.
3. Actors are Registration Staff and Parttime Employee.
4. Main scenario should include Staff A and Employee B.
5. Exceptions should include invalid login, empty search, no employee found, target not found, insufficient selected shifts.

## Analysis phase prompt

When asked to write analysis content:

1. Use `View` class names: LoginView, HomepageView, SearchEmployeeView, RegisterShiftView.
2. Use interface element prefixes:
   - `inUsername`, `inPassword`, `subLogin`;
   - `subSearchEmployee`;
   - `inKey`, `subSearch`, `outsubListEmployee`;
   - `outEmployeeInfo`, `inoutShiftTable`, `subSave`.
3. Use entity classes: User, Restaurant, Employee, Shift, RegistrationShift.
4. Analysis methods:
   - `checkLogin()`;
   - `searchEmployee()`;
   - `getUnregistrationShift()` if following the current analysis text;
   - `saveRegistration()`.
5. Sequence v.2 should use actor -> view -> entity interactions, not DAO details.

## Design phase prompt

When asked to write design content:

1. Use `Frm` names: LoginFrm, HomeFrm, SearchEmployeeFrm, RegisterShiftFrm.
2. Use DAO classes: DAO, UserDAO, EmployeeDAO, ShiftDAO, RegistrationShiftDAO.
3. Use methods:
   - `UserDAO.checkLogin(User u): boolean`;
   - `EmployeeDAO.searchEmployee(String key): ArrayList<Employee>`;
   - `ShiftDAO.getShiftNextWeek(): ArrayList<Shift>`;
   - `RegistrationShiftDAO.getRegistrationByEmployee(Employee e): ArrayList<RegistrationShift>`;
   - `RegistrationShiftDAO.saveRegistration(ArrayList<RegistrationShift> list): boolean`.
4. Database tables:
   - `tblUser`;
   - `tblRestaurant`;
   - `tblEmployee`;
   - `tblShift`;
   - `tblRegistrationShift`.
5. Sequence v.3 should include `actionPerformed()`, constructors, entity packing, DAO calls, result returns, and UI display.

## Testing phase prompt

When asked to write testing content:

1. Create a black-box test plan table with No., Module, Test case.
2. Standard test case is: existing employee, valid login, valid selected shifts, save succeeds.
3. Detailed standard test case must include:
   - database before testing;
   - scenario and expected results;
   - database after testing.
4. Use full test data for project tables.
5. Expected database change: rows inserted into `tblRegistrationShift` for selected shifts.
6. Do not include hotel sample data.

## Recommended wording style

Use clear academic English consistent with existing chapters:

- `This module supports...`
- `The Registration Staff...`
- `The system displays...`
- `The selected shifts are valid because...`
- `As a result...`

Keep explanations explicit and step-based because the course report expects traceability from business process to diagrams and tests.
