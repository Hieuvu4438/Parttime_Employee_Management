Software engineering
Subject No. 40
Duration: 60 minutes
A client requires us to develop a software to manage the (part-time) employees of stores with the  
following description:
•The restaurant chain has many restaurants. Each restaurant has many hourly employees.  
Each working day has 2 shifts, 1st shift from 8am to 4pm, 2nd shift from 4pm to 0am.  
Hourly rates are the same for all hourly employees.
•Each employee, after signing the contract, is allowed to register his available working time.  
The number of sessions that can work in each week that each registered employee must  
meet the prescribed minimum threshold. This information may change on a weekly basis,  
before scheduling work for the next week.
•Management will be based on the registration schedule of each employee to schedule the  
next week. Make sure each shift has enough N employees to work. If there is a shift where  
the number of registered employees is greater than N, priority will be given to the  
employees who are working fewer hours. The next week schedule will be announced to all  
staff.
•When coming to work, the employee scans the check-in card to work, when returning, the  
employee scans the checkout card to return.
•Employee wages are calculated based on the actual hours worked by the employee and are  
paid weekly. If an employee works more than 8 hours, the salary for the extra time will be  
calculated. If an employee arrives late or leaves early, the time of absence will be deducted.
Module "Schedule next week ": A staff selects the function to schedule the next week → The  
scheduling interface appears including a table with 7 lines corresponding to 7 days of the next  
week, each row has 2 columns corresponding to 2 shifts of the day. Each column contains the  
names of employees registered for that shift → The staff clicks on a shift → The interface shows a  
list of employees who have registered to that shift and have not been assigned to that shift, one  
employee per line: name, phone number, total scheduled hours for next week, sort in ascending  
order of total scheduled hours for next week → The staff clicks on some employee and clicks the  
select button → Interface returns to schedule page with the information of selected employees is  
added to the column of the corresponding shift → The staff repeats the above selection steps until  
the end of the next week's shift and clicks save → The system saves.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
46
