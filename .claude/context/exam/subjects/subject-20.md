Software engineering
Subject No. 20
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
Module  "Statistical  monthly  revenue ":  A manager  staff selects  the  statistics  menu  →  select  
monthly revenue statistics → list of last 12 months is displayed, one line for a month: name, total  
number of guest, total revenue, sorted in descending order of total revenue. The staff clicks on a line  
of a month, the system displays the details of the customer's bills for the month, each bill on the  
line: id, name of customer, date and time, total number of orders, total payment amount.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
26
