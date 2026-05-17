# Teacher Reference — Analysis Phase

> Source domain: hotel management system. This is an external example only.
>
> Use this file to understand expected analysis-phase deliverables: scenario, entity extraction, analysis class diagram, and analysis sequence diagram.

## 1. Scenario Writing Pattern

A scenario is written as a concrete interaction flow with actors, UI screens, input values, result tables, and final system response.

### Standard scenario — edit room sample

1. A manager starts the application to edit room 305.
2. The login interface appears with username input, password input, and login button.
3. The manager enters username `man01`, password `******`, and clicks login.
4. The manager main UI appears with Manage hotel, Manage room, and View report.
5. The manager chooses Manage room.
6. The room management UI appears with Add, Edit, and Delete room.
7. The manager chooses Edit room.
8. The search room UI appears with keyword input and search button.
9. The manager enters keyword `305` and searches.
10. Matching rooms are listed.
11. The manager clicks room 305.
12. The edit room UI appears with id as read-only and editable fields for name, price, type, and description.
13. The manager modifies price to 800 and clicks save.
14. A success alert appears and the system returns to the main manager UI.

Exceptional scenarios:

- Login credentials are incorrect.
- No room is found.

### Standard scenario — book room sample

1. A seller chooses booking for a client on the phone.
2. The search available room UI appears with check-in date, check-out date, and search button.
3. The seller asks the client for desired dates.
4. The client wants 30/04/2020 to 01/05/2020.
5. The seller enters dates and searches.
6. Available rooms are listed.
7. The seller informs the client and asks the client to choose.
8. The client chooses a sea-view room.
9. The seller selects room 305.
10. The client information UI appears.
11. The seller asks for client information.
12. The client provides name, address, id card number, phone number, and email.
13. The seller searches by name.
14. Matching clients are listed.
15. The seller selects the correct client.
16. The confirmation UI appears with selected room, client, and booking dates.
17. The seller confirms with the client.
18. The client agrees.
19. The seller confirms.
20. A booking success alert appears and the system returns to seller home.
21. The seller informs the client.

Exceptional scenarios:

- No available room is found.
- No matching client is found or the client is not yet in the system.

### Standard scenario — view report sample

1. A manager selects View report after login.
2. The report configuration UI appears.
3. The manager selects object `room` and criterion `by revenue`.
4. The report UI appears with start date, end date, and view button.
5. The manager enters 01/05/2020 to 30/05/2020 and views the report.
6. Room revenue results are listed.
7. The manager clicks room 305 to see detail.
8. A detail table lists clients who stayed in that room during the period.
9. The manager clicks back.
10. The system returns to manager UI.

## 2. Entity Class Extraction Method

### Noun extracting approach

1. Describe the system functions in a short, complete paragraph.
2. Extract all nouns that appear in the paragraph. Count each noun only once.
3. Classify each noun:
   - entity class candidate;
   - attribute of an entity class;
   - rejected noun because it is too general, too abstract, or out of scope.
4. Consider quantitative relationships among entity classes:
   - 1-1: keep or regroup into one class;
   - 1-n: keep;
   - n-n: split into at least two 1-n relationships by adding an intermediate class.
5. Determine object relationships among classes: generalization, aggregation, composition, association, dependency.

### Sample extracted classes

From the hotel sample, initial classes include:

- Room;
- Hotel;
- Client;
- User;
- Bill;
- Service;
- Booking;
- BookedRoom;
- UsedService;
- RoomStat;
- HotelStat;
- ClientStat;
- ServiceStat;
- IncomeStat.

### Sample relationship reasoning

- A Hotel has many Rooms; a Room belongs to one Hotel: 1-n.
- A Client can book many Rooms and a Room can be booked by many Clients: n-n, so add Booking.
- Because one Booking can include many Rooms, add BookedRoom between Booking and Room.
- A Booking can be paid several times; each payment creates a Bill: Booking to Bill is 1-n.
- A User can create many Bills: User to Bill is 1-n.
- BookedRoom and Service is n-n, so add UsedService.
- Statistic classes may inherit from corresponding entity classes when they reuse attributes.

## 3. Analysis Class Diagram Method

### Step 1: Create view classes from interfaces

Each interface except alerts, notifications, warnings, and confirmation dialogs can become a view class.

### Step 2: Identify interface elements

Use prefixes:

