Software engineering
Subject No. 15
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
Module "Order": A staff selects the ordering function → the table interface appears with a drop-
down list of tables → selects the correct table for the customer who is ordering → The interface to  
enter the ordered dish appears → The staff asks the customer and enters the name of the dish +  
selects search → the results appear including a list of detailed dishes: code, type, name, price. →  
The staff selects 1 dish exactly as customer ordered → Required to enter quantity → enters quantity  
and click OK → Dish name + quantity + subtotal added to the list of selected dishes below. The  
staff repeats these steps of choosing dishes until all the dishes that customers in the table have  
ordered. The staff reads again to confirm with customer →  click confirm → system saves.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
21
