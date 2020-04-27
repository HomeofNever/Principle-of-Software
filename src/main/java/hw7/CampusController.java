package hw7;

import java.util.Scanner;

public class CampusController {
    private CampusModel m;
    private CampusView v;

    public CampusController(CampusModel m, CampusView v) {
        this.m = m;
        this.v = v;
    }

    public void startSession() {
        Scanner scanner = new Scanner(System. in);
        while (true) {
            String command = scanner. nextLine();
            switch (command) {
                case "b":
                    System.out.print(v.optionListBuildings(m.buildingSet()));
                    break;
                case "m":
                    System.out.print(v.optionListCommands());
                    break;
                case "q":
                    return;
                case "r":
                    System.out.print("First building id/name, followed by Enter: ");
                    String first = scanner.nextLine();
                    System.out.print("Second building id/name, followed by Enter: ");
                    String second = scanner.nextLine();

                    Building a = parseBuildingInput(first);
                    Building b = parseBuildingInput(second);
                    System.out.print(v.printPath(m.findPath(a, b), m.checkNode(a, b), a, b));
                    break;
                default:
                   System.out.print(v.optionUnknown());
                    break;
            }
        }
    }



    // Helper
    private static Integer parseBuildingId(String input) {
        Integer result = null;
        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException ignored) {}

        return result;
    }

    private Building parseBuildingInput(String input) {
        Building a;
        Integer i = parseBuildingId(input);
        if (i == null) {
            a = m.findBuildingByName(input);
        } else {
            a = m.findBuildingById(i);
        }
        if (a == null) {
            a = new Building(input, -1, -1 , -1);
        }
        return a;
    }
}
