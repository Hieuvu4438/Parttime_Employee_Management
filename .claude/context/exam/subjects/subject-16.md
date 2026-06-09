Software engineering
Subject No. 16
Duration: 60 minutes
A client requires us to develop an order management software in a restaurant, described as follows:
•The restaurant has many tables (Table code, name, maximum number of guests,  
description). Many small tables can be merged into one large table upon request from a large  
group of guests.
•Each table, can be booked many times in a day, or in different days.
•Each customer (Code, name, phone number, email, address) can book many times, each  
time can book many tables (in this case, it will be merged into 1 table)
•Restaurants can make combos that combine a number of dishes that are enough for one meal  
for one person to eat. Customers can call available combos like this.
•Customers at each table can order multiple dishes (Code, type, name, description, current  
price) or combo. Each dish (combo) can be ordered with a different amount.
•When paying, the invoice contains all information: desk code, name and code of staff,  
customer name if any, then a table, each line contains information about an used item  
(combo): id, name, unit price, quantity, amount. The last line shows the total amount of the  
invoice.
Module "Book a table": A staff selects the table reservation function when a customer calls → the  
interface to find an available table appears → The customer enters the date + time of booking + the  
number of guests and presses the button search → results appear including a list of available tables  
on that date and time: code, name, maximum number of guests, description → Customer selects a  
table according to customer's choice → Customer information input interface appears → The staff  
asks the customer and enters the code, name, phone number, email, address and click search → The  
system displays a list of customers whose name contains the entered keyword, each customer on 1  
line: code, name, phone number, email, address → The staff clicks on the right line with the  
currently booked customer (if not, click add a new customer) → The system displays full table  
information + customer information + date and time of booking → The staff confirms with the  
customer and click confirm → The system saves the booking information in the database.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
22
