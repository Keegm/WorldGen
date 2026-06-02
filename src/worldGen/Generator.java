package worldGen;

public class Generator {

    private int seed;
    
    public Generator(int s) {
        seed = s;
    }

    public Tile getTile(int x, int y) {
    	float scale = .030f;
        float n = fractalNoise(x * scale, y * scale);
        

        if (n < 0.35f) return Tile.WATER;
        if (n < 0.45f) return Tile.SAND;
        if (n < 0.65f) return Tile.GRASS;
        return Tile.ROCK;
        
    }

    public float random2D(int ix, int iy) {
        int hash = ix * 731231113 + iy * 332216785;
        hash ^= seed;
        hash = (hash ^ (hash >> 13)) * 211342397;
        hash ^= (hash >> 16);
        return (hash & 0x7fffffff) / (float)0x7fffffff;
    }
    
    private float fade(float t) {
        return t * t * (3 - 2 * t);
    }

    private float lerp(float a, float b, float t) {
    	return a + t * (b - a);
    }
    
    public float smoothNoise(float x, float y) {
    	int ix = (int)Math.floor(x);
    	int iy = (int)Math.floor(y);
    	
    	float fx = x - ix;
    	float fy = y - iy;
    	
    	float v00 = random2D(ix, iy);
    	float v10 = random2D(ix + 1, iy);
    	float v01 = random2D(ix, iy + 1);
    	float v11 = random2D(ix + 1, iy + 1);
    	
    	float i1 = lerp(v00, v10, fade(fx));
    	float i2 = lerp(v01, v11, fade(fx));
    	
    	return lerp(i1, i2, fade(fy));
    	
    }
    
    public float fractalNoise(float x, float y) {
    	float total = 0;
    	float frequency = 0.05f;
    	float amplitude = 1.0f;
    	float persistence = 0.5f;
    	float maxValue = 0;
    	
    	for (int i = 0; i < 5; i++) {
    		total += smoothNoise(x * frequency, y * frequency) * amplitude;
    		maxValue += amplitude;
    		
    		frequency *= 2;
    		amplitude *= persistence;
    	}
    	
    	return total / maxValue;
    }
}