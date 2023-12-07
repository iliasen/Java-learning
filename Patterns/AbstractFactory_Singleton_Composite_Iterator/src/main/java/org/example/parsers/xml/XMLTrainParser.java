package org.example.parsers.xml;

import org.example.parsers.XMLParser;
import org.example.vehicles.Train;
import org.example.vehicles.Vehicle;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XMLTrainParser implements XMLParser {
    @Override
    public List<Vehicle> parseFile(String filePath) {
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("train");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    try {
                        Train train = createTrainFromElement(element);
                        vehicles.add(train);
                    } catch (ParseException e) {
                        // Handle parse exception for individual train element
                        e.printStackTrace();
                    }
                }
            }
        } catch (ParserConfigurationException | IOException e) {
            // Handle general XML parsing exception
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            // Handle SAXException
            e.printStackTrace();
        }

        return vehicles;
    }

    private Train createTrainFromElement(Element element) throws ParseException {
        int trainNumber = Integer.parseInt(getTextContent(element, "trainNumber"));
        LocalDate departureDate = parseDate(getTextContent(element, "departureDate"));
        String departureTime = getTextContent(element, "departureTime");
        String destination = getTextContent(element, "destination");
        String departureStation = getTextContent(element, "departureStation");
        String departurePlatform = getTextContent(element, "departurePlatform");
        String arrivalStation = getTextContent(element, "arrivalStation");
        String ticketTypesAndCosts = getTextContent(element, "ticketTypesAndCosts");
        LocalDate arrivalDate = parseDate(getTextContent(element, "arrivalDate"));
        String arrivalTime = getTextContent(element, "arrivalTime");

        return new Train(trainNumber, departureDate, departureTime, destination, departureStation, departurePlatform,
                arrivalStation, ticketTypesAndCosts, arrivalDate, arrivalTime);
    }

    private String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }

    private LocalDate parseDate(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        }
        return null;
    }
}