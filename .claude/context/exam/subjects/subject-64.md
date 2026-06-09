Software engineering
Subject No. 64
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
Module "Customer returns and pays ": When a customer brings the costumes back to return, a  
staff chooses the menu to find the list of borrowed costumes by the customer's name → enter the  
customer's name + click search → the system displays a list of customers whose names contains the  
entered  keyword  →  The  staff  selects  the  correct  customer  name  with  the  current  customer  
information → the system displays a list of the costumes that the customer is borrowing, each  
costume on a line with full information about the costume, loan date, loan price per day, number of  
days borrowed, and rental amount up to the date of payment, the last column is the check box to  
select pay → The staff clicks on the pay button for the costumes that the customer returns (with  
may not pay in full), enter the status of the costume and the fine if any, finally clicks the payment  
button → the system displays the invoice with full customer information + a list of the costumes to  
be returned as described above + the last line is the total amount paid, the amount deposited, the  
amount the customer has to pay or return to the customer → clicks confirm → the system updates to  
the database.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
82
