
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ExampleFrame extends JFrame implements ActionListener {
	
	private final JButton act;
	private final JPanel draw;
	
	private final static Dimension size = new Dimension(640, 480);
	
	public ExampleFrame() {
		this.setPreferredSize(size);
		this.setSize(size);
		this.setMaximumSize(size);
		
		draw = new JPanel();
		draw.setSize(30, 30);
		draw.setMaximumSize(new Dimension(30, 30));
		draw.setPreferredSize(new Dimension(30, 30));
		draw.setBackground(new Color(255, 255, 255));
		
		act = new JButton("Woosh!");
		
		act.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(act.getLabel());
				draw.getGraphics().setColor(new Color(255, 255, 255));
				draw.getGraphics().drawRect(0, 0, draw.getWidth(), draw.getHeight());
			}
			
		});
		
		draw.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				draw.getGraphics().drawRect(e.getX(), e.getY(), 1, 1);
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				//draw.getGraphics().drawRect(e.getX(), e.getY(), 1, 1);
			}
			
		});
		
		this.add(draw, BorderLayout.CENTER);
		this.add(act, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		int sum = 0;
		for(int i = 0; i <= 2; i++) {
			for(int j = 1; j <= 3; j++) {
				sum += i*j;
			}
		}
		System.out.println(sum);
		final ExampleFrame frame = new ExampleFrame();
		frame.setSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("CLICKED");
		
	}
	
}
