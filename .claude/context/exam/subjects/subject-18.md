Software engineering
Subject No. 18
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
Module "Payment": Customer requests a staff to pay → Staff selects payment function → Table  
selection interface appears with a list of tables → Customer selects the table matches the customer's  
table → The detailed invoice interface of the table appears as described above (can add/edit the  
ordered items in the bill, if necessary) → The customer asks if the customer has a coupon. → if yes,  
then click add coupon + enter code → invoice interface add coupon line and update total amount to  
pay → The staff informs customer the amount → After payment, the staff clicks confirm → system  
saves and prints detailed invoices for customers.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
24
