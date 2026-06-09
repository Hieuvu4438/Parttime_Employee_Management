Software engineering
Subject No. 50
Duration: 60 minutes
A client requires us to develop a  rental management software for a mini football field with the  
following description:
•The football field has many mini courts for rent. Depending on customer requirements, it is  
possible to combine 2 or 4 adjacent small courts into 1 large court for rent.
•Each court can be rented by many customers at different time slot. Each customer can rent  
many different courts.
•Customers can rent the court by session of the week or by month (on one or a number of  
fixed sessions a week, within a few specific months).
•When making a contract to rent a court, customers receive a rental voucher. In it, the first  
line  records  the  date  of  the  contract,  the  owner's  information,  and  the  customer's  
information. The next lines, each line record a mini court with full information about the  
court, rental price per session, rental time slot of the week, start date, end date of the rental  
period, total expected rent. The last line shows the expected rental amount
•When booking a court, customers must deposit in advance. And this deposit information is  
also clearly stated in the invoice/bill.
•When customers come to play football at the court, the owner can serve refreshments and  
snacks. What kind of products do customers use each session, how many bottles (packs) of  
each type, and how much total money is updated into the system. The customer will pay this  
incidental fee at the end of the rental period.
•When paying for the court rental, the customer receives an invoice detailing the rental  
information and the cost of the rental, just like the booking slip. There may be some  
additional sessions arising or rescheduled according to customer requirements. In addition,  
the next part of the invoice states the food and drink used in each session, each session is  
listed in a table, in which each line of the table describes an item: code, name, price,  
quantity used, total money. The total amount of each session and the total amount for the  
whole booking.
•The yard manager must import the items for sale from many different suppliers (code, name,  
address, email, phone, description). Each time of importing goods, there is an import invoice  
specifying supplier information and a list of items, each line: id, name, unit price, quantity,  
amount. The last line is the total amount.
Module "Booking": A customer comes to book a  → A staff selects the booking function → the  
system displays the interface to find an empty court according to the time slot → The staff enters  
the time slot + select the type of court as requested by the customer + click search → the system  
displays a list of available courts according to the selected time slot → clicks on a courts → the  
56

system displays an interface to fill in customer information → The staff enters a name and search →  
the system displays a list of customers whose names contains the entered keyword → clicks on the  
correct customer name with the current customer (if the customer first comes to book a court, must  
add a new one) → the system displays the interface to enter the time period of the start date, End  
date of the booking (preferred to book by quarter) → clicks confirm → the system displays a  
booking slip with full customer information, booking information, booking price, booking time slot,  
total number session according to the selected time, the estimated total amount and the deposit  
amount → clicks confirm → the system prints the booking slip and updates it to the database.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
57
