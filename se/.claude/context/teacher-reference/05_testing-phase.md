# Teacher Reference — Testing Phase

> Source domain: hotel management system. This is an external example only.
>
> Use this file to understand how the teacher structures black-box testing deliverables. Do not copy hotel test cases, hotel tables, or hotel data into Parttime Employee Management.

## 1. Black-Box Testing Deliverables

The sample testing phase contains two main deliverables:

1. Black-box test case list / test plan.
2. Detailed standard black-box test case with full test data.

The detailed test case includes:

- database before testing;
- testing scenario and expected results;
- database after testing.

## 2. Black-Box Test Case List Pattern

The sample lists test cases in a table with columns:

| No. | Module | Test case |
| --- | --- | --- |
| 1 | Edit room | edit an existed room |
| 2 | Edit room | edit a room which does not exist |
| 3 | Edit room | edit two consecutive times an existed room |
| 4 | Booking room | there is available room and client exists |
| 5 | Booking room | there is available room and client does not exist |
| 6 | Booking room | no available room |
| 7 | Booking room | book two consecutive times an existed room in the same checkin/checkout |
| 8 | View room statistic | there is booking room: start date < checkin < checkout < end time |
| 9 | View room statistic | there is booking room: checkin < start date < checkout |
| 10 | View room statistic | there is booking room: checkin < end date < checkout |
| 11 | View room statistic | no booking room inside the interval [start time, end time] |

## 3. Detailed Test Case Format

For the selected standard test case, write:

### Test case title

Example:

```text
Test case No. 1 — Edit an existing room
```

### Database before testing

List all related tables and full data rows needed for the test.

### Testing scenario and expected results

Use a two-column table:

| Scenario | Expected result |
| --- | --- |
| 1. Start the application | The login interface appears with username field, password field, and login button. |
| 2. Enter valid credentials and click login | The correct home interface appears. |

### Database after testing

List changed tables and rows. If no database changes occur, state that the database does not change.

## 4. Sample Detailed Test Case — Edit Existing Room

### Purpose

Verify that an existing room can be edited successfully.

### Database before testing

Sample tables:

- `tblUser`;
- `tblHotel`;
- `tblRoom`.

### Scenario and expected results pattern

1. Start application -> login interface appears.
2. Enter valid manager credentials -> manager home appears.
3. Click Manage room -> room management appears.
4. Click Edit room -> search room appears.
5. Enter keyword -> matching rooms appear.
6. Select one room -> edit room interface appears with current values.
7. Modify price and save -> success message appears.
8. Click OK -> return to manager home.

### Database after testing

Only the edited room row changes.

## 5. Sample Detailed Test Case — Booking Room Standard Case

### Purpose

Verify booking succeeds when an available room exists and the client already exists.

### Database before testing

Sample tables:

- `tblUser`;
- `tblHotel`;
- `tblRoom`;
- `tblClient`;
- `tblBooking`;
- `tblBookedRoom`.

### Scenario and expected results pattern

1. Seller clicks booking -> search available room appears.
2. Enter check-in and check-out dates -> available rooms appear.
3. Select a room -> search client interface appears.
4. Search by client name -> matching clients appear.
5. Select existing client -> confirmation interface appears.
6. Confirm -> booking success message appears.
7. Click OK -> return to seller home.

### Database after testing

Only booking-related tables change:

- a new row is added to `tblBooking`;
- a new row is added to `tblBookedRoom`.

## 6. Sample Detailed Test Case — View Room Statistic Standard Case

### Purpose

Verify room revenue statistic is displayed correctly when bookings exist fully inside the statistic interval.

### Database before testing

Sample tables:

- `tblUser`;
- `tblHotel`;
- `tblRoom`;
- `tblClient`;
- `tblBooking`;
- `tblBookedRoom`.

### Scenario and expected results pattern

1. Manager clicks View statistic -> statistic configuration appears.
2. Select object = room -> room statistic criteria appear.
3. Select type = revenue -> room statistic screen appears.
4. Enter start and end dates -> room statistic rows appear ordered by income.
5. Click a room -> detailed statistic appears.
6. Click Back -> return to manager home.

### Database after testing

No database change.

## 7. Rules for Writing Project Testing Phase

When adapting to Parttime Employee Management:

1. The black-box test plan should list the test cases for the real module, especially the standard case and exceptional cases.
2. The detailed test case requested by the user should be the **standard test case only** unless explicitly asked otherwise.
3. Include full project-specific database test data before testing.
4. Write step-by-step user actions and expected UI/system results.
5. Include database after testing:
   - if the function saves data, show changed tables and new/updated rows;
   - if the function only views data, state that no database changes occur.
6. Use real project tables such as `tblUser`, `tblEmployee`, `tblShift`, `tblRegistrationShift`, and related tables only if they exist in the current project design.
7. Never use hotel sample tables or rows in the final project report.
