package seedu.duke.unused;

//@@author AndreWongZH-unused

/*
    We decided to scrap the functionality that notifies user if quiz has not been taken since 2 days ago.
    This is because the taking of quiz is randomized and thus there is no way in our current version to
    manually update the date for quiz taken.
 */

/*
    public class QuizListDecoder {
        public static final int NUM_OF_DAYS = -2;

        //@@author AndreWongZH
        public ArrayList<Quiz> decodeQuizList(ArrayList<String> encodedQuizList) throws StorageCorruptedException {
            final ArrayList<Quiz> decodedQuizzes = new ArrayList<>();

            for (Quiz quiz : decodedQuizzes) {
                long numDays = DAYS.between(LocalDate.now(), quiz.getLastAccessed());
                if (numDays <= NUM_OF_DAYS) {
                    Notify.create()
                            .title("Plan&score Notification")
                            .text("you have outdated quizzes! Attempt them now!")
                            .hideAfter(10000)
                            .showInformation();
                    break;
                }
            }

            return decodedQuizzes;
        }
    }
*/
