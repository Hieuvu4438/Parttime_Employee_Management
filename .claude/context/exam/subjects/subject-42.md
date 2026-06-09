Software engineering
Subject No. 42
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
Module "Calculate this  week's wages ": A staff selects  the function of calculating wages for  
employees during the week → The calculation interface appears with a box to enter the time period  
for calculation → The staff enters the starting date, the end of the last week → The interface shows  
a list of wages for all employees in that week, each employee on one line, in the alphabetic order of  
name: code, name, phone number, total hours worked in shift, total money in shift, total overtime,  
total number of hours late coming home early, total fines, total last received → The staff clicks on a  
line of an employee→ The interface shows a detailed statistics table of working hours of the  
selected employee in that week, each line corresponds to 1 working shift: day, date, shift, checkin  
time, checkout time, number hours in shift, amount in shift, number of overtime hours, overtime  
amount, number of hours of late arrival and early return, fines, total amount received for the shift.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
48
