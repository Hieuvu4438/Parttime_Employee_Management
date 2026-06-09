Software engineering
Subject No. 22
Duration: 60 minutes
A client requires us to develop a store management software, described as follows:
•Each item (Item code, name, description) can be imported many times, each time has  
different quantity and different price, coming from a supplier ( code, name, address, phone  
number) are different
•You can import many different items at a time
•Each imported time there is an imported bill with supplier information, followed by a list of  
imported items, each item has full information: code, name, quantity, unit price, and total  
amount (automatically calculated) and the last line is the total amount of the imported bill
•Similarly, each item can be exported many times, each time to different sub-agents ( code,  
brand name, address, phone number), with different quantities and different export prices.
•Each export time can export many items, as long as the export quantity does not exceed the  
number of goods in the store
•Each export time has an export bill with sub-agent information, followed by a list of  
exported items, each item has full information: code, name, quantity, unit price, and total  
amount (automatically calculated) and the last line is the total amount of the bill.
Module "Exporting": A staff selects the export menu → the export page appears with the search  
box for sub-agence → enters the name of the agent and clicks to search → the system displays a list  
of agents whose names contain the entered keyword → clicks the line of the correct sub-agence (in  
case of new sub-agence, it must be added) → the system displays the interface to find exported item  
→ The staff enters the name item and click search → the system displays a list of items whose  
names containing the keywords entered → the employee selects the correct item in the list + enters  
the quantity + unit price → the item appears in the list of exported items  → repeat until all the  
items need to be exported, then submit → the system saves and prints out the export bill as  
described.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
28
