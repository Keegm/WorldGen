package worldGen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Main {
	
	private static boolean up, down, left, right;
	private static float speed = 1;

    public static void main(String[] args) {
        World world = new World();
        WorldPanel panel = new WorldPanel(world);

        JFrame frame = new JFrame("WorldGen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        
        
        
        frame.addKeyListener(new KeyAdapter() {
        	
        	
        	public void keyPressed(KeyEvent e) {
        		switch (e.getKeyCode()) {
        			case KeyEvent.VK_W -> up = true;
        			case KeyEvent.VK_A -> left = true;
        			case KeyEvent.VK_S -> down = true;
        			case KeyEvent.VK_D -> right = true;
        		
        		
        		}
        	}
        	
        	public void keyReleased(KeyEvent e) {
        		switch (e.getKeyCode()) {
        			case KeyEvent.VK_W -> up = false;
        			case KeyEvent.VK_A -> left = false;
        			case KeyEvent.VK_S -> down = false;
        			case KeyEvent.VK_D -> right = false;
        		
        		
        		}
        	}
        });
        
        new Timer(16, e -> {
        	float dx = 0, dy = 0;
        	
        	if (up) dy = -speed;
        	if (down) dy = speed;
        	if (left) dx = -speed;
        	if (right) dx = speed;
        	
        	int tilesX = panel.getWidth() / panel.TILE_SIZE;
        	int tilesY = panel.getHeight() / panel.TILE_SIZE();
        	
        	int pX = (int)Math.floor(panel.camX() + (panel.getWidth() / 2f) / panel.TILE_SIZE());
        	int pY = (int)Math.floor(panel.camY() + (panel.getHeight() / 2f) / panel.TILE_SIZE());

        	Tile tile = world.tileAt(pX, pY);
        	if (tile == Tile.WATER) speed = 1;
        	else speed = 2;
        	
        	if (dx != 0 || dy != 0) {
        		panel.moveCamera(dx, dy);
        	}
        }).start();
        
        
    }
}
