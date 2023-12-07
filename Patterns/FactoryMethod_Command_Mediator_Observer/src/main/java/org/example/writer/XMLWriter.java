package org.example.writer;

import org.example.task.Task;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.List;

public class XMLWriter implements FileWriter {
    @Override
    public void write(List<Task> tasks, String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            javax.xml.parsers.DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Создание корневого элемента
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("tasks");
            doc.appendChild(rootElement);

            for (Task task : tasks) {
                // Создание элемента task
                Element taskElement = doc.createElement("task");
                rootElement.appendChild(taskElement);

                // Создание элементов с данными задачи
                Element projectIdElement = doc.createElement("projectId");
                projectIdElement.appendChild(doc.createTextNode(task.getTaskId()));
                taskElement.appendChild(projectIdElement);

                Element taskNameElement = doc.createElement("taskName");
                taskNameElement.appendChild(doc.createTextNode(task.getTaskName()));
                taskElement.appendChild(taskNameElement);

                Element taskIdElement = doc.createElement("taskId");
                taskIdElement.appendChild(doc.createTextNode(task.getTaskId()));
                taskElement.appendChild(taskIdElement);

                Element plannedEffortElement = doc.createElement("plannedEffort");
                plannedEffortElement.appendChild(doc.createTextNode(String.valueOf(task.getPlannedEffort())));
                taskElement.appendChild(plannedEffortElement);

                Element plannedResourceElement = doc.createElement("plannedResource");
                plannedResourceElement.appendChild(doc.createTextNode(String.valueOf(task.getPlannedResource())));
                taskElement.appendChild(plannedResourceElement);
            }

            // Запись документа в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName + ".xml"));

            transformer.transform(source, result);
            System.out.println("Запись в XML файл выполнена успешно.");
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Ошибка при записи в XML файл: " + e.getMessage());
        }
    }
}