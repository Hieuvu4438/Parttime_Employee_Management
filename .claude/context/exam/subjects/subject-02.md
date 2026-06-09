Software engineering
Subject No. 02
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
modulee "Returning of books ": A staff selects the return menu → scans the reader card to get  
reader information → reader detail information appears + a list of unreturned books + returned  
borrowed books → The staff scan returned books in turn → the list of borrowed books is shortened  
until the end of borrowed books (or all the books returned by readers are scanned) then submit →  
print out a loan slip (if there are still books on loan) containing the code, name, reader barcode, loan  
card barcode, and a list of books that are still borrowed, each title on one line: code, title, author,  
barcode, Borrowing date, due date and last line total number of books on loan + penalty slip (if  
fined) containing code, name, reader barcode, loan coupon barcode, and list of fine (late return  
books, damaged, lost...), each end books on one line: code, title, author, bar code, date borrowed,  
due date, pay date, fine amount and last line the total amount of the fine.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
8
