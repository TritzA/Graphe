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
     * @param world                2D table of shape M x N representing the world
     *                             0 : Water Region
     *                             Positive non-null value : Country region for country of that specific value
     * @param isBreadthFirstSearch If true, algorithm used should be Breadth-First Search
     *                             If false, algorithm used should be Depth-First Search
     * @return List of all continents with their respective countries
     * Continents should be in order from left to right, top to bottom
     * Each continent should have its countries in order
     */
    //private static boolean[][] isVisited = new boolean [100000][100000];
    public static List<Set<Integer>> findContinents(ArrayList<ArrayList<Integer>> world, boolean isBreadthFirstSearch) {
        List<Set<Integer>> listOfContinent = new ArrayList<>();
        for (int i = 0; i < world.size(); i++) {
            for (int j = 0; j < world.get(i).size(); j++) {
                if (world.get(i).get(j) != 0) {
                    listOfContinent.add(dfs(world, i, j));
                }
            }
        }
        System.out.println(listOfContinent);
        return listOfContinent;
    }

    public static Set<Integer> dfs(ArrayList<ArrayList<Integer>> world, int i, int j) {
        Set<Integer> continent = new HashSet<>();
        if (world.get(i).get(j) == 0) {
            return continent;
        }
        continent.add(world.get(i).get(j));

        world.get(i).set(j, 0);

        if (i != 0) {
            continent.addAll(Objects.requireNonNull(dfs(world, i - 1, j)));
        }

        if (i != world.size() - 1) {
            continent.addAll(Objects.requireNonNull(dfs(world, i + 1, j)));
        }
        if (j != 0) {
            continent.addAll(Objects.requireNonNull(dfs(world, i, j - 1)));
        }
        if (j != world.get(i).size() - 1) {
            continent.addAll(Objects.requireNonNull(dfs(world, i, j + 1)));
        }
        return continent;
    }

    public static Set<Integer> bfs(ArrayList<ArrayList<Integer>> world, int i, int j) {
        int l, r, u, d;
        for (int k = 0; k < 4; k++) {
            switch (i) {
                case 0:
                    l = world.get(i).get(j - 1);
                    break;
                case 1:
                    r = world.get(i).get(j + 1);
                    break;
                case 2:
                    u = world.get(i - 1).get(j);
                    break;
                case 3:
                    l = world.get(i + 1).get(j);
                    break;
            }
        }
        return null;
    }

    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}




