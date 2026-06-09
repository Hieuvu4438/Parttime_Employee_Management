Software engineering
Subject No. 07
Duration: 60 minutes
A client requires us to develop a software to manage student results, described as follows:
•Each student (Student ID, password, name, date of birth, course, hometown, address) is  
allowed to register for a minimum of 10 credits/semester and a maximum of 15  
credits/semester
•Each student can register for many subjects (subject code, subject name, number of credits)
•Each subject can have multiple subjects that students must complete before they can be  
registered
•Each subject can have multiple classes (class code, class name, maximum number of  
students, classrooms, fixed hours of the week)
•Students are not allowed to register for two classes with the same class time
•For each subject, a student is only allowed to enroll in a class
•Student results (part number 1, number 2, number 3, test score, final score=x% number1+ y
% number2 + z% number3 + w% test score) are saved for each subject
•The student's grade point average for the semester is calculated as a weighted average of the  
number of credits per subject
Module "Register for classes ": Students log in → select the menu to register for the new semester  
→ the registration page appears → students select a subject from the list of subjects + select the  
class in the list of classes (and associated lecturers) corresponding to the selected subject → repeat  
until all subjects are registered. If the above constraints are satisfied, then notify the success + print  
out the registration form for the student: student code, student name, course, semester + list of  
registered subjects, each subject has:  code,  name, credit number, class time, lecturer name
1.Write a standard scenario for this module
2.Extract and build class diagram for all related entity classes
3.Static design: Design UI and the detailed class diagram of the design phase for this module
4.Dynamic design: Build the sequence diagram of the design phase for this module
5.Write a standard blacklox testcase for this module
13
