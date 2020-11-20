package Maze;

import java.util.LinkedList;

public enum Tile {
    Floor,
    Wall,
    Exit;

    private int dist;
    private LinkedList<Tile> list = new LinkedList<>();
    @Override
    public String toString() {
        return switch (this) {
            case Exit -> "*";
            case Floor -> "_";
            case Wall -> "#";
        };
    }

    public LinkedList<Tile> getList(){
        return list;
    }
    public void addList(Tile t){
        list.add(t);
    }

    public void setDist(int d){
        this.dist = d;
    }

    public int getDist(){
        return this.dist;
    }

}