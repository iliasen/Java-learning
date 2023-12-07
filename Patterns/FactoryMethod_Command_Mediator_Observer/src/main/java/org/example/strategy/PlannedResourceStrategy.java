package org.example.strategy;

import org.example.task.Task;

import java.util.List;

public class PlannedResourceStrategy implements Strategy {
    @Override
    public double calculate(List<Task> tasks) {
        return tasks.stream()
                .mapToDouble(Task::getPlannedResource)
                .sum();
    }
}
