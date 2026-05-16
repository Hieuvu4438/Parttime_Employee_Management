# Module 1 Overview — Register for Next Week

## Module identity

- Project: Parttime Employee Management.
- Module number: Module 1.
- Module name: Register for next week.
- Main purpose: allow registration staff to record a part-time employee's available shifts for the next week.

## Scope

This module covers the registration step before official scheduling.

The module starts when a part-time employee requests next week's shift registration and ends when the selected shifts are validated and saved into the database.

The module does **not** cover:

- generating the official next-week schedule;
- assigning employees to shifts;
- check-in/check-out tracking;
- wage calculation;
- employee performance statistics.

Those are separate modules in the larger system.

## Real project users and actors

### Registration Staff

The main system user of Module 1.

Responsibilities:

- log in to the system;
- open the Register for next week function;
- search for the requesting part-time employee by name;
- select the correct employee from search results;
- ask the employee which shifts they want to register;
- select checkboxes in the registration table;
- save the registration if validation passes.

### Parttime Employee

The external/requesting actor for Module 1.

Responsibilities:

- request next week's shift registration;
- provide name or identifying information to the Registration Staff;
- confirm the correct employee row in the search result list;
- provide desired shifts for next week;
- optionally supplement shifts if the selected number is below the minimum threshold.

## Related roles outside Module 1

These roles belong to the larger Parttime Employee Management system, but are not the main users of Module 1:

- Schedule Manager — schedules next week based on registrations.
- Financial Manager — calculates weekly wages.
- Executive Manager — views statistics of best employees.

Do not make these roles active participants in Module 1 unless explicitly discussing the whole system.

## Core business rule

Minimum Shift Threshold:

- Current scenario value: 3 shifts/week.
- The registration is valid only if the total number of selected checkboxes is greater than or equal to this threshold.
- If selected shifts are below the threshold, the system warns the staff and asks for additional shifts.
- If the employee refuses to add shifts, the registration is not saved.

## Shift structure

Next week is represented as a 7-day table.

Each day has exactly two shifts:

- Shift 1: 8:00 AM to 4:00 PM.
- Shift 2: 4:00 PM to 12:00 AM.

The registration UI therefore contains 14 possible checkbox cells:

```text
7 days x 2 shifts = 14 checkboxes
```

## Standard success criteria

A standard successful registration satisfies all of the following:

1. Registration Staff logs in successfully.
2. Staff opens Register for next week.
3. Staff searches for the employee by non-empty name keyword.
4. Search result contains the correct employee.
5. Staff selects exactly the correct employee.
6. Staff selects desired shifts in the 7-day x 2-shift table.
7. Total selected shifts is at least the Minimum Shift Threshold.
8. System saves registration records into the database.
9. System displays a success notification.
10. Staff informs the employee that registration is complete.

## Standard scenario data currently used in report

- Registration Staff: Staff A.
- Parttime Employee: Employee B.
- Search keyword: `B`.
- Search results:
  - B23DCCE060 — B — 0912345678.
  - B23DCCE088 — Binh — 0987654321.
- Correct employee: row 1, B.
- Minimum Shift Threshold: 3 shifts/week.
- Standard selected shifts: 4 shifts:
  - Monday Shift 1;
  - Wednesday Shift 2;
  - Friday Shift 1;
  - Saturday Shift 2.
- Expected result: registration successfully saved.

## Do not confuse with teacher sample

The hotel-management teacher sample uses booking rooms, clients, sellers, receptionists, bills, and room statistics. Those are not Module 1 concepts.

For Module 1, use registration staff, part-time employee, employee search, shift registration, and registration records.
