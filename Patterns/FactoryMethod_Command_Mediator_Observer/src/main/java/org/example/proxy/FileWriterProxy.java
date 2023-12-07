package org.example.proxy;

import lombok.AllArgsConstructor;
import org.example.task.Task;
import org.example.writer.FileWriter;

import java.util.List;

@AllArgsConstructor
public class FileWriterProxy implements FileWriter {

    private final FileWriter fileWriter;

    @Override
    public void write(List<Task> tasks, String fileName) {
        System.out.println("Прокси перехватил запись в файл");
        System.out.println("В файл " + fileName + " записываются задачи:");
        tasks.forEach(System.out::println);
        fileWriter.write(tasks, fileName);
    }

}