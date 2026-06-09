Software engineering
Subject No. 66
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
Module "Revenue Statistics ": A staff selects the menu of revenue statistics by time (month, quarter,  
year) → the system displays a box to select statistics by month, quarter, or year → The staff clicks  
by month → the system displays the monthly revenue statistics in the form of a table, each line  
corresponds to 1 month (corresponding to the quarter, year): month name, total revenue (Sorted by  
chronological  order  from  the  nearest  month  (respectively  quarter,  year)  to  the  oldest  month  
(respectively quarter, year)). The staff clicks on a line → the system displays the details of invoices  
in that line, each invoice on 1 line: id, customer name, borrowed date, total number of borrowed  
clothes, total amount of the bill
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
84
