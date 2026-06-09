Software engineering
Subject No. 01
Duration: 60 minutes
A client requires us to develop a library management software, described as follows:
•Each book title (Code, name, author, publication year, cover price, quantity, barcode,  
description) can be borrowed many times by many different readers.
•Each reader has a reader card containing the reader's code, name, date of birth, address,  
phone number, barcode
•Up to 5 books can be borrowed at a time, and the total number of books being borrowed by  
one person cannot exceed 5 books
•The maximum duration to borrow a book is 1 month from the date of borrowing that book,  
if the reader returns it after this time, he will be fined 20% of the book cover price.
•Each time a reader returns borrowed books, he can return part or all of the borrowed books
•When borrowing new books, the librarian can still see a list of books that the reader has  
borrowed and paid or not paid before.
modulee "Borrowing of books ": A staff chooses the book borowing menu → scans the reader card  
to get reader information → reader detail information appears + list unreturned books + returned  
book list → The staff scans selected books one by one → the list of borrowed books is added until  
the books to borrow (or up to 5 books) are exhausted, then submit → print out a loan slip containing  
the code, name, reader barcode, bar code of the loan slip, and a list of books that are still borrowed,  
each title book on one line: code, title, author, barcode, loan date, due date and The last line shows  
the total number of books being borrowed.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
7
