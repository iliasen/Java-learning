package org.example.strategy;

import org.example.task.Task;

import java.util.List;


public interface Strategy {
    double calculate(List<Task> tasks);
}
