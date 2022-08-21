// Dataset.java
// Doug Semple
// MSc Project

// a container class for patterns

import java.util.*;
import java.io.*;
import java.util.StringTokenizer;
import java.net.*;

public class Dataset
{
    // have used vectors as the size of the dataset may change
    private Vector theData;
    
    // variables to store dimensions of character grid
    private int gridRows;
    private int gridCols;
    
    // constructor
    public Dataset()
    {
        theData = new Vector();
        
        // setting grid dimensions to default
        // size of 10 by 10
        gridRows = 10;
        gridCols = 10;
    }
    
    // add a character to the dataset
    public void addPattern(Pattern newPattern)
    {
        theData.add(newPattern);
    }
    
    // returns the size of the dataset
    public int getDatasetSize()
    {
        return theData.size();
    }
    
    // set the number of rows in the grid
    public void setRows(int rows)
    {
        gridRows = rows;
    }
    
    // return the number of rows in the grid
    public int getRows()
    {
        return gridRows;
    }
    
    // set the number of columns in the grid
    public void setCols(int cols)
    {
        gridCols = cols;
    }
    
    // return the number of columns in the grid
    public int getCols()
    {
        return gridCols;
    }
    
    // return Pattern object held at position X
    public Pattern getPattern(int X)
    {
        return (Pattern)theData.elementAt(X);
    }
	
