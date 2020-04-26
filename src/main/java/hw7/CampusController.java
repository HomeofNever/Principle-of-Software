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
                    v.printBuilding(m.buildingSet());
                    break;
                case "m":
                    v.printCommands();
                    break;
                case "q":
                    return;
                case "r":
                    System.out.println("First building id/name, followed by Enter: ");
                    String first = scanner.nextLine();
                    System.out.println("Second building id/name, followed by Enter:");
                    String second = scanner.nextLine();

                    Building a = m.findBuilding(first);
                    Building b = m.findBuilding(second);
                    v.printPath(m.findPath(a, b), m.checkNode(a, b), a, b);
                default:
                    v.printUnknown();
                    break;
            }
        }
    }
}
