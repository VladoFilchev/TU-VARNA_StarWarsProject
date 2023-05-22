package commands;

import EnumHandler.RankHandler;
import enums.CelestialType;
import enums.Ranks;
import enums.SaberColors;
import models.CelestialObject;
import models.Galaxy;
import models.Jedi;

import java.util.*;


public class CommandService {
    private Galaxy galaxy;

    public CommandService(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    private Enum<?> enumParser(String enumeration, Enum<?>[] values, Class enumClass) throws Exception {
        for (Enum<?> types : values) {
            if (types.name().toLowerCase().equals(enumeration)) {
                return Enum.valueOf(enumClass, enumeration.toUpperCase());
            }
        }
        throw new Exception("Type of declared celestial object does not exist");
    }

    public void addPlanet(String planetName, String celestialType) throws Exception {

        CelestialObject celestialObject = new CelestialObject(planetName, (CelestialType) enumParser(celestialType, CelestialType.values(), CelestialType.class));
        if (!galaxy.addCelestialObject(celestialObject)) {
            System.out.println("The celestial object already exists");
        } else {
            System.out.println("The celestial object has been added successfully");
        }

    }

    public void createJedi(String planetName, String jediName, String jediAge, String rank, String saberColors, String jediStrength) throws Exception {
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                Ranks enumRank = (Ranks) enumParser(rank, Ranks.values(), Ranks.class);
                SaberColors enumSaberColor = (SaberColors) enumParser(saberColors, SaberColors.values(), SaberColors.class);
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
                    } else {
                        System.out.println("Unable to promote jedi " + jediName + " already the highest rank");
                    }
                } else {
                    System.out.println("Jedi with name " + jediName + " not found!");
                }
            }
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
                    } else {
                        System.out.println("Unable to demote jedi " + jediName);
                    }
                } else {
                    System.out.println("Jedi with name " + jediName + " not found!");
                }
            }
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

    public void getYoungestJedi(String planetName, String rank) throws Exception {
        List<Jedi> jediSet = new ArrayList<>();
        Ranks parsedRank = (Ranks) enumParser(rank, Ranks.values(), Ranks.class);
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
    }

    public void getMostUsedSaberColor(String planetName, String rank) throws Exception {
        Ranks parsedRank = (Ranks) enumParser(rank, Ranks.values(), Ranks.class);
        for (CelestialObject celestialObject : galaxy.getCelestialObjectSet()) {
            if (celestialObject.getName().equals(planetName)) {
                for (Jedi jedi : celestialObject.getJedis()) {
                    if (jedi.getRank().equals(parsedRank)) {

                    }
                }
            }
        }
    }

    public void printPlanet(String planetName) {

    }

    public void printJedi(String jediName) {

    }

    public void compareCitizens(String firstPlanetName, String secondPlanetName) {

    }

    public void help() {
        System.out.println("Welcome to the Star Wars Universe:" +
                "\naddplanet <planetName> <celestialType>" +
                "\ncreatejedi <planetName> <jediName> <jediAge> <jediRank> <saberColor> <jediStrength>" +
                "\nremovejedi <jediName> <planetName>" +
                "\npromotejedi <jediName> <multiplier>" +
                "\ndemotejedi <jediName> <multiplier>" +
                "\ngetstrongestjedi <planetName>" +
                "\ngetmostusedsabercolor <planetName> <jediRank>" +
                "\ngetmostusedsabercolor <planetName>" +
                "\nprintplanet <planetName>" +
                "\nprintjedi <jediName>" +
                "\ncomparecitizens <firstPlanet> <secondPlanet" +
                "\ngetyoungestjedi <planetName> <jediRank>");
    }


}
