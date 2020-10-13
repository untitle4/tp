# User Guide

## Introduction

Plan&Score is a Java command-line application that allows Primary 6 students 
to plan and track their classes, CCAs and test dates.
This enables the students to remember their schedule, 
so they can plan well in advance for their tests and score better.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Plan&score` from [here](https://github.com/AY2021S1-CS2113T-W12-4/tp/releases).
3. Run the program by entering `java -jar plan.jar` in a terminal.

## List of Features 


### Adding a test: `add test`
Adds a new test with a name, date and time

Format: `add test /n [name of test] /s [start date-time of test] /e [end date-time of test]`

* `[name of test]` can be in a natural language format and 
cannot contain '/' .  
* `[start date-time of test]`, `[end date-time of test]` must be in 
yyyy-mm-dd HHMM format.

Examples: `add test /n Math test /s 2020-10-3 1300 /e 2020-10-3 1400`

Example Output: 

`Got it. I've added this test:`

    [TEST] [NOT DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 1 task in the list.`

<br />
<br />

### Deleting a test: `delete test`
Deleting a test date based on its index in the list


Format: `delete test /n [test number]`

* `[test number]` must be in numerals. 

Examples: `delete test /n 1`

Example Output: 

`Noted. I've removed this task:`

    [TEST] [NOT DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 0 task in the list.`

<br />
<br />

### Setting a test as done: `done test`
Seeting a test date as done based on its index in the list


Format: `done test [test number]`

* `[test number]` must be in numerals. 

Examples: `done test 1`

Example Output: 

`Nice! I've marked this test as done:`

    [TEST] [DONE] Math test from 3rd October 2020, 01:00 pm to 3rd October 2020, 02:00 pm

`Now you have 1 task in the list.`

<br />
<br />

### List out schedule: `list`
List out the entire schedule for classes, ccas and tests

Format: `list`

Example Output: 

```
    Classes:
    Math from 19th Aug, 1pm to 19th Aug , 2pm 
    Science from 19th Aug, 3pm to 19th Aug , 4pm 
     
    CCA:
    Basketball Camp from 21st Sep, 1pm to 22nd Sep, 3pm
    Frisbee from 19th Sep, 1pm to 19th Sep, 3pm
    
    Test:
    Math test from 26th Sep, 11am to 26th Sep, 1pm
```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
* list `list`
