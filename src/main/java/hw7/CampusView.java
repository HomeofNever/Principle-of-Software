package hw7;

import hw4.Edge;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CampusView {

    public StringBuilder optionListBuildings(Set<Building> m) {
        StringBuilder result = new StringBuilder();
        for(Building b : m) {
            if (!b.isIntersection())
                result.append(b.getName()).append( ",").append(b.getId()).append("\n");
        }

        return result;
    }

    public StringBuilder printPath(Map<Building, List<Edge<Building, Double>>> minPaths, int r, Building node1, Building node2) {
        StringBuilder result = new StringBuilder();
        if (r == 0) {
                Double totalCost = 0.0;
                if (minPaths.get(node2) != null) {
                    result.append("Path from ").append(node1.getName()).append(" to ").append(node2.getName()).append(":\n");
                    if (!node1.equals(node2)) {
                        List<Edge<Building, Double>> ls = minPaths.get(node2);
                        totalCost = ls.get(ls.size() - 1).getName();
                        for (int i = 1; i < ls.size(); i++) {
                            Building location = ls.get(i).getTo();
                            String name = location.isIntersection() ? "Intersection " + location.getId() : location.getName();
                            result.append("\t").append("Walk ").append(" Direction ").append("to (").append(name).append(")\n");
                        }
                    }
                    result.append(String.format("Total distance: %.3f pixel units\n", totalCost));
                } else {
                    result.append("There is no path from ").append(node1.getName()).append(" to ").append(node2.getName()).append(".\n");
                }
        } else {
            if (r == 4 || r ==1 || r == 3) {
                result.append("Unknown building: [").append(node1.getName()).append("]\n");
            }
            if (r == 3 || r == 2) {
                result.append("Unknown building: [").append(node2.getName()).append("]\n");
            }
        }

        return result;
    }

    public StringBuilder  optionUnknown() {
        return new StringBuilder().append("Unknown option\n");
    }

    public StringBuilder optionListCommands() {
        StringBuilder b = new StringBuilder();
        b.append("b lists all buildings (only buildings) in the form name,id in lexicographic (alphabetical) order of name.\n")
            .append("r prompts the user for the ids or names of two buildings (only buildings!) and prints directions for the shortest route between them.\n")
            .append("q quits the program.\n")
            .append("m prints a menu of all commands. (This Message)\n");

        return b;
    }
}
