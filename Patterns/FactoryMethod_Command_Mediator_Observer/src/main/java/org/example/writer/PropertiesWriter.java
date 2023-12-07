package org.example.writer;

import org.example.task.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

public class PropertiesWriter implements FileWriter {
    @Override
    public void write(List<Task> tasks, String filename) {
        Properties properties = new Properties();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String key = "task" + (i + 1);
            String value = task.toString();
            properties.setProperty(key, value);
        }

        try (OutputStream outputStream = new FileOutputStream(filename + ".properties")) {
            properties.store(outputStream, "Property file");
            System.out.println("Запись в файл .properties выполнена успешно.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл .properties: " + e.getMessage());
        }
    }
}