package commands;

import EnumHandler.RankHandler;
import XML.JAXBParser;
import comparator.JediComparator;
import enums.CelestialType;
import enums.Ranks;
import enums.SaberColors;
import models.CelestialObject;
import models.Galaxy;
import models.Jedi;

import javax.xml.bind.JAXBException;
import java.util.*;


public class CommandService {
    private Galaxy galaxy;
    private final JAXBParser jaxbParser;
    private String fileName;

    public CommandService(Galaxy galaxy) {
        this.galaxy = galaxy;
        jaxbParser = new JAXBParser();
    }

    private <T extends Enum<T>> T enumParser(String enumeration, Class<T> enumClass) throws IllegalArgumentException {
        try {
            return Enum.valueOf(enumClass, enumeration.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type of declared celestial object does not exist");
        }
    }

    public void open(String fileName) throws JAXBException {
        if (this.fileName == null) {
            galaxy = jaxbParser.read(fileName);
            this.fileName = fileName;
            System.out.println("The file has been loaded");
        } else {
            System.out.println("The file already loaded");
        }
    }

    public void close() {
        if (this.fileName != null) {
            galaxy = null;
            this.fileName = null;
            System.out.println("The file has been closed");
        } else {
            System.out.println("There is not an loaded file");
        }
    }

    public void save() throws JAXBException {
        if (this.fileName != null) {
            jaxbParser.write(this.galaxy, fileName);
            System.out.println("File successfully saved");
        } else {
            System.out.println("There is not a loaded file");
        }
    }

    public void saveAs(String fileName) throws JAXBException {
        if (this.fileName != null) {
            jaxbParser.write(this.galaxy, fileName);
            System.out.println("File successfully saved");
        } else {
            System.out.println("There is not a loaded file");
        }
    }

    public void addPlanet(String planetName, String celestialType) {

        CelestialObject celestialObject = new CelestialObject(planetName, enumParser(celestialType, CelestialType.class));
        if (!galaxy.addCelestialObject(celestialObject)) {
            System.out.println("The celestial object already exists");
        } else {
            System.out.println("The celestial object has been added successfully");
        }

    }

    public void createJedi(String planetName, String jediName, String jediAge, String rank, String saberColors, String jediStrength) {
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                Ranks enumRank = enumParser(rank, Ranks.class);
                SaberColors enumSaberColor = enumParser(saberColors, SaberColors.class);
                int age = Integer.parseInt(jediAge);
                double power = Double.parseDouble(jediStrength);

                Jedi jedi = new Jedi(jediName, age, enumRank, enumSaberColor, power);
                celestialObject.addJedi(jedi);
                System.out.println("Jedi added successfully!");
                return;
            }
        }
        System.out.println("This planet does not exist!");
    }

    public void removeJedi(String jediName, String planetName) {
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                for (Jedi jedi : celestialObject.getJedis()) {
                    if (jedi.getName().equals(jediName)) {
                        celestialObject.removeJedi(jedi);
                        System.out.println("Jedi removed successfully!");
                        return;
                    }
                    System.out.println("This jedi does not exist!");
                }
            }
        }
        System.out.println("This planet does not exist!");
    }

    public void promoteJedi(String jediName, String multiplier) {
        RankHandler rankHandler = new RankHandler();
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            for (Jedi jedi : celestialObject.getJedis()) {
                if (jedi.getName().equals(jediName)) {
                    Ranks currentRank = jedi.getRank();
                    Ranks nextRank = rankHandler.getNextRank(currentRank);
                    double currentPower = jedi.getPower();
                    double parseMultiplier = Double.parseDouble(multiplier);
                    if (nextRank != null && parseMultiplier >= 0) {
                        jedi.setRank(nextRank);
                        jedi.setPower(currentPower += parseMultiplier * currentPower);
                        System.out.println("Jedi promoted to " + nextRank);
                    }
                } else {
                    System.out.println("Unable to promote jedi " + jediName + " already the highest rank");
                }
            }
            System.out.println("Jedi with name " + jediName + " not found!");
        }
    }

    public void demoteJedi(String jediName, String multiplier) {
        RankHandler rankHandler = new RankHandler();
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            for (Jedi jedi : celestialObject.getJedis()) {
                if (jedi.getName().equals(jediName)) {
                    Ranks currentRank = jedi.getRank();
                    Ranks nextRank = rankHandler.getDemotedRank(currentRank);
                    double currentPower = jedi.getPower();
                    double parseMultiplier = Double.parseDouble(multiplier);
                    if (nextRank != null && parseMultiplier >= 0) {
                        jedi.setRank(nextRank);
                        jedi.setPower(currentPower -= parseMultiplier * currentPower);
                        System.out.println("Jedi demoted to " + nextRank);
                    }
                } else {
                    System.out.println("Unable to demote jedi " + jediName);
                }
            }
            System.out.println("Jedi with name " + jediName + " not found!");
        }
    }

    public void getStrongestJedi(String planetName) {
        Jedi jediWithHighestPower = null;
        double temp = Double.MIN_VALUE;
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                for (Jedi jedi : celestialObject.getJedis()) {
                    if (jedi.getPower() > temp) {
                        temp = jedi.getPower();
                        jediWithHighestPower = jedi;
                    }
                }
                System.out.println(jediWithHighestPower);
                return;
            }
        }
        System.out.println("This planet does not exist!");
    }

    public void getYoungestJedi(String planetName, String rank) {
        List<Jedi> jediSet = new ArrayList<>();
        Ranks parsedRank = enumParser(rank, Ranks.class);
        int temp = Integer.MAX_VALUE;
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                for (Jedi jedi : celestialObject.getJedis()) {
                    if (jedi.getRank().equals(parsedRank)) {
                        if (jedi.getAge() < temp) {
                            temp = jedi.getAge();
                        }
                    }
                }
            }
        }
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                for (Jedi jedi : celestialObject.getJedis()) {
                    if (jedi.getAge() == temp) {
                        jediSet.add(jedi);
                    }
                }
            }
        }
        jediSet.sort(Comparator.comparing(Jedi::getName));
        System.out.println(jediSet.get(0));
    }

    public void getMostUsedSaberColor(String planetName) {
        Map<SaberColors, Integer> saberColorsCounts = new HashMap<>();
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                for (Jedi jedi : celestialObject.getJedis()) {
                    if (jedi.getRank().equals(Ranks.GRAND_MASTER)) {
                        SaberColors saberColor = jedi.getSaberColor();
                        saberColorsCounts.put(saberColor, saberColorsCounts.getOrDefault(saberColor, 0) + 1);
                    }
                }
            }
        }
        SaberColors mostUsedSaberColor = null;
        int maxCount = 0;
        for (Map.Entry<SaberColors, Integer> entry : saberColorsCounts.entrySet()) {
            SaberColors saberColors = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                mostUsedSaberColor = saberColors;
                maxCount = count;
            }
        }
        if (mostUsedSaberColor == null) {
            System.out.println("No Jedi found with the specified planet and rank.");
        }
        System.out.println("Most used saber color between jedis with rank GRAND_MASTER on the selected planet: " + mostUsedSaberColor);
    }

    public void getMostUsedSaberColor(String planetName, String rank) {
        Map<SaberColors, Integer> saberColorsCounts = new HashMap<>();
        Ranks parsedRank = enumParser(rank, Ranks.class);
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                for (Jedi jedi : celestialObject.getJedis()) {
                    if (jedi.getRank().equals(parsedRank)) {
                        SaberColors saberColor = jedi.getSaberColor();
                        saberColorsCounts.put(saberColor, saberColorsCounts.getOrDefault(saberColor, 0) + 1);
                    }
                }
            }
        }
        SaberColors mostUsedSaberColor = null;
        int maxCount = 0;
        for (Map.Entry<SaberColors, Integer> entry : saberColorsCounts.entrySet()) {
            SaberColors saberColors = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                mostUsedSaberColor = saberColors;
                maxCount = count;
            }
        }
        if (mostUsedSaberColor == null) {
            System.out.println("No Jedi found with the specified planet and rank.");
        }
        System.out.println("Most used saber color between jedis on the selected planet: " + mostUsedSaberColor);
    }

    private void printPlanet(CelestialObject planetName) {
        JediComparator jediComparator = new JediComparator();
        List<Jedi> jediList = new ArrayList<>(planetName.getJedis());
        jediList.sort(jediComparator);
        for (Jedi jedi : jediList) {
            System.out.println(jedi);
        }
        System.out.println("No planet with such name in the galaxy");
    }

    private void printJedi(CelestialObject celestialObject, String jediName) {
        for (Jedi jedi : celestialObject.getJedis()) {
            if (jedi.getName().equals(jediName)) {
                System.out.println(jedi);
                System.out.println(celestialObject.getName());
            }
        }
        System.out.println("No Jedi with such name in the galaxy");
    }

    public void print(String value) {
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(value)) {
                printPlanet(celestialObject);
            } else {
                printJedi(celestialObject, value);
            }
        }
    }

    public void compareCitizens(String firstPlanetName, String secondPlanetName) {
        JediComparator jediComparator = new JediComparator();
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(firstPlanetName)) {
                for (CelestialObject celestialObject1 : galaxy.getCelestialObjectSet()) {
                    if (celestialObject1.getName().equals(secondPlanetName)) {
                        List<Jedi> jediList = new ArrayList<>(celestialObject.getJedis());
                        List<Jedi> jediList1 = new ArrayList<>(celestialObject1.getJedis());
                        List<Jedi> combinedList = new ArrayList<>();
                        combinedList.addAll(jediList);
                        combinedList.addAll(jediList1);
                        combinedList.sort(jediComparator);
                        for (Jedi jedi : combinedList) {
                            System.out.println(jedi);
                        }
                    }
                }
            }
        }
    }

    public void help() {
        System.out.println("""
                Welcome to the Star Wars Universe:
                open <fileName>
                close
                save
                saveAs <fileName>
                help
                exit
                addplanet <planetName> <celestialType>
                createjedi <planetName> <jediName> <jediAge> <jediRank> <saberColor> <jediStrength>
                removejedi <jediName> <planetName>
                promotejedi <jediName> <multiplier>
                demotejedi <jediName> <multiplier>
                getstrongestjedi <planetName>
                getmostusedsabercolor <planetName> <jediRank>
                getmostusedsabercolor <planetName>
                print <planetName>
                print <jediName>
                comparecitizens <firstPlanet> <secondPlanet
                getyoungestjedi <planetName> <jediRank>""");
    }


}
