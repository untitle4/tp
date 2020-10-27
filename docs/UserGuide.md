# User Guide
- [1. Introduction](#introduction)
- [2. About this User Guide](#about-this-user-guide)
- [3. How to user this User Guide](#how-to-use-user-guide)
- [4. Quick Start](#quick-start)
- [5. Commands](#commands)
    - [Command Format](#command-format)
    - [Category Types](#category-types)
  - [Help:](#viewing-help-help) `help`
  - [PLAN component of Plan&Score](#plan-component-of-plan&score)
    - [Class Category](#category-class)
      - [Add a class:](#adding-a-class-add-class) `add class`
      - [Delete a task:](#deleting-a-class-delete-class) `delete class`
    - [Cca Category](#category-cca)
      - [Add a cca:](#adding-a-cca-add-cca) `add cca`
      - [Delete a cca:](#deleting-a-cca-delete-cca) `delete cca`
    - [Test Category](#category-test)
      - [Add a test:](#adding-a-test-add-test) `add test`
      - [Delete a test:](#deleting-a-test-delete-test) `delete test`
    - [Tuition Category](#category-tuition)
      - [Add a tuition:](#adding-a-tuition-add-tuition) `add tuition`
      - [Delete a tuition:](#deleting-a-tuition-delete-tuition) `delete tuition`
    - [List schedule:](#list-out-schedule-list-event) `list event`
    - [Find event:](#finding-an-event-find-event) `list event`
  - [SCORE component of Plan&Score:](#score-component-of-plan&score)
    - [Take a quiz:](#taking-a-quiz-quiz)`quiz`
    - [Add a quiz question:](#adding-a-quiz-question-add-quiz) `add quiz`
    - [Delete a quiz question:](#deleting-a-quiz-question-delete-quiz) `delete quiz`
    - [List quiz questions:](#listing-out-all-quiz-questions-list-quiz) `list quiz`
    - [Find a quiz by keyword:](#finding-a-quiz-find-quiz) `find`
    - [Search for former incorrect quiz questions:](#searching-for-former-incorrect-quiz-questions-quiz-record) `quiz record`
  - [Exit the programme:](#exits-programme-bye) `bye`
- [6. Saving Data](#saving-data)
- [7. FAQ](#faq)
- [8. Troubleshooting](#troubleshooting)
- [9. Command Summary](#command-summary)

## Introduction

Plan&Score is an application consisting of 2 key components:

Event scheduler
Quiz component

and a sub-component:

Contact list


These components aim to tackle the issue of poor planning and revision most Primary 6 students in Singapore face.

This application uses a command line interface, meaning that you operate the application by typing commands into a Command Box.


Figure 1. The graphical user interface for Plan&Score

## About this User Guide
This User Guide serves to provide an in-depth explanation of Plan&Score’s usage and functionalities, as well as troubleshooting steps you can take if problems are encountered.

## How to use this User Guide
To navigate to the contents of your desired feature, simply click on the hyperlinks provided in the contents page above.

The highlights and symbols used in this document are as follow:




Add class  A grey highlight is used to denote text to be entered into the  
           command line.


## Quick Start

1. Ensure that you have Java `11` or above installed.
2. Download the latest version of `Plan&Score` from [here](https://github.com/AY2021S1-CS2113T-W12-4/tp/releases).
3. Run the program by entering `java -jar plan.jar` in your terminal.
4. You can exit the program by running `bye`.

## Commands

#### Command Format
* A command can contain multiple parameters.
For example:
  * A `test` contains a `description`, `start` and `end`, where
  * `description`, `start` and `end` are parameters that make up a `test`.


#### Category Types
* Plan&Score has the following categories types: 

  1. `event`
      1.1. `class`
      1.2. `cca`
      1.3. `test`
      1.4. `tuition`

  2. `contact`
  3. `quiz`
* The category type often follows after the command word (E.g `class` follows after `add` to form `add class`).

### Viewing help: `help`
Shows all available commands that you can use

Firstly, type ‘help’ in the command line as seen below, and press ‘Enter’ to execute it.




The output containing all the different commands is seen in the console.


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

	10. Find relevant event(s): 'find [keyword(s)]'

	11. Add contact: 'add contact /sub [subject] /n [name of contact person] /hp [phone number] /e [email address]'
	12. Delete contact: 'delete contact [contact number]'
	13. List contact: 'list contact'

	14. Take Mathematics quiz: 'quiz [no. of questions]'
	15. Add quiz question: 'add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3] /o4 [option 4] /a [option answer] /exp (explanation)'
	16. Delete quiz question: 'delete quiz [question number]'
	17. List quiz questions: 'list quiz'
	18. Find quiz questions: 'find quiz [keyword(s)]'
	19. Display former incorrect quiz question records: 'quiz record'

	20. Exit program: 'bye'


	NOTE:
	1. Please enter the date-time in the following format: YYYY-MM-DD [time in 24hr format]
	e.g. 2020-08-19 1300

	2. For command 15 (Add quiz question), the 'explanation' field is OPTIONAL

```

### Plan component of Plan&Score
It allows you to plan and track the following category types:
Class
Cca
Test
Tuitions



#### Category: Class
##### Adding a class: `add class`
Allows you to add a new class with a name, date and time.

Firstly, type ‘add’ in the command line as seen below, followed by the category type which is the class. Afterwards, key in the description of the class as well as its start and end date and time. Press ‘Enter’ to execute it.



The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.



Format: `add class /n [name of class] /s [start date-time of class] /e [end date-time of class]`

* `[name of class]` can be in a natural language format and
cannot contain '/'. 

* `[start date-time of class]` and `[end date-time of class]` must be in

yyyy-mm-dd HHMM format with HHMM in 24-hour format.




Examples: `add class /n Math /s 2020-08-19 1300 /e 2020-08-19 1400`

Example Output:

```
Got it. I've added this class:

   [CLASS] Math from 19th August 2020, 01:00 pm to 19th August 2020, 02:00 pm

Now you have 1 class in the list.
```

<br>

##### Deleting a class: `delete class`
Allows you to delete a class based on its index in the list.



Firstly, type ‘delete’ in the command line as seen below, followed by the category type which is the class. Afterwards, key in the corresponding index of the class you would like to delete. Press ‘Enter’ to execute it.


The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.



Format: `delete class [class number]`

* `[class number]` must be written in numerals.

Examples: `delete class 1`

Example Output:

`Noted. I've removed this class:`

   [CLASS] Math from 19th August 2020, 01:00 pm to 19th August 2020, 02:00 pm

`Now you have 0 classes in the list.`

<br>

#### Category: Cca
##### Adding a cca: `add cca`
Allow you to add a new cca with a name, date and time.

Firstly, type ‘add’ in the command line as seen below, followed by the category type which is the cca. Afterwards, key in the description of the cca as well as its start and end date and time. Press ‘Enter’ to execute it.

Format: `add cca /n [name of cca] /s [start date-time of cca] /e [end date-time of cca]`



Examples: `add cca /n Basketball training /s 2020-09-19 1900 /e 2020-09-19 2100`

Example Output:
```
Got it. I've added this cca:

   [CCA] Basketball training from 19th September 2020, 07:00 pm to 19th September 2020, 09:00 pm

Now you have 1 cca in the list.
```

<br>

##### Deleting a cca: `delete cca`
Allows you to delete a cca based on its index in the list.

Firstly, type ‘delete’ in the command line as seen below, followed by the category type which is the cca. Afterwards, key in the corresponding index of the cca you would like to delete. Press ‘Enter’ to execute it.



Format: `delete cca [cca number]`

* `[cca number]` must be written in numerals.

Examples: `delete cca 1`

Example Output:

```
Noted. I've removed this cca:

   [CCA] Basketball training from 19th September 2020, 07:00 pm to 19th September 2020, 09:00 pm

Now you have 0 ccas in the list.
```

<br>

#### Category: Test
##### Adding a test: `add test`
Allows you to add a new test with a name, date and time.

Format: `add test /n [name of test] /s [start date-time of test] /e [end date-time of test]`



Examples: `add test /n Math test /s 2020-10-3 1300 /e 2020-10-3 1400`

Example Output:

```
Got it. I've added this test:

   [TEST] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

Now you have 1 test in the list.
```

<br>

##### Deleting a test: `delete test`
Allows you to delete a test event based on its index in the list.



Format: `delete test [test number]`

* `[test number]` must be written in numerals.

Examples: `delete test 1`

Example Output:

```
Noted. I've removed this test:

   [TEST] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

Now you have 0 tests in the list.
```

<br>

#### Category: Tuition
##### Adding a tuition: `add tuition`
Allows you to add a new test with a name, date, time, location.

Format: `add tuition /n [name of tuition] /s [start date-time of test] /e [end date-time of test] /l [location of tuition]`



Examples: `add test /n Math test /s 2020-10-3 1300 /e 2020-10-3 1400 /l home`

Example Output:

```
Got it. I've added this test:

   [TUITION] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm at home

Now you have 1 tuition in the list.
```

<br>

##### Deleting a tuition: `delete tuition`
Allows you to delete a tuition based on its index in the list.



Format: `delete tuition [tuition number]`

* `[tuition number]` must be written in numerals.

Examples: `delete tuition 1`

Example Output:

```
Noted. I've removed this tuition:

   [TUITION] Math tuition from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm at home

Now you have 0 tuitions in the list.
```

<br>

#### Listing out schedule: `list event`

Allows you to list out the entire schedule for classes, ccas and tests.
You can also choose to list today's schedule, the schedule
for the week or the schedule for a specific date.

Format: `list event`

Example Output:

```
   Classes:
   1. [CLASS] Math from 19th Aug, 01:00 PM to 19th Aug , 02:00 PM
   2. [CLASS] Science from 19th Aug, 03:00 PM to 19th Aug , 04:00 PM
   
   CCA:
   1. [CCA] Frisbee from 19th Sep, 06:00 PM to 19th Sep, 07:00 PM
   2. [CCA] Basketball Camp from 21st Sep, 01:00 PM to 22nd Sep, 03:00 PM
  
   Test:
   1. [TEST] Math from 26th Sep, 11:00 AM to 26th Sep, 12:00 PM

```
<br />

Format: `list event today`

Example Output: For date, 2020-08-19

```   Classes:
       1. [CLASS] Math from 19th Aug, 01:00 PM to 19th Aug , 02:00 PM
       2. [CLASS] Science from 19th Aug, 03:00 PM to 19th Aug , 04:00 PM
       
       CCA:
       1. [CCA] Frisbee from 19th Aug, 06:00 PM to 19th Aug, 07:00 PM
      
       Test:
       1. [TEST] Math from 19th Aug, 11:00 AM to 19th Aug, 12:00 PM
```
<br />

Format: `list event week`

Example Output: For a particular week, 2020-10-19 to 2020-10-25

```
TUESDAY:
1. [CCA] Basketball from 20th Oct 2020 , 01:00 pm to 21st Oct 2020 , 02:00 pm
WEDNESDAY:
1. [TEST] English from 21st Oct 2020 , 02:00 pm to 21st Oct 2020 , 03:00 pm  
```

<br>

Format: `list event [date]`

Example Output: For date, 2020-08-19

```   Classes:
       1. [CLASS] Math from 19th Aug, 01:00 PM to 19th Aug , 02:00 PM
       2. [CLASS] Science from 19th Aug, 03:00 PM to 19th Aug , 04:00 PM
       
       CCA:
       1. [CCA] Frisbee from 19th Aug, 06:00 PM to 19th Aug, 07:00 PM
      
       Test:
       1. [TEST] Math from 19th Aug, 11:00 AM to 19th Aug, 12:00 PM
```
<br />

#### Finding an event: `find event`
Allows you to look for classes, ccas and tests and tuitions that match your given keyphrase.

In the case when you have many events in your schedule and you need to find a specific event to check the date. You can use the `find event` to look for the event you want by entering keywords related to it.

Let's say we need to find out when my vocabulary test is on.
We can first type `find event vocabulary` into the command box, and press Enter to execute it.



The output can then be seen in the console.



And we can find the date we are looking for over here.




Format: `find event [KEYPHRASE]`

Examples: `find event math`

Example Output:
```
   [CLASS] Math from 19th Aug, 01:00 PM to 19th Aug , 02:00 PM
   [TEST] Math from 26th Sep, 11:00 AM to 26th Sep, 12:00 PM
```


### Score component of Plan&Score
Enables the following category type:
quiz

The quiz feature enables you to spend the extra time resulting from your productive scheduling to hone your Mathematics for your upcoming PSLE.

#### Taking a Quiz: `quiz`
Taking a Mathematics quiz with any number of questions you want, ranging from just 1 question to the total number of questions in the quiz list.

Format: `quiz [number of questions]`



Examples: `quiz 15`

Example Output:

```
Question 1: What is 1 + 1?
(1) 0
(2) 1
(3) 2
(4) 3

... 14 more questions in the same format once you enter your answer for each question ...
```
<br>

#### Adding a Quiz Question: `add quiz`
Allows you to add a Mathematic multiple-choice quiz question to the quiz list.

Note that the `/e (explanation)` is an optional parameter. You can add a quiz question with or without an explanation.

Format: `'add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3] /o4 [option 4] /a [option answer]
/e (explanation)`


[photo of error message]

[photo of command]
[photo of command output]


Examples: `add quiz /q What is 1+1? /o1 0 /o2 1 /o3 2 /o4 3 /a 3`
[photo 2]
Example Output:

```
Quiz question added!
```

<br />


#### Deleting a Quiz Question: `delete quiz`
Allows you to delete a quiz question from the quiz list.

Format: `'delete quiz [quiz question]`

Examples: `delete quiz 11`

Expected output:
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

#### Listing out All Quiz Questions: ‘list quiz’
Allows you to list out all the questions in the quiz list.

Format: `list quiz`

Example Output:

```
Here are the questions in your quiz list:
Question 1:
1+1=?

(1) 1
(2) 2
(3) 3
(4) 4

Explanation: one plus one must be equal to two!


Question 2:
1+1=?

(1) 1
(2) 2
(3) 3
(4) 4

```


#### Finding a quiz: `find quiz`
Look for quizzes that match the given keyword(s) that you want.

Note that searches are case-insensitive and user can provide multiple words to compare with

Format: `find quiz [KEYWORDS]`

Examples: `find quiz 1+1`

Example Output:
```
Question 1:
1+1=?

(1) 1
(2) 2
(3) 3
(4) 4

Explanation: one plus one must be equal to two!


Question 2:
1+1=?

(1) 1
(2) 2
(3) 3
(4) 4

```

#### Searching for former incorrect quiz questions: `quiz record`
Allows you to list out the incorrect quizzes in your last quiz attempt.

Format: `record quiz`

Example Output:
```
Here are the incorrect quizzes in your last quiz attempt:

1+1=?

(1) 1
(2) 2
(3) 3
(4) 4


Your answer: (1)
Correct answer: (2)

```

### Exits Programme: `bye`
Exits the programme once you have finished using it.

Format: `bye`

Example Output:

```
BYE BYE! SEE YOU NEXT TIME! :3
```

## Saving Data
Plan&Score saves all your data automatically after every command. There is no need to save manually.

<br />

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: We recommend you to transfer the entire folder containing plan.jar and the `data` directory. Then, follow instructions given in our `Quick Start` section to set up Plan&Score.

## Troubleshooting

**Q**: Plan&Score is unable to start. How can I fix this?

**A**: Your data files might have been corrupted. To fix this, you can attempt the following steps:

**We recommend adult supervision for this process.**

1. Locate the `data` directory. It should be in the same directory as where Plan&Score is located.
1. Open the `events.txt` file.
1. Check if any of the data are violating our decoding formats:
  * For class/cca/test, the format should be: `[IDENTIFIER]|[true/false]|[DESCRIPTION]|[DATE IN YYYY-MM-DD HHMM]|[DATE IN YYYY-MM-DD HHMM]`
  * For tuition, the format should be: `[IDENTIFIER]|[true/false]|[DESCRIPTION]|[DATE IN YYYY-MM-DD HHMM]|[DATE IN YYYY-MM-DD HHMM]|location`
1. Edit the file to the correct formats shown above
1. Launch Plan&Score
1. Confirm that Plan&Score runs without any error

In the unfortunate event where the error persists, please reset Plan&Score to factory settings.
We recommend keeping a duplicate of the contents in `events.txt` to assist with the re-adding of events.
1. Ensure you have duplicated `events.txt`
1. Delete `events.txt`
1. Launch Plan&Score
1. Confirm that Plan&Score runs without any error
1. `events.txt` should appear in the `data` directory
1. Copy any unaffected events from the duplicated file in Step 1
1. Re-add affected events through the command line interface

## Command Summary


Action | Format | Examples
-------|--------|--------
help|`help`
add|`add <category> /n <description> /s <start-date-time> /e <end date-time>`|`add class /n Math /s 2020-09-06 1300 /e 2020-09-06 1400`
delete|`delete <category> <item number>`|`delete class 1`, `delete test 1`, `delete cca 1`
quiz|`quiz <no of questions>`|`quiz 15`
list| `list <category>` `list event (date)`|`list event (<date/today/week>)`
Find|`find <keyword>`|`find English`
bye|`bye`

