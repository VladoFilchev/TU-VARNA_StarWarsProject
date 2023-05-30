package XML;

import models.Galaxy;

import javax.xml.bind.*;
import java.io.File;

public class JAXBParser {

    public Galaxy read(String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Galaxy.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Galaxy) unmarshaller.unmarshal(new File(fileName));
    }

    public void write(Galaxy galaxy, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Galaxy.class);
        Marshaller marshaller= jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(galaxy,new File(fileName));
    }
}
