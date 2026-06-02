package worldGen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Main {
	
	private static boolean up, down, left, right, sprint;
	private static boolean lClick;
	private static float clickX, clickY;
	private static float speed = 1;

	private static int brushRadius = 4;
	
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
        			case KeyEvent.VK_SHIFT -> sprint = true;
        		
        		
        		}
        	}
        	
        	public void keyReleased(KeyEvent e) {
        		switch (e.getKeyCode()) {
        			case KeyEvent.VK_W -> up = false;
        			case KeyEvent.VK_A -> left = false;
        			case KeyEvent.VK_S -> down = false;
        			case KeyEvent.VK_D -> right = false;
        			case KeyEvent.VK_SHIFT -> sprint = false;
        		
        		
        		}
        	}
        	
        });
        
        panel.addMouseListener(new MouseAdapter() {
        	
        	public void mousePressed(MouseEvent m) {
        		if (m.getButton() == MouseEvent.BUTTON1) {
        			lClick = true;
        		}
        	}
        	
        	public void mouseDragged(MouseEvent m) {
        		clickX = m.getX();
        		clickY = m.getY();
        		System.out.println("DREAGGED");
        	}
        	
        	public void mouseReleased(MouseEvent m) {
        		if (m.getButton() == MouseEvent.BUTTON1) {
        			lClick = false;
        		}
        	}
        	
        });
        
        panel.addMouseMotionListener(new MouseAdapter() {
        	public void mouseDragged(MouseEvent m) {
        		clickX = m.getX();
        		clickY = m.getY();
        	}
        });
        
        new Timer(16, e -> {
        	float dx = 0, dy = 0;
        	
        	
        	int pX = (int)Math.floor(panel.camX() + (panel.getWidth() / 2f) / panel.TILE_SIZE());
        	int pY = (int)Math.floor(panel.camY() + (panel.getHeight() / 2f) / panel.TILE_SIZE());
        	


        	
        	Tile tile = world.tileAt(pX, pY);
        	if (tile == Tile.WATER) speed = 1;
        	else speed = 2;
        	
        	if (sprint) speed += 1;
        	else speed = 2;
        	
        	//Check Top Left and Right
        	if (up && world.tileAt(pX, pY - 1) != Tile.ROCK 
        			&& world.tileAt(pX + WorldPanel.TILE_SIZE * 5, pY - 1) != Tile.ROCK) dy = -speed;
        	//Check Bottom Left and Right
        	if (down && world.tileAt(pX, pY + WorldPanel.TILE_SIZE * 10 + 1) != Tile.ROCK 
        			&& world.tileAt(pX + WorldPanel.TILE_SIZE * 5, pY + WorldPanel.TILE_SIZE * 10 + 1) != Tile.ROCK) dy = speed;
        	//Check Top Left and Bottom Left
        	if (left && world.tileAt(pX - 1, pY) != Tile.ROCK 
        		&& world.tileAt(pX - 1, pY + WorldPanel.TILE_SIZE * 10) != Tile.ROCK) dx = -speed;
        	//Check Top Right and Bottom Right
        	if (right && world.tileAt(pX + WorldPanel.TILE_SIZE * 5 + 1, pY) != Tile.ROCK
        			&& world.tileAt(pX + WorldPanel.TILE_SIZE * 5 + 1, pY + WorldPanel.TILE_SIZE * 10) != Tile.ROCK) dx = speed;
        	
        	
        	//Placing
        	if (lClick) {
        		float worldX = panel.camX() + (clickX / panel.TILE_SIZE());
        		float worldY = panel.camY() + (clickY / panel.TILE_SIZE());
        		
        		world.place(worldX, worldY, brushRadius);
        		panel.repaint();
        	}
        	

        	
        	int tilesX = panel.getWidth() / panel.TILE_SIZE;
        	int tilesY = panel.getHeight() / panel.TILE_SIZE();
        	


        	
        	if (dx != 0 || dy != 0) {
        		panel.moveCamera(dx, dy);
        	}
        }).start();
        
        
    }
}
