package seedu.duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class StorageManager {
    private static final String STORAGE_DIRECTORY_PATH = new File("data").getAbsolutePath();
    private static final String STORAGE_EVENT_PATH = STORAGE_DIRECTORY_PATH + "/events.txt";

    private EventListEncoder eventListEncoder;
    private EventListDecoder eventListDecoder;
    private File eventFile;
    private ArrayList<Event> eventList;
    private ArrayList<Event> ccaList;
    private ArrayList<Event> testList;
    private ArrayList<Event> classList;

    public StorageManager() {
        this.eventListEncoder = new EventListEncoder();
        this.eventListDecoder = new EventListDecoder();
        this.eventList = new ArrayList<>();
        this.ccaList = new ArrayList<>();
        this.testList = new ArrayList<>();
        this.classList = new ArrayList<>();

        initializeStorageManager();
    }

    public ArrayList<Event> getEventList () {
        return eventList;
    }

    public ArrayList<Event> getCcaList () {
        return ccaList;
    }

    public ArrayList<Event> getTestList () {
        return testList;
    }

    public ArrayList<Event> getClassList () {
        return classList;
    }

    private void initializeStorageManager() {
        eventFile = new File(STORAGE_EVENT_PATH);
        ArrayList<String> data = new ArrayList<>();

        try {
            boolean fileCreated = createDataFile();
            if(!fileCreated) {
                Scanner sc = new Scanner(eventFile);
                while (sc.hasNext()) {
                    String dataString = sc.nextLine();
                    data.add(dataString);
                }
                eventList = eventListDecoder.decodeEventList(data);
                separateEventsIntoList(eventList);
            }
        } catch (IOException e) {
            System.out.println("There was an error loading your files.");
        }
    }

    private void separateEventsIntoList (ArrayList<Event> events) {
        for(Event event : events) {
            if (event instanceof Cca) {
                ccaList.add(event);
            } else if (event instanceof Class) {
                classList.add(event);
            } else if (event instanceof Test) {
                testList.add(event);
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private boolean createDataFile () throws IOException {
        File file = new File(STORAGE_DIRECTORY_PATH);
        file.mkdir();
        file = new File(STORAGE_EVENT_PATH);

        return file.createNewFile();
    }

    public void save (ArrayList<Event> eventList) throws IOException {
        ArrayList<String> encodedEventList = eventListEncoder.encodeEventList(eventList);
        Files.write(Path.of(STORAGE_EVENT_PATH), encodedEventList);
    }
}
