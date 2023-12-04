package com.example.salon.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {
    private String name;
    private double price;
    private List<Client> clients;
    private String serviceName;
    private double servicePrice;

    public Service(String name, double price, List<Client> clients) {
        this.name = name;
        this.price = price;
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public List<Client> getClients() {
        return clients;
    }

    public static int calculateTotalClients(List<Service> services) {
        Set<Client> uniqueClients = new HashSet<>();

        for (Service service : services) {
            uniqueClients.addAll(service.getClients());
        }

        return uniqueClients.size();
    }
    public static double calculateTotalPrice(List<Service> services) {
        double totalPrice = 0.0;
        for (Service service : services) {
            totalPrice += service.getPrice();
        }
        return totalPrice;
    }

    public void addClient(Client client) {
        clients.add(client);
    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Test
    public List<String> getClientNames() {
        List<String> clientNames = new ArrayList<>();
        for (Client client : clients) {
            clientNames.add(client.getName());
        }
        return clientNames;
    }
}

