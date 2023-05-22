import CLI.CommandLineInterface;
import XML.XMLParser;
import commands.CommandService;
import enums.CelestialType;
import enums.Ranks;
import enums.SaberColors;
import models.CelestialObject;
import models.Galaxy;
import models.Jedi;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        // Galaxy galaxy = new Galaxy();
        // CelestialObject celestialObject = new CelestialObject("Pluto", CelestialType.MOON);
        // Jedi jedi = new Jedi("Anakin", 5, Ranks.MASTER, SaberColors.BLACK, 6000);
        // celestialObject.addJedi(jedi);
        // galaxy.addCelestialObject(celestialObject);
        // File file = new File("StarWarsUniverse.xml");
        // XMLParser xmlParser = new XMLParser();
        // galaxy=xmlParser.read(file);
        // System.out.println(galaxy);
        CommandLineInterface commandLineInterface=new CommandLineInterface();
        commandLineInterface.run();
    }
}