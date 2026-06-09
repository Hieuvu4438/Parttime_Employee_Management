Software engineering
Subject No. 57
Duration: 60 minutes
A client  requires  us  to  develop  a  software  to  manage  installment  loans  with  the  following  
description:
•The company cooperates with many partners which are retailers of products with many  
categories from phones, computers, electronics, refrigeration, home appliances, cars, real  
estate .. .
•When a customer buys one or several phone models and needs to use the installment service,  
the staff will carry out procedures to sign an installment loan contract for that customer. The  
contract contains information about the company's representative, customer information,  
signing date, and a list of items, each item on one line: code, name, unit of measure, unit  
price, quantity, amount. The next line is the total amount and loan term. Next is a list of  
payment  times,  each  installment  on  1  line:  payment  date,  total  payment  amount,  total  
outstanding balance.
•Each item has its own list price, the company paying for the item will receive a discount, the  
company will collect it from the customer at an interest rate based on the listed price of the  
item.
•Customers can pay installments for each contract once a month, for the optional period of  
the contract.
•Customers can pay before the due date but the payment value remains unchanged
•If the customer makes late payment compared to the monthly deadline, the late balance will  
be included in the principal and interest will be calculated according to the principal.
•The company can pay the item bill for each item or in installments for a period of 1 week, 1  
month...  Each  payment  will  save  the  invoice  with  full  information  of  the  company  
representative, the representative of the partner, payment date, total payment and list of paid  
items, each customer's purchase on 1 line: code, name of customer, date of purchase, unit of  
measure, quantity, unit price, cost money.
Module "Customers paying ": A staff selects the function of receiving payment from Customer  
(Customer can pay directly at the counter, transfer or online - this is the description for direct  
payment) → Displays interface to find contract information -> The staff enters contract code ->  
Show details of contract, history of payments, total outstanding balance and payable amount -> The  
staff informs customer the amount to be paid payment and total outstanding balance, asks the  
customer  how  many installments  or  total  amount  (customer  can  pay in  advance  for  the  next  
installments, or pay not enough for this installment) → selects payment due that customer wants to  
pay payment, enter the amount → displays payment invoice containing information of customer,  
company representative, list of items as in the original contract, total payment, total outstanding  
balance, and a list of remaining payments -> The staff confirms with customer and click save ->  
System saves and prints the invoice for the staff to deliver to customer.
70

1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
71
