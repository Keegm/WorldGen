package worldGen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Main {

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
        			case KeyEvent.VK_W -> panel.moveCamera(0, -5);
        			case KeyEvent.VK_A -> panel.moveCamera(-5, 0);
        			case KeyEvent.VK_S -> panel.moveCamera(0, 5);
        			case KeyEvent.VK_D -> panel.moveCamera(5, 0);
        		
        		
        		}
        	}
        });
    }
}
