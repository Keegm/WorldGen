package worldGen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Chunk {
	private Tile[][] tiles;
	private final int x;
	private final int y;
	public static final int SIZE = 32;
	
	public long timeLastUsed;
	
	private BufferedImage image;
	
	
	
	public Chunk(int x, int y, Generator generator) {
		tiles = new Tile[SIZE][SIZE];
		this.x = x;
		this.y = y;
		
		timeLastUsed = System.nanoTime();
		
		generate(generator);
		renderChunkImage();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	private void generate(Generator generator) {
		int startX = x * SIZE;
		int startY = y * SIZE;
		
		for (int lx = 0; lx < SIZE; lx++) {
			for (int ly = 0; ly < SIZE; ly++) {
				tiles[lx][ly] = generator.getTile(startX + lx, startY + ly);
			}
		}
	}
	
	public Tile getTile(int localX, int localY) {
		return tiles[localX][localY];
	}
	
	public boolean equals(int cx, int cy) {
		return x == cx && y == cy;
	}
	
	public void renderChunkImage() {
		image = new BufferedImage(SIZE * WorldPanel.TILE_SIZE, 
				SIZE * WorldPanel.TILE_SIZE, 
				BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = image.createGraphics();
		
		for(int lx = 0; lx < SIZE; lx++) {
			for (int ly = 0; ly < SIZE; ly++) {
				Tile t = tiles[lx][ly];
				Color c = switch (t) {
					case GRASS -> new Color(18, 204, 9);
	                case ROCK  -> Color.GRAY;
	                case WATER -> Color.BLUE;
	                case SAND  -> Color.YELLOW;
				};
				
				g.setColor(c);
	            g.fillRect(lx * WorldPanel.TILE_SIZE,
	                       ly * WorldPanel.TILE_SIZE,
	                       WorldPanel.TILE_SIZE,
	                       WorldPanel.TILE_SIZE);
			}
		}
		
		g.dispose();
		
	}
}
