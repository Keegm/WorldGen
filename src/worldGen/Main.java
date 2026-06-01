package worldGen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Main {
	
	private static boolean up, down, left, right;

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
        	int dx = 0, dy = 0;
        	
        	if (up) dy = -5;
        	if (down) dy = 5;
        	if (left) dx = -5;
        	if (right) dx = 5;
        	
        	if (dx != 0 || dy != 0) {
        		panel.moveCamera(dx, dy);
        	}
        }).start();
        
        
    }
}
