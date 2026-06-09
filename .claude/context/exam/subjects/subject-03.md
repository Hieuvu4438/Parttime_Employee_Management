Software engineering
Subject No. 03
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
Module  "Statistics  of  borrowed  books ":  The  staff  selects  the  statistics  menu  →  selects  the  
statistics of borrowed books → enter the time period (start - end) → the list of borrowed books are  
displayed in order of the number of loans from most to least, each line contains: code, book title,  
author, barcode, total number of loans. The staff clicks on a line of a book to display a detailed list  
of times  that reader has borrowed that book, each line contains: reader name, borrowed day,  
returned day, the fine if any.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
9
