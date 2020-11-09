# Developer Guide

* Table of Contents
{:toc}

## Introduction

### Purpose
This document describes the architecture and system design of Plan&Score, which will evolve throughout future releases. 

Each release will have an edition of the document, and the current edition of the document for the first public release is v2.1.

The goal of this document is to cover the high-level system architecture and design. This document is divided into three major parts: design, implementation, product proposition. 

## Design

### Architecture

![diagram](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ArchitectureDiagram.png)

Figure 1. Architecture Diagram

The Architecture Diagram given above explains the high-level design of the App. Given below is a quick overview of each component.

The `Main` class is responsible for,
* At app launch: Initializes the main components in the correct sequence
* At shut down: Terminates the continuous loop and shut down the components

`Common` represents a collection of classes used by multiple other components.

The rest of the App consists of four components.
* `UserInterface`: The user interface of the App.
* `Controller`: User input parser and command executor
* `Model`: Holds the data of the App in memory
* `Storage`: Reads data from, and writes data to, the hard disk.

Each of the four components:
Exposes its functionality using a concrete {Component Name}Manager class.

The sections below give more details of each component.

<!-- @@author durianpancakes -->
#### UserInterface component

![userinterfacecomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/UserInterfaceComponent.png)

Figure 2. Class Diagram of the UserInterface component

API: UserInterface.java

The UserInterface component,
* Prompts commands from the user.
* Creates the Controller component and execute user commands.
* Displays information based on changes to Model data.
* Prints the user’s events of the week in a timetable format.

This component uses the singleton design, meaning that there is only an instance required throughout the entire lifetime of the application, obtained with the following command
`UserInterface userInterface = UserInterface.getInstance()`
<br />
<br />
<!-- @@author -->

#### Controller component

![controllercomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ControllerComponent.png)

Figure 3. Class Diagram of the Controller component

API: ControlManager.java

The Controller component,
* Receives user input from the user interface.
* Extracts the command and model type from the user input.
* Generate the required command from the CommandFactory.java class
* Extract the required model to be accessed and modified.
* Executes the command with the corresponding model.
* The sequence diagram below illustrates the above mentioned steps.

![controllercomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ControllerSequence.png)

Figure 4. Sequence Diagram of the Controller component

<br />
<br />

#### Model component

![modelcomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ModelComponent.png)

Figure 5. Class Diagram of the Model component

API: Model.java

The Model component,
* Holds all the in-memory data of type event, quiz, config and contact.
* Each data type has a corresponding manager that the controller can interface with. This is named as <datatype>Manager.java.
* During a command execution, the manager will handle the modification and reading of its data type.
* After the execution, the corresponding output is passed on to the User Interface component to be shown to the user.

<br />
<br />

<!-- @@author durianpancakes -->
#### Storage component
![storagecomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/StorageComponent.png)

Figure 6. Class Diagram of the Storage component

API: StorageManager.java

The Storage component,
* Creates the necessary data files for the operation of Plan&Score.
* Reads encoded data from Plan&Score’s data files.
* Writes encoded data to Plan&Score’s data files.

There are 3 categories of data stored by Plan&Score: `event`, `quiz` and `config`

Plan&Score loads data automatically from .txt files in the `data` directory.
Each `StorageManager` reads in their respective data files through a `decoder` and writes to the same file through an `encoder`

##### Event Storage
![eventstorage](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/EventStorageManager.png)

Figure 7. Class Diagram of EventStorageManager

API: EventStorageManager.java
`EventStorageManager` is responsible for the reading and writing of data from Plan&Score’s `events.txt` file, located in the `{root}/data` directory.

It utilises a decoder (`EventListDecoder.java`) for the reading of data, and an encoder (`EventListEncoder.java`) for the writing of data. 

###### Reading events

`EventListDecoder` is responsible for the decoding of data from `events.txt`.

It returns an `EventParameter` to `EventStorageManager`. 

![eventreadstorage](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/EventStorageReadSequence.png)

Figure 8. Sequence Diagram of the reading of data

###### Writing events

`EventListEncoder` is responsible for the creation of the encoded strings for `EventStorageManager` to write to `events.txt`.

