Software engineering
Subject No. 63
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
Module "Costume renting": After choosing the costumes to borrow, a customer brings them to the  
cashier's counter to make a loan slip. The staff enters the customer's name and searches → The  
system returns a list of customers with the name entered → The staff clicks on the customer's name  
in the list (if the customer borrows for the first time, enter a new one) → The system displays the 
interface to add borrowed costumes: For each costume, the staff clicks to find the costume by name  
→ enter the name of the costume + click search → the system displays a list of costumes with the  
name entered → clicks on the right line with the costume selected by the customer + enter the  
quantity → The system adds a line corresponding to that costume in the rental slip as described. The  
total deposit is equal to the total cost of the costumes and is automatically calculated at the end of  
the bill. -> clicks to create a loan slip → The system saves it in the database and prints out the loan  
slip for the customer and receives the deposit.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
81
