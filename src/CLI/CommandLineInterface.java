package CLI;

import commands.CommandService;
import models.Galaxy;

import java.util.Scanner;

public class CommandLineInterface {

    public void run() throws Exception {
        Galaxy galaxy = new Galaxy();
        CommandService commandService = new CommandService(galaxy);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting");
                break;
            }
            flag = processCommand(input, commandService);

        }
        scanner.close();

    }

    private static boolean processCommand(String command, CommandService commandService) throws Exception {
        String[] tokens = command.split(" ");
        String action = tokens[0];


        switch (action) {
            case "help" -> commandService.help();
            case "addplanet" -> commandService.addPlanet(tokens[1], tokens[2]);
            case "createjedi" ->
                    commandService.createJedi(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
            case "removejedi" -> commandService.removeJedi(tokens[1], tokens[2]);
            case "getstrongestjedi" -> commandService.getStrongestJedi(tokens[1]);
            case "promotejedi" -> commandService.promoteJedi(tokens[1], tokens[2]);
            case "demotejedi" -> commandService.demoteJedi(tokens[1], tokens[2]);
            case "getyoungestjedi" -> commandService.getYoungestJedi(tokens[1], tokens[2]);
            case "getmostusedsabercolor" -> {
                try {
                    commandService.getMostUsedSaberColor(tokens[1], tokens[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    commandService.getMostUsedSaberColor(tokens[1]);
                }
            }
            case "print" -> commandService.print(tokens[1]);
            case "comparecitizens" -> commandService.compareCitizens(tokens[1], tokens[2]);
            case "open" -> commandService.open(tokens[1]);
            case "close" -> commandService.close();
            case "save" -> commandService.save();
            case "saveas" -> commandService.saveAs(tokens[1]);
            case "exit" -> {
                return false;
            }
            default -> System.out.println("Unknown command: " + action);
        }
        return true;
    }
}
