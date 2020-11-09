## Project: Plan&Score

Plan&Score is a Java command-line application that allows Primary 6 students to plan and track their classes, CCAs and test dates. This enables the students to remember their schedule, so they can plan well in advance for their tests and score better.

## Contributions by Andre Wong
Given below are my contributions to the project.
* Features:
    * List events [#18](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/18)
        * What it does: allows the user to list out all their classes, ccas, tests and tuitions.
        * Justification: Allow users to view all their schedule at a glance. The numbering that appears here also corresponds to which events to delete later.
    * Find events [#178](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/178)
        * What it does: allows the user to search for all the events by keywords.
        * Justification: Allows user to look for events in which they cannot remember the exact details such as start date.
    * Add quiz [#123](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/123)
        * What it does: allows the user to add a quiz question to quiz manager
        * Justification: Users can create their own question freely to test themselves afterwards. This helps to improve learning.
    * Find contacts [#240](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/240)
        * What it does: allows the user to find contacts.
        * Justification: Users can search by email, phone number , name or subject if they cannot remember them all.
    * Contacts storage [#347](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/347)
        * What it does: allows the application to store the contacts in a text file automatically.
        * Justification: The contact-data will now be persistent even after the application closes.
        
* Unused Feature:
    * Quiz Notification [#217](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/217)
        * What it does: shows notification to user if quiz has not been attempted since two days ago.
        * Justification: Reminds users to attempt quiz that they might have forgotten to do their quiz.

* Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=AndreWongZH&tabRepo=AY2021S1-CS2113T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* Enhancements:
    * Refactor code in controller package to have a command parser to extract out commands, have a model parser to extract out the models so to adhere to single responsibility principle
    * Create a command factory that generates the correct command class to execute on
        * All commands inherit from abstract class command so that all commands will have method execute()
    * EventManager class to add another layer of abstraction
    * Refactor storage class to inherit from abstract StorageManager to merge duplicated methods [#125](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/125)
    * Logger class follows a singleton pattern [#146](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/146)
    * Add encapsulation to models by adding an interface to all the modelManagers
    * Command sanitization
        * Ensure that handle cases where there are extra parameters, swapped parameters, command in uppercase and command with extra whitespaces
    * Factory reset
        * Ensure the program can still run after factory reset

* Project management:
    * Update code using forking workflow when doing pull requests
    * Set up issue tracker and milestones for `v1.0`, `v2.0` and `v2.1`
    * Set up github project dashboard to track progress
    * Released `v2.0` onto github
    * Reviewed PRs: [#17](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/17) [#355](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/355) [#363](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/363) [#375](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/375)

* Community:
    * Contributed to forum discussions: [#29](https://github.com/nus-cs2113-AY2021S1/forum/issues/29) [#37](https://github.com/nus-cs2113-AY2021S1/forum/issues/87)
    * Reported bugs and suggestions for other teams: [#2](https://github.com/nus-cs2113-AY2021S1/tp/pull/2)

* Contribution to user guide
    * Wrote documentation for `Finding events`, `Finding contacts`, `Listing events`, `Adding quiz`, `Quiz notification`
    * Added images initially for initialization, help, add and delete class
    * Added images under section `Finding events`
    * Update and enhanced table for `command summary`
    
* Contribution to developer guide
    * Add colors and removed footboxes to UML diagrams
    * Add UML diagrams for figure 1, 3, 4, 5, 17, 18
    * Wrote documentation for `Architecture`, `Controller component` and `Model component` in the Design section
    * Wrote documentation for `List contact/quiz`, `List event (date /today)` and `Find event <keyword(s)>` feature
    * Wrote documentation for manual testing for sections on `Adding of CCAs`, `Listing of events` and `Finding of contacts` and for Non-Functional Requirements