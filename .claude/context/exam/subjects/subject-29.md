Software engineering
Subject No. 29
Duration: 60 minutes
A client requires us to develop a software to manage the World Championship Tournament with the  
following description:
•Each tournament (Code, name, year, time held, location, description) allows multiple  
players (code, name, year of birth, nationality, Elo coefficient, notes) to participate.
•There may be hundreds of players involved, but each player must play 11 matches according  
to the Swiss rule.
•In the first game, the players are ranked in order of Elo coefficients from high to low. Then  
going from the top to the bottom of the arrangement, two players standing next to each other  
will form a pair for round 1.
•In each round, win 1 point, draw 0.5 point, lose 0 point. After each round, the results of each  
match are updated according to the previously scheduled matches. At the same time, the Elo  
coefficient increasing or decreasing after each round is also updated (Calculated by FIDE's  
formula, just enter the results).
•Starting from the 2nd round, the temporary standings after the previous round are ranked  
according to the following criteria: total score (descending), total score of opponents met  
(descending), Elo coefficient ( decrease). And the match is determined as follows, going  
from top to bottom of the provisional standings, for each unpaired player, that player's  
opponent is the first player encountered and satisfied: no match, and haven't met the  
considered player.
•After 11 such rounds, the top player in the ranking will be the champion.
Module "Statistics of Elo change ": A staff selects the statistics menu → select the function of Elo  
change statistics of the chess players after the tournament → the results appear: List of players, each  
player is shown full information: code, name, year of birth, nationality, old Elo coefficient, new Elo  
coefficient,  increased/decreased  Elo  coefficient  (sorted  in  descending  order  of  the  increase  or  
decrease  of  the  Elo  coefficient  of  the  oddities,  followed  by  the  descending  of  the  new  Elo  
coefficient). Click on a line of a chess player → the system displays the details of the matches that  
the player has played, each match on 1 line: id, opponent's name, result (win, draw, loss), Elo  
increase or decrease.
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
35
