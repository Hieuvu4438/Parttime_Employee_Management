Software engineering
Subject No. 65
Duration: 60 minutes
A client requires us to develop a  software to help them manage their rental costumes with the  
following description:
•The  store  has  many  costumes,  of  different  categories,  a  costume  can  have  different  
quantities.
•Costume is ordered or pre-imported from suppliers. Each suplier can provide different  
costume types. Each import can import many types of costume from the same provider, each  
costume has a different amount.
•Customers can rent many times, each time renting many different costumes, each costume  
has a different number. If renting for the first time, a deposit must be equal to the total  
original value of the rental costumes, if renting many times (customers), the deposit will be  
decided by the staff making the invoice.
•When paying, customers can pay part or all of the rental costumes  in one time, each  
payment has a payment voucher corresponding to the returned costumes. The deposit is only  
returned to the customer when all rental costumes have been returned. In case the customer  
pays a part of the costumes, after paying, the remaining deposit is more than the original  
value of the rental costumes, the customer is entitled to receive the remaining balance, only  
keeping the maximum deposit equal to the original value of the rental costumes.
•When paying, if the costume is damaged or dirty, the customer must pay a fine. A costume  
can have multiple errors concurrently. Fines for each error are estimated by the the staff.
Module "Statistics of costumes ": A staff selects the menu of statistics of costumes → Enter the  
time period (start - end date) statistics → The system displays the list of costumes borrowed in the  
form of a table, each line corresponds to a costume with complete information: code, name, model,  
genre, column total number of loans, column total amount collected (Sorted in descending order of  
the total borrowed column, followed by the descending of the total proceeds column). The staff  
clicks on a line of an costume → the system displays the details of the invoice with the borrowed  
costume, each invoice on 1 line: id, name of the borrower, borrowed date and time, payment date  
and time, total amount.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
83
