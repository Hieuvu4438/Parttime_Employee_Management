Software engineering
Subject No. 12
Duration: 60 minutes
A client requires us to develop a tour booking management software, described as follows:
•Each tour (Tour code, name, departure place, destination, description) can depart on many  
different days, depending on the departure date and the number of people buying the tour for  
each group will have different prices.
•Each tour can have a schedule to go through many different tourist sites. At each site, each  
tour can use different services of different providers.
•Each customer (Code, name, ID number, ID card type, phone number, email, address) can  
buy tickets for many different tours. Each tour can buy a different number of tickets. Each  
purchase is issued an invoice specifying tour information, departure date, tour price, number  
of guests, name of customer representative, total payment amount.
•The same customer can go on the same tour multiple times, only differing in departure date  
and ticket price.
•Customers can return tickets and have to pay the fine of  ticket cancelation.
Module "Cancel the ticket": A staff selects the ticket cancelation function requested by a customer  
→ the ticket search interface appears → enters the ticket code → the result appears. Detailed ticket:  
tour  name,  departure  place,  destination,  departure  date,  name  of  the  group  representative,  ID  
number, ID type, guest address, phone number, email, number of guests, ticket price → choose to  
cancel tickets → the system displays the fine invoice including the information as on the ticket +  
fine according to the cancel time →  press Ok → the system saves the result to the system, and the  
staff sends the change back to the customer.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
.
18
