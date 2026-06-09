Software engineering
Subject No. 31
Duration: 60 minutes
A client requires us to develop a  software to manage the F1 Formula Championship  with the  
following description:
•There is a championship every year. A tournament consisting of many races taking place  
around the world (Race code, name, number of laps, location, time, description).
•Each tournament has many participating teams (Code, name, brand, description).
•Each racing team has many riders (code, name, date of birth, nationality, biography). But in  
each race, each team is only allowed to allow a maximum of 2 riders to participate.
•Each driver can play for many racing teams at different times. But at a time only play for 1  
team.
•For each race, the results are ranked in order of finishing (time) and the score is only  
calculated for the top 10, respectively in the order of finishing 25, 18, 15, 12, 10, 8, 6, 4, 2,  
1.
•If the driver is in the top 10 but does not finish due to a dropout or an accident, then 0  
points.
•The score and time of each driver will be added up between the stages to decide the  
individual and team prizes of the season.
Module "Update results": A staff selects the function of entering race results → the result input  
interface appears → The staff selects the race name from the list drop-down → The list of registered  
racers for the race appears in the form of a table, each line contains blank boxes to enter the time to  
the finish line, the number of laps completed → The staff enters all the results of all racers and click  
Save → The system saves the results to the database.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
37
