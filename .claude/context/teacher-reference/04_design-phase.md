# Teacher Reference — Design Phase

> Source domain: hotel management system. This is an external example only.
>
> Use this file to understand the transformation from analysis artifacts into design artifacts. Do not copy hotel-specific classes, tables, or test data into Parttime Employee Management.

## 1. Design of Entity Classes

### Input

The input is the analysis entity class diagram.

### Process

#### Step 1: Add ID attributes

Add `id` for classes that do not inherit from another class.

Hotel sample classes with IDs:

- Hotel;
- Room;
- Client;
- Bill;
- Booking;
- BookedRoom;
- User;
- Service;
- UsedService.

#### Step 2: Add attribute types

Add concrete types for all attributes in all classes.

Examples:

- `name: String`;
- `price: Float` or `int` depending on design;
- `checkin: Date`;
- `checkout: Date`.

#### Step 3: Convert associations to aggregation/composition

Association classes from analysis are converted into clearer object relationships.

Hotel sample:

- `Room + Booking -> BookedRoom` becomes:
  - Room is a component of BookedRoom;
  - BookedRoom is a component of Booking.
- `BookedRoom + Service -> UsedService` becomes:
  - Service is a component of UsedService;
  - UsedService is a component of BookedRoom.

#### Step 4: Add object attributes

Add object attributes that correspond to aggregation/composition relationships.

Hotel sample:

- Room is a component of Hotel with n-1 quantity, so Hotel has `listRoom: Room[]`.
- Room is a component of BookedRoom, so BookedRoom has `room: Room`.
- BookedRoom is a component of Booking, so Booking has `bookedRooms: BookedRoom[]`.
- Client is a component of Booking, so Booking has `client: Client`.
- User is a component of Booking, so Booking has `user: User`.
- User is a component of Bill, so Bill has `user: User`.
- Service is a component of UsedService, so UsedService has `service: Service`.
- UsedService is a component of BookedRoom, so BookedRoom has `usedServices: UsedService[]`.

## 2. Database Design

### Input

The input is the entity class diagram from the design phase.

### Process

#### Step 1: Create tables from entity classes

For each entity class, create a corresponding table.

Naming pattern:

```text
tbl + EntityClassName
```

Example:

- Room -> `tblRoom`.

#### Step 2: Transfer non-object attributes to table columns

Only non-object attributes become table columns.

Object attributes such as `listRoom: Room[]` are not direct columns.

#### Step 3: Convert quantity relationships into table relationships

- 1-1: regroup into one table or keep separated if there is a specific reason.
- 1-n: keep two separated tables.
- n-n: create intermediate tables to split into 1-n relationships. If n-n still appears, return to analysis and correct the entity model.

#### Step 4: Configure key columns

Primary keys:

- If a table has `id`, configure it as the primary key.

Foreign keys:

- If `tblA` to `tblB` is 1-n, put the foreign key in `tblB`.
- Name can be `aId`, `idA`, or a project-consistent equivalent.

Example:

- 1 Hotel has many Rooms -> `tblRoom` has `hotelId` or `idHotel`.

#### Step 5: Remove redundant attributes

Remove two types of redundant data:

- duplicated attributes: non-key attributes of the same object appearing in multiple tables;
- derived attributes: values mechanically calculated from other database values, such as totals in statistic classes.

If a table has no useful attributes left except one foreign key, remove that table if appropriate.

## 3. Static Design Class Diagram

The sample applies MVC-oriented design:

- View classes become form classes.
- Control classes become DAO classes.
- Entity classes stay as model/domain classes.

### Edit room sample

View classes:

- LoginFrm;
- ManagerHomeFrm;
- ManageRoomFrm;
- SearchRoomFrm;
- EditRoomFrm.

Control/DAO classes:

- DAO: common database connection.
- UserDAO: `checkLogin()`.
- RoomDAO:
  - `searchRoom()`;
  - `updateRoom()`.

Entity classes:

- User;
- Room.

### Booking room sample

View classes:

- SellerHomeFrm;
- SearchFreeRoomFrm;
- SearchClientFrm;
- AddClientFrm;
- ConfirmFrm.

Control/DAO classes:

- RoomDAO: `searchFreeRoom()`.
- ClientDAO:
  - `searchClient()`;
  - `addClient()`.
- BookingDAO: `addBooking()`.

