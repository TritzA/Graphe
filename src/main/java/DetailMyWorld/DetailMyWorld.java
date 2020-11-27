package DetailMyWorld;

import java.util.*;
import java.util.stream.Collectors;

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
        List<Set<Integer>> listOfContinents = new ArrayList<>();
        for (int r = 0; r < world.size(); r++) {
            for (int c = 0; c < world.get(r).size(); c++) {
                if (world.get(r).get(c) != 0) {
                    if (isBreadthFirstSearch) {
                        listOfContinents.add(bfs(world, r, c));
                    } else {
                        listOfContinents.add(dfs(world, r, c));
                    }
                }
            }
        }
        return listOfContinents;
    }

    public static Set<Integer> bfs(ArrayList<ArrayList<Integer>> world, int r, int c) {
        Set<Integer> continent = new HashSet<>();
        Queue<Integer> rowQueue = new ArrayDeque<>();
        Queue<Integer> colQueue = new ArrayDeque<>();
        boolean[][] marked = new boolean[world.size()][world.get(0).size()];

        rowQueue.offer(r);
        colQueue.offer(c);

        while (!rowQueue.isEmpty()) {

            int col = colQueue.poll();
            int row = rowQueue.poll();

            continent.add(world.get(row).get(col));
            world.get(row).set(col, 0);

            if (col != 0) {
                if (world.get(row).get(col - 1) != 0 && !marked[row][col - 1]) {
                    colQueue.offer(col - 1);
                    rowQueue.offer(row);
                    marked[row][col - 1] = true;
                }
            }

            if (col != world.get(row).size() - 1) {
                if (world.get(row).get(col + 1) != 0 && !marked[row][col + 1]) {
                    colQueue.offer(col + 1);
                    rowQueue.offer(row);
                    marked[row][col + 1] = true;
                }
            }

            if (row != 0) {
                if (world.get(row - 1).get(col) != 0 && !marked[row - 1][col]) {
                    colQueue.offer(col);
                    rowQueue.offer(row - 1);
                    marked[row - 1][col] = true;
                }
            }

            if (row != world.size() - 1) {
                if (world.get(row + 1).get(col) != 0 && !marked[row + 1][col]) {
                    colQueue.offer(col);
                    rowQueue.offer(row + 1);
                    marked[row + 1][col] = true;
                }
            }

        }
        return continent;
    }

    public static Set<Integer> dfs(ArrayList<ArrayList<Integer>> world, int r, int c) {
        Set<Integer> continent = new HashSet<>();
        Stack<Integer> rowStack = new Stack<>();
        Stack<Integer> colStack = new Stack<>();
        boolean[][] marked = new boolean[world.size()][world.get(0).size()];

        rowStack.push(r);
        colStack.push(c);

        while (!rowStack.isEmpty()) {

            int col = colStack.pop();
            int row = rowStack.pop();

            continent.add(world.get(row).get(col));
            world.get(row).set(col, 0);

            if (col != 0) {
                if (world.get(row).get(col - 1) != 0 && !marked[row][col - 1]) {
                    colStack.push(col - 1);
                    rowStack.push(row);
                    marked[row][col - 1] = true;
                }
            }

            if (col != world.get(row).size() - 1 && !marked[row][col + 1]) {
                if (world.get(row).get(col + 1) != 0) {
                    colStack.push(col + 1);
                    rowStack.push(row);
                    marked[row][col + 1] = true;
                }
            }

            if (row != 0) {
                if (world.get(row - 1).get(col) != 0 && !marked[row - 1][col]) {
                    colStack.push(col);
                    rowStack.push(row - 1);
                    marked[row - 1][col] = true;
                }
            }

            if (row != world.size() - 1) {
                if (world.get(row + 1).get(col) != 0 && !marked[row + 1][col]) {
                    colStack.push(col);
                    rowStack.push(row + 1);
                    marked[row + 1][col] = true;
                }
            }

        }
        return continent;
    }

    //Not use, default method
    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}