- `in`: input elements such as text fields or date inputs;
- `out`: output elements such as tables or content areas;
- `sub`: submit elements such as buttons or links;
- combinations such as `outsub` for clickable output tables.

### Step 3: Identify methods and assign classes

For each function, answer:

1. What is the method name?
2. What are the input parameters?
3. What is the output parameter?
4. Which class should own the method?

Assignment principle:

- If the output parameter is an entity class type, assign the method to that entity class.
- If not, consider input parameters.
- If input includes one entity class, assign the method to that entity class.
- If input includes multiple entity classes, choose the entity class that can contain all input parameters according to the entity class diagram.

## 4. Analysis Class Diagram Examples

### Edit room sample

View classes:

- LoginView: `inUsername`, `inPassword`, `subLogin`.
- ManagerHomeView: `subRoomManage`.
- RoomManageView: `subEditRoom`.
- SearchRoomView: `inKey`, `subSearch`, `outsubListRoom`.
- EditRoomView: `outId`, `inoutName`, `inoutType`, `inoutPrice`, `inoutDes`, `subSave`.

Methods:

- `checkLogin(username, password): boolean`, assigned to User.
- `searchRoom(key): list of Room`, assigned to Room.
- `updateRoom(room): boolean`, assigned to Room.

### Book room sample

View classes:

- SellerHomeView: `subBooking`.
- SearchFreeRoomView: `inCheckin`, `inCheckout`, `subSearch`, `outsubListRoom`.
- SearchClientView: `inKey`, `subSearch`, `outsubListClient`, `subAddClient`.
- ConfirmView: `outBooking`, `subConfirm`.

Methods:

- `searchFreeRoom(checkin, checkout): list of Room`, assigned to Room.
- `searchClient(key): list of Client`, assigned to Client.
- `addBooking(booking): boolean`, assigned to Booking.

### View room statistic sample

View classes:

- ManagerHomeView: `subViewStat`.
- SelectStatView: `inStatObject`, `inStatType`.
- RoomStatView: `inStartDate`, `inEndDate`, `subView`, `outsubListRoomStat`.
- RoomDetailView: `outListBill`, `subBack`.

Methods:

- `getRoomStat(startDate, endDate): list of RoomStat`, assigned to RoomStat.
- `getBillByRoom(startDate, endDate, roomId): list of Bill`, assigned to Bill.

## 5. Analysis Sequence Diagram Pattern

Scenario version 2 describes interactions among actor, view classes, and entity classes.

### Edit room sequence pattern

1. Actor interacts with LoginView.
2. LoginView calls User to process login.
3. User calls `checkLogin()`.
4. LoginView opens ManagerHomeView.
5. ManagerHomeView opens ManageRoomView.
6. ManageRoomView opens SearchRoomView.
7. SearchRoomView calls Room to search.
8. Room calls `searchRoom()` and returns results.
9. SearchRoomView displays results.
10. SearchRoomView opens EditRoomView.
11. EditRoomView calls Room to update.
12. Room calls `updateRoom()`.
13. EditRoomView displays success and returns to ManagerHomeView.

### Book room sequence pattern

1. Seller opens booking from SellerHomeView.
2. SellerHomeView opens SearchFreeRoomView.
3. SearchFreeRoomView calls Room and `searchFreeRoom()`.
4. SearchFreeRoomView displays available rooms.
5. Seller selects room.
6. SearchFreeRoomView opens SearchClientView.
7. SearchClientView calls Client and `searchClient()`.
8. SearchClientView displays matching clients.
9. Seller selects client.
10. SearchClientView opens ConfirmView.
11. ConfirmView calls Booking and `addBooking()`.
12. ConfirmView displays success and returns to SellerHomeView.

### View room statistic sequence pattern

1. ManagerHomeView opens SelectStatView.
2. SelectStatView opens RoomStatView.
3. RoomStatView calls RoomStat and `getRoomStat()`.
4. RoomStatView displays statistics.
5. RoomStatView opens RoomDetailView.
6. RoomDetailView calls Bill and `getBillOfRoom()`.
7. RoomDetailView displays details and returns to SelectStatView.

## How to Adapt This to Parttime Employee Management

1. Use the same analysis methods, but read real project scenarios from `chapters/`.
2. Create view classes from real module interfaces such as login, home, search employee, and register shift.
3. Assign methods based on real project entity outputs and inputs.
4. Do not reuse hotel entities, method names, or sample data.
