Software engineering
Subject No. 48
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
Module "Revenue Statistics ": A staff selects the statistics menu → selects revenue statistics by  
movie (or by theater) → enters the start and end time of statistics → a list of available movies  
(cinemas) appears, one line for each movie: Code, movie name, total number of tickets sold, total  
revenue earned, sorted in descending order of total revenue -> clicks on a line of a movie (theater)  
->  The system displays  the details  of the total income  for each showing of the  movie,  each  
corresponding line: showtime, quantity tickets sold, total income, sorted in the order of showing  
from old to new -> clicks on a show to display a list of invoices sold for that show, each invoice on  
1 line arranged by payment time: code, customer name if any, total number of tickets, total amount  
of the bill (only those tickets related to that show in the invoice).
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
54
