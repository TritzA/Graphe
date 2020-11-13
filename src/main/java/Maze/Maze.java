package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {

    /** TODO
     * Returns the distance of the shortest path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    private static void setVoisins(int x, int y, ArrayList<ArrayList<Tile>> maze) {
        Tile l, r, u, d;
        Tile th = maze.get(x).get(y);

        try {
            l = getValue(x, y - 1, maze);
            th.addList(l);
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            r = getValue(x, y + 1, maze);
            th.addList(r);
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            u = getValue(x - 1, y, maze);
            th.addList(u);
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            d = getValue(x - 1, y, maze);
            th.addList(d);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private static Tile getValue(int x, int y, ArrayList<ArrayList<Tile>> maze){
        return maze.get(x).get(y);
    }

    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        Boolean con = false;
        Stack<Tile> q = new Stack<Tile>();
        for (int j = 0; j < maze.size(); j++) {
           for(int i = 0; i < maze.get(j).size(); i++){
               setVoisins(j, i, maze);
               Tile th = maze.get(j).get(i);

                if(th.toString().equals("*")){
                    if(q.empty()) {
                        th.setDist(0);
                        q.push(th);
                    }else
                        con = true;
                }else{
                    th.setDist(-1);
                }
            }
        }

        if(!con){
            return null;
        }

        while(!q.empty()){
            System.out.println("AAAAAAAAAAAAAAAAAAAAA");
            Tile t = q.pop();
            System.out.println(t.getDist());
            for(Tile p: t.getList()){
                if(p.getDist() == -1) {
                    System.out.println("BBBBBBBBBBB");
                    p.setDist(t.getDist() + 1);
                    q.push(p);
                    if(p.toString().equals("*")){
                        return p.getDist();
                    }
                }
            }
        }

        return null;
    }

    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}

