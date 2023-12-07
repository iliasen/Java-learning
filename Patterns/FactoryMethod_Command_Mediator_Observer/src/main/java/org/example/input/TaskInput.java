package org.example.input;

import org.example.task.Task;

import java.util.Scanner;

public class TaskInput {

    public static Task input() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название проекта:");
        String projectName = scanner.nextLine();

        System.out.println("Введите название задачи:");
        String taskName = scanner.nextLine();

        System.out.println("Введите id задачи:");
        String taskId = scanner.nextLine();

        System.out.println("Введите запланированную трудоёмкость (в часах):");
        int plannedEffort = scanner.nextInt();

        System.out.println("Введите запланированный ресурс (человек):");
        int plannedResource = scanner.nextInt();

        return new Task(projectName, taskName, taskId, plannedEffort, plannedResource);

    }
}