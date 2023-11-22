package com.example.salon.servlet;

import com.example.salon.model.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ServiceXMLUtil {

    private static final String XML_FILE_PATH = "services.xml";

    public static void saveServicesToXML(List<Service> services) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc;
            File xmlFile = new File(XML_FILE_PATH);

            Element rootElement;
            if (xmlFile.exists()) {
                doc = docBuilder.parse(xmlFile);
                rootElement = doc.getDocumentElement();
                rootElement.normalize();
            } else {
                doc = docBuilder.newDocument();
                rootElement = doc.createElement("services");
                doc.appendChild(rootElement);
            }

            for (Service service : services) {
                Element serviceElement = doc.createElement("service");
                rootElement.appendChild(serviceElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(service.getServiceName()));
                serviceElement.appendChild(nameElement);

                Element priceElement = doc.createElement("price");
                priceElement.appendChild(doc.createTextNode(String.valueOf(service.getServicePrice())));
                serviceElement.appendChild(priceElement);

                for (String clientName : service.getClientNames()) {
                    Element clientElement = doc.createElement("client");
                    clientElement.appendChild(doc.createTextNode(clientName));
                    serviceElement.appendChild(clientElement);
                }
            }

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            System.out.println("XML file successfully saved.");

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}