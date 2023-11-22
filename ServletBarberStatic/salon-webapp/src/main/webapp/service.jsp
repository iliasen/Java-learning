<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<%@ page import="com.example.salon.model.Service" %>
<%@ page import="com.example.salon.model.Client" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html>
<head>
    <title>Барабер Шоп</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<h1>Услуги в МачоДачаХаусе</h1>
<table border="1">
    <tr>
        <th>Услуга</th>
        <th>Цена</th>
        <th>Клиенты</th>
    </tr>
    <% List<Service> services = (List<Service>) request.getAttribute("services");
        for (Service service : services) { %>
    <tr>
        <td><%= service.getName() %></td>
        <td><%= service.getPrice() %></td>
        <td>
            <% List<Client> clients = service.getClients();
                if (clients != null && !clients.isEmpty()) {
                    for (int i = 0; i < clients.size(); i++) {
                        out.print(clients.get(i).getName());
                        if (i < clients.size() - 1) {
                            out.print(", ");
                        }
                    }
                } else { %>
            Нет записанных клиентов
            <% }
            %>
        </td>
    </tr>
    <% } %>
</table>
<p>Общая стоимость: <%= request.getAttribute("totalPrice") %></p>
<p>Общее количество клиентов: <%= request.getAttribute("totalClients") %></p>
<h2>Самая популярная услуга:</h2>
<p>Название: <%= request.getAttribute("mostPopularServiceName") %></p>
<p>Количество записей: <%= request.getAttribute("mostPopularServiceCount") %></p>
<canvas id="myChart" width="200" height="100"></canvas>

<%--<h2>Добавить новую услугу:</h2>
<form method="post" action="<%= request.getContextPath() + "/services" %>">
    <label for="serviceName">Название:</label>
    <input type="text" id="serviceName" name="serviceName" required>
    <br>
    <label for="servicePrice">Цена:</label>
    <input type="number" id="servicePrice" name="servicePrice" required>
    <br>
    <input type="submit" value="Добавить">
</form>--%>


<script>
    // Преобразование JSON-строк в массивы JavaScript
    var serviceNames = JSON.parse('<%= request.getAttribute("serviceNamesJson") %>');
    var serviceCounts = JSON.parse('<%= request.getAttribute("serviceCountsJson") %>');

    // Отрисовка графика
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: serviceNames,
            datasets: [{
                label: 'Популярность услуг',
                data: serviceCounts,
                backgroundColor: 'rgba( 75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
</body>
</html>