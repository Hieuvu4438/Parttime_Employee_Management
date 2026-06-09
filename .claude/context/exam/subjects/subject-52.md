Software engineering
Subject No. 52
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
information. The next lines, each line record a mini yard with full information about the  
yard, rental price per session, rental time slot of the week, start date, end date of the rental  
period, total expected rent. The last line shows the expected rental amount
•When booking a yard, customers must deposit in advance. And this deposit information is  
also clearly stated in the invoice/bill.
•When customers come to play football at the yard, the owner can serve refreshments and  
snacks. What kind of products do customers use each session, how many bottles (packs) of  
each type, and how much total money is updated into the system. The customer will pay this  
incidental fee at the end of the rental period.
•When paying for the yard rental, the customer receives an invoice detailing the rental  
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
Module "Update used items of the rental session ": When the customer arrives to receive the court  
and return the court for that session, the staff selects the menu to find the booking ticket by the  
customer's name → enter the customer's name + click search → the system displays a list of  
customers with the name entered → selects the correct customer name with the current customer  
60

information → the system displays a list of orders that the customer is booking → clicks on the  
checkout button rental session 1 booking ticket → the system displays an interface to enter the court  
reception time, return time, and rent (early payment will not be reduced, but late payment will be  
charged more) + repeat the following steps until the list of food products that customers have used  
during the rental sessions: click more items used → the interface to search for goods by name  
appears → enters the name of the goods and search → the interface for the list of goods with the  
name entered appears → clicks on 1 item → the interface to enter the unit price and quantity  
appears → enters and confirms → the used item information is added to the list of used items of the  
session -> The last line is the total amount of customers → clicks to confirm → the system updates  
to the database (no payment required).
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
61
