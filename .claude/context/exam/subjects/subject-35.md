Software engineering
Subject No. 35
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
Module "Return and pay": When a customer returns books, a staff selects the menu to find a list of  
borrowed stories by the customer's name → enter the customer's name + click search → system  
displays a list of customers whose names contains entered keyword → The staff selects the correct  
customer name → the system displays a list of book titles that the customer is borrowing, each title  
on a line with full information : code, name, the date of the loan, the loan price, and the rental  
amount up to the date of payment, the last column is the check box to choose to pay → The staff  
clicks on the return button for the book titles that the customer has returned (may not pay all 1  
times), enters the status of the book and the fine if any, finally click the payment button → the  
system displays  the invoice with full customer information + a list of returned book titles as  
described above + the last line is the total payment → The staff clicks confirm → the system  
updates to the database.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
41
