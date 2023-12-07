package org.example.parsers.properties;

import org.example.parsers.PropertiesParser;
import org.example.vehicles.Bus;
import org.example.vehicles.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PropertiesBusParser implements PropertiesParser {

    @Override
    public List<Vehicle> parseFile(String filePath) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Bus bus = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                if (line.startsWith("busData.bus")) {
                    String[] parts = line.split("=");

                    if (parts.length == 2) {
                        String[] propertyParts = parts[0].split("\\.");

                        if (propertyParts.length == 3) {
                            int busIndex = Integer.parseInt(propertyParts[1]);

                            if (busIndex >= vehicles.size()) {
                                vehicles.add(createNewBus(parts[1].trim()));
                            } else {
                                bus = (Bus) vehicles.get(busIndex);
                                setPropertyValue(bus, propertyParts[2], parts[1].trim());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    private Bus createNewBus(String properties) {
        String[] propertyValues = properties.split("\n");
        int routeNumber = 0;
        LocalDate departureDate = null;
        String departureTime = null;
        String destination = null;
        String departureStation = null;
        String departurePlatform = null;
        String arrivalStation = null;
        double ticketCost = 0.0;
        String busBrand = null;
        String travelTime = null;

        for (String property : propertyValues) {
            String[] propertyParts = property.split("=");

            if (propertyParts.length == 2) {
                String propertyName = propertyParts[0].trim();
                String propertyValue = propertyParts[1].trim();

                switch (propertyName) {
                    case "routeNumber":
                        routeNumber = Integer.parseInt(propertyValue);
                        break;
                    case "departureDate":
                        departureDate = LocalDate.parse(propertyValue);
                        break;
                    case "departureTime":
                        departureTime = propertyValue;
                        break;
                    case "destination":
                        destination = propertyValue;
                        break;
                    case "departureStation":
                        departureStation = propertyValue;
                        break;
                    case "departurePlatform":
                        departurePlatform = propertyValue;
                        break;
                    case "arrivalStation":
                        arrivalStation = propertyValue;
                        break;
                    case "ticketCost":
                        ticketCost = Double.parseDouble(propertyValue);
                        break;
                    case "busBrand":
                        busBrand = propertyValue;
                        break;
                    case "travelTime":
                        travelTime = propertyValue;
                        break;
                    default:
                        // Ignore unknown properties
                        break;
                }
            }
        }

        return new Bus(routeNumber, departureDate, departureTime, destination, departureStation, departurePlatform,
                arrivalStation, ticketCost, busBrand, travelTime);
    }

    private void setPropertyValue(Bus bus, String propertyName, String propertyValue) {
        switch (propertyName) {
            case "routeNumber":
                bus.setRouteNumber(Integer.parseInt(propertyValue));
                break;
            case "departureDate":
                bus.setDepartureDate(LocalDate.parse(propertyValue));
                break;
            case "departureTime":
                bus.setDepartureTime(propertyValue);
                break;
            case "destination":
                bus.setDestination(propertyValue);
                break;
            case "departureStation":
                bus.setDepartureStation(propertyValue);
                break;
            case "departurePlatform":
                bus.setDeparturePlatform(propertyValue);
                break;
            case "arrivalStation":
                bus.setArrivalStation(propertyValue);
                break;
            case "ticketCost":
                bus.setTicketCost(Double.parseDouble(propertyValue));
                break;
            case "busBrand":
                bus.setBusBrand(propertyValue);
                break;
            case "travelTime":
                bus.setTravelTime(propertyValue);
                break;
            default:
                // Ignore unknown properties
                break;
        }
    }
}