	// load data from file
    public void readFile(String theFileName)
    {
        try
        {
            FileReader dataFile = new FileReader(new File(theFileName));
            BufferedReader dataStream = new BufferedReader(dataFile);
            
            // getting character grid sizes from file
            int theRows = Integer.parseInt(dataStream.readLine());
            int theCols = Integer.parseInt(dataStream.readLine());
            
            // setting character grid sizes in dataset
            setRows(theRows);
            setCols(theCols);
            
            String stg = dataStream.readLine();
            
            while (stg != null) // checking for end of file
            {   
                
                int dataBits[][] = new int[theRows][theCols];
                
                for (int y = 0; y < theRows; y++)
                {
                    String rowOfData = dataStream.readLine();
                    StringTokenizer st = new StringTokenizer(rowOfData);
                    for (int x = 0; x < theCols; x++)
                    {
                        dataBits[y][x] = Integer.parseInt(st.nextToken());
                    }
                }
                
                // create new letter from loaded data
                Pattern thePattern = new Pattern(dataBits);
                
                // add new letter to dataset container
                addPattern(thePattern);
                
                // getting next line of data from file
                stg = dataStream.readLine();
            }
            dataStream.close(); // close file
        }
        catch (NullPointerException e)
		{
			System.out.println(e.getMessage());
		}
        catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	// load data from URL
    public void readURL(String theURLName)
    {
        try
        {
			URL theDataURL = new URL(theURLName);
            InputStreamReader dataFile = new InputStreamReader(theDataURL.openStream());
            BufferedReader dataStream = new BufferedReader(dataFile);
            
            // getting character grid sizes from file
            int theRows = Integer.parseInt(dataStream.readLine());
            int theCols = Integer.parseInt(dataStream.readLine());
            
            // setting character grid sizes in dataset
            setRows(theRows);
            setCols(theCols);
            
            String stg = dataStream.readLine();
            
            while (stg != null) // checking for end of file
            {   
                
                int dataBits[][] = new int[theRows][theCols];
                
                for (int y = 0; y < theRows; y++)
                {
                    String rowOfData = dataStream.readLine();
                    StringTokenizer st = new StringTokenizer(rowOfData);
                    for (int x = 0; x < theCols; x++)
                    {
                        dataBits[y][x] = Integer.parseInt(st.nextToken());
                    }
                }
                
                // create new letter from loaded data
                Pattern thePattern = new Pattern(dataBits);
                
                // add new letter to dataset container
                addPattern(thePattern);
                
                // getting next line of data from file
                stg = dataStream.readLine();
            }
            dataStream.close(); // close file
        }
        catch (NullPointerException e){System.out.println(e.getMessage());}
        catch (Exception ex){System.out.println(ex.getMessage());}
    }
	
	// hard code data into Dataset
    public void codeData()
    {
		// setting character grid sizes in dataset
        setRows(7);
        setCols(5);
		
		// creating Patterns
		int dataBitsA[][] = {{0,0,1,0,0},{0,1,1,1,0},{0,1,0,1,0},{1,1,0,1,1},{1,1,1,1,1},{1,0,0,0,1},{1,0,0,0,1}};
		Pattern A = new Pattern(dataBitsA);
		int dataBitsB[][] = {{1,1,1,1,0},{1,0,0,0,1},{1,0,0,0,1},{1,1,1,1,0},{1,0,0,0,1},{1,0,0,0,1},{1,1,1,1,0}};
		Pattern B = new Pattern(dataBitsB);
		int dataBitsC[][] = {{0,0,1,1,1},{0,1,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{0,1,0,0,0},{0,0,1,1,1}};
		Pattern C = new Pattern(dataBitsC);
		int dataBitsD[][] = {{1,1,1,0,0},{1,0,0,1,0},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,1,0},{1,1,1,0,0}};
		Pattern D = new Pattern(dataBitsD);
		int dataBitsE[][] = {{1,1,1,1,1},{1,0,0,0,0},{1,0,0,0,0},{1,1,1,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,1,1,1,1}};
		Pattern E = new Pattern(dataBitsE);
		int dataBitsF[][] = {{1,1,1,1,1},{1,0,0,0,0},{1,0,0,0,0},{1,1,1,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};
		Pattern F = new Pattern(dataBitsF);
		int dataBitsG[][] = {{0,0,1,1,0},{0,1,0,0,1},{1,0,0,0,0},{1,0,0,0,0},{0,1,0,0,1},{0,0,1,1,1},{0,0,0,0,1}};
		Pattern G = new Pattern(dataBitsG);
		int dataBitsH[][] = {{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{1,1,1,1,1},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1}};
		Pattern H = new Pattern(dataBitsH);
		int dataBitsI[][] = {{0,1,1,1,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,1,1,1,0}};
		Pattern I = new Pattern(dataBitsI);
		int dataBitsJ[][] = {{0,1,1,1,1},{0,0,0,1,0},{0,0,0,1,0},{0,0,0,1,0},{0,0,0,1,0},{1,0,1,0,0},{1,1,0,0,0}};
		Pattern J = new Pattern(dataBitsJ);
		int dataBitsK[][] = {{1,0,0,0,0},{1,0,0,0,0},{1,0,0,1,0},{1,0,1,0,0},{1,1,0,0,0},{1,0,1,0,0},{1,0,0,1,0}};
		Pattern K = new Pattern(dataBitsK);
		int dataBitsL[][] = {{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,1,1,1,1}};
		Pattern L = new Pattern(dataBitsL);
		int dataBitsM[][] = {{1,0,0,0,1},{1,1,0,1,1},{1,0,1,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1}};
		Pattern M = new Pattern(dataBitsM);
		int dataBitsN[][] = {{1,0,0,0,1},{1,1,0,0,1},{1,0,1,0,1},{1,0,1,0,1},{1,0,1,0,1},{1,0,0,1,1},{1,0,0,0,1}};
		Pattern N = new Pattern(dataBitsN);
		int dataBitsO[][] = {{0,0,1,0,0},{0,1,0,1,0},{0,1,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{0,1,0,1,0},{0,0,1,0,0}};
		Pattern O = new Pattern(dataBitsO);
		int dataBitsP[][] = {{1,1,1,0,0},{1,0,0,1,0},{1,0,0,1,0},{1,1,1,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};
		Pattern P = new Pattern(dataBitsP);
		int dataBitsQ[][] = {{0,0,1,0,0},{0,1,0,1,0},{0,1,0,1,0},{1,0,0,0,1},{1,0,1,0,1},{0,1,0,1,0},{0,0,1,0,1}};
		Pattern Q = new Pattern(dataBitsQ);
		int dataBitsR[][] = {{1,1,1,0,0},{1,0,0,1,0},{1,0,0,1,0},{1,1,1,0,0},{1,1,0,0,0},{1,0,1,0,0},{1,0,0,1,0}};
		Pattern R = new Pattern(dataBitsR);
		int dataBitsS[][] = {{0,0,1,1,1},{0,1,0,0,0},{1,0,0,0,0},{1,1,1,0,0},{0,0,0,1,0},{0,0,0,0,1},{1,1,1,1,0}};
		Pattern S = new Pattern(dataBitsS);
		int dataBitsT[][] = {{1,1,1,1,1},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},};
		Pattern T = new Pattern(dataBitsT);
		int dataBitsU[][] = {{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{1,1,0,1,1},{0,1,1,1,0}};
		Pattern U = new Pattern(dataBitsU);
		int dataBitsV[][] = {{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{0,1,0,1,0},{0,1,0,1,0},{0,0,1,0,0},{0,0,1,0,0}};
		Pattern V = new Pattern(dataBitsV);
		int dataBitsW[][] = {{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,1,0,1},{1,1,0,1,1},{1,0,0,0,1}};
		Pattern W = new Pattern(dataBitsW);
		int dataBitsX[][] = {{1,0,0,0,1},{0,1,0,1,0},{0,1,0,1,0},{0,0,1,0,0},{0,1,0,1,0},{0,1,0,1,0},{1,0,0,0,1}};
		Pattern X = new Pattern(dataBitsX);
		int dataBitsY[][] = {{1,0,0,0,1},{1,0,0,0,1},{0,1,0,1,0},{0,1,0,1,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}};
		Pattern Y = new Pattern(dataBitsY);
		int dataBitsZ[][] = {{1,1,1,1,1},{0,0,0,0,1},{0,0,0,1,0},{0,0,1,0,0},{0,1,0,0,0},{1,0,0,0,0},{1,1,1,1,1}};
		Pattern Z = new Pattern(dataBitsZ);
		
        // add letters to dataset container
        addPattern(A);
		addPattern(B);
		addPattern(C);
		addPattern(D);
		addPattern(E);
		addPattern(F);
		addPattern(G);
		addPattern(H);
		addPattern(I);
		addPattern(J);
		addPattern(K);
		addPattern(L);
		addPattern(M);
		addPattern(N);
		addPattern(O);
		addPattern(P);
		addPattern(Q);
		addPattern(R);
		addPattern(S);
		addPattern(T);
		addPattern(U);
		addPattern(V);
		addPattern(W);
		addPattern(X);
		addPattern(Y);
		addPattern(Z);
	}
	
	// create empty
	// used for display of output grid before output calculated
	public void makeEmpty()
	{
		int dataBits[][] = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		Pattern blank = new Pattern(dataBits);
		addPattern(blank);
	}
	
	// change number of patterns in dataset
	public void datasetSize(int newSize)
	{
		theData.setSize(newSize);
	}
}