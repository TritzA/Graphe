package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {

    private static void setNeighbors(int row, int col, ArrayList<ArrayList<Vertex>> mazeV) {
        Vertex left, right, up, down;
        Vertex v = mazeV.get(row).get(col);

        try {
            left = getValue(row, col - 1, mazeV);
            v.addList(left);
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            right = getValue(row, col + 1, mazeV);
            v.addList(right);
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            up = getValue(row - 1, col, mazeV);
            v.addList(up);
        } catch (IndexOutOfBoundsException ignored) {
        }

        try {
            down = getValue(row + 1, col, mazeV);
            v.addList(down);
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    private static Vertex getValue(int row, int col, ArrayList<ArrayList<Vertex>> mazeV) {
        return mazeV.get(row).get(col);
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

        for (ArrayList<Tile> row : maze) {
            ArrayList<Vertex> mazeVRow = new ArrayList<>();
            mazeV.add(rowNumber, mazeVRow);
            rowNumber++;
            for (Tile tile : row) {
                Vertex vertex = new Vertex(tile);
                mazeVRow.add(vertex);
            }
        }

        Stack<Vertex> stack = new Stack<>();

        for (int row = 0; row < mazeV.size(); row++) {

            for (int col = 0; col < mazeV.get(row).size(); col++) {
                setNeighbors(col, row, mazeV);
                Vertex vertex = mazeV.get(row).get(col);

                if (vertex.getTile().toString().equals("_")) {
                    vertex.setDist(-1);// floor

                } else if (vertex.getTile().toString().equals("*")) {

                    if (stack.empty()) {
                        vertex.setDist(0);// entrance
                        stack.push(vertex);
                    } else {
                        canContinue = true;
                        vertex.setDist(-3);// exit
                    }

                } else {
                    vertex.setDist(-2);// wall
                }
            }
        }

        if (!canContinue) {
            return null;
        }

        while (!stack.empty()) {
            Vertex vertexPop = stack.pop();
            for (Vertex vertexAdj : vertexPop.getList()) {

                if (vertexAdj.getDist() == -1 || vertexAdj.getDist() == -3) {// floor or exit
                    vertexAdj.setDist(vertexPop.getDist() + 1);
                    stack.push(vertexAdj);
                    if (vertexAdj.getTile().toString().equals("*")) {
                        return vertexAdj.getDist();
                    }
                }
            }
        }
        return null;
    }

    //Not use, default method
    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}