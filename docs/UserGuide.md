# User Guide

## Introduction

Plan&Score is a Java command-line application that allows Primary 6 students 
to plan and track their classes, CCAs and eventTest dates.
This enables the students to remember their schedule, 
so they can plan well in advance for their tests and score better.

- [1. Quick Start](#quick-start)
- [2. Features](#list-of-features)
    - [Help:](#viewing-help-help) `help`
    - [Add a class:](#adding-a-class-add-class) `add class`
    - [Delete a task:](#deleting-a-class-delete-class) `delete class`
    - [Add a cca:](#adding-a-cca-add-cca) `add cca`
    - [Delete a cca:](#deleting-a-cca-delete-cca) `delete cca`
    - [Add a test:](#adding-a-test-add-test) `add test`
    - [Delete a test:](#deleting-a-test-delete-test) `delete test`
    - [Toogle task done:](#toggling-task-done-done) `done`
    - [List schedule:](#list-out-schedule-list-event) `list event`
    - [Find a task by keyword or date:](#find-tasks-find) `find`
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

   1. Add class: add class /n [name of class] /s [start date-time of class] /e [end date-time of class]
   2. Delete class: delete class /n [class number]
   3. Add eventCca: add eventCca /n [name of eventCca] /s [start date-time of eventCca] /e [end date-time of eventCca]
   4. Delete eventCca: type delete eventCca /n [eventCca number]
   5. Add eventTest: type add eventTest /n [name of eventTest] /s [start date-time of eventTest] /e [end date-time of eventTest]
   6. Delete eventTest: type delete eventTest /n [eventTest number]
   7. Delete all: delete all

    Please enter the date-time in the following format: YYYY-MM-DD [time in 24hr format]
    e.g. 2020-08-19 1300

```

### Adding a class: `add class`
Adds a new class with a name, date and time

Format: `add class /n [name of class] /s [start date-time of class] /e [end date-time of class]`

* `[name of class]` can be in a natural language format and 
cannot contain '/' .  
* `[start date-time of class]`, `[end date-time of class]` must be in 
yyyy-mm-dd HHMM format.

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
yyyy-mm-dd HHMM format.

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
yyyy-mm-dd HHMM format.

Examples: `add test /n Math test /s 2020-10-3 1300 /e 2020-10-3 1400`

Example Output:
 
```
Got it. I've added this test:

    [TEST] [NOT DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 1 test in the list.`
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
`Noted. I've removed this test:`

    [TEST] [NOT DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 0 tests in the list.`
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
List out the entire schedule for classes, ccas and tests

Format: `list`

Example Output: 

```
    Classes:
    1. Math from 19th Aug, 1pm to 19th Aug , 2pm 
    2. Science from 19th Aug, 3pm to 19th Aug , 4pm 
     
    CCA:
    1. Basketball Camp from 21st Sep, 1pm to 22nd Sep, 3pm
    2. Frisbee from 19th Sep, 1pm to 19th Sep, 3pm
    
    Test:
    1. Math eventTest from 26th Sep, 11am to 26th Sep, 1pm
```
### Saving data
Plan&Score saves all data automatically after every command. There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Shift the event.txt file to the same directory as the java file.

## Troubleshooting

**Q**: Plan&Score is unable to start. How can I fix this?

**A**: Your data files might have been corrupted. To fix this, you can attempt the following steps:

**We recommend adult supervision for this process.**

1. Locate the `data` directory. It should be in the same directory as where Plan&Score is located
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
done|`done <category> <item number>`|`done class 1`, `done test 1`, `done cca 1`
list|`list event`
