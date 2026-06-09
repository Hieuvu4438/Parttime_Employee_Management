Software engineering
Subject No. 62
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
Module "Import costume": A staff selects the function to import costumes from a provider → The  
interface to find provider by name appears -> The staff enters the name of the provider and finds it  
-> Shows a list of providers containing the new name -> Clicks the correct provider (if not in the list  
of results, switch to the interface to enter new provider information and continue) → Repeat until  
all costumes need to be imported from that provider: select search costumes by name - > select and  
enter quantity, unit price → confirms the invoice entered with provider and pay to the provider,  
receive costumes → The system saves and prints the invoice to ask the provider to sign and save.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
80
