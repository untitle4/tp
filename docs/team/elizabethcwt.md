## Elizabeth Chan's Project Portfolio Page
### Project: Plan&Score

#### Overview
Plan&Score is a Java command-line application that does two main things:
1. Plan
<br>
Allows students to plan and track their:
    <ul>
        <li>School classes
        <li>CCAs
        <li>Tests
        <li>Tuition
    </ul>

    This inculcates effective scheduling habits in these students from a young age, which they will carry with them as they grow older.

2. Score
<br>
Allows students to practice quizzes regularly and consistently, honing their required skills for their upcoming PSLE.

Apart from that, Plan&Score also allows students to save contact details of their teachers, cca coaches, or any other relevant person in a highly convenient way.

In essence, Plan&Score is not merely a scheduling application like many other applications in the market. It is a one-of-a-kind and all-encompassing application, tailored for Primary 6 students in Singapore (for now).


#### Summary of Contributions

Link to my code contribution (via RepoSense):
[My contributions](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=elizabethcwt&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

Given below are my contributions to Plan&Score.

- Feature: Help
    - What it does: Displays the updated list of available features and corresponding commands for user
    - Justification: By including the required format to enter the input via quotation marks, provides a convenient way for users to remember how to input commands. It also allows them to view and track new features during application updates.
    - Highlights: This feature is usually what the user first accesses when launching the application, and provides a bird’s eye view of the whole application before delving into each individual feature.

- Feature: Class
    - What it does: Stores user’s classes, attains class list size, allows adding and deleting of classes
    - Justification: This feature allows users to schedule well, by entering their school classes into the application. Users can then remove any of these classes once they’re done, or no longer relevant. Users can then view exactly how many classes, as well as which classes they have, to manage their workload and time better.
    - Highlights: This feature affects existing commands and commands to be added in future. It requires an in-depth analysis of design alternatives.
    - Relevant classes in source code: EventClass.java class and EventClassManager.java

- Feature: Take Quiz
    - What it does: Displays a number of quiz questions, as specified by the user, in randomised order. Then, reviews the correctness of the user’s answers, an explanation for each question, as well as the user’s score.
    - Justification: This feature allows users to score well, by allowing them to take quizzes of any size they’d like, according to how much time they have. Since it is integrated into this application, along with the scheduler, it is highly accessible for users.
    - Highlights: This feature requires the use of a manager, to manage multiple arraylists. It also requires a lot of checks to handle different user inputs for each question, valid or invalid.
    - Relevant classes in source code: Quiz.java, QuizManager.java and UserAnswerManager.java

- Project Management
    - Managed release `v1.0` on GitHub
    - Reviewed PRs: [#363](https://github.com/AY2021S1-CS2113T-W12-4/tp/pull/363)
    
- Documentation
    - User Guide
        - Created structure and added hyperlinks
        - Created photos of tips and warnings boxes
        - Added photos for score component features/commands
        - Added details for `help`, `add class`, `delete class`, and `quiz` commands/features
        - Added `help` and `quiz` components in the Command Summary
                     
    - Developer's Guide
        - Added explanation for help feature
        - Added UML diagrams for the `quiz storage` and `find quiz` features.
        
- Community
    - Reported bugs and gave suggestions to other teams based on their newest release (jarfile):
    [T13-3](https://github.com/AY2021S1-CS2113-T13-3/tp/releases)
    
    - Reviewed and gave comments and suggestions to the Developer's Guide for another team:
    [T13-2](https://ay2021s1-cs2113t-w13-2.github.io/tp/DeveloperGuide.html)