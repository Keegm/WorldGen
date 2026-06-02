package worldGen;

public enum Tile {
    GRASS, ROCK, WATER, SAND;
    
    public Tile next() {
        Tile[] vals = values();
        return vals[(this.ordinal() + 1) % vals.length];
    }
}