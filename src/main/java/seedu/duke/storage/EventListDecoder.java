package seedu.duke.storage;

import seedu.duke.exception.StorageSeparatorException;
import seedu.duke.model.event.cca.EventCca;
import seedu.duke.model.event.classlesson.EventClass;
import seedu.duke.model.event.Event;
import seedu.duke.model.event.test.EventTest;
import seedu.duke.model.event.tuition.EventTuition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//@@author durianpancakes
public class EventListDecoder {
    public EventListDecoder() {
    }

    public ArrayList<Event> decodeEventList(ArrayList<String> encodedEventList) throws StorageSeparatorException {
        final ArrayList<Event> decodedEvents = new ArrayList<>();
        for (String encodedEvent : encodedEventList) {
            decodedEvents.add(decodeEventFromString(encodedEvent));
        }
        return decodedEvents;
    }

    private Event decodeEventFromString(String encodedEvent) throws StorageSeparatorException {
        final String[] data = encodedEvent.trim().split("\\|", 3);

        switch (data[0]) {
        case EventCca.CCA_ICON:
            return parseCca(data);
        case EventClass.CLASS_ICON:
            return parseClass(data);
        case EventTest.TEST_ICON:
            return parseTest(data);
        case EventTuition.TUITION_ICON:
            return parseTuition(data);
        default:
            return null;
        }
    }

    private EventCca parseCca(String[] data) throws StorageSeparatorException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);

        if (eventInfo.length != 3) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        // isValidParams(description, start, end);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        return new EventCca(description, isDone, startCalendar, endCalendar);
    }

    private EventClass parseClass(String[] data) throws StorageSeparatorException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);

        if (eventInfo.length != 3) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        //isValidParams(description, start, end);

        return new EventClass(description, isDone, startCalendar, endCalendar);
    }

    private EventTest parseTest(String[] data) throws StorageSeparatorException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 3);

        if (eventInfo.length != 3) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];

        //isValidParams(description, start, end);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }


        return new EventTest(description, isDone, startCalendar, endCalendar);
    }

    private EventTuition parseTuition(String[] data) throws StorageSeparatorException {
        boolean isDone;
        String description;
        isDone = Boolean.parseBoolean(data[1]);
        String[] eventInfo = data[2].trim().split("\\|", 4);

        if (eventInfo.length != 4) {
            throw new StorageSeparatorException();
        }

        description = eventInfo[0];
        String start = eventInfo[1];
        String end = eventInfo[2];
        String location = eventInfo[3];

        // isValidTuition(description, start, end, location);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }


        return new EventTuition(description, isDone, startCalendar, endCalendar, location);
    }

    private void isValidParams(Calendar description, Calendar start, Calendar end) {
        assert description != null;
        assert !description.equals("");
        assert start != null;
        assert !start.equals("");
        assert end != null;
        assert !end.equals("");
    }

    private void isValidTuition(Calendar description, Calendar start, Calendar end, Calendar location) {
        isValidParams(description, start, end);
        assert location != null;
        assert !location.equals("");
    }
}
