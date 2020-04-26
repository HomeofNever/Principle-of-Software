package hw7;

import java.util.Scanner;

public class CampusPaths {

    public static void main(String[] args) {
        CampusModel m = new CampusModel();
        m.createNewGraph("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv ");
        CampusView v = new CampusView();

        CampusController c = new CampusController(m, v);

        c.startSession();
    }
}