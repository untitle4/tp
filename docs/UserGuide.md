# User Guide

## Introduction

Plan&Score is a Java command-line application that allows Primary 6 students 
to plan and track their classes, CCAs and eventTest dates.
This enables the students to remember their schedule, 
so they can plan well in advance for their tests and score better.

## Quick Start

1. Ensure that you have Java ```11``` or above installed.
2. Down the latest version of `Plan&score` from [here](https://github.com/AY2021S1-CS2113T-W12-4/tp/releases).
3. Run the program by entering `java -jar plan.jar` in a terminal.

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

`Got it. I've added this class:`

    [CLASS] [NOT DONE] Math from 19th August 2020, 01:00 pm to 19th August 2020, 02:00 pm

`Now you have 1 class in the list.`

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

### Adding a eventCca: `add eventCca`
Adds a new eventCca with a name, date and time

Format: `add eventCca /n [name of eventCca] /s [start date-time of eventCca] /e [end date-time of eventCca]`

* `[name of eventCca]` can be in a natural language format and 
cannot contain '/' .  
* `[start date-time of eventCca]`, `[end date-time of eventCca]` must be in 
yyyy-mm-dd HHMM format.

Examples: `add eventCca /n Basketball training /s 2020-09-19 1900 /e 2020-09-19 2100`

Example Output: 

`Got it. I've added this eventCca:`

    [CCA] [NOT DONE] Basketball training from 19th September 2020, 07:00 pm to 19th September 2020, 09:00 pm

`Now you have 1 eventCca in the list.`

<br>

### Deleting a eventCca: `delete eventCca`
Deleting a eventCca based on its index in the list

Format: `delete eventCca [eventCca number]`

* `[eventCca number]` must be written in numerals. 

Examples: `delete eventCca 1`

Example Output: 

`Noted. I've removed this eventCca:`

    [CCA] [NOT DONE] Basketball training from 19th September 2020, 07:00 pm to 19th September 2020, 09:00 pm

`Now you have 0 ccas in the list.`

<br>

### Adding a eventTest: `add eventTest`
Adds a new eventTest with a name, date and time

Format: `add eventTest /n [name of eventTest] /s [start date-time of eventTest] /e [end date-time of eventTest]`

* `[name of eventTest]` can be in a natural language format and 
cannot contain '/' .  
* `[start date-time of eventTest]`, `[end date-time of eventTest]` must be in 
yyyy-mm-dd HHMM format.

Examples: `add eventTest /n Math eventTest /s 2020-10-3 1300 /e 2020-10-3 1400`

Example Output: 

`Got it. I've added this eventTest:`

    [TEST] [NOT DONE] Math eventTest from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 1 eventTest in the list.`

<br />
<br />

### Deleting a eventTest: `delete eventTest`
Deleting a eventTest date based on its index in the list


Format: `delete eventTest [eventTest number]`

* `[eventTest number]` must be written in numerals. 

Examples: `delete eventTest 1`

Example Output: 

`Noted. I've removed this eventTest:`

    [TEST] [NOT DONE] Math eventTest from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 0 tests in the list.`

<br />
<br />

### Setting an event as done: `done`
Setting a class, eventTest or eventCca event as done based on its index in the list

Format:
* For class: `done class [class number]`
* For eventTest: `done eventTest [eventTest number]`
* For eventCca: `done eventCca [eventCca number]`

* `[eventTest number]` must be in numerals. 

Examples: `done eventTest 1`

Example Output: 

`Nice! I've marked this eventTest as done:`

    [TEST] [DONE] Math eventTest from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 1 eventTest in the list.`

<br />
<br />

### List out schedule: `list`
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


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Shift the event.txt file to the same directory as the java file.

## Command Summary

Action | Format | Examples
-------|--------|--------
help|`help`
add|`add <category> /n <description> /s <start-date-time> /e <end date-time>`|`add class /n Math /s 2020-09-06 1300 /e 2020-09-06 1400`
delete|`delete <category> <item number>`|`delete class 1`, `delete eventTest 1`, `delete eventCca 1`
done|`done <category> <item number>`|`done class 1`, `done eventTest 1`, `done eventCca 1`
list|`list`
