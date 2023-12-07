package org.example.writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.task.Task;

import java.io.IOException;
import java.util.List;

public class CSVWriter implements FileWriter {
    @Override
    public void write(List<Task> tasks, String fileName) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new java.io.FileWriter(fileName + ".csv"), CSVFormat.DEFAULT)) {

            csvPrinter.printRecord("ID", "Название", "Описание", "Статус");

            for (Task task : tasks) {
                csvPrinter.printRecord(task.getProjectName(), task.getTaskName(), task.getTaskId(), task.getPlannedEffort(), task.getPlannedResource());
            }

            System.out.println("Запись в CSV файл выполнена успешно.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в CSV файл: " + e.getMessage());
        }

    }
}