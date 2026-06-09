Software engineering
Subject No. 37
Duration: 60 minutes
A client requires us to develop a book rental management software with the following description:
•The store has many book titles. Each book title has a different quantity and a different rental  
price (rental per day).
•Each book title can be borrowed by many customers. Each customer can borrow many  
books each time.
•Each time of borrowing, the borrower will receive a loan voucher. In which, the first line  
contains the name of the customer and the date of the loan. Information for each borrowed  
book is written on a line: name, author, publisher, year of publication, rental price. The last  
line shows the number of borrowed book titles.
•When returning, the customer will receive a payment invoice. In it, the first line contains the  
customer's name and payment date. Information for each paid book is written on one line:  
name, author, publisher, year of publication, date of borrowing, date of payment, rent,  
amount. If fined, there is an additional column of fines. The last line shows the total amount  
of the payment.
Module "Statistics of borrowers ": A staff selects the menu of statistics of customers → Enter the  
time period (start - end date) statistics → system displays a list of customers who borrow a lot in the  
form of a table, each line corresponds to a customer: code, name, idcard number, phone number,  
address, followed by the column of total number of loans, column of total amount paid (Sorted in  
descending order of the total number of loans, followed by the descending direction of the total  
amount paid). The staff clicks on 1 line of a customer → the system displays the details of the  
invoices that customer has borrowed, each invoice on 1 line: borrowed date, total number of books  
borrowed, total payment amount.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
43
