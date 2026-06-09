Software engineering
Subject No. 51
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
Module "Goods importing": A staff selects the import menu whem import goods from a provider  
→ the import page appears with a box to search for provider by name → The staff enters a name +  
clicks to search → the system displays a list of the providers whose name contains the entered  
keyword → clicks on the currently imported provider (if the provider is new, add a new one) →  
58

Repeat the following steps for all imported goods: clicks to search for goods by name → enter name  
+ click search → the system displays a list of the goods whose name contains the name just entered  
→ the staff selects the name of the goods in the list of available goods (if the goods are new, choose  
to add new) + enter the unit price and quantity → that item will be added to the list of imported  
goods of the invoice → repeat until all the imported goods are finished, submit → successful import  
report and print the imported invoice as described.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
59
