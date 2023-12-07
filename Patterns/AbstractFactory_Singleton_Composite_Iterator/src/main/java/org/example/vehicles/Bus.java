package org.example.vehicles;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.table.format.TableFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class Bus implements Vehicle {
    private int routeNumber;
    private LocalDate departureDate;
    private String departureTime;
    private String destination;
    private String departureStation;
    private String departurePlatform;
    private String arrivalStation;
    private double ticketCost;
    private String busBrand;
    private String travelTime;

    @Override
    public void printTableInfo() {

        TableFormat tableFormat = TableFormat.getTableFormat();
        String format = tableFormat.getFormat();

        System.out.printf(format, getRouteNumber(), getDepartureDate(), getDepartureTime(), getDestination(),
                getDepartureStation(), getDeparturePlatform(), getArrivalStation(), getTicketCost(), getBusBrand(), getTravelTime());
        System.out.println("---------------------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------");

    }
}


