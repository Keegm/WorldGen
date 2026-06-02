package worldGen;

import java.util.HashMap;
import java.util.Map;

public class World {
    private Generator generator;
    
    private Map<Long, Chunk> chunks = new HashMap<>();
    private static final int MAX_CHUNKS = 2048;
    
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
        if (c != null) {
        	c.timeLastUsed = System.nanoTime();
        }
        if (c == null) {
        	if (chunks.size() == MAX_CHUNKS) {
        		unloadLeastRecentlyUsedChunk();
        	}
            c = new Chunk(chunkX, chunkY, generator);
            chunks.put(key, c);
        }
        return c;
    }
    
    public void unloadLeastRecentlyUsedChunk() {
    	long oldest = Long.MAX_VALUE;
    	long oldestKey = 0;
    	for (var pair : chunks.entrySet()) {
    		Chunk c = pair.getValue();
    		if (c.timeLastUsed < oldest) {
    			oldest = c.timeLastUsed;
    			oldestKey = pair.getKey();
    		}
    	}
    	
    	chunks.remove(oldestKey);
    }
    
    

    

}