It returns a `String` to `EventStorageManager` for further writing. 

![eventwritestorage](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/EventStorageSaveSequence.png)

Figure 9. Sequence Diagram of the writing of data
<!-- @@author -->

##### Quiz Storage
API: QuizStorageManager.java

![quizwritestorage](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/QuizWriteStorage.png)

Figure 10. Sequence Diagram of the writing of data

![quizreadstorage](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/QuizReadStorage.png)

Figure 11. Sequence Diagram of the reading of data

The Quiz Storage,
* Checks existence of the quiz data file in the `data` directory. If the file does not exist, create a new data file for quiz storage.
* Invokes the `quizListEncoder` class to encode the ArrayList of type Quiz into its String representations and writes them into the quiz data file.
* Invokes the `quizListDecoder` class to decode the String representations of quizzes in the quiz data file and add the quizzes back into the ArrayList of type Quiz.

<!-- @@author Aliciaho-->
##### Config Storage
API: ConfigStorageManager.java
![configstoragecomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ConfigStorage.png)

Figure 12. Class Diagram of the ConfigStorageManager

The Config Storage,
* Checks existence of the config data file in the `data` directory. If the file does not exist, create a new data file for config storage.
* Invokes the `configEncoder` class to encode the String of user name, the integer number of recommended hours and finally, the boolean which checks if the program has run before. The encoded string is written into a config data file.
* Invokes the `configDecoder` class to decode the String user name, the integer number of recommended hours and the boolean which checks if the program has run before from a config data file. The three variables are used to show a different welcome message.


The Config Parameter,
* Helps to store the String of user name, the integer number of recommended hours and finally, the boolean which checks if the program has run before.
* Contents stored in the parameter are subsequently used by configEncoder to encode them into a string. 
<!-- @@author Aliciaho-->

---

## Implementation

This section describes some noteworthy details on how certain features are implemented.

<!-- @@author durianpancakes -->
### User Interface
The user interface of Plan&Score uses the singleton design. There is only one instance of UserInterface to be used throughout the application. The API for the user interface is UserInterface.java.

#### Printing to user
Instead of using the default method `System.out.println()` provided by Java to display messages to the user, use `showToUser(String …)` where multiple strings can be added into the arguments. Each string provided that is separated by `,` will be printed on the next line.

Example input:
```
UserInterface userInterface = UserInterface.getInstance();

String string1 = "Hello";
String string2 = "How are you?";

userInterface.showToUser(string1, string2);
```

Example output:
```
Hello
How are you?
```

Code:
```
public void showToUser(String... message) {
   for (String m : message) {
       out.println(m);
   }
}
```
<!-- @@author -->


#### Printing arrays to user

The method `printArray(ArrayList<String> stringArrayList)` is provided for the printing of any arraylists.

Example input:
```
ArrayList<String> stringArrayList = new ArrayList<>();

// Adding items into the stringArrayList
stringArrayList.add("Hello");
stringArrayList.add("How are you?);

// Obtaining user interface instance
UserInterface userInterface = UserInterface.getInstance();
userInterface.printArray(stringArrayList);
```

Example output:
```
Hello
How are you?
```

Code:
```
public void printArray(ArrayList<String> stringArrayList) {
   assert stringArrayList != null;
   for (String line : stringArrayList) {
       userInterface.showToUser(line);
   }
}
```
<!-- @@author Aliciaho-->
### Help feature

![helpcomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/Help.png)

Figure 13. Sequence Diagram of the help feature

When a user enters ‘help’, the input will be read in by the UI class.
The UI class will then parse the user input into the ControlManager class, which calls the runLogic() method.
The extractCommand() method of the CommandParser class is then called, extracting and returning the command type based on the user’s input.
In this case, the command type would be ‘help’.
The corresponding actionableCommand will be generated via the generateActionableCommand() method in the CommandFactory class.
Lastly, the execute() method in the HelpCommand class is called, which in turn calls its own handleHelp() method.
This displays the help message via the showToUser() method of the userInterface.

Upon completion of this feature, it returns a boolean value “true” to the active flag in UserInterface.java to allow the continuous usage of the program.

