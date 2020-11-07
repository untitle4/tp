# User Guide
- [1. Introduction](#introduction)
- [2. About this User Guide](#about-this-user-guide)
- [3. How to user this User Guide](#how-to-use-this-user-guide)
- [4. Quick Start](#quick-start)
- [5. Initialisation](#initialisation)
- [6. Commands](#commands)
    - [Command Format](#command-format)
    - [Category Types](#category-types)
  - [Help:](#viewing-help-help) `help`
  - [PLAN component of Plan&Score](#plan-component-of-planscore)
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
    - [List schedule:](#listing-out-schedule-list-event-<today/[date]/week/nextweek>) `list event <today/[date]/week/nextweek>`
    - [Find event:](#finding-an-event-find-event) `find event`
  - [SCORE component of Plan&Score:](#score-component-of-planscore)
    - [Take a quiz:](#taking-a-quiz-quiz)`quiz`
    - [Add a quiz question:](#adding-a-quiz-question-add-quiz) `add quiz`
    - [Delete a quiz question:](#deleting-a-quiz-question-delete-quiz) `delete quiz`
    - [List quiz questions:](#listing-out-all-quiz-questions-list-quiz) `list quiz`
    - [Find a quiz by keyword:](#finding-a-quiz-find-quiz) `find quiz`
    - [Search for former incorrect quiz questions:](#searching-for-former-incorrect-quiz-questions-quiz-record) `quiz record`
  - [Contact component of Plan&Score:](#contact-component-of-planscore)
    - [Add a contact:](#adding-a-contact-add-contact) `add contact`
    - [Delete a contact:](#deleting-a-contact-delete-contact) `delete contact`
    - [List a contact:](#listing-out-contacts-list-contact) `list contact`
    - [Find a contact:](#finding-a-contact-find-contact) `find contact`
  - [Exit the program:](#exits-program-bye) `bye`
- [7. Saving Data](#saving-data)
- [8. FAQ](#faq)
- [9. Troubleshooting](#troubleshooting)
- [10. Command Summary](#command-summary)

## Introduction

Plan&Score is an application consisting of 2 key components:

1. Event scheduler
1. Quiz component

and a sub-component:

1. Contact list


These components aim to tackle the issue of poor planning and revision most Primary 6 students in Singapore face.

This application uses a command line interface, meaning that you operate the application by typing commands into a Command Box.

![initialization](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/initialization.png)

Figure 1. The graphical user interface for Plan&Score

## About this User Guide
This User Guide serves to provide an in-depth explanation of Plan&Score’s usage and functionalities, as well as troubleshooting steps you can take if problems are encountered.

## How to use this User Guide
To navigate to the contents of your desired feature, simply click on the hyperlinks provided in the contents page above.

The highlights and symbols used in this document are as follow:

![information](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/information.png)

![warning](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/warning.png)

`Add class`  A grey highlight is used to denote text to be entered into the  
           command line.


## Quick Start

1. Ensure that you have Java `11` or above installed.
2. Download the latest version of `Plan&Score` from [here](https://github.com/AY2021S1-CS2113T-W12-4/tp/releases).
3. Run the program by entering `java -jar plan.jar` in your terminal.
4. You can exit the program by running `bye`.

##Initialisation
When you first open Plan&Score, you will be greeted by a welcome message as well as 
a prompt asking for your name. This is as shown below.

![introduction](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/intro_screen.png)

Type in your name and press 'Enter' to proceed.

![keyname](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/name.png)

Afterwards, you will be prompt to enter the number of recommended hours that 
you wish to accomplish per day. Key in your desired number of hours 
and press 'Enter' to proceed. 

![keyhours](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/hours.png)

This is the end of the initialisation process. You can now enjoy the rest of Plan&Score's
amazing features!

![finishintro](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/finish_intro.png)

## Commands

#### Command Format
* A command can contain multiple parameters.
For example:
  * A `test` contains a `description`, `start` and `end`, where
  * `description`, `start` and `end` are parameters that make up a `test`.


#### Category Types
* Plan&Score has the following categories types: 

  1. `event`
      1. `class`
      1. `cca`
      1. `test`
      1. `tuition`

  1. `contact`
  1. `quiz`
* The category type often follows after the command word (E.g `class` follows after `add` to form `add class`).

### Viewing help: `help`
Shows all available commands that you can use

Firstly, type ‘help’ in the command line as seen below, and press ‘Enter’ to execute it.

![help1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/help1.png)

The output containing all the different commands is seen in the console.

![help2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/help_command_1.png)
![help2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/help_command_2.png)

### Plan component of Plan&Score
It allows you to plan and track the following category types:
* Class
* Cca
* Test
* Tuitions

![indexinfo](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/indexinfo.png)

#### Category: Class
##### Adding a class: `add class`
Allows you to add a new class with a name, date and time.

Firstly, type `add` in the command line as seen below, followed by the category type which is the class. Afterwards, key in the description of the class as well as its start and end date and time. Press `Enter` to execute it.

![addclass1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/addclass1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![addclass2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_class_2.png)

<br />
WARNING:
<br />

* `[name of class]` can be in a natural language format and
cannot contain '/'. 

* `[start date-time of class]` and `[end date-time of class]` must be in yyyy-mm-dd HHMM format with HHMM in 24-hour format.

* Parameters `/n`, `/s`, `/e` cannot be swapped.


![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![addclasserror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/class_error.png)


<br />

##### Deleting a class: `delete class`
Allows you to delete a class based on its index in the list.

Firstly, type ‘delete’ in the command line as seen below, followed by the category type which is the class. Afterwards, key in the corresponding index of the class you would like to delete. Press ‘Enter’ to execute it.
![deleteclass1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_class_1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.
![deleteclass2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_class_2.png)

<br />
WARNING:
<br />

* `[class number]` must be written in numerals.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![deleteclasserror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_class_error.png)

<br>

#### Category: Cca
##### Adding a cca: `add cca`
Allow you to add a new cca with a name, date and time.

Firstly, type ‘add’ in the command line as seen below, followed by the category type which is the cca. Afterwards, key in the description of the cca as well as its start and end date and time. Press ‘Enter’ to execute it.

![addCca1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_cca_1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![addCca2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_cca_2.png)

<br />
WARNING:
<br />

* `[name of cca]` can be in a natural language format and
cannot contain '/'. 

* `[start date-time of cca]` and `[end date-time of cca]` must be in yyyy-mm-dd HHMM format with HHMM in 24-hour format.

* Parameters `/n`, `/s`, `/e` cannot be swapped.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![addccaerror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_cca_error.png)

<br />

##### Deleting a cca: `delete cca`
Allows you to delete a cca based on its index in the list.

Firstly, type ‘delete’ in the command line as seen below, followed by the category type which is the cca. Afterwards, key in the corresponding index of the cca you would like to delete. Press ‘Enter’ to execute it.

![deleteCca1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_cca_1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![deleteCca2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_cca_2.png)

<br />
WARNING:
<br />

* `[cca number]` must be written in numerals.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![deleteccaerror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_cca_error.png)

<br />

#### Category: Test
##### Adding a test: `add test`
Allows you to add a new test with a name, date and time.

Firstly, type ‘add’ in the command line as seen below, followed by the category type which is the test. Afterwards, key in the description of the test as well as its start and end date and time. Press ‘Enter’ to execute it.

![addtest1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_test_1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![addtest2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_test_2.png)

<br />
WARNING:
<br />

* `[name of test]` can be in a natural language format and
cannot contain '/'. 

* `[start date-time of test]` and `[end date-time of test]` must be in yyyy-mm-dd HHMM format with HHMM in 24-hour format.

* Parameters `/n`, `/s`, `/e` cannot be swapped.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![addtesterror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_test_error.png)

<br />

##### Deleting a test: `delete test`
Allows you to delete a test event based on its index in the list.

Firstly, type ‘delete’ in the command line as seen below, followed by the category type which is the test. Afterwards, key in the corresponding index of the test you would like to delete. Press ‘Enter’ to execute it.

![deletetest1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_test_1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![deletetest2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_test_2.png)

<br />
WARNING:
<br />

* `[test number]` must be written in numerals.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![deletetesterror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_test_error.png)

<br />

#### Category: Tuition
##### Adding a tuition: `add tuition`
Allows you to add a new test with a name, date, time, location.

Firstly, type ‘add’ in the command line as seen below, followed by the category type which is the tuition. Afterwards, key in the description of the tuition, its start and end date and time as well as its location. Press ‘Enter’ to execute it.

![addtuition1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_tuition_1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![addtuition2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_tuition_2.png)

<br />
WARNING:
<br />

* `[name of tuition]` and `[location of tuition]` can be in a natural language format and
cannot contain '/'. 

* `[start date-time of tuition]` and `[end date-time of tuition]` must be in yyyy-mm-dd HHMM format with HHMM in 24-hour format.

* Parameters `/n`, `/s`, `/e`, `/l` cannot be swapped.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![addtuitionerror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_tuition_error.png)

<br />

##### Deleting a tuition: `delete tuition`
Allows you to delete a tuition based on its index in the list.

Firstly, type ‘delete’ in the command line as seen below, followed by the category type which is the tuition. Afterwards, key in the corresponding index of the tuition you would like to delete. Press ‘Enter’ to execute it.

![deletetuition1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_tuition_1.png)

The output is seen in the console. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![deletetuition2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_tuition_2.png)

<br />
WARNING:
<br />

* `[tuition number]` must be written in numerals.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![deletetuitionerror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_tuition_error.png)

<br />

#### Listing out schedule: `list event <today/[date]/week/nextweek>`

Allows you to list out the entire schedule for classes, ccas and tests.
You can also choose to list today's schedule, the schedule
for the week or the schedule for a specific date.

##### List event: `list event`
Firstly, to list out all events, type ‘list event’ in the command line, as seen below. Press ‘Enter’ to execute it.

![listevent1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_1.png)

The output is seen in the console. The list is arranged by their respective categories. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![listevent2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_2.png)

<br />

##### List event today: `list event today`

Secondly, to list out all events today, type ‘list event today’ in the command line, as seen below. Press ‘Enter’ to execute it.

![listeventtoday1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_today_1.png)

The output is seen in the console. The list is arranged by their respective categories. The date and time is converted to a more readable form, enabling you to read it more pleasantly. The index of the event is corresponding to its index in the entire list.

![listeventtoday2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_today_2.png)

<br />

##### List event week: `list event week`

Thirdly, to list out all events in the current week, type ‘list event week’ in the command line, as seen below. Press ‘Enter’ to execute it.

![listeventweek1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_week_1.png)

The output is seen in the console. It is displayed in a calendar format for easier readability. The events are listed according to time. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![listeventweek2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_week_2.png)

<br />

##### List event next week: `list event nextweek`

Fourthly, to list out all events in the next week, type ‘list event nextweek’ in the command line, as seen below. Press ‘Enter’ to execute it.

![listeventnextweek1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_nextweek_1.png)

The output is seen in the console. It is displayed in a calendar format for easier readability. The events are listed according to time. The date and time is converted to a more readable form, enabling you to read it more pleasantly.

![listeventnextweek2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_nextweek_2.png)

<br />

##### List event date: `list event [desired date]`

Lastly, to list out all events on a particular date, type ‘list event [desired date]’ in the command line, as seen below. Press ‘Enter’ to execute it.

![listeventdate1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_date_1.png)

The output is seen in the console. The list is arranged by their respective categories. The date and time is converted to a more readable form, enabling you to read it more pleasantly. The index of the event is corresponding to its index in the entire list.

![listeventdate2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_date_2.png)

<br />

#### Finding an event: `find event`
Allows you to look for classes, ccas and tests and tuitions that match your given keyphrase.

In the case when you have many events in your schedule and you need to find a specific event to check the date. You can use the `find event` command to look for the event you want by entering keywords related to it.

Let's say we need to find out when my vocabulary test is on.
We can first type `find event vocabulary` into the command box, and press Enter to execute it.

![find1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find1.JPG)

The output can then be seen in the console.

![find2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find2.JPG)

And we can find the date we are looking for over here.

![find3](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find3.JPG)

<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find%20note.jpg" width="500"  alt="findnote"/>

<br/>
<br/>

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
Allows you to add a Mathematics multiple-choice quiz question to the quiz list.

Note that the `/e (explanation)` is an optional parameter. You can add a quiz question with or without an explanation.

Format: `add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3] /o4 [option 4] /a [option answer]
/e (explanation)`

Examples: `add quiz /q What is 1+1? /o1 0 /o2 1 /o3 2 /o4 3 /a 3`

Example Output:

```
    Quiz question added!
```

<br />


#### Deleting a Quiz Question: `delete quiz`
Allows you to delete a quiz question from the quiz list.

Format: `delete quiz [quiz question]`

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

#### Listing out All Quiz Questions: `list quiz`
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

Format: `find quiz [keyword(s)]`

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

Format: `quiz record`

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

### Contact component of Plan&Score

Note that our current implementation of contact does not store the contact details in any file storage.
This will come in version 2.1.

#### Adding a contact `add contact`
Allows you to add a teacher's contact details to the contact list.

Format: `add contact /s [subject] /n [name of contact person] /p [phone number] /e [email address]`

Examples: `add contact /s math /n thomas /p 91779977 /e thomas@gmail.com`

Example Output:

```
    Got it. I've added this contact: 
    math teacher: thomas
    Phone number: 91779977
    email address: thomas@gmail.com
    
    Now you have 1 contact in your list.
```

#### Deleting a contact `delete contact`
Allows you to delete a contact from the contact list.

Format: `delete contact [contact list number]`

Examples: `delete contact 1`

Example Output:

```
    Noted. I've removed this contact: 
    math teacher: thomas
    Phone number: 91779977
    email address: thomas@gmail.com
    
    Now you have 0 contact in your list.
```

#### Listing out contacts `list contact`
Allows you to list out all the contacts in the contact list

Format: `list contact`

Example Output:

```
    Contact 1:
    math teacher: thomas
    Phone number: 91779977
    email address: thomas@gmail.com
```

#### Finding a contact `find contact`
Look for contacts that match the given keywords you want.

Format: `find contact [keyword(s)]`

Examples: `find contact math`

Example Output:

```
    math teacher: thomas
    Phone number: 91779977
    email address: thomas@gmail.com
```


### Exits Program: `bye`
Exits the program once you have finished using it.

Type the command and press 'Enter' to execute it.

![bye1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/bye_1.png)

The output is shown in the console. It contains a simple goodbye message.

![bye2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/bye_2.png)

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
add|`add [category] /n [description] /s [start-date-time] /e [end date-time]` `add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3] /o4 [option 4] /a [option answer] /e (explanation)` `add contact /s [subject] /n [name of contact person] /p [phone number] /e [email address]`|`add class /n Math /s 2020-09-06 1300 /e 2020-09-06 1400`
delete|`delete [category] [item number]`|`delete class 1`, `delete test 1`, `delete cca 1`
quiz|`quiz [no of questions]`|`quiz 15`
list| `list quiz` `list event (date)` `list contact`|`list event (<date/today/week>)`
Find|`find event [keyword(s)]` `find quiz [keyword(s)]` `find contact [keyword(s)]`|`find event English`
bye|`bye`

