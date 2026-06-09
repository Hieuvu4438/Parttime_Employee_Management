Software engineering
Subject No. 49
Duration: 60 minutes
A client requires us to develop a cinema chain management software with the following description:
•The company has a chain of cinemas (cinema code, theater name, address, introduction).
•Each cinema has many screening rooms (room code, number of seats, room characteristics)
•Each movie (Movie ID, movie title, type, year of production, description) can be shown in  
different cinemas at different times
•Each screening room can show many movies at different times
•At the time, in a screening room only one movie is shown, and sold at a fixed ticket price.
•The same movie, showing at the same screening room, but in different time slot and dates  
may have different ticket prices.
•Same show, different seats may have different ticket prices.
•Employees  only  sell  tickets  to  customers  when  the  screening  room  at  the  showtime  
requested by the customer is still full of empty seats for the customer.
•When buying tickets, customers are issued an invoice containing the tickets purchased. Each  
ticket on one line: movie name, screening room, showtime, seats, offer, price. The next line  
is the total amount.
•The cinema also sells fast food services (such as popcorn, drinking water...). Customers can  
purchase with movie tickets (in which case, the bill will include these services), or buy  
separately. If buying separately, issue a separate invoice, each line is an item: code, name,  
unit price, quantity, incentives, money. The next line is the total amount.
Module "Statistics of services ": A staff selects the statistics menu → selects statistics list items sold  
→ enters the start and end time of statistics → a list of service items appears, one line for each item:  
Code, name, total quantity sold, total revenue, sorted in descending order of total revenue. The staff  
clicks on a line of an item to display the details of the sale of that item, each line corresponds to the  
information: sale date, unit price, quantity, total amount, sorted in the order of sale date from old to  
new. For items that are exchanged for points, when statistics are still converted into money as usual  
for statistics.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
55
