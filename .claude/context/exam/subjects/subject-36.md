Software engineering
Subject No. 36
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
Module "Statistics of book": A staff selects the menu statistics of the most borrowed book → Enter  
the time period (start - end date) statistics → The system displays a list of borrowed titles in a table  
format, each line corresponds to a book title: code, name, author, publisher, year of publication,  
column total number of times borrowed, column total amount (Sorted in descending order of the  
total borrowed column, followed by the descending of the total income column). The staff clicks on  
1 line of a book → the system displays the details of the invoice with that book borrowed, each  
invoice on 1 line: id, name of the borrower, borrowed date and time, payment date and time, total  
payment amount.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
42
