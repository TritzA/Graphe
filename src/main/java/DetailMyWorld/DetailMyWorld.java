package DetailMyWorld;

import Maze.Tile;

import java.util.*;
import java.util.stream.Collectors;

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
        if (world.size() == 0)
            return null;
        int numberOfContinent = 0;
        List<Set<Integer>> listOfAllContinents = new ArrayList<>(numberOfContinent);
        if (isBreadthFirstSearch) {
            for (int i = 0; i < world.size(); i++) {
                for (int j = 0; j < world.get(i).size(); j++) {
                    if (world.get(i).get(j) != 0) {
                        numberOfContinent++;
                        listOfAllContinents.add(dfs(world, i, j));
                    }
                }
            }
        }
        else{
            boolean con = true;
        }
        return listOfAllContinents;
    }

   private static Stack<Integer> setVoisins(int x, int y, ArrayList<ArrayList<Integer>> world) {
       int l, r, u, d;
       Stack<Integer> queue = new Stack<>();
       queue.push(world.get(x).get(y));
       try {
           l = getValue(x, y - 1, world);
            queue.push(l);
       } catch (IndexOutOfBoundsException ignored) {
       }

       try {
           r = getValue(x, y + 1, world);
           queue.push(r);
       } catch (IndexOutOfBoundsException ignored) {
       }

       try {
           u = getValue(x - 1, y, world);
           queue.push(u);
       } catch (IndexOutOfBoundsException ignored) {
       }

       try {
           d = getValue(x - 1, y, world);
           queue.push(d);
       } catch (IndexOutOfBoundsException ignored) {
       }
       return queue;
   }

    private static Integer getValue(int x, int y, ArrayList<ArrayList<Integer>> world){
        return world.get(x).get(y);
    }

    public static Set<Integer> dfs(ArrayList<ArrayList<Integer>> world, int i, int j) {
        Set<Integer> S = new HashSet<>();
        S.add(world.get(i).get(j));
        while (!S.isEmpty()) {
            if (i < 0 || i > world.size() || j < 0 || j > world.get(i).size() || world.get(i).get(j) == 0) {
                return null;
            }
            else
                {
                for(Integer edge : setVoisins(i, j, world))
                {
                  if (edge!=0){
                      S.add(edge);
                  }
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