### Set Hours feature

![sethourscomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/SetHours.png)

Figure 14. Sequence Diagram of the set hours feature

When a user enters ‘set hours’, the input will be read in by the UI class.
The UI class will then parse the user input into the ControlManager class, which calls the runLogic() method.
The extractCommand() method of the CommandParser class is then called, extracting and returning the command type based on the user’s input.
In this case, the command type would be ‘set hours’.
The corresponding actionableCommand will be generated via the generateActionableCommand() method in the CommandFactory class.
Lastly, the execute() method in the SetHoursCommand class is called, which in turn calls the editHours() function from the configManager class.


Upon completion of this feature, it returns a boolean value “true” to the active flag in UserInterface.java to allow the continuous usage of the program.
<!-- @@author Aliciaho-->
### Add feature

![addcomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/Add.png)

Figure 15. Sequence Diagram of the add feature

Firstly, when the user enters add .. /n .. /s .. /e .. , the input will be read in by the UI class.
The UI class will then parse the user input into the ControlManager class where the command will be extracted and processed by the CommandParser class.
According to which category they belong to i.e class,cca,test,tuition etc, they are sent to their respective category managers.
In each manager, the user input is processed and made into a new Event item by the Event class.
The result is subsequently outputted by the UI class to the user. 

Upon completion of this feature, it returns a boolean value “true” to the active flag in UserInterface.java to allow the continuous usage of the program.


### Delete feature

![deletecomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/DeleteFeatureDiagram.png)

Figure 16. Sequence Diagram of the delete feature

Firstly, when the user enters delete <event> <number>, the input will be read in by the UI class.
The UI class will then parse the user input into the ControlManager class where the command will be extracted and processed by the CommandParser class. The result is passed into ModelExtractor Class.
According to which category they belong to i.e. class, cca, test, tuition etc, they are sent to their respective category managers. In each manager, the user input is processed and the Event item would be deleted by the Event class.
The result is subsequently outputted by the UI class to the user. 

Upon completion of this feature, it returns a boolean value “true” to the active flag in UserInterface.java to allow the continuous usage of the program. 

### List feature

#### List contact / quiz

![listcontact](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ListContact.png)

Figure 17. Sequence diagram for listing quizzes

The list command will invoke the `ContactManager` or `QuizManager` class’s list() method respectively.
If the ArrayList is empty, it will inform the user that there is no data to display.
Else the method will then loop through the ArrayList as show in the loop box in figure 17 and convert it into its string representation.
This is then passed to the `UserInterface#printArray()` to be printed out to the user.

#### List event (date / today / week)
While the back-end data processing is the same for all three types of list requests, the list event week request requires a different front-end class to display to the user.
As such, we divide this section into 2 sub-sections, with List event (<date>/today) in the first subsection, and List event week in the next subsection.

The execution of the ListCommand will cause the `EventManager` class to invoke `EventManager#listSchedule()`.
This will then create an instance of the `ListSchedule` class, with all the classes, ccas, tests and tuitions data
as its attributes.

#### List event (date / today)

![listevent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ListEvent.png)

Figure 18. Sequence diagram for listing out events.

Inside this `EventManager#listSchedule()`, we then call the `ListSchedule#getPrintableEvents()` and this will start
to convert ArrayList of type Event into its corresponding `toString()` representation.
This is then padded with numbers and an ArrayList of type string is returned to `EventManager#listSchedule()`.

It is then passed to the `UserInterface#printArray()` to be printed out to the user.

Special parameters:

In the case where the parameters passed in is ‘today’, the `ListSchedule#checkAndConvertToday()` will
check if the user passed in ‘today’ as a parameter.
If that is true, then it converts the userInput attribute to `LocalDate.now()`, which is the current date.
When filtering the events to be converted, the start time of the event is compared with the parameter date.
If it is equal, the event will be converted and be printed out.

<!-- @@author durianpancakes -->
#### List event week/nextweek

![listeventweek](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ListWeekSequence.png)

Figure 19. Sequence diagram for listing out events for week/nextweek.

