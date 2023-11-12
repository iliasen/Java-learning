package coffees.xml;

import coffees.Coffee;
import coffees.CoffeeShop;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
    private StringBuilder data = new StringBuilder();
    private CoffeeShop coffeeShop;
    private Coffee currentCoffee;
    private String currentElementName = null;

    public XMLHandler(CoffeeShop coffeeShop) {
        this.coffeeShop = coffeeShop;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("coffee")) {
            currentCoffee = new Coffee();
        }
        currentElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("coffee")) {
            coffeeShop.addCoffee(currentCoffee);
        } else if (currentCoffee != null && currentElementName != null) {
            String value = data.toString().trim();
            switch (currentElementName) {
                case "type":
                    currentCoffee.setType(value);
                    break;
                case "grind":
                    currentCoffee.setGrind(value);
                    break;
                case "make":
                    currentCoffee.setMake(value);
                    break;
                case "pour":
                    currentCoffee.setPour(value);
                    break;
            }
        }

        data.setLength(0);
        currentElementName = null;
    }
}