Software engineering
Subject No. 24
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
Module "Statistics of items ": A manager staff selects the statistics menu → selects the function of  
item statistics → enter the statistical period (start - end) → the results show a list of item in order of  
total  revenue  from  the  most  to  the  least  in  the  selected  period,  each  line  has  the  following  
information: item code, item name, quantity sold, the total revenue during the selected time period.  
The staff clicks on a line of an item to display detailed statistics of invoices of sub-agents who have  
purchased that product.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
30
