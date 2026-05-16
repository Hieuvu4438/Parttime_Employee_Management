# Claude Guidance for Parttime Employee Management

## Project source of truth

The real project is **Parttime Employee Management**. Current report content lives in `main.tex` and `chapters/*.tex`.

When writing or modifying report content, first read the relevant existing LaTeX chapters and preserve the project's actual actors, entities, interfaces, tables, scenarios, and terminology.

## Teacher reference boundary

The folder `.claude/context/teacher-reference/` contains teacher-provided sample materials for an unrelated **hotel management system**.

Treat those files as **reference methodology only**:

- use them to understand the expected phase structure;
- use them to mirror academic report style and level of detail;
- use them to infer what kind of tables, sections, and explanations are expected.

Never treat teacher-reference files as facts about this project.

Do not copy hotel-specific content into the report, including:

- actors: Manager, Seller, Receptionist, Administrator, Client, unless independently present in current project chapters;
- entities: Hotel, Room, Booking, BookedRoom, Bill, Service, UsedService;
- UI classes: SearchRoomFrm, EditRoomFrm, SearchFreeRoomFrm, SearchClientFrm, ConfirmFrm;
- database tables and test data from the hotel sample.

## Adaptation workflow

For phase-writing tasks:

1. Read the user's requested target chapter or section.
2. Read `.claude/context/project/module-1/` for real Module 1 context.
3. Read current project chapters to verify the latest project content.
4. Read only the relevant teacher-reference file for structure.
5. Convert the teacher's method to the Parttime Employee Management domain.
6. Keep changes surgical and avoid rewriting unrelated sections.

## Current module terminology

Based on the existing report, the selected module is the registration of next week's shifts for a part-time employee.

Known project concepts include:

- Actors/users: registration staff, part-time employee.
- View classes: LoginFrm, HomeFrm, SearchEmployeeFrm, RegisterShiftFrm.
- Entity classes: User, Restaurant, Employee, Shift, RegistrationShift.
- DAO classes: UserDAO, EmployeeDAO, ShiftDAO, RegistrationShiftDAO.
- Tables: tblUser, tblRestaurant, tblEmployee, tblShift, tblRegistrationShift.
- Core flow: login, choose Register for next week, search employee, select employee, choose shifts, save registration.

Before relying on any of these concepts, verify the current LaTeX chapters if accuracy matters for the task.
