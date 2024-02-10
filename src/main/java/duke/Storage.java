package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 * It provides functionality to read tasks from a file and write tasks back to the file,
 * allowing for persistent storage of tasks across application sessions.
 */
public class Storage {
    private final File f;

    /**
     * Constructs a new Storage object.
     * Initializes a File object to handle read and write operations to the specified file path.
     *
     * @param filePath The file path used for storing and retrieving task data.
     */
    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    /**
     * Loads tasks from the file associated with this Storage object.
     * Parses the file content and constructs a list of tasks based on the data.
     *
     * @return ArrayList of Task objects read from the file.
     * @throws DukeException If the file cannot be found or read from.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedLst = new ArrayList<>();
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (IOException e) {
            throw new DukeException("Sorry, I can't find the file to load from");
        }
        while (s.hasNext()) {
            String[] taskArr = s.nextLine().split(" \\| ");
            switch (taskArr[0]) {
            case "T":
                assert taskArr.length == 3 : "Saved Todo task is invalid";
                Task newTodo = Todo.fromSaveFormat(taskArr);
                loadedLst.add(newTodo);
                break;
            case "D":
                assert taskArr.length == 4 : "Saved Deadline task is invalid";
                Task newDeadline = Deadline.fromSaveFormat(taskArr);
                loadedLst.add(newDeadline);
                break;
            case "E":
                assert taskArr.length == 5 : "Saved Event task is invalid";
                Task newEvent = Event.fromSaveFormat(taskArr);
                loadedLst.add(newEvent);
                break;
            default:
                throw new DukeException("Sorry, I can't understand the file format");
            }
        }
        return loadedLst;
    }

    /**
     * Saves tasks to the file associated with this Storage object.
     * Writes the list of tasks from the provided TaskList into the file in a specified format.
     *
     * @param tl The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public void save(TaskList tl) throws IOException {
        f.getParentFile().mkdirs();
        assert f.exists() || f.createNewFile() : "File should exist or be created";
        FileWriter cfw = new FileWriter(f);
        for (Task t : tl.getLst()) {
            cfw.write(t.toSaveFormat() + "\n");
        }
        cfw.close();
    }
}
