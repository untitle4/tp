# Developer Guide


## Design

### Architecture

![diagram](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ArchitectureDiagram.png)

The Architecture Diagram given above explains the high-level design of the App. Given below is a quick overview of each component.

The `Duke` class is responsible for,
* At app launch: Initializes the main components in the correct sequence and read data from our data files
* At shut down: Terminates the continuous loop and shut down the components

`Common` represents a collection of classes used by multiple other components.

The rest of the App consists of four components.
* `UserInterface`: The user interface of the App.
* `Controller`: User input parser and command executor
* `Model`: Holds the data of the App in memory
* `Storage`: Reads data from, and writes data to, the hard disk.

Each of the four components:
The interaction between them is defined in an interface.
Exposes its functionality using a concrete {Component Name}Manager class.

The sections below give more details of each component.

#### UserInterface component

<br />
<br />

#### Controller component

![controllercomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ControllerComponent.png)

API: ControlManager.java

The Controller component,
* Receives user input from the user interface.
* Extracts the command and model type from the user input.
* Generate the required command from the CommandFactory.java class
* Extract the required model to be accessed and modified.
* Executes the command with the corresponding model.

<br />
<br />

#### Model component

![modelcomponent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ModelComponent.png)

API: Model.java

The Model component,
* Holds all the in-memory data of type event, quiz and contact.
* Each data type has a corresponding manager that the controller can interface with. This is named as <datatype>Manager.java.
* During a command execution, the manager will handle the modification and reading of its data type.
* After the execution, the corresponding output is then passed on to the User Interface component to be shown to the user.

<br />
<br />

#### Storage component

---

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### List feature

#### List contact / quiz
The list command will invoke the `QuizManager` or `ContactManager` class’s list() method.
If the ArrayList is empty, it will inform the user that there is no data to display. 
The method will then loop through the ArrayList and convert it into its string representation. 
This is then passed to the `UserInterface#printArray()` to be printed out to the user.

The diagram below shows the execution flow explained below.

![listcontact](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ListContact.png)

#### List event (<date> / today / week)
The execution of the ListCommand will cause the `EventManager` class to invoke `EventManager#listSchedule()`.
This will then create an instance of the `ListSchedule` class, with all the classes, ccas, tests and tuitions data
as its attributes.
Inside this `EventManager#listSchedule()`, we then call the `ListSchedule#getPrintableEvents()` and this will start 
to convert ArrayList of type Event into its corresponding `toString()` representation.
This is then padded with numbers and an ArrayList of type string is returned to `EventManager#listSchedule()`.
It is then passed to the `UserInterface#printArray()` to be printed out to the user.

The diagram below shows the execution flow explained below.

![listevent](https://raw.githubusercontent.com/AY2021S1-CS2113T-W12-4/tp/master/docs/diagram/ListEvent2.png)

Special parameters:

In the case where the parameters passed in is ‘today’, the `ListSchedule#checkAndConvertToday()` will 
check if the user passed in ‘today’ as a parameter. 
If that is true, then it converts the userInput attribute to `LocalDate.now()`, which is the current date. 
When filtering the events to be converted, the start time of the event is compared with the parameter date. 
If it is equal, the event will be converted and be printed out.


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}


## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
