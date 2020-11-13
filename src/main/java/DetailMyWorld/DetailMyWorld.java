package DetailMyWorld;

import java.util.*;
import java.util.stream.Collectors;

public class DetailMyWorld {
    /** TODO
     * Returns a list of all continents with their respective countries within `world`
     * @param world 2D table of shape M x N representing the world
     *                  0 : Water Region
     *                  Positive non-null value : Country region for country of that specific value
     * @param isBreadthFirstSearch
     *              If true, algorithm used should be Breadth-First Search
     *              If false, algorithm used should be Depth-First Search
     * @return List of all continents with their respective countries
     *              Continents should be in order from left to right, top to bottom
     *              Each continent should have its countries in order
     */
    public static List<Set<Integer>> findContinents(ArrayList<ArrayList<Integer>> world, boolean isBreadthFirstSearch) {
        return null;
    }


    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
