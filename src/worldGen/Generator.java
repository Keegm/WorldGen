package worldGen;

public class Generator {

    private int seed;
    
    public Generator(int s) {
        seed = s;
    }

    public Tile getTile(int x, int y) {
        System.out.println(random2D(x, y));
        return null;
    }

    public float random2D(int ix, int iy) {
        int hash = ix * 731231113 + iy * 332216785;
        hash ^= seed;
        hash = (hash ^ (hash >> 13)) * 211342397;
        hash ^= (hash >> 16);
        return (hash & 0x7fffffff) / (float)0x7fffffff;
    }
}