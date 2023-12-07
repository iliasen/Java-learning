package org.example.strategy;

import org.example.task.Task;

import java.util.List;

public class PlannedEffortStrategy implements Strategy {
    @Override
    public double calculate(List<Task> tasks) {
        return tasks.stream()
                .mapToDouble(Task::getPlannedEffort)
                .sum();
    }
}