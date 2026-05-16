# Teacher Reference — Use Case System Description in UML

> Source domain: hotel management system. This is an external example only.
>
> Use this file to understand how to build general and detailed use case diagrams. Do not copy hotel-specific actors or use cases into Parttime Employee Management.

## 1. General Use Case Diagram Method

### Step 1: Propose actors

- For each user, propose an actor of the system.
- If several users share common features, propose an abstract actor and let concrete actors inherit from it.
- Consider indirect users who can start or trigger some function of the system.

### Step 2: Propose use cases

- For each function of each actor, propose a use case.

### Step 3: Refine use cases

- If at least two use cases are similar, regroup them into one use case.
- If regrouping may cause misunderstanding about the related actors, use an abstract use case as the common parent.

## 2. Detailed Use Case Diagram Method for One Function

### Step 1: Extract the target use case

Take the use case part of the selected function from the general use case diagram.

### Step 2: Propose sub-use cases

An interface that interacts with an actor can become a sub-use case.

### Step 3: Determine relationships

Determine the relationship from each sub-use case to the main use case:

- generalization;
- include;
- extend.

### Step 4: Regroup similar sub-use cases

If necessary, regroup similar sub-use cases into a more general use case.

## 3. Sample General Use Case Diagram Content

### Sample actors

- Direct actors: Manager, Administrator, Seller, Receptionist.
- Abstract actor: Employee.
- Indirect actor: Client.

### Sample use cases by actor

Receptionist:

- book on site;
- cancel on site;
- checkin;
- checkout.

Seller:

- book via phone;
- cancel via phone.

Manager:

- manage hotel;
- manage room;
- view report.

System administrator:

- manage account.

### Sample use case refinement

- `Book on site` and `Book via phone` are both special forms of `Book room`.
- `Cancel on site` and `Cancel via phone` are both special forms of `Cancel booking`.

## 4. Sample Use Case Descriptions

- Manage hotel: enables the manager to manage hotel information.
- Manage room: enables the manager to manage room information.
- View report: enables the manager to view statistical reports about hotel, room, client, service, or revenue.
- Manage account: enables the administrator to manage user accounts.
- Book a room: enables the seller or receptionist to book a room for a client.
- Book on site: enables the receptionist to book a room for an on-site client.
- Book via phone: enables the seller to book a room for a remote client by telephone.
- Cancel a booking: enables the seller or receptionist to cancel a booking for a client.
- Cancel on site: enables the receptionist to cancel an on-site booking.
- Cancel via phone: enables the seller to cancel a remote booking by telephone.
- Checkin: enables the receptionist to check in a client.
- Checkout: enables the receptionist to check out and process payment for a client.

## 5. Detailed Use Case Example — Edit Room

From the natural-language flow, identify interfaces and interactions:

- Login.
- Manage room.
- Add room.
- Edit room.
- Delete room.
- Search room.

Sample descriptions:

- Add room: enables the manager to add a new room into the system.
- Edit room: enables the manager to edit a room in the system.
- Delete room: enables the manager to delete a room from the system.
- Search room: enables the manager to search a room by id or name to edit or delete.

## 6. Detailed Use Case Example — Book Room

From the natural-language flow, booking requires:

- login;
- search available rooms;
- search client;
- add new client if the client does not exist;
- confirm booking.

Sample descriptions:

- Search free room: enables the seller or receptionist to search available rooms for the client.
- Search client: enables the seller or receptionist to search client information by name or phone number.
- Add client: enables the seller or receptionist to add a new client during booking.

## 7. Detailed Use Case Example — View Room Revenue Report

From the natural-language flow, the manager must:

- log in;
- configure report;
- choose statistic period;
- view main room report;
- optionally view detail of a room.

Sample descriptions:

- View report: enables the manager to view all kinds of reports.
- View room report: enables the manager to view the main report about rooms by revenue.
- View detail a room: enables the manager to view bookings related to a room during the statistic period.

## How to Adapt This to Parttime Employee Management

1. Identify real actors from the project chapters.
2. Build a general use case diagram from the real functions.
3. For the selected module, extract sub-use cases from interfaces and actor interactions.
4. Use teacher sample only for method and structure.
5. Never introduce hotel actors, hotel use cases, or hotel entities unless the project actually defines them.
