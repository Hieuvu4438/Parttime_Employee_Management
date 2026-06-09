Software engineering
Subject No. 25
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
Module "Statistics of sub-agence ": A manager staff selects the statistics menu → select the top  
sub-agence statistics function → enter the statistical period (start strat - end) → the results show a  
list of sub-agence in order of the most total revenue to the least in the selected time period, each line  
has the following information: sub-agence code, name, the total revenue from the sub-agence  
during the selected period. The staff clicks on a line of an sub-agence, a detailed list of invoices  
(date, total number of item, total amount) of each time the sub-agence has imported item will  
appear.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
31
