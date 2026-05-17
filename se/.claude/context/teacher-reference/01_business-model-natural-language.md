# Teacher Reference — Business Model in Natural Language

> Source domain: hotel management system. This is an external example only.
>
> Use this file to learn how a natural-language business model is structured. Do not copy hotel-specific content into Parttime Employee Management.

## 1. Objective and Scope Pattern

The sample starts by defining the application type, internal users, installation scope, and database deployment.

Example structure:

- The system is a desktop-based application.
- The system is used internally inside one organization.
- Only employees of that organization can use the application.
- The system supports only one business unit or organization.
- The application can be installed on many employee computers.
- The database is stored on a central server.

## 2. Users and Functions Pattern

The sample lists each user role and the functions available to that role.

### Sample hotel roles

- Receptionist:
  - book room for on-site clients;
  - cancel bookings for on-site clients;
  - check in clients;
  - check out and process payments.
- Seller:
  - book room for remote clients by telephone;
  - cancel bookings for remote clients by telephone.
- Manager:
  - manage hotel information;
  - manage room information;
  - view statistical reports.
- System administrator:
  - manage user accounts.

## 3. Detailed Business Process Pattern

The sample then selects important functions and writes each one as a step-by-step business process in natural language.

### Edit room information — sample flow

1. A manager logs in.
2. The manager home UI appears with options for hotel information, room information, and reports.
3. The manager selects room management.
4. The room management UI appears with add, edit, and delete options.
5. The manager selects edit.
6. The search room UI appears with a keyword input and search button.
7. The manager enters a room-name keyword and searches.
8. Matching rooms are listed with id, name, type, price, and description.
9. The manager chooses a room.
10. The edit room UI appears with editable values except id.
11. The manager modifies values and saves.
12. The system shows a success alert and returns to manager home.

### Booking room by telephone — sample flow

1. A client calls the hotel to book a room.
2. The receptionist forwards the call to a seller.
3. The seller asks for the desired stay period and opens the booking function.
4. The available-room search UI appears with check-in date, check-out date, and search button.
5. The seller enters the dates and searches.
6. Available rooms are listed with id, name, type, price, and description.
7. The seller informs the client and asks the client to choose room(s).
8. The seller selects the chosen room.
9. The client information UI appears with a search input and search button.
10. The seller asks for client details and searches by client name.
11. Existing clients are listed with id card number, name, address, phone number, and notes.
12. The seller selects the matching client or adds a new client if necessary.
13. The confirmation UI displays client information, selected room information, and dates.
14. The seller confirms the information with the client.
15. The seller clicks confirm.
16. The system shows a success alert and returns to seller home.
17. The seller informs the client that booking is successful.

### View room revenue report — sample flow

1. A manager logs in.
2. The manager selects statistical reports.
3. The report configuration UI appears with report object and criteria selections.
4. The manager selects room as the object and revenue as the criterion.
5. The report UI appears with start date, end date, and view button.
6. The manager enters the dates and views the report.
7. Results are listed by total revenue with room id, name, type, total filled days, and total revenue.
8. The manager clicks a room to view details.
9. The detail UI displays selected room information and related bookings ordered by check-in time.
10. The manager returns to the manager home UI.

## 4. Related Object Information Pattern

The sample identifies main domain objects and their important attributes.

### Sample hotel objects

- Hotel: name, address, star rating, description.
- Room: name, type, price, description.
- Client: id card number, name, address, phone number, email, note.
- Provided service: name, unit, price, description.
- Employee/user/account: full name, username, password, role, note.
- Bill: creator information, client information, booked room information, used service details, total amount, paid amount, remaining amount.
- Room revenue statistic: room name, room type, total filled days, total income.
- Client revenue statistic: client id card number, client name, client address, total stayed days, total payment.
- Service revenue statistic: service name, total quantity, total income.
- Revenue by time statistic: period name, total number of clients, total income.

## 5. Relationship Description Pattern

The sample describes object relationships in plain language before converting them into diagrams.

### Sample hotel relationships

- A hotel has many rooms; a room belongs to one hotel.
- A room can be booked by many clients in different periods.
- A client can book many rooms in different periods and can book many rooms at the same time.
- For a single client, at a time, the client stays only in one room.
- A client in a booked room can use many services.
- A service can be used by many clients in many booked rooms.
- A room can be booked only if it is available during the desired period.
- For each booking, the client can pay several times until checkout.
- A client can cancel a booked room.
- A seller or receptionist can book many times for clients.
- A receptionist can process many payments.

## How to Adapt This to Parttime Employee Management

When writing the real project business model:

1. Replace hotel scope with the restaurant/part-time employee scope.
2. Replace hotel roles with the real system actors from the project.
3. Replace hotel functions with the selected module functions, such as registering next week's shifts.
4. Write detailed business processes as actor-interface-system flows.
5. List project entities and relationships from the real project chapters, not from this sample.
