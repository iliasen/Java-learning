package org.example.print;

import org.example.table.format.TableFormat;
import org.example.vehicle.list.VehicleList;
import org.example.vehicles.Vehicle;

public class BusTablePrinter implements VehicleTablePrinter {
    @Override
    public void printTableHeader() {

        TableFormat tableFormat = TableFormat.getTableFormat();
        String format = tableFormat.getFormat();

        System.out.printf(format, "Route Number", "Departure Date", "Departure Time", "Destination", "Departure Station",
                "Departure Platform", "Arrival Station", "Ticket Cost", "Bus Brand", "Travel Time");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }
}
