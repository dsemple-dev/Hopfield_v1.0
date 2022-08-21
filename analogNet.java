// analogNet.java
// Doug Semple
// MSc Project

// Analog Network

public class analogNet
{
    private Dataset theData; // training data
    private Dataset theResults; // results data
    private double inputNodes[]; // inputs to the network
    private double outputNodes[]; // outputs from the network
    private double theWeights[][]; // weights matrix
    private double theNeurons[]; // the neurons
	private int patterns; // no of patterns in dataset
	private int rows; // number of rows in character grid
	private int cols; // number of columns in character grid
	private int numBits; //number of pixels making up each character
    private double threshold; // the threshold

    // constructor
    public analogNet(Dataset ds, double thold)
    {
		theData = ds;
		
		patterns = theData.getDatasetSize();
		rows = theData.getRows();
		cols = theData.getCols();
		numBits = (rows * cols);
		
		threshold = thold; // setting the threshold
		
		inputNodes = new double[numBits];
		outputNodes = new double [numBits];
		theNeurons = new double[numBits];
		theWeights = new double[numBits][numBits];
		
		theResults = new Dataset(); // creating container to hold results
		theResults.setRows(rows);
		theResults.setCols(cols);
	}
	
	// send training data to inputs
	public void populate(Pattern thePattern)
	{
		int theBits[][] = thePattern.getTheBits(); // geting pixel settings
	
		for (int r = 0; r < rows; r++) // current row
		{
			for(int c = 0; c < cols; c++) // current column
			{
				if (theBits[r][c] == 0) // modify data
				{
					theBits[r][c] = -1;
				}
				inputNodes[(r * cols) + c] = theBits[r][c];
			}
		}
	}// end of populate
	
	// transfer data from input nodes to output nodes
	public void inputs2Outputs()
	{
		for (int node = 0; node < numBits; node++)
		{
			outputNodes[node] = inputNodes[node];
		}
	}// end of inputs2Outputs
	
	// train the weights matrix
	public void trainWeights(int patNo /* which pattern */)
	{
		// loop for each input node
		for (int m = 0; m < numBits; m++) 
		{
			// loop for each input neuron
			for (int n = 0; n < numBits; n++)
			{
				if (patNo == 0) // first pass
				{
					// initialise weights to zero
					theWeights[m][n] = 0;
				}
				
				if (m == n)
				{
					continue; 
					// jump out of loop as
					// this should remain unchanged
				}
				
				if (m > n)
				{
					// last pattern in dataset
					if (patNo == (theData.getDatasetSize() - 1))
					{
						theWeights[m][n] = theWeights[n][m]; // been calculated already
					}
					
					continue; // jump out of loop					
				}
				
				// adjust the weight for the current pattern
				theWeights[m][n] += (outputNodes[m] * outputNodes[n]);
			}
		}	
	}// end of trainWeights
	
	// the feed forward pass
	public void feedForward()
	{
		// cycle through each neuron
		// summing
		for (int m = 0; m < numBits; m++)
		{
			for (int n = 0; n < numBits; n++)
			{
				theNeurons[m] += (inputNodes[n] * theWeights[n][m]);
			}
			
			// sigmoid function
			theNeurons[m] = (2.0 / (1.0 + Math.exp(-1.0 * theNeurons[m])) -1);
		}
		
	}// end of feed forward pass
	
	// test the pattern
	public boolean memoryTest()
	{
		int pixelMatchCount = 0; // variable to store number of matching pixels
		
		for (int m = 0; m < numBits; m++)
		{
			// testing for pixel match
			if (theNeurons[m] == outputNodes[m])
			{
				pixelMatchCount++;
			}
			
			// replacing previous pattern
			outputNodes[m] = theNeurons[m];
		}
		
		// testing to see if whole pattern matches
		if (pixelMatchCount == (numBits - 1))
		{
			return true; // the patterns match completely
		}
		else
		{
			return false; // the patterns do not match
		}
	}// end of memory test
	
	public void outputs2Results()
	{		
		int theData[][] = new int[rows][cols];
		
		// converting to correct format
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				if (outputNodes[(r * cols) + c] > 0)
				{
					theData[r][c] = 1; 
				}
				else
				{
					theData[r][c] = -1;
				}
			}
		}
		
		// adding pattern to container
		theResults.addPattern(new Pattern(theData));
	}
	
	// method to return the results
	public Dataset getResults()
	{
		return theResults;
	}// end of get results
	
	public void feedBack()
	{
		// copy to inputs
		for (int n = 0; n < numBits; n++)
		{
			inputNodes[n] = outputNodes[n];
		}
	}
}