package Maze;

import java.util.LinkedList;

public class Vertex {
    private int dist;
    private LinkedList<Vertex> list = new LinkedList<>();
    Tile tile;

    public Vertex(Tile tile) {
        this.tile = tile;
    }

    public LinkedList<Vertex> getList(){
        return list;
    }
    public void addList(Vertex t){
        list.add(t);
    }

    public void setDist(int d){
        this.dist = d;
    }

    public int getDist(){
        return this.dist;
    }

    public Tile getTile(){
        return this.tile;
    }
}