package seedu.duke;

import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;

import java.util.ArrayList;

public class CcaManager {
    private ArrayList<Event> cca = new ArrayList<>();

    public CcaManager(ArrayList<Event> inputList) {
        cca = inputList;
    }

    public ArrayList<Event> getCcaList() {
        return cca;
    }

    private int getCcaListSize() {
        return cca.size();
    }

    public void addCca(String userInput) throws CcaEmptyStringException, CcaParamException {
        if ((!userInput.contains("/n")) || (!userInput.contains("/s"))
                || (!userInput.contains("/e"))) {
            throw new CcaParamException();
        }

        final String[] ccaDetails = userInput.trim().split("\\/");
        String ccaDescription = ccaDetails[1].substring(2);
        String ccaStartDate = ccaDetails[2].substring(2);
        String ccaEndDate = ccaDetails[3].substring(2);

        if (ccaDescription.equals("") || ccaStartDate.equals("")
                || ccaEndDate.equals("")) {
            throw new CcaEmptyStringException();
        }

        cca.add(new Cca(ccaDescription, ccaStartDate, ccaEndDate));

        System.out.println("Got it. I've added this cca: ");
        System.out.println(cca.get(getCcaListSize() - 1));
        getCcaStatement();
    }

    public void deleteCca(String[] userInput) {
        int ccaIndex = Integer.parseInt(userInput[2]);

        System.out.println("Noted. I've removed this cca: ");
        System.out.println(cca.get(ccaIndex - 1));

        cca.remove(ccaIndex - 1);
        getCcaStatement();
    }

    public void setCcaDone(String[] userInput) {
        int ccaIndex = Integer.parseInt(userInput[2]) - 1;
        cca.get(ccaIndex).setDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(cca.get(ccaIndex).getDescription());
    }

    private void getCcaStatement() {
        String ccaStatement = getCcaListSize() == 1 ? " cca" : " ccas";
        System.out.println("Now you have " + getCcaListSize() + ccaStatement
                + " in the list.");
    }
}