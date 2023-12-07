package org.example.task;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private String projectName;
    private String taskName;
    private String taskId;
    private int plannedEffort;
    private int plannedResource;
}
