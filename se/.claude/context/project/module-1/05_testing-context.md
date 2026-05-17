# Module 1 Testing Context — Black-Box Testing

## Testing phase target

The current testing chapter has two intended sections:

1. Black-Box Test Plan.
2. Standard Black-Box Test Case with Full Test Data.

The user requested only the standard test case in detail, but the test plan should still list standard and exception cases.

## Module under test

Module:

```text
Register for next week
```

Purpose:

- Registration Staff registers next week's available shifts for a selected Parttime Employee.

## Test plan candidate cases

Use these as project-specific black-box test plan candidates.

| No. | Module | Test case |
| --- | --- | --- |
| 1 | Register for next week | register shifts successfully when employee exists and selected shifts meet the minimum threshold |
| 2 | Register for next week | login with incorrect username or password |
| 3 | Register for next week | search employee with empty keyword |
| 4 | Register for next week | search employee but no employee is found |
| 5 | Register for next week | search results appear but the target employee is not in the list |
| 6 | Register for next week | select fewer shifts than the minimum threshold and employee supplements shifts |
| 7 | Register for next week | select fewer shifts than the minimum threshold and employee refuses to supplement shifts |
| 8 | Register for next week | register two consecutive times for the same employee and same next-week period, if overwrite/update behavior is required |

Only test case No. 1 is the standard successful test case.

## Standard test case goal

Verify that Registration Staff can successfully register next week's shifts for an existing Parttime Employee when:

- login information is valid;
- employee search keyword is non-empty;
- search results contain the target employee;
- the correct employee is selected;
- selected shifts are at least the Minimum Shift Threshold;
- registration data is saved successfully.

## Standard test case scenario data

Actor data:

- Registration Staff: Staff A.
- Staff account role: Registration Staff.
- Parttime Employee: B.

Search data:

- Search keyword: `B`.
- Correct employee code: `B23DCCE060`.
- Correct employee name: `B`.
- Correct employee phone: `0912345678`.
- Additional search result: `B23DCCE088`, `Binh`, `0987654321`.

Validation data:

- Minimum Shift Threshold: 3 shifts/week.
- Selected shifts: 4 shifts.
- Validation result: valid.

Selected shifts:

| Day | Date | Shift |
| --- | --- | --- |
| Monday | 24/03/2026 | Shift 1 |
| Wednesday | 26/03/2026 | Shift 2 |
| Friday | 28/03/2026 | Shift 1 |
| Saturday | 29/03/2026 | Shift 2 |

Expected success message:

```text
Registration successfully saved!
```

## Suggested database before testing

Use the project's design tables.

### tblUser

Should contain at least one Registration Staff account.

Suggested fields:

- `ID`;
- `username`;
- `password`;
- `role`;
- `description`.

Suggested row:

| ID | username | password | role | description |
| --- | --- | --- | --- | --- |
| 1 | staffA | 123456 | Registration Staff | Staff account for registering next-week shifts |

### tblRestaurant

Should contain the restaurant where employees work.

Suggested fields:

- `ID`;
- `restaurantName`;
- `address`;
- `description`.

Suggested row:

| ID | restaurantName | address | description |
| --- | --- | --- | --- |
| 1 | Restaurant 1 | Ha Noi | Main restaurant branch |

### tblEmployee

Should contain target employee and at least one similar search result.

Suggested fields:

- `ID`;
- `restaurantID`;
- `code`;
- `fullName`;
- `phoneNumber`;
- `email`;
- `dob`;
- `contractDate`.

Suggested rows:

| ID | restaurantID | code | fullName | phoneNumber | email | dob | contractDate |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 1 | B23DCCE060 | B | 0912345678 | b@example.com | 01/01/2005 | 01/03/2026 |
| 2 | 1 | B23DCCE088 | Binh | 0987654321 | binh@example.com | 02/02/2005 | 01/03/2026 |

### tblShift

