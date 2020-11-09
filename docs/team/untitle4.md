# Chen Jinran's Project Portfolio Page

## Project: Plan&Score

Plan&Score is a Command Line Interface application for primary 6 students to
possess a schedule, quiz and contact tracker. It is written in Java.

Given below are my contributions to the project.

* Features:
    * Add/Delete ccas [#25](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/25)
        * What it does: Allows the user to add an event of type cca in the schedule.
    Allows the user to delete the existing ccas in the schedule.
        * Justification: This is a basic feature for constructing the schedule.
    * Quiz storage [#121](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/121)
        * What it does: Allows the application to store the quizzes in a text file automatically.
    * Delete quiz [#121](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/121)
        * What it does: Allows the user to delete the existing quizzes in the quiz list.
    * Find quiz [#185](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/185)
        * What it does: Allows users to find quizzes in the quiz list with input keyword(s).
    * List quiz [#127](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/127)
        * What it does: Allows users to list out all quiz questions in the quiz list.
    * Quiz record [#213](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/213)
        * What it does: Allows users to review the incorrect quizzes in their last quiz attempt.
    * Add/Delete contacts [#131](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/131)
        * What it does: Allows users to add a contact in the contact list.
    Allows the user to delete the existing contacts in the contact list.

* Unused Features:
    * Set ccas done [#34](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/34)
        * What it does: Allows users to set a cca from `NOT DONE` to `DONE`.

* Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=untitle4&tabRepo=AY2021S1-CS2113T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* Project management:
    * PRs reviewed: [#363](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/363)

* Enhancements to existing features:
    * Added an optional parameter `explanation` for a quiz question: [#150](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/150)
        
* Documentation:
    * User Guide:
        * Added implementation details of the `add`, `delete`,
        `list` and `find` features for quiz.
        * Added implementation details of the `add` and `delete` for cca.
        * Added picture demonstration of the `add`, `delete`, `list` and `find` command for contacts 
        and update the corresponding implementation details.
    * Developer Guide:
        * Drafted the description of the `quiz` feature.
        * Added UML diagrams for the `quiz storage` and `find quiz` features.
       
* Community:
    * Reported bugs and suggestions for other teams in the class (example: [Reviewing T14-3](https://github.com/untitle4/ped/issues/4))