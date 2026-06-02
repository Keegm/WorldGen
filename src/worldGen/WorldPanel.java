package worldGen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldPanel extends JPanel {
	private World world;
	
	private float cameraX = 0;
	private float cameraY = 0;
	public final static int TILE_SIZE = 2;
	private int dimX = 1920;
	private int dimY = 1080;
	
	//Prebuffered Images
	private BufferedImage grassImg;
	private BufferedImage waterImg;
	private BufferedImage rockImg;
	private BufferedImage sandImg;
	
	public WorldPanel(World w) {
		world = w;
		setPreferredSize(new Dimension(dimX, dimY));
		
		grassImg = makeTileImage(new Color(18, 204, 9));
		waterImg = makeTileImage(Color.BLUE);
		rockImg = makeTileImage(Color.GRAY);
		sandImg = makeTileImage(Color.YELLOW);
	}
	
	private BufferedImage makeTileImage(Color c) {
		BufferedImage img = new BufferedImage(TILE_SIZE, TILE_SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();
		g2.setColor(c);
		g2.fillRect(0, 0, TILE_SIZE, TILE_SIZE);
		
		return img;
	}
	
	protected void paintComponent(Graphics g) {

		
		super.paintComponent(g);
		
    	int baseX = (int)Math.floor(cameraX);
    	int baseY = (int)Math.floor(cameraY);
    	
    	int chunkSizePx = Chunk.SIZE * TILE_SIZE;
    	
    	int startChunkX = Math.floorDiv(baseX, Chunk.SIZE);
        int startChunkY = Math.floorDiv(baseY, Chunk.SIZE);
        
        int endChunkX = Math.floorDiv(baseX + getWidth() / TILE_SIZE, Chunk.SIZE);
        int endChunkY = Math.floorDiv(baseY + getHeight() / TILE_SIZE, Chunk.SIZE);
    	

		
		for(int sy = startChunkY; sy <= endChunkY; sy++) {
			for (int sx = startChunkX; sx <= endChunkX; sx++) {
				
				Chunk chunk = world.getOrCreateChunk(sx, sy);
				BufferedImage img = chunk.getImage();
				
				int drawX = (sx * Chunk.SIZE - baseX) * TILE_SIZE;
				int drawY = (sy * Chunk.SIZE - baseY) * TILE_SIZE;
				
				g.drawImage(img, drawX, drawY, null);
			}
		}
		
		g.setColor(Color.BLACK);
		int px = getWidth() / 2 - TILE_SIZE / 2;
		int py = getHeight() / 2 - TILE_SIZE / 2;
		g.fillRect(px, py, TILE_SIZE * 10, TILE_SIZE * 20);
	}
	
	public void moveCamera(double dx, double dy) {
		cameraX += dx;
		cameraY += dy;
		repaint();
	}
	
	public float camX() {
		return cameraX;
	}
	
	public float camY() {
		return cameraY;
	}
	
	public int TILE_SIZE() {
		return TILE_SIZE;
	}
}
