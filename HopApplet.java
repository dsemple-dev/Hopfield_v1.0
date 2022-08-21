// HopApplet.java
// Doug Semple
// MSc Project

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class HopApplet extends JApplet implements ActionListener
{

	private Dataset training;
	private Dataset results;
	private analogNet aNet;
	private binaryNet bNet;
	private int theRows; // number of rows in grid
	private int theCols; // number of columns in grid
	private int inPattern, outPattern; // current index in dataset
	private JButton firstPatternButton; // display first pattern on input grid
	private JButton nextPatternButton; // display next pattern on input grid
	private JButton prevPatternButton; // display last pattern on input grid
	private JButton lastPatternButton; // display last pattern on input grid
	private JButton propagateButton; // run the network
	private JButton weightsButton; // display the weights
	private JTextArea infoDisplay; 
	private JMenuItem theDatasets[]; // Dataset Menu items
	

    // constructor
    public void init()
    {
		// create new datasets
        training = new Dataset();
		results = new Dataset();
		
		// load letters from URL
        //training.readURL("http://dmzweb.scms.rgu.ac.uk/~u0208220/10X10.dat");
		
		training.codeData(); // delete this
		
		// making reults dataset contain a blank pattern so grid is drawn
		results.makeEmpty();
        
		theRows = training.getRows();
		theCols = training.getCols();
		
		inPattern = 0;
		outPattern = 0;
		
		// setting up the canvas
        Container c = getContentPane();
		c.setBackground(Color.white);
		c.setLayout(new GridLayout(4,1));
		
		// padding panels
		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(Color.white);
		c.add(firstPanel);
		JPanel secondPanel = new JPanel();
		secondPanel.setBackground(Color.white);
		c.add(secondPanel);
		JPanel thirdPanel = new JPanel();
		thirdPanel.setBackground(Color.white);
		c.add(thirdPanel);
		
		// fourth panel
		JPanel fourthPanel = new JPanel();
		fourthPanel.setLayout(new FlowLayout());
		c.add(fourthPanel);
		
		// create information display text area
		infoDisplay = new JTextArea("", 4, 70);
		infoDisplay.setEditable(false);
		fourthPanel.add(new JScrollPane(infoDisplay));
		
		// control panel
		JPanel ctrlPanel = new JPanel();
		ctrlPanel.setLayout(new FlowLayout());
		ctrlPanel.setSize(100, 100);
		fourthPanel.add(ctrlPanel);
		
		// next/preb button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,4));
		ctrlPanel.add(buttonPanel);
		
		// create first pattern button
		firstPatternButton = new JButton(createImg("firstPat"));
		firstPatternButton.addActionListener(this);
		buttonPanel.add(firstPatternButton);
		
		// create previous pattern button
		prevPatternButton = new JButton(createImg("prevPat"));
		prevPatternButton.addActionListener(this);
		buttonPanel.add(prevPatternButton);
		
		// create next pattern button
		nextPatternButton = new JButton(createImg("nextPat"));
		nextPatternButton.addActionListener(this);
		buttonPanel.add(nextPatternButton);
		
		// create last pattern button
		lastPatternButton = new JButton(createImg("lastPat"));
		lastPatternButton.addActionListener(this);
		buttonPanel.add(lastPatternButton);
		
		// create propagate button
		propagateButton = new JButton("Propagate");
		propagateButton.addActionListener(this);
		ctrlPanel.add(propagateButton);
		
		// create display weights button
		weightsButton = new JButton("Display Weights");
		weightsButton.addActionListener(this);
		ctrlPanel.add(weightsButton);
		
		// creating menu bar
        JMenuBar theMenuBar = new JMenuBar();
		setJMenuBar(theMenuBar);
		
		// creating Dataset menu
		JMenu datasetMenu = new JMenu("Dataset");
		theMenuBar.add(datasetMenu);
		theDatasets = new JMenuItem[2];
		theDatasets[0] = new JMenuItem("7X5");
		datasetMenu.add(theDatasets[0]);
		theDatasets[1] = new JMenuItem("10X10");
		datasetMenu.add(theDatasets[1]);
	}
	
	// method for loading images for icons
	private ImageIcon createImg(String imgName)
	{
	    String path = "http://dmzweb.scms.rgu.ac.uk/~u0208220/graphics/"+ imgName + ".jpg";
	    URL picURL = this.getClass().getResource(path);

	    if (picURL != null) 
		{
	        return new ImageIcon(picURL);
	    }
		else
		{
	        return null;
	    }
	}

	
	// method to handle action events
	public void actionPerformed (ActionEvent e) 
	{
		if(e.getSource() == nextPatternButton)
		{
			if (inPattern < (training.getDatasetSize() - 1))
			{
				inPattern++;
				repaint();
			}
			else
			{
				infoDisplay.append("Sorry, there are no more patterns in the dataset.\n");
			}
		}
		
		if(e.getSource() == prevPatternButton)
		{
			if (inPattern > 0)
			{
				inPattern--;
				repaint();
			}
			else
			{
				infoDisplay.append("This is the first pattern in the dataset.\n");
			}
		}
		
		if(e.getSource() == firstPatternButton)
		{
			inPattern = 0;
			repaint();
		}
		
		if(e.getSource() == lastPatternButton)
		{
			inPattern = (training.getDatasetSize() - 1);
			repaint();
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		drawGrid(g, "Input Pattern", 150, 100, training, inPattern);
		drawGrid(g, "Output Pattern", 500, 100, results, outPattern);
    }
	
	// reusable code to paint the grids
	public void drawGrid(Graphics g, String theTitle, int xStart, int yStart, Dataset ds, int patternIndex)
	{	
		// getting letter to be drawn
		Pattern drawPattern = ds.getPattern(patternIndex);
		int drawBits[][] = drawPattern.getTheBits();
		
		// calculating pixel size
		int pixelSize = 40;
		
		// writing stuff
		g.setFont(new Font("Times", Font.BOLD, 20));
		g.setColor(Color.darkGray);
		g.drawString(theTitle, (xStart - 5), (yStart - 10));
		
		g.setColor(Color.black);
	    g.drawRect(xStart, yStart, 10 * theCols, 10 * theRows);
		for (int y = 0; y < theRows; y++)
		{
			for (int x = 0; x < theCols; x++)
			{		
				if (drawBits[y][x] == 1) // set colour for pixels set to 1
				{
					g.setColor(Color.black);
				}
				else // set colour for pixels set to 0, -1
				{
					g.setColor(Color.orange);
				}
				g.fillRect((xStart + (x * pixelSize)), (yStart + (y * pixelSize)), pixelSize, pixelSize);
				g.setColor(Color.black);
				g.drawRect((xStart + (x * pixelSize)), (yStart + (y * pixelSize)), pixelSize, pixelSize);
			}
		}
	}
}