Inside this `EventManager`, we then call the `UserInterface#printWeekSchedule(EventManager, ListWeekCommand)`, passing 
in the current instance of EventManager into this method and a ListWeekCommand that determines if the current or next 
week is to be printed. 
`UserInterface#printWeekSchedule(EventManager)` will then construct an instance of `CalendarWeekRenderer(EventManager)` 
which will display the week schedule to the user.

As of V2.1, the CalendarWeekRenderer does not support printing the location parameter of the Tuition class.

Example code snippet:

```
UserInterface userInterface = UserInterface.getInstance();
userInterface.printWeekSchedule(this, ListWeekCommand.CURRENT_WEEK); // the EventManager instance is passed into the method call
```
<!-- @@author -->


### Find feature

#### Find event <keyword(s)>

The execution of FindCommand will cause the `EventManager` class to invoke `EventManager#find()`. Inside this `EventManager#find()`, it creates an instance of the `FindSchedule` class, with all the classes, ccas, tests and tuitions data as its attributes. 

We then call the `FindSchedule#getFilteredEvents()` and this will start to convert ArrayList of type Event into its corresponding `toString()` representation. An ArrayList of type String is returned to `EventManager#find()`.

This is then passed to the `UserInterface#printArray()` to be printed out to the user.

The diagram below shows the execution flow explained below.

![findevent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/FindEvent.png)

Figure 20. Sequence diagram for finding events.

#### Find quiz <keyword(s)>

The execution of FindCommand will cause the `QuizManager` class to create an instance of the `FindQuiz` class, with all the quizzes data as its attributes. 

We then call the `findQuiz#filterQuizzes()` and this will start to convert ArrayList of type Quiz into its corresponding `toString()` representation. An ArrayList of type String is returned to `QuizManager#find()`.

This is then passed to the `UserInterface#printArray()` to be printed out to the user.

The diagram below shows the execution flow explained below.

![findquiz](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/FindQuiz.png)

Figure 21. Sequence diagram for finding quizzes.

### Quiz feature

#### Take quiz

This execution of the quiz command will invoke the QuizManager class’s quiz() method.
The variable correctCounter will be initialized to be 0.
If the input <number> is:

- Less than 1 (including negative numbers), or
- more than the total number of questions in the current quiz list,

the program will inform the user that their input is invalid, and provide the range of questions the user can attempt.

If the input is not an integer:
- the program will inform the user that their input is of the wrong format
 and probe them to enter a value of integer type.
- The method will then randomly select the input number of quiz questions in the quiz ArrayList
 and convert them into their string representation.
- This is then passed to the UserInterface#printArray() to be printed out to the user.
- When each quiz question is printed, the user needs to input an answer to this question.
- The input answer will then be compared to the answer of that quiz question.
- If the answers are the same, the variable correctCounter in QuizManager() will be incremented by 1. 
- Upon completion of this feature, it returns a boolean value “true” to the active flag in UserInterface.java,
 which allows the continuous usage of the program.

## Product scope
### Target user profile

 Target User Profile:
* Is a Primary 6 student with a packed schedule
* prefer desktop apps over other types
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps


### Value proposition

One-stop application for Primary 6 students to plan their schedule and do revision tests at the same time.
## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|As a forgetful Primary 6 Student|to be able to remember all the classes I have in school|plan my class schedule accordingly|
|v1.0|Primary 6 student|to be able to remember all the extra-curricular activities I have in school |Plan my cca schedule accordingly. |
|v1.0|Primary 6 student|to be able to remember all the tuition classes I have outside of school and remember the locations of my tuition centres  |Plan my tuition schedule accordingly and would not get lost. |
|v1.0|Primary 6 student|I have many class tests and examinations leading up to PSLE, and would like to keep track of all my upcoming test dates|I know the test dates in advance and can plan my revision schedule well.|
|v1.0|Primary 6 student|I would like to list all my classes, extra-curricular activities, tuition classes and test dates|I can have a overview of what I schedules I have|
|v1.0|Primary 6 student|I would like to be able to remember all the commands used in this program|I do not need to refer to the user guide all the time|
|v1.0|Primary 6 student|I would like to be able to remember all the commands used in this program|I do not need to refer to the user guide all the time|
|v2.0|Primary 6 student|to remember the contact details of my teachers|I can contact them in case there is an emergency.|
|v2.0|Primary 6 student|to see the list of questions I have added|I can keep track of the questions I need.|
|v2.0|Primary 6 student|to practice via short and interactive online trivia|I can revise in an entertaining manner.|

