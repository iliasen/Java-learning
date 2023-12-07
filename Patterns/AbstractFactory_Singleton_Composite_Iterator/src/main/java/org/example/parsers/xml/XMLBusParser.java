package org.example.parsers.xml;

import org.example.parsers.XMLParser;
import org.example.vehicles.Bus;
import org.example.vehicles.Vehicle;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XMLBusParser implements XMLParser {
    @Override
    public List<Vehicle> parseFile(String filePath) {
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("bus");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Bus bus = createBusFromElement(element);
                    vehicles.add(bus);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    private Bus createBusFromElement(Element element) {
        int routeNumber = Integer.parseInt(getTextContent(element, "routeNumber"));
        LocalDate departureDate = parseDate(getTextContent(element, "departureDate"));
        String departureTime = getTextContent(element, "departureTime");
        String destination = getTextContent(element, "destination");
        String departureStation = getTextContent(element, "departureStation");
        String departurePlatform = getTextContent(element, "departurePlatform");
        String arrivalStation = getTextContent(element, "arrivalStation");
        double ticketCost = Double.parseDouble(getTextContent(element, "ticketCost"));
        String busBrand = getTextContent(element, "busBrand");
        String travelTime = getTextContent(element, "travelTime");

        return new Bus(routeNumber, departureDate, departureTime, destination, departureStation,
                departurePlatform, arrivalStation, ticketCost, busBrand, travelTime);
    }

    private String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node = nodeList.item(0); // Assuming there is only one element with the specified tagName
        return node.getTextContent();
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}