package DetailMyWorld;

import Maze.Tile;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.IndexOutOfBoundsException;

public class DetailMyWorld {
    /**
     * TODO
     * Returns a list of all continents with their respective countries within `world`
     *
     * @param world 2D table of shape M x N representing the world
     * 0 : Water Region
     * Positive non-null value : Country region for country of that specific value
     * @param isBreadthFirstSearch
     * If true, algorithm used should be Breadth-First Search
     * If false, algorithm used should be Depth-First Search
     * @return List of all continents with their respective countries
     * Continents should be in order from left to right, top to bottom
     * Each continent should have its countries in order
     */


    public static List<Set<Integer>> findContinents(ArrayList<ArrayList<Integer>> world, boolean isBreadthFirstSearch) {

        List<Set<Integer>> listOfAllContinents = new ArrayList<>();
        if(isBreadthFirstSearch){
            for (int i = 0; i < world.size(); i++) {
                for (int j = 0; j < world.get(i).size(); j++) {
                    if (world.get(i).get(j) != 0) {
                        listOfAllContinents.add(dfs(world, i, j));
                    }
                }
            }
            }
        return listOfAllContinents;
    }

    public static Set<Integer> dfs(ArrayList<ArrayList<Integer>> world, int i, int j) {
        Set<Integer> S = new HashSet<>();
        while (!S.isEmpty()) {
            if (i < 0 || i > world.size() || j < 0 || j > world.get(i).size() || world.get(i).get(j) == 0) {
                return null;
            } else {
                S.add(world.get(i).get(j));
                world.get(i).set(j, 0);
                try {

                    dfs(world, i - 1, j); //up
                    dfs(world, i + 1, j); //down
                    dfs(world, i, j + 1); // right
                    dfs(world, i, j - 1); //left
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        return S;
        }

    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}



