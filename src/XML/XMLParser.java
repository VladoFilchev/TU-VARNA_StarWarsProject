package XML;

import models.Galaxy;

import javax.xml.bind.*;
import java.io.File;

public class XMLParser {

    public Galaxy read(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Galaxy.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Galaxy) unmarshaller.unmarshal(file);
    }

    public void write(Galaxy galaxy, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Galaxy.class);
        Marshaller marshaller= jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(galaxy,file);
    }
}
