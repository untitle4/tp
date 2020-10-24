# User Guide

## Introduction

Plan&Score is a Java command-line application that does two main things:
1.  Plan: Allows Primary 6 students to plan and track their:
       - School classes
       - CCAs
       - Tests
       - Tuition
  
2. Score: With the quiz feature, students can spend the time they have from productive scheduling, to hone their
Mathematics for their upcoming PSLE.

Plan&Score also has a third feature, allowing students to save the contact details of their teachers, cca coaches etc
for convenient contact.

As a whole, Plan&Score tackles not only the scheduling issue most students have, but also incorporates other features
to ensure they are better able to ace their PSLE.


- [1. Quick Start](#quick-start)
- [2. Features](#list-of-features)
  - [Help:](#viewing-help-help) `help`
  - [Add a class:](#adding-a-class-add-class) `add class`
  - [Delete a task:](#deleting-a-class-delete-class) `delete class`
  - [Add a cca:](#adding-a-cca-add-cca) `add cca`
  - [Delete a cca:](#deleting-a-cca-delete-cca) `delete cca`
  - [Add a test:](#adding-a-test-add-test) `add test`
  - [Delete a test:](#deleting-a-test-delete-test) `delete test`
  - [Toggle task done:](#toggling-task-done-done) `done`
  - [List schedule:](#list-out-schedule-list-event) `list event`
  - [Take a quiz:](#taking-a-quiz)`quiz`
  - [Add a quiz question:](#adding-a-quiz-question) `add quiz`
  - [Delete a quiz question:](#deleting-a-quiz-question) `delete quiz`
  - [List quiz questions:](#listing-out-all-quiz-questions) `list quiz`
  - [Find a task by keyword or date:](#find-tasks-find) `find`
  - [Exit the programme:](#exits-programme-bye) `bye`
- [3. FAQ](#faq)
- [4. Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java ```11``` or above installed.
2. Down the latest version of `Plan&score` from [here](https://github.com/AY2021S1-CS2113T-W12-4/tp/releases).
3. Run the program by entering `java -jar plan.jar` in a terminal.
4. You can exit the program by running `bye`

## List of Features 

### Viewing help: `help`
Shows all available commands to the user

Format: `help`

Example Output:

```
Hello! Here is a list of commands you can try:

	1. Add class: 'add class /n [name of class] /s [start date-time of class] /e [end date-time of class]'
	2. Delete class: 'delete class [class number]'

	3. Add cca: 'add cca /n [name of cca] /s [start date-time of cca] /e [end date-time of cca]'
	4. Delete cca: 'delete cca [cca number]'

	5. Add test: 'add test /n [name of test] /s [start date-time of test] /e [end date-time of test]'
	6. Delete test: 'delete test [test number]'

	7. Add tuition: 'add tuition /n [name of tuition] /s [start date-time of tuition] /e start date-time of tuition] /l [location of tuition]'
	8. Delete tuition: 'delete tuition [tuition number]'

	9. List events (class, test, cca, tuition): 'list'

	10. Set class as done: 'done class [class number]'
	11. Set test as done: 'done test [test number]'
	12. Set cca as done: 'done cca [cca number]'
	13. Set tuition as done: 'done tuition [tuition number]'

	14. Find relevant event(s): 'find [keyword(s)]'

	15. Add contact: 'add contact /sub [subject] /n [name of contact person] /hp [phone number] /e [email address]'
	16. Delete contact: 'delete contact [contact number]'
	17. List contact: 'list contact'

	18. Take Mathematics quiz: 'quiz [no. of questions (10, 20 or 30)]'
	19. Add quiz question: 'add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3] /o4 [option 4] /a [option answer] /exp [explanation]'
	20. Delete quiz question: 'delete quiz [question number]'
	21. List quiz questions: 'list quiz'

	22. Exit program: 'bye'


	NOTE:
	1. Please enter the date-time in the following format: YYYY-MM-DD [time in 24hr format]
	e.g. 2020-08-19 1300

	2. For command 20 (Add quiz question), the 'explanation' field is OPTIONAL
```

### Adding a class: `add class`
Adds a new class with a name, date and time

Format: `add class /n [name of class] /s [start date-time of class] /e [end date-time of class]`

* `[name of class]` can be in a natural language format and 
cannot contain '/' .  
* `[start date-time of class]`, `[end date-time of class]` must be in 
yyyy-mm-dd HHMM format with HHMM in 24-hour format.

Examples: `add class /n Math /s 2020-08-19 1300 /e 2020-08-19 1400`

Example Output: 

```
Got it. I've added this class:

    [CLASS] [NOT DONE] Math from 19th August 2020, 01:00 pm to 19th August 2020, 02:00 pm

Now you have 1 class in the list.
```

<br>

### Deleting a class: `delete class`
Deleting a class based on its index in the list

Format: `delete class [class number]`

* `[class number]` must be written in numerals. 

Examples: `delete class 1`

Example Output: 

`Noted. I've removed this class:`

    [CLASS] [NOT DONE] Math from 19th August 2020, 01:00 pm to 19th August 2020, 02:00 pm

`Now you have 0 classes in the list.`

<br>

### Adding a Cca: `add Cca`
Adds a new Cca with a name, date and time

Format: `add Cca /n [name of Cca] /s [start date-time of Cca] /e [end date-time of Cca]`

* `[name of Cca]` can be in a natural language format and 
cannot contain '/' .  
* `[start date-time of Cca]`, `[end date-time of Cca]` must be in 
yyyy-mm-dd HHMM format with HHMM in 24-hour format.

Examples: `add Cca /n Basketball training /s 2020-09-19 1900 /e 2020-09-19 2100`

Example Output: 
```
Got it. I've added this Cca:

    [CCA] [NOT DONE] Basketball training from 19th September 2020, 07:00 pm to 19th September 2020, 09:00 pm

Now you have 1 eventCca in the list.
```

<br>

### Deleting a cca: `delete cca`
Deleting a cca based on its index in the list

Format: `delete cca [cca number]`

* `[cca number]` must be written in numerals. 

Examples: `delete cca 1`

Example Output: 

```
Noted. I've removed this cca:

    [CCA] [NOT DONE] Basketball training from 19th September 2020, 07:00 pm to 19th September 2020, 09:00 pm

Now you have 0 ccas in the list.
```

<br>

### Adding a test: `add test`
Adds a new test with a name, date and time

Format: `add test /n [name of test] /s [start date-time of test] /e [end date-time of test]`

* `[name of test]` can be in a natural language format and 
cannot contain '/' .  
* `[start date-time of test]`, `[end date-time of test]` must be in 
yyyy-mm-dd HHMM format with HHMM in 24-hour format.

Examples: `add test /n Math test /s 2020-10-3 1300 /e 2020-10-3 1400`

Example Output:
 
```
Got it. I've added this test:

    [TEST] [NOT DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

Now you have 1 test in the list.
```

<br />
<br />

### Deleting a test: `delete test`
Deleting a test date based on its index in the list

Format: `delete test [test number]`

* `[test number]` must be written in numerals. 

Examples: `delete test 1`

Example Output: 

```
Noted. I've removed this test:

    [TEST] [NOT DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

Now you have 0 tests in the list.
```

<br />
<br />

### Setting an event as done: `done <class/cca/test>`
Setting a class, test or cca event as done based on its index in the list

Format:
* For class: `done class [class number]`
* For test: `done test [test number]`
* For cca: `done cca [cca number]`

* `[test number]` must be in numerals. 

Examples: `done test 1`

Example Output: 

```
Nice! I've marked this test as done:

    [TEST] [DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

Now you have 1 test in the list.
```

<br />
<br />

### List out schedule: `list event`
List out the entire schedule for classes, ccas and tests.
User can also choose to list today's schedule or schedule 
for the week.

Format: `list event`

Example Output: 

```
    Classes:
    1. [CLASS] [NOT DONE] Math from 19th Aug, 01:00 PM to 19th Aug , 02:00 PM 
    2. [CLASS] [NOT DONE] Science from 19th Aug, 03:00 PM to 19th Aug , 04:00 PM 
     
    CCA:
    1. [CCA] [NOT DONE] Frisbee from 19th Sep, 06:00 PM to 19th Sep, 07:00 PM
    2. [CCA] [NOT DONE] Basketball Camp from 21st Sep, 01:00 PM to 22nd Sep, 03:00 PM
    
    Test:
    1. [TEST] [NOT DONE] Math from 26th Sep, 11:00 AM to 26th Sep, 12:00 PM
```
<br />

Format: `list event today`

Example Output: For date, 2020-08-19

```   Classes:
        1. [CLASS] [NOT DONE] Math from 19th Aug, 01:00 PM to 19th Aug , 02:00 PM 
        2. [CLASS] [NOT DONE] Science from 19th Aug, 03:00 PM to 19th Aug , 04:00 PM 
         
        CCA:
        1. [CCA] [NOT DONE] Frisbee from 19th Aug, 06:00 PM to 19th Aug, 07:00 PM
        
        Test:
        1. [TEST] [NOT DONE] Math from 19th Aug, 11:00 AM to 19th Aug, 12:00 PM
```
<br />

Format: `list event week`

Example Output: For week, 2020-10-19 to 2020-10-25

```
TUESDAY:
1. [CCA] [NOT DONE] basketball from 20th Oct 2020 , 01:00 pm to 21st Oct 2020 , 02:00 pm
WEDNESDAY:
1. [TEST] [NOT DONE] eng from 21st Oct 2020 , 02:00 pm to 21st Oct 2020 , 03:00 pm   
```

<br>
<br>

### Taking a Quiz: `quiz`
Taking a Mathematics quiz with 10, 20 or 30 questions as stated by the user.

Format: `quiz <number of questions>`

Examples: `quiz 10`

Example Output: 

```
Question 1: What is 1 + 1?
(1) 0
(2) 1
(3) 2
(4) 3

... 9 more questions in the same format once user enters their answer for each question ...
```
<br>
<br>

### Adding a Quiz Question: `add quiz`
Adding a quiz question to the quiz list.

Format: `'add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3] /o4 [option 4] /a [option answer] 
/exp [optional explanation]`

Examples: `add quiz /q What is 1+1? /o1 0 /o2 1 /o3 2 /o4 3 /a 3`

Example Output: 

```
Quiz question added!
```

<br />
<br />

### Deleting a Quiz Question: `delete quiz`
Deleting a quiz question from the quiz list.

Format: `'delete quiz [quiz question]`

Examples: `delete quiz 11`

Example Output: 

```
Noted. I've removed this quiz question:
What is 1+1?
(1) 0
(2) 1
(3) 2
(4) 3

Now you have 10 quizzes in the quiz list.
```

<br />
<br />

### Listing out All Quiz Questions

### Exits Programme: `bye`
Exits the programme once the user have finished
using it.

Format: `bye`

Example Output:

```
BYE BYE! SEE YOU NEXT TIME! :3
```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Shift the event.txt file to the same directory as the java file.

## Command Summary

Action | Format | Examples
-------|--------|--------
help|`help`
add|`add <category> /n <description> /s <start-date-time> /e <end date-time>`|`add class /n Math /s 2020-09-06 1300 /e 2020-09-06 1400`
delete|`delete <category> <item number>`|`delete class 1`, `delete test 1`, `delete cca 1`
done|`done <category> <item number>`|`done class 1`, `done test 1`, `done cca 1`
list|`list event [optional] <today/week>`
bye|`bye`
