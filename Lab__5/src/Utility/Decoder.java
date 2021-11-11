package Utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ticket.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.PriorityQueue;

public class Decoder {
    public static PriorityQueue<Ticket> decodeIntoCollection(String filename) {
        PriorityQueue<Ticket> tickets = new PriorityQueue<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("ticket");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                Ticket ticket = new Ticket();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ticket.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    ticket.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    Coordinates coordinates = new Coordinates();
                    coordinates.setX(Long.valueOf(element.getElementsByTagName("x").item(0).getTextContent()));
                    coordinates.setY(Double.parseDouble(element.getElementsByTagName("y").item(0).getTextContent()));
                    ticket.setCoordinates(coordinates);
                    ticket.setCreationDate(ZonedDateTime.parse(element.getElementsByTagName("creation_date").item(0).getTextContent()));
                    ticket.setPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()));
                    ticket.setType(TicketType.valueOf(element.getElementsByTagName("type").item(0).getTextContent()));
                    Event event = new Event(ticket);
                    event.setName(element.getElementsByTagName("name_event").item(0).getTextContent());
                    event.setEventType(EventType.valueOf(element.getElementsByTagName("type_event").item(0).getTextContent()));
                    event.setDate(LocalDate.parse(element.getElementsByTagName("date_event").item(0).getTextContent()));
                    ticket.setCreationDate(ZonedDateTime.now());
                    ticket.setEvent(event);
                    tickets.add(ticket);

                }


            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        System.out.println((
                tickets.size() != 0) ?
                "Collection is filled with " + tickets.size() + " elements."
                : "Collection is empty");
        return tickets;
    }
}