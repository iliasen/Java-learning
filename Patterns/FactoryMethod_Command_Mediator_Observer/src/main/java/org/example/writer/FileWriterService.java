package org.example.writer;

import lombok.AllArgsConstructor;
import org.example.task.Task;

import java.util.List;


@AllArgsConstructor
public class FileWriterService {

    private final FileWriter fileWriter;

    public void write(List<Task> tasks, String fileName) {
        fileWriter.write(tasks, fileName);
    }

}
