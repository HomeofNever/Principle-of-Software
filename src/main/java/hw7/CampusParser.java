package hw7;

import java.util.*;
import java.io.*;

public class CampusParser {
    /*
        This class is a static helper class and should never be constructed.
        So there is No AF or RI presented.
     */

    /** @param: filename The path to the "CSV" file that contains the <hero, book> pairs
     @param: charsInBooks The Map that stores parsed <book, Set-of-heros-in-book> pairs;
     usually an empty Map
     @param: chars The Set that stores parsed characters; usually an empty Set.
     @effects: adds parsed <book, Set-of-heros-in-book> pairs to Map charsInBooks;
     adds parsed characters to Set chars
     @throws: IOException if file cannot be read of file not a CSV file
     */
    public static void readBuildings(String filename, Set<Building> buildingList)
            throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;

        while ((line = reader.readLine()) != null) {
            String[] res = line.split(",", 4);
            if (res.length != 4) {
                throw new IOException("File "+filename+" not a CSV (\"BUILDING\",\"ID\", \"x\", \"y\") file.");
            }
            buildingList.add(new Building(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]), Integer.parseInt(res[3])));
        }
    }

    public static void readBuildingConnections(String filename, Map<Integer, Integer> connections)
            throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;

        while ((line = reader.readLine()) != null) {
            String[] res = line.split(",", 2);
            if (res.length != 2) {
                throw new IOException("File "+filename+" not a CSV (\"ID\",\"ID\") file.");
            }
            connections.put(Integer.parseInt(res[0]), Integer.parseInt(res[1]));
        }
    }

    public static void main(String[] arg) throws IOException {
//        String  edgeFile =  "data/RPI_map_data_Edges.csv";
//        String buildingFile = "data/RPI_map_data_Nodes.csv";
//
//        List<Building> b = new LinkedList<>();
//        Map<Integer, Integer> m = new HashMap<>();
//        readBulidings(buildingFile, b);
//        readBulidingConnections(edgeFile, m);
//
//        System.out.println(b);
//        System.out.println(m);
    }
}
