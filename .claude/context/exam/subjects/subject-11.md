Software engineering
Subject No. 11
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
Module "Buy tickets": A staff selects the function to buy tickets requested by a customer→ tour  
search interface (by destination name) is displayed → The staff enters the destination name and  
click search → The results appeared include a list of available tours corresponding to the selected  
criteria, each tour displays compelete information + departure date + corresponding price at the time  
of search → The staff selects 1 tour according to customer's choice → Invoice (ticket) details will  
be  displayed:  tour  name,  departure  place,  destination,  departure  date,  name  of  the  delegation  
representative, ID number, ID type, guest address, phone number, email, number of guests, price  
ticket → The staff selects payment → customer pays → the system saves the result and prints the  
ticket for the customer.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
.
17
