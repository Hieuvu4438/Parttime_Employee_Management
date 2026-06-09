Software engineering
Subject No. 34
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
Module "Borrowing": After selecting books to borrow, a customer takes them to the cashier counter  
to make loan slips. A librarian staff enters the customer's name and searches → The system returns a  
list of customers whose name contains the name entered → The staff clicks on the customer's name  
in the list (if the customer borrows for the first time, enter a new one) → The system displays the  
interface to add borrowed books: For each book title, the staff clicks to search for book by name →  
enter the title of the book + click search → the system displays a list of the book titles whose name  
contains the entered name → The staff clicks on the correct line with the book selected by the  
customer → System adds 1 line corresponding to the borrowing list. When finish the book list, the  
staff clicks to create a loan slip → The system saves the loan slip and displays the loan slip on the  
screen → The staff clicks to print → The system prints the loan slip for the customer.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
40
