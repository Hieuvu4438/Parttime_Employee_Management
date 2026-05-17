# Module 1 Business Flow — Register for Next Week

## Standard flow

1. A part-time employee requests the registration staff to register available shifts for the next week.
2. Registration Staff opens the login interface.
3. Registration Staff enters username and password and clicks `Login`.
4. The system checks login information.
5. If login is successful, the system displays the staff home page.
6. Registration Staff selects the `Register for next week` function.
7. The system displays the Employee Search Interface.
8. Registration Staff asks the Parttime Employee for their full name.
9. The employee provides a name keyword.
10. Registration Staff enters the keyword into the search field and clicks `Search`.
11. The system queries employees whose names contain the keyword.
12. The system displays the Employee Search Result List with employee code, name, and phone number.
13. Registration Staff reads or shows the list to the employee for confirmation.
14. The employee confirms the correct row.
15. Registration Staff selects the correct employee row.
16. The system displays the Register Shift Interface.
17. The Register Shift Interface shows:
    - selected employee information;
    - a 7-day x 2-shift registration table;
    - checkboxes for selectable shifts;
    - a Save button.
18. Registration Staff asks which shifts the employee wants to register.
19. The employee provides desired shifts.
20. Registration Staff ticks the corresponding checkboxes.
21. Registration Staff clicks `Save`.
22. The system counts checked checkboxes.
23. The system compares the count with the Minimum Shift Threshold.
24. If the selected shift count is valid, the system saves the registration into the database.
25. The system displays `Registration successfully saved!`.
26. Registration Staff informs the employee that registration has been completed successfully.

## Current standard scenario example

### Search example

Input keyword:

```text
B
```

Search result list:

| No. | Employee Code | Employee Name | Phone Number |
| --- | --- | --- | --- |
| 1 | B23DCCE060 | B | 0912345678 |
| 2 | B23DCCE088 | Binh | 0987654321 |

Correct selected employee:

```text
B23DCCE060 — B — 0912345678
```

### Registration table example

Minimum Shift Threshold:

```text
3 shifts/week
```

Selected shifts in the standard successful scenario:

| Day | Date | Shift 1 | Shift 2 |
| --- | --- | --- | --- |
| Monday | 24/03/2026 | selected | not selected |
| Tuesday | 25/03/2026 | not selected | not selected |
| Wednesday | 26/03/2026 | not selected | selected |
| Thursday | 27/03/2026 | not selected | not selected |
| Friday | 28/03/2026 | selected | not selected |
| Saturday | 29/03/2026 | not selected | selected |
| Sunday | 30/03/2026 | not selected | not selected |

Total selected shifts:

```text
4 shifts
```

Validation result:

```text
4 >= 3, so registration is valid.
```

## Exception flows

### Login information is incorrect

1. System displays `Invalid username or password.`
2. Staff re-enters username and password.
3. If valid, system displays the staff home page.

### Search field is empty and employee provides again

1. Staff clicks Search without entering a keyword.
2. System displays `Please enter the search keyword.`
3. Staff asks the employee to provide the name again.
4. Employee provides the name.
5. Staff searches again.
6. Search results appear.

### Search field is empty and employee refuses to provide again

1. Staff clicks Search without entering a keyword.
2. System displays `Please enter the search keyword.`
3. Staff asks the employee to provide the name again.
4. Employee refuses.
5. Staff closes the registration interface.
6. System returns to the home page.
7. Registration ends without saving.

### No employee found and employee provides again

1. System displays `No employee found.`
2. Staff asks whether the employee wants to try again.
3. Employee provides a different or more accurate name.
4. Staff searches again.
5. Search results appear.

### No employee found and employee refuses to provide again

1. System displays `No employee found.`
2. Staff asks whether the employee wants to try again.
3. Employee refuses.
4. Staff closes the registration interface.
5. System returns to the home page.
6. Registration ends without saving.

### Target employee not found in result list

1. Search results appear, but the correct employee is not in the list.
2. Staff asks the employee to confirm the name or provide a more accurate name.
3. Employee provides a corrected keyword.
4. Staff searches again.
5. Correct results appear.

### Insufficient minimum shifts and employee supplements

1. Staff selects fewer shifts than the Minimum Shift Threshold.
2. System displays a warning, for example:
   `The number of registered shifts (2) is below the minimum threshold (3). Please register at least 1 more shift.`
3. Staff informs the employee that more shifts are needed.
4. Employee agrees and chooses additional shift(s).
5. Staff selects additional checkbox(es).
6. Staff clicks Save again.
7. System validates successfully and saves registration.

### Insufficient minimum shifts and employee refuses to supplement

1. Staff selects fewer shifts than the Minimum Shift Threshold.
2. System displays the insufficient-shifts warning.
3. Staff informs the employee that additional shifts are needed.
4. Employee refuses to register more shifts.
5. Staff closes the registration interface.
6. System does not save the data and returns to the home page.

## Flow-writing style for report

When writing scenarios in LaTeX:

- use concrete actors such as Staff A and Parttime Employee B;
- include UI names and visible fields/buttons;
- include exact sample input values where possible;
- include expected result tables for search and shift registration;
- include system validation and database-save outcome;
- separate standard flow from exception flows.
