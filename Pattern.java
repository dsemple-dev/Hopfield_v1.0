// Pattern.java
// Doug Semple
// MSc Project

public class Pattern
{
    private int theBits[][];
    
    // constructor
    public Pattern(int bits[][])
    {
        theBits = bits;
    }
    
    // returns the letter's bits
    public int[][] getTheBits()
    {
        return theBits;
    }
}