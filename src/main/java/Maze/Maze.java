package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {

    private static void setNeighbors(int x, int y, ArrayList<ArrayList<Vertex>> mazeV) {
        Vertex left, right, up, down;
        Vertex v = mazeV.get(x).get(y);

        try {
            left = getValue(x, y - 1, mazeV);
            v.addList(left);
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            right = getValue(x, y + 1, mazeV);
            v.addList(right);
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            up = getValue(x - 1, y, mazeV);
            v.addList(up);
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            down = getValue(x + 1, y, mazeV);
            v.addList(down);
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    private static Vertex getValue(int x, int y, ArrayList<ArrayList<Vertex>> mazeV) {
        return mazeV.get(x).get(y);
    }

    /**
     * TODO
     * Returns the distance of the shortest path within the maze
     *
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        boolean canContinue = false;
        ArrayList<ArrayList<Vertex>> mazeV = new ArrayList<>();
        int rowNumber = 0;
        for (ArrayList<Tile> row: maze) {
            ArrayList<Vertex> mazeVRow = new ArrayList<>();
            mazeV.add(rowNumber, mazeVRow);
            rowNumber++;
            for (Tile t: row) {
                Vertex v = new Vertex(t);
                mazeVRow.add(v);
            }
        }

        Stack<Vertex> stack = new Stack<>();

        for (int row = 0; row < mazeV.size(); row++) {
            for (int col = 0; col < mazeV.get(row).size(); col++) {
                setNeighbors(col, row, mazeV);
                Vertex v = mazeV.get(row).get(col);

                if (v.getTile().toString().equals("_"))
                    v.setDist(-1);// floor
                else if (v.getTile().toString().equals("*")) {
                    if (stack.empty()) {
                        v.setDist(0);// entrance
                        stack.push(v);
                    } else {
                        canContinue = true;
                        v.setDist(-3);// exit
                        System.out.println("*");
                    }
                } else {
                    v.setDist(-2);// wall
                }
            }
        }

        if (!canContinue) {
            return null;
        }

        while (!stack.empty()) {
            Vertex vertex = stack.pop();

            for (Vertex v : vertex.getList()) {
                if (v.getDist() == -1 || v.getDist() == -3 ){
                    v.setDist(vertex.getDist() + 1);
                    stack.push(v);
                    if (v.getTile().toString().equals("*") ) {
                        return v.getDist();
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