Entity classes:

- Room;
- Client;
- Booking;
- BookedRoom;
- User.

### View room statistic sample

View classes:

- ManagerHomeFrm;
- SelectStatFrm;
- RoomStatFrm;
- RoomDetailFrm.

Control/DAO classes:

- RoomStatDAO: `getRoomStat()`.
- BillDAO: `getBillByRoom()`.

Entity classes:

- RoomStat;
- Bill;
- Room;
- Booking;
- BookedRoom;
- Client;
- UsedService;
- Service;
- User.

## 4. Design Sequence Diagram

Scenario version 3 is more detailed than analysis scenario version 2. It includes:

- form event handling such as `actionPerformed()`;
- constructors of form classes;
- entity object packing via constructors or setters;
- DAO method calls;
- DAO result packing into entity objects;
- UI display and navigation.

### Edit room sequence pattern

1. Actor enters login data and clicks login on LoginFrm.
2. `LoginFrm.actionPerformed()` is called.
3. LoginFrm creates a User object.
4. LoginFrm calls `UserDAO.checkLogin()`.
5. UserDAO checks login and sets additional User attributes.
6. LoginFrm opens ManagerHomeFrm.
7. Manager clicks Manage room.
8. ManagerHomeFrm opens RoomManageFrm.
9. Manager clicks Edit room.
10. RoomManageFrm opens SearchRoomFrm.
11. Manager enters room keyword and searches.
12. SearchRoomFrm calls `RoomDAO.searchRoom()`.
13. RoomDAO packs results into Room objects.
14. SearchRoomFrm displays results.
15. Manager selects a room.
16. SearchRoomFrm opens EditRoomFrm.
17. Manager modifies attributes and saves.
18. EditRoomFrm creates or updates a Room object.
19. EditRoomFrm calls `RoomDAO.updateRoom()`.
20. EditRoomFrm displays success and returns to ManagerHomeFrm.

### Booking room sequence pattern

1. Client calls seller to book a room.
2. Seller clicks booking on SellerHomeFrm.
3. SellerHomeFrm opens SearchFreeRoomFrm.
4. Seller enters check-in and check-out dates.
5. SearchFreeRoomFrm calls `RoomDAO.searchFreeRoom()`.
6. RoomDAO packs available Room objects.
7. SearchFreeRoomFrm displays rooms.
8. Seller selects a room.
9. SearchFreeRoomFrm packs Booking and BookedRoom transfer data.
10. SearchFreeRoomFrm opens SearchClientFrm.
11. Seller searches client.
12. SearchClientFrm calls `ClientDAO.searchClient()`.
13. ClientDAO packs Client objects.
14. SearchClientFrm displays clients.
15. Seller selects current client.
16. SearchClientFrm adds client to Booking.
17. SearchClientFrm opens ConfirmFrm.
18. Seller confirms with client and clicks confirm.
19. ConfirmFrm calls `BookingDAO.addBooking()`.
20. ConfirmFrm displays success and returns to SellerHomeFrm.

### View room statistic sequence pattern

1. Manager clicks View report on ManagerHomeFrm.
2. ManagerHomeFrm opens SelectStatFrm.
3. Manager configures room revenue report.
4. SelectStatFrm opens RoomStatFrm.
5. Manager enters start and end date.
6. RoomStatFrm calls `RoomStatDAO.getRoomStat()`.
7. RoomStatDAO packs RoomStat objects and inherited Room attributes.
8. RoomStatFrm displays report.
9. Manager selects a room row.
10. RoomStatFrm opens RoomDetailFrm.
11. RoomDetailFrm constructor calls `BookingDAO.getBookingOfRoom()` or an equivalent DAO query.
12. DAO packs Booking, Client, BookedRoom, and Room component objects.
13. RoomDetailFrm displays detailed statistic.

## How to Adapt This to Parttime Employee Management

1. Start from the real analysis entity class diagram in the project.
2. Add IDs, types, relationship object attributes, and design-level details for real classes only.
3. Convert real project entity classes into database tables with `tbl` prefix if the report follows this convention.
4. Use MVC design with `Frm` view classes and DAO classes if consistent with the project chapters.
5. Write sequence diagram scenario v.3 with event handling, constructors, entity packing, and DAO calls.
6. Do not introduce hotel domain classes or tables.
