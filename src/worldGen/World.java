package worldGen;


public class World {
    private Generator generator;

    public World() {
        generator = new Generator(655043217);
    }


    public Tile tileAt(int x, int y) {

        return generator.getTile(x, y);
    }

    

}