Should contain all shifts for the next week, or at least the selected shifts.

For full test data, include all 14 shifts if space allows.

Suggested fields:

- `ID`;
- `date`;
- `shiftNumber`;
- `description`;
- `startDate`;
- `endDate`.

Suggested selected-shift rows:

| ID | date | shiftNumber | description | startDate | endDate |
| --- | --- | --- | --- | --- | --- |
| 1 | 24/03/2026 | 1 | Monday Shift 1 | 24/03/2026 08:00 | 24/03/2026 16:00 |
| 6 | 26/03/2026 | 2 | Wednesday Shift 2 | 26/03/2026 16:00 | 27/03/2026 00:00 |
| 9 | 28/03/2026 | 1 | Friday Shift 1 | 28/03/2026 08:00 | 28/03/2026 16:00 |
| 12 | 29/03/2026 | 2 | Saturday Shift 2 | 29/03/2026 16:00 | 30/03/2026 00:00 |

If using all 14 rows, preserve this ID convention:

- Monday Shift 1 = 1, Monday Shift 2 = 2;
- Tuesday Shift 1 = 3, Tuesday Shift 2 = 4;
- Wednesday Shift 1 = 5, Wednesday Shift 2 = 6;
- Thursday Shift 1 = 7, Thursday Shift 2 = 8;
- Friday Shift 1 = 9, Friday Shift 2 = 10;
- Saturday Shift 1 = 11, Saturday Shift 2 = 12;
- Sunday Shift 1 = 13, Sunday Shift 2 = 14.

### tblRegistrationShift

Before standard testing, there should be no rows for Employee B for the selected shifts.

Suggested fields:

- `ID`;
- `employeeID`;
- `shiftID`;
- `userID`;
- `registeredTime`;
- `status`;
- `description`.

Suggested before state:

```text
No registration rows for employeeID = 1 in the next-week selected shifts.
```

## Expected database after standard testing

`tblRegistrationShift` should contain one row for each selected shift.

Suggested rows:

| ID | employeeID | shiftID | userID | registeredTime | status | description |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | 1 | 1 | 1 | 16/05/2026 09:00 | Registered | Monday Shift 1 registration |
| 2 | 1 | 6 | 1 | 16/05/2026 09:00 | Registered | Wednesday Shift 2 registration |
| 3 | 1 | 9 | 1 | 16/05/2026 09:00 | Registered | Friday Shift 1 registration |
| 4 | 1 | 12 | 1 | 16/05/2026 09:00 | Registered | Saturday Shift 2 registration |

Other tables should not change in the standard successful registration case.

## Standard test scenario and expected results structure

Use a two-column table:

| Scenario | Expected result |
| --- | --- |
| 1. Start the application | Login interface appears with username field, password field, and Login button. |
| 2. Enter valid staff credentials and click Login | Home interface appears with Register for next week button. |
| 3. Click Register for next week | Employee Search Interface appears. |
| 4. Enter keyword `B` and click Search | Search result list appears with B and Binh. |
| 5. Click row for B | Register Shift Interface appears with selected employee information and 7 x 2 shift table. |
| 6. Select Monday Shift 1, Wednesday Shift 2, Friday Shift 1, Saturday Shift 2 and click Save | System validates 4 selected shifts against threshold 3 and displays success message. |
| 7. Click OK | System returns to HomeFrm or completes registration flow. |

## Testing writing rules

When writing the LaTeX testing chapter:

- Keep the module name consistent: `Register for next week`.
- Use `Registration Staff` and `Parttime Employee`, not hotel sample roles.
- Use project tables: `tblUser`, `tblRestaurant`, `tblEmployee`, `tblShift`, `tblRegistrationShift`.
- In the detailed standard test case, include full input data and exact expected UI results.
- Show database changes only in `tblRegistrationShift` unless the chosen scenario explicitly changes another table.
- Avoid testing implementation internals because this is black-box testing.
