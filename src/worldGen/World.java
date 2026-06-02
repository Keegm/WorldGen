package worldGen;

import java.util.HashMap;
import java.util.Map;

public class World {
    private Generator generator;
    
    private Map<Long, Chunk> chunks = new HashMap<>();

    public World() {
        generator = new Generator(655045427);
    }


    public Tile tileAt(int x, int y) {
    	int chunkX = Math.floorDiv(x, Chunk.SIZE);
    	int chunkY = Math.floorDiv(y, Chunk.SIZE);
    	
    	//Query the Chunk
    	Chunk chunk = getOrCreateChunk(chunkX, chunkY);
    	
    	int localX = Math.floorMod(x,  Chunk.SIZE);
    	int localY = Math.floorMod(y, Chunk.SIZE);
    	
    	return chunk.getTile(localX, localY);
    }
    
    public Chunk getOrCreateChunk(int chunkX, int chunkY) {
    	long key = (((long)chunkX) << 32) ^ (chunkY & 0xffffffffL);

        Chunk c = chunks.get(key);
        if (c == null) {
            c = new Chunk(chunkX, chunkY, generator);
            chunks.put(key, c);
        }
        return c;
    }
    
    

    

}