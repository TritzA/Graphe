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

    public static List<Set<Integer>> findContinents(ArrayList<ArrayList<Integer>> world, boolean isBreadthFirstSearch) {
        List<Set<Integer>> listOfContinent = new ArrayList<>();
        for (int i = 0; i < world.size(); i++) {
            for (int j = 0; j < world.get(i).size(); j++) {
                if (world.get(i).get(j) != 0) {
                    if (isBreadthFirstSearch) {
                        listOfContinent.add(bfs(world, i, j));
                    } else {
                        listOfContinent.add(dfs(world, i, j));
                    }
                }
            }
        }
        System.out.println(listOfContinent);
        return listOfContinent;
    }

    public static Set<Integer> bfs(ArrayList<ArrayList<Integer>> world, int i, int j) {

        Set<Integer> continent = new HashSet<>();
        Queue<Integer> row = new ArrayDeque<>();
        Queue<Integer> col = new ArrayDeque<>();
        row.offer(i);
        col.offer(j);
        while (!row.isEmpty()) {

            int x, y;
            x = col.poll();
            y = row.poll();
            if (world.get(y).get(x) != 0) {
                continent.add(world.get(y).get(x)); // optimisele code
            }
            world.get(y).set(x, 0);
            if (x != 0) {
                if (world.get(y).get(x - 1) != 0) {
                    col.offer(x - 1);
                    row.offer(y);
                    continent.add(world.get(y).get(x - 1));
                    world.get(y).set(x - 1, 0);
                }
            }

            if (x != world.get(y).size() - 1) {
                if (world.get(y).get(x + 1) != 0) {
                    col.offer(x + 1);
                    row.offer(y);
                    continent.add(world.get(y).get(x + 1));
                    world.get(y).set(x + 1, 0);
                }
            }

            if (y != 0) {
                if (world.get(y - 1).get(x) != 0) {
                    col.offer(x);
                    row.offer(y - 1);
                    continent.add(world.get(y - 1).get(x));
                    world.get(y - 1).set(x, 0);
                }
            }

            if (y != world.size() - 1) {
                if (world.get(y + 1).get(x) != 0) {
                    col.offer(x);
                    row.offer(y + 1);
                    continent.add(world.get(y + 1).get(x));
                    world.get(y + 1).set(x, 0);
                }
            }

        }
        return continent;
    }

    public static Set<Integer> dfs(ArrayList<ArrayList<Integer>> world, int i, int j) {
        int l, r, u, d;
        Set<Integer> continent = new HashSet<>();
        Stack<Integer> row = new Stack<>();
        Stack<Integer> col = new Stack<>();
        row.push(i);
        col.push(j);
        while (!row.isEmpty()) {
            int x, y;
            x = col.pop();
            y = row.pop();
            if (world.get(y).get(x) != 0) {
                continent.add(world.get(y).get(x));
            }
            world.get(y).set(x, 0);

            if (x != 0) {
                if (world.get(y).get(x - 1) != 0) {
                    col.push(x - 1);
                    row.push(y);
                }
            }

            if (x != world.get(y).size() - 1) {
                if (world.get(y).get(x + 1) != 0) {
                    col.push(x + 1);
                    row.push(y);
                }
            }

            if (y != 0) {
                if (world.get(y - 1).get(x) != 0) {
                    col.push(x);
                    row.push(y - 1);
                }
            }

            if (y != world.size() - 1) {
                if (world.get(y + 1).get(x) != 0) {
                    col.push(x);
                    row.push(y + 1);
                }
            }
        }
        return continent;
    }

    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}