## Non-Functional Requirements

* Should work on any Windows, Mac and Linux operating system with Java 11 installed.
* Should update the storage every time a command changes the data.
* Users should be able to view the output of their command within 10 seconds
* Accessing and loading of data should take less than 5 seconds when we have less than 10000 data entries.

## Glossary

* *glossary item* - Definition

Data entries 
	Event, Quiz or contact data that are either in storage or in their respective Manager class

## Instructions for manual testing

Given below are instructions to test the app manually

### Adding of CCAs
1. Test case: `add`
    * Expected: Inform the user that the category type is missing.
1. Test case: `add cca`
    * Expected: Inform the user to include all /n /s and /e inputs.
1. Test case: `add cca /n abc /s date1 /e date 2`
	* Expected: Inform the user to enter a valid date time format.
1. Test case: `add test /n Math test /s 2020-10-03 1300 /e 2020-10-03 1400`
	* Expected: Inform the user that cca has been added. Running `list event` command will show the added cca inside.
1. Test case: `add test /n Math test /s 2020-10-03 1300 /e 2020-10-03 1000`
    * Expected: Inform the user that start time is later than end time.

### Listing of events
1. Test case: `list`
	* Expected: Inform the user to list either event, quiz or contact
1. Test case: `list event`
	* Expected: Show a list of events and categorized into its event type. If there are no events, inform the user that the schedule is empty.
1. Test case: `list event 2020-10-03`
	* Expected: Show a  list of events that matches with the specified date. If no events that match, inform the user that the schedule is not found.
1. Test case: `list event date`
	* Expected: Inform the user to enter a valid date time format.
1. Test case: `list event 2020-10-3 2pm`
    * Expected: Inform the user not to enter extra parameters.

### Finding of contacts
1. Test case: `find`
	* Expected: Inform the user to find either event, quiz or contact
1. Test case: `find contact math`
	* Expected: Show a list of contacts that matches the keywords. If there are no contacts found , inform the user that the search has no result.
1. Test case: `find contact`
	* Expected: Remind the user to include the keyword in the command.
1. Test case: `find testing`
	* Expected: Inform user that the program does not recognise the category.

<!-- @@author durianpancakes -->
### Saving data
#### Dealing with missing data files

There are two ways we use to define a missing file: 
1. When the “data” directory in the project root directory is missing.
1. When any of the critical data .txt files (i.e events.txt, quiz.txt) are missing from the “data” directory.

We can simulate this by deleting any of the critical data .txt file, or the “data” directory 	as a whole. 

Expected: The “data” directory as well as “event.txt“ and “quiz.txt” are recreated.
However, any prior data stored will be lost. 	


#### Dealing with corrupted data files

We define a file to be corrupted when our decoders are unable to decode the data .txt files.

We can simulate this by changing the encoded text in the file. For example, a Class that has the follow parameters:

* Description: Math tutorial
* isDone: false
* Start: 2020-09-21 1500
* End: 2020-09-21 1600

should be encoded as “[CLASS]|false|Math tutorial|2020-09-21 1500|2020-09-21 1600” in the events.txt file. 

Corrupted forms can be in the following forms, but not limited to:
* “[CLASS]\|Math tutorial\|2020-09-21 1500\|2020-09-21 1600”: the isDone parameter is missing from the encoded string.
* “[CLASS]\|false\|Math tutorial\|2020-09-21 1600”: one of the date-time parameter is missing from the encoded string
* “[CLASS]+false+Math tutorial+2020-09-21 1500+2020-09-21 1600”: the parameter separator “+” is not recognized by our decoder.

Expected: the program will not be able to start, with the error message “): Storage file corrupted. Please delete your data directory and relaunch.” shown.
<!-- @@author -->