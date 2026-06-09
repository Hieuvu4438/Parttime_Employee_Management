Software engineering
Subject No. 33
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
Module "View the team rankings ": A staff selects the statistics function → Select to view the  
current racing team rankings → The staff selects a stage from the dropdown list → The system  
displays a list of racing teams, in the form of a table, each line contains: Team name, team owner,  
total points of the team's drivers after stages, total time after stages (sorted in descending order of  
total score, then in ascending order of total time). The staff clicks on a line of a racing team → the  
system displays detailed results for each stage of that racing team, each stage on 1 line: race name,  
total score, total time of the 2 racers in the team.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
39
