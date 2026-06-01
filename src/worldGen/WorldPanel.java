package worldGen;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {
	private World world;
	
	private int cameraX = 0;
	private int cameraY = 0;
	private int tileSize = 10;
	private int dimX = 1920;
	private int dimY = 1080;
	
	public WorldPanel(World w) {
		world = w;
		setPreferredSize(new Dimension(dimX, dimY));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		for(int sy = 0; sy < dimY / tileSize; sy++) {
			for (int sx = 0; sx < dimX / tileSize; sx++) {
				
				int worldX = cameraX + sx;
				int worldY = cameraY + sy;
				
				Tile t = world.tileAt(worldX, worldY);
				switch (t) {
					case GRASS -> g.setColor(new Color(18, 204, 9));
					case ROCK -> g.setColor(Color.GRAY);
					case WATER -> g.setColor(Color.BLUE);
					case SAND -> g.setColor(Color.YELLOW);
				}
				
				g.fillRect(sx * tileSize, sy * tileSize, tileSize, tileSize);
			}
		}
		
		g.setColor(Color.BLACK);
		int px = getWidth() / 2 - tileSize / 2;
		int py = getHeight() / 2 - tileSize / 2;
		
		g.fillRect(px, py, tileSize, tileSize + 6);
	}
	
	public void moveCamera(int dx, int dy) {
		cameraX += dx;
		cameraY += dy;
		repaint();
	}
}
