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
    - [Set Recommended Hours](#set-recommended-hours-set-hours) `set hours`
    - [Class Category](#category-class)
      - [Add a class:](#adding-a-class-add-class) `add class`
      - [Delete a class:](#deleting-a-class-delete-class) `delete class`
    - [Cca Category](#category-cca)
      - [Add a cca:](#adding-a-cca-add-cca) `add cca`
      - [Delete a cca:](#deleting-a-cca-delete-cca) `delete cca`
    - [Test Category](#category-test)
      - [Add a test:](#adding-a-test-add-test) `add test`
      - [Delete a test:](#deleting-a-test-delete-test) `delete test`
    - [Tuition Category](#category-tuition)
      - [Add a tuition:](#adding-a-tuition-add-tuition) `add tuition`
      - [Delete a tuition:](#deleting-a-tuition-delete-tuition) `delete tuition`
    - [List schedule:](#listing-out-schedule-list-event-todaydateweeknextweek>) `list event <today/[date]/week/nextweek>`
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

<!-- @@author Aliciaho -->
## Initialisation

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

<!-- @@author Aliciaho-->

## Commands

#### Command Format
* A command can contain multiple parameters.
    * A `test` contains a `description`, `start` and `end`, where `description`, `start` and `end` are parameters that make up a `test`.
* Command parameters cannot be swapped.
    * `add test /n [name of test] /s [start date-time of test] /e [end date-time of test]` will work but `add test /s [start date-time of test] /e [end date-time of test] /n [name of test]` will give an error
* Extra spaces in the command will be sanitized.
    * `   list     event      ` will be sanitized to `list event`
* Command will be converted as lowercase.
    * `LIST EVENT` will be modified to `list event`
* Commands with extra parameters provided will give an error
    * `delete class 1 2 3 abc` will be give an error.


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

<!-- @@author Aliciaho-->
#### Set Recommended Hours: `set hours`
Allows you to change the number of recommended hours you would want to have per day.

Firstly, type `set hours` in the command line as seen below. Press 'Enter' to execute it.

![sethours1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/set_hours_1.png)

Afterwards, Plan&Score will prompt you to enter the number of recommended hours that 
you wish to accomplish per day. 

![sethours2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/set_hours_2.png)

Key in your new desired number of hours and press 'Enter' to proceed. 
The output is then seen in the console as shown below.

![sethours3](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/set_hours_3.png)

<br />
WARNING:
<br />

* `[number of hours]` must be written in numerals.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![sethourserror](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/set_hours_error.png)
<!-- @@author Aliciaho-->

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

<!-- @@author Aliciaho-->
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
<!-- @@author Aliciaho-->

<!-- @@author durianpancakes -->
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

<br /
<!-- @@author -->

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

<!-- @@author Aliciaho-->
##### List event today: `list event today`

Secondly, to list out all events today, type ‘list event today’ in the command line, as seen below. Press ‘Enter’ to execute it.

![listeventtoday1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_today_1.png)

The output is seen in the console. The list is arranged by their respective categories. The date and time is converted to a more readable form, enabling you to read it more pleasantly. The index of the event is corresponding to its index in the entire list.

![listeventtoday2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_today_2.png)

<br />
<!-- @@author Aliciaho-->

<!-- @@author durianpancakes -->
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
<!-- @@author -->

<!-- @@author Aliciaho-->
##### List event date: `list event [desired date]`

Lastly, to list out all events on a particular date, type ‘list event [desired date]’ in the command line, as seen below. Press ‘Enter’ to execute it.

![listeventdate1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_date_1.png)

The output is seen in the console. The list is arranged by their respective categories. The date and time is converted to a more readable form, enabling you to read it more pleasantly. The index of the event is corresponding to its index in the entire list.

![listeventdate2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_event_date_2.png)

<br />
<!-- @@author Aliciaho-->

#### Finding events: `find event`
Allows you to look for classes, ccas and tests and tuitions that match your given keyphrase.

In the case when you have many events in your schedule and you need to find a specific event to check the date. You can use the `find event` command to look for the event you want by entering keywords related to it.

Let's say we need to find out when my vocabulary test is on.
We can first type `find event vocabulary` into the command box, and press 'Enter' to execute it.

![find1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find1.JPG)

The output can then be seen in the console.

![find2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find2.JPG)

And we can find the date we are looking for as pointed out by the yellow arrow below.

![find3](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find3.JPG)

<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find%20note.jpg" width="500"  alt="findnote"/>

<br/>

### Score component of Plan&Score
Enables the following category type:
quiz

The quiz feature enables you to spend the extra time resulting from your productive scheduling to hone your Mathematics for your upcoming PSLE.

#### Taking a Quiz: `quiz`
Taking a Mathematics quiz with any number of questions you want, ranging from just 1 question to the total number of questions in the quiz list.

First, enter `quiz`, followed by the number of questions you would like to take in your quiz. For example, you could enter `quiz 1` to attempt a quiz with 1 question.

The following output would then be displayed.
<br>
![quiz1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/quiz1.png)
<br>

#### Adding a Quiz Question: `add quiz`
Allows you to add a Mathematics multiple-choice quiz question to the quiz list.

Note that the `/e (explanation)` is an optional parameter. You can add a quiz question with or without an explanation.

First, enter `add quiz`, follow by `/q`.

Then, enter the question you would like to add.

Next, enter `/o1`, followed by the answer option you would like to enter. Repeat this for the rest of the three options.

Now, enter `/a`, followed by the number corresponding to the correct answer option.

Lastly, enter `/e`, followed by the explanation to the solution of the question. This last part is optional.

An example of such a command would be: `add quiz /q What is 26+5? /o1 28 /o2 31 /o3 38 /o4 41 /a 2 /exp Adding 5 to 26 gives us 31.`.

The corresponding output would then be displayed as shown below.
<br>
![quizadded](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/quizadded.png)
<br />


#### Deleting a Quiz Question: `delete quiz`
Allows you to delete a quiz question from the quiz list.

Simply enter `delete quiz`, followed by the index number corresponding to the quiz question you would like to delete in your quiz list.

For example, you could enter `delete quiz 1`.

You would expect to see this output.
<br>
![deletequiz1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/deletequiz1.png)
<br />

#### Listing out All Quiz Questions: `list quiz`
Allows you to list out all the questions in the quiz list.

All you have to enter is `list quiz` for this command.

This output should then be seen.
<br>
![listquiz](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/listquiz.png)


#### Finding a quiz: `find quiz`
Look for quizzes that match the given keyword(s) that you want.

Note that searches are case-insensitive and user can provide multiple words to compare with

The format for this command is `find quiz`, followed by the keyword(s) you would like to search for in your list of quiz questions.

For example, you could enter `find quiz 26`.

Then, you would expect to see this in your output.
<br>
![findquiz](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/findquiz.png)

#### Searching for former incorrect quiz questions: `quiz record`
Allows you to list out the incorrect quizzes in your last quiz attempt.

Simply enter `quiz record`.

You should see the full list of questions you have answered wrongly in your previous quiz attempt, as shown below.
<br>
![quizrecord](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/quizrecord.png)

### Contact component of Plan&Score

#### Adding a contact `add contact`
Allows you to add a teacher's contact details to the contact list.

Firstly, type 'add' in the command line as seen below, followed by the category type which is the contact. 
Afterwards, key in the description of the contact. Press 'Enter' to execute it.

![add_contact_command](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_contact_command.png)

The output is seen in the console.

![add_contact](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_contact.png)

WARNING:
* `[subject of contact]`, `[name of contact]`, `[phone number of contact]` and 
`[email of contact]` can be in a natural language format and cannot contain '/'.
* Parameters `/s`, `/n`, `/p`, `/e` cannot be swapped.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![add_contact_wrongly](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/add_contact_wrongly.png)

#### Deleting a contact `delete contact`
Allows you to delete a contact from the contact list.

Firstly, type 'delete' in the command line as seen below, followed by the category type which is the contact. 
Afterwards, key in the corresponding index of the contact you would like to delete. Press 'Enter' to execute it.

![delete_contact_command](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_contact_command.png)

The output is seen in the console.

![delete_contact](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_contact.png)

WARNING:
* `[contact number]` must be written in numerals.

![erroraddclass](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/erroraddclass.png)

![delete_contact_wrongly](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/delete_contact_wrongly.png)

#### Listing out contacts `list contact`
Allows you to list out all the contacts in the contact list

To list out all contacts, type 'list contact' in the command line, as seen below. 
Press 'Enter' to execute it.

![list_contact_command](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_contact_command.png)

The output is seen in the console.

![list_contact](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/list_contact.png)

#### Finding a contact `find contact`
Look for contacts that match the given keywords you want.

In the case when you have many contacts in your list and you need to find a specific contact, you can use the 
`find contact` command to look for the contact you want by entering keywords related to it.

Let's say we need to find out the contact of our math teacher. We can type `find contact math` 
into the command box, and press 'Enter' to execute it.

![find_contact_command](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find_contact_command.png)

The output can then be seen in the console.

![find_contact](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find_contact.png)

<img src="https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/find%20note.jpg" width="500"  alt="findnote"/>

<!-- @@author Aliciaho-->
### Exits Program: `bye`
Exits the program once you have finished using it.

Type the command and press 'Enter' to execute it.

![bye1](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/bye_1.png)

The output is shown in the console. It contains a simple goodbye message.

![bye2](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/images/bye_2.png)
<!-- @@author Aliciaho-->

<!-- @@author durianpancakes -->
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
<!--@@author -->

## Command Summary


Action | Format | Examples
-------|--------|--------
help|`help`
add event|`add [class/cca/test] /n [description] /s [start-date-time] /e [end date-time]`, `add [tuition] /n [description] /s [start-date-time] /e [end-date-time] /l [location]`|`add class /n Math /s 2020-09-06 1300 /e 2020-09-06 1400`, `add tuition /n Math /s 2020-09-06 1300 /e 2020-09-06 1400 /l home`
add quiz|`add quiz /q [question] /o1 [option 1] /o2 [option 2] /o3 [option 3] /o4 [option 4] /a [option answer] /e (explanation)`|`add quiz /q 1 + 1 = ? /o1 1 /o2 2 /o3 3 /o4 4 /a 2 /exp no explanation needed`
add contact|`add contact /s [subject] /n [name of contact person] /p [phone number] /e [email address]`|`add contact /s math /n thomas /p 91779977 /e thomas@gmail.com`
delete event|`delete [class/cca/test/tuition] [item number]`|`delete class 1`, `delete test 1`, `delete cca 1`
delete quiz|`delete quiz [item number]`|`delete quiz 1`
delete contact|`delete contact [item number]`|`delete contact 1`
list event|`list event (<date/today/week>)`|`list event 2020-09-06`
list quiz|`list quiz`
list contact| `list contact`
Find event|`find event [keyword(s)]`|`find event English`
Find quiz|`find quiz [keyword(s)]`|`find quiz 2 + 2 = 4`
Find contact|`find contact [keyword(s)]`|`find contact jonny@gmail.com`
quiz|`quiz [no of questions]`|`quiz 15`
display quiz record|`quiz record`
set hours|`set hours`
bye|`bye`
