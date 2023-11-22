package com.example.salon.servlet;

import com.example.salon.model.Client;
import com.example.salon.model.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.example.salon.model.Service.calculateTotalClients;
import static com.example.salon.model.Service.calculateTotalPrice;

public class ServicesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            File xmlFile = new File(getServletContext().getRealPath("/WEB-INF/services.xml"));
            System.out.println("Path to XML file: " + xmlFile.getAbsolutePath());

            if (!xmlFile.exists()) {
                System.out.println("XML file does not exist!");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "XML file does not exist!");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList serviceNodes = doc.getElementsByTagName("service");
            List<Service> services = new ArrayList<>();

            List<String> serviceNamesList = new ArrayList<>();
            List<Integer> serviceCountsList = new ArrayList<>();

            String mostPopularServiceName = "";
            int mostPopularServiceCount = 0;

            for (int i = 0; i < serviceNodes.getLength(); i++) {
                Element serviceElement = (Element) serviceNodes.item(i);
                String serviceName = serviceElement.getElementsByTagName("name").item(0).getTextContent();
                double servicePrice = Double.parseDouble(serviceElement.getElementsByTagName("price").item(0).getTextContent());

                List<Client> clients = new ArrayList<>();

                NodeList clientNodes = serviceElement.getElementsByTagName("client");
                for (int j = 0; j < clientNodes.getLength(); j++) {
                    Element clientElement = (Element) clientNodes.item(j);
                    String clientName = clientElement.getTextContent();
                    clients.add(new Client(clientName));
                }

                System.out.println("Service: " + serviceName + ", Price: " + servicePrice + ", Clients: " + clients);
                services.add(new Service(serviceName, servicePrice, clients));

                int currentServiceCount = clients.size();
                serviceNamesList.add(serviceName);
                serviceCountsList.add(currentServiceCount);

                if (currentServiceCount > mostPopularServiceCount) {
                    mostPopularServiceCount = currentServiceCount;
                    mostPopularServiceName = serviceName;
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            String serviceNamesJson = mapper.writeValueAsString(serviceNamesList);
            String serviceCountsJson = mapper.writeValueAsString(serviceCountsList);

            request.setAttribute("serviceNamesJson", serviceNamesJson);
            request.setAttribute("serviceCountsJson", serviceCountsJson);
            request.setAttribute("services", services);
            request.setAttribute("totalPrice", calculateTotalPrice(services));
            request.setAttribute("totalClients", calculateTotalClients(services));
            request.setAttribute("mostPopularServiceName", mostPopularServiceName);
            request.setAttribute("mostPopularServiceCount", mostPopularServiceCount);
            request.setAttribute("serviceNames", serviceNamesList.toArray(new String[0]));
            request.setAttribute("serviceCounts", serviceCountsList.toArray(new Integer[0]));
            request.getRequestDispatcher("/service.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Произошла ошибка ввода-вывода при обработке запроса: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Произошла внутренняя ошибка сервера: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Обработка POST-запроса
        String serviceName = request.getParameter("serviceName");
        double servicePrice = Double.parseDouble(request.getParameter("servicePrice"));

        Service service = new Service(serviceName, servicePrice, new ArrayList<>());

        String[] clientNames = request.getParameterValues("clientName");
        if (clientNames != null) {
            for (String clientName : clientNames) {
                service.addClient(new Client(clientName));
            }
        }

        List<Service> services = (List<Service>) request.getAttribute("services");
        if (services == null) {
            services = new ArrayList<>();
            request.setAttribute("services", services);
        }

        services.add(service);
        ServiceXMLUtil.saveServicesToXML(services);
        // Перенаправление обратно на GET-запрос
        response.sendRedirect(request.getContextPath() + "/services");
    }
}
