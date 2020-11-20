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

        List<Set<Integer>> listOfAllContinents = new ArrayList<>(); //La liste des continents à retourner
        if(isBreadthFirstSearch){ // méthode avec breadthfirstsearch
            for (int i = 0; i < world.size(); i++) {    // On parcours toute la matrice
                for (int j = 0; j < world.get(i).size(); j++) {
                    if (world.get(i).get(j) != 0) {  // On garde seulement les elements qui sont # 0
                        listOfAllContinents.add(dfs(world, i, j)); // on fait un dfs en les mettant dans
                    }                                              // dans la liste des continents
                }                                               // le set elimine les doublons, d'ou son utilité
            }
            }
        return listOfAllContinents;
    }
// Voila le dfs, qui cherche tous les elements adjacents a un elements non nul,
// ensuite l'adjacent de chaque element adjcent, ainside suite, on utilisé une méthode recursive ici

    public static Set<Integer> dfs(ArrayList<ArrayList<Integer>> world, int i, int j) {
        Set<Integer> S = new HashSet<>(); // on utilise ici un set, car on retourne une liste de set
        while (!S.isEmpty()) {            // qui est listeOfAllContinents
                try {
                    S.add(world.get(i).get(j)); // On met l'élément dans le set
                    world.get(i).set(j, 0);     // et on le change à zéro pour éviter de le reprendre
                    dfs(world, i - 1, j); //up
                    dfs(world, i + 1, j); //down
                    dfs(world, i, j + 1); // right
                    dfs(world, i, j - 1); //left
                } catch (IndexOutOfBoundsException ignored) {
            }
        }
        return S;    // on retourne un continent, qui est un set
        }

    public static void printWorld(ArrayList<ArrayList<Integer>> world) {
        for (ArrayList<Integer> row : world) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}



