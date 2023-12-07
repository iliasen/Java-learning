package org.example;

import org.example.factory.FileWriterFactory;
import org.example.factory.PropertiesWriterFactory;
import org.example.factory.XMLWriterFactory;
import org.example.input.TaskInput;
import org.example.proxy.FileWriterProxy;
import org.example.strategy.PlannedEffortStrategy;
import org.example.strategy.PlannedResourceStrategy;
import org.example.strategy.Strategy;
import org.example.task.Task;
import org.example.writer.FileWriter;


import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task("1", "2", "3", 4, 5));
        tasks.add(new Task("1", "2", "3", 5, 6));

        tasks.add(TaskInput.input());

        Strategy strategy = new PlannedResourceStrategy();
        System.out.println(strategy.calculate(tasks));

        strategy = new PlannedEffortStrategy();
        System.out.println(strategy.calculate(tasks));

        FileWriterFactory factory = new PropertiesWriterFactory();

        FileWriter fileWriter = new FileWriterProxy(factory.createFileWriter());
        fileWriter.write(tasks, "priv");


        factory = new XMLWriterFactory();
        fileWriter = new FileWriterProxy(factory.createFileWriter());
        fileWriter.write(tasks, "priv");
    }

}