
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;


public class SelectionSort extends JPanel {
	
	
	private final int WIDTH = 1000, HEIGHT = WIDTH*9/16;
	private final int SIZE = 100;
	private final int BAR_WIDTH = WIDTH / SIZE;
	private int[] bar_height = new int[SIZE];
	private SwingWorker<Void,Void> sorter;
	int i, j;
	
	
	
	private SelectionSort() {
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initBarHeight();
		initSorter();
	}
	
	
	




	private void initSorter() {
	sorter = new SwingWorker<>() {
		@Override
		public Void doInBackground() throws InterruptedException {
			//here is Selection sort algorithm 
			for( i =0; i<bar_height.length-1; i++) {
				int min = i;
				for( j=i+1; j<bar_height.length; j++) {
					if(bar_height[j]<bar_height[min]) {
						min =j;
						Thread.sleep(100);
						repaint();
					}
					
				}
				
				if (min != i) {
					swap (i, min);
					
				}
			}
			
			
			return null;
		} 
		
		
	};
		sorter.execute();
	}


	private void initBarHeight() {
		
		int intervel = HEIGHT/SIZE; 
		for(int i = 0; i < SIZE; i++) {
			bar_height[i] =  (int)(Math.random() * HEIGHT);
		}
		
	}
	
	private void swap(int indexA, int indexB) {
		int temp = bar_height[indexA];
		 bar_height[indexA] =  bar_height[indexB];
		 bar_height[indexB] = temp;
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		for(int i=0; i<SIZE ; i++) {
			
			g.fillRect(i*BAR_WIDTH, 0, BAR_WIDTH, bar_height[i]);
	 
		}
		
		g.setColor(Color.RED);
		g.fillRect(i*BAR_WIDTH, 0, BAR_WIDTH, bar_height[i]);
		
		g.setColor(Color.GREEN);
		g.fillRect((j)*BAR_WIDTH, 0, BAR_WIDTH, bar_height[j]);
		
		
	}
	
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->{
			JFrame frame = new JFrame("Sort Algorithms Visualizer");
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(new SelectionSort());
			frame.validate();
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});

	}

}
