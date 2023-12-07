package org.example.writer;

import org.example.task.Task;

import java.util.List;


public interface FileWriter {
    public void write(List<Task> tasks, String fileName);
}
