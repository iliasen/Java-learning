package org.example;

import org.example.factory.BusParserFactory;
import org.example.factory.ParserFactory;
import org.example.factory.TrainParserFactory;

import org.example.filters.GetNVehiclesAroundCurrentTime;

import org.example.parsers.XMLParser;
import org.example.print.BusTablePrinter;
import org.example.print.TrainTablePrinter;
import org.example.print.VehicleTablePrinter;
import org.example.vehicle.list.VehicleList;
import org.example.vehicles.Vehicle;

import java.time.LocalDate;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        ParserFactory parserFactory = new BusParserFactory();
        XMLParser xmlParser = parserFactory.createXMLParser();

        List<Vehicle> busList = xmlParser.parseFile("src/main/java/org/example/xml/bus.xml");

//        busList = DateFilter.filterByDate(busList, LocalDate.of(2021, 9, 3));
//        busList = TimeFilter.filterByTime(busList, "14:40");
        busList = GetNVehiclesAroundCurrentTime.getNVehiclesAroundCurrentTime(busList, 5);

        VehicleList vehicles = new VehicleList(busList);

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        VehicleTablePrinter vehicleTablePrinter = new BusTablePrinter();
        vehicleTablePrinter.printInfo(vehicles);


        ParserFactory parser = new TrainParserFactory();
        xmlParser = parser.createXMLParser();

        List<Vehicle> trainList = xmlParser.parseFile("src/main/java/org/example/xml/train.xml");

        trainList = GetNVehiclesAroundCurrentTime.getNVehiclesAroundCurrentTime(trainList, 5);

        vehicles = new VehicleList(trainList);

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        vehicleTablePrinter = new TrainTablePrinter();
        vehicleTablePrinter.printInfo(vehicles);
    }

}
