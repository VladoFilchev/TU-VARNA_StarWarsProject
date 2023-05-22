package XML;

import XML.XMLParser;
import models.Galaxy;

import java.io.File;

public class StarWarsService {
    private Galaxy galaxy;
    private final XMLParser xmlParser;
    private File file;

    public StarWarsService(XMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void loadFromFile() {

    }

    public void loadFileFromRepo() {

    }
}
