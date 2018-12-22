package regex;
import java.util.regex.*;
import java.util.*;

/******************************************************************************
 * <h1>RegexMatcher</h1>
 * Purpose: The RegexMatcher Class uses Regular Expressions (abbreviated Regex) to search
 * through an input string to find sequences that match the Regex pattern.
 * @author John Herrmann
 * @version 1.0
 * @since 2018-10-10
 *****************************************************************************/
public class regexMatcher {

      private Pattern           regex;              // The regex pattern
      private String            input_sequence;     // String to be searched
      public  ArrayList<String> outputPatternsFound;// The output list of strings
      
      public  boolean           sequenceFound;      // Boolean denoting if seq
                                                    // ...was found
      
      /*************************************************************************
     * <h2>RegexMatcher</h2>
     * Purpose: This method instantiates the RegexMatcher class
     *************************************************************************/
      public regexMatcher()
      {
             this.regex = null;
             this.input_sequence = null;
             this.outputPatternsFound = new ArrayList(0);
             this.sequenceFound = false;
      }
      
     /*************************************************************************
     * <h2>init</h2>
     * Purpose: This method initializes the RegexMatcher class
     *
     * @param regex the input pattern
     * @param input_sequence the string to be searched for regex pattern
     * @return status Pass/Fail status bit (true = success, false = fail) 
     *************************************************************************/
    public boolean init(Pattern regex, String input_sequence) 
    {
        if(regex == null | input_sequence == null)
        {
            System.out.println("RegexMatcher.init() - Found NULL Input");
            return false;
        }
        else
        {
            this.regex = regex;
            this.input_sequence = input_sequence;
            return true;
        }
    }
    /*************************************************************************
     * <h2>setSequence</h2>
     * Purpose: This method sets the input sequence string that will be searched 
     *
     * @param input_sequence the string to be searched for regex pattern
     * @return status Pass/Fail status bit (true = success, false = fail) 
     *************************************************************************/
    public boolean setSequence(String input_sequence) 
    {
        if(input_sequence == null)
        {
            System.out.println("RegexMatcher.setSequence() - Found NULL Input");
            return false;
        }
        else
        {
            this.input_sequence = input_sequence;
            return true; 
        }
    }
    /*************************************************************************
     * <h2>setPattern</h2>
     * Purpose: This method sets the input pattern
     *
     * @param regex the input pattern
     * @return status Pass/Fail status bit (true = success, false = fail) 
     *************************************************************************/
    public boolean setPattern(Pattern regex) 
    {
        if(regex == null)
        {
            System.out.println("RegexMatcher.setPattern() - Found NULL Input");
            return false;
        }
        else
        {
            this.regex = regex;
            return true; 
        }
    }
    /*************************************************************************
     * <h2>getPattern</h2>
     * Purpose: This method returns the current Regex Pattern private instance variable
     *
     * @return the current pattern 
     *************************************************************************/
    public Pattern getPattern() 
    {
        return this.regex;
    }
    
    /*************************************************************************
     * <h2>findPattern</h2>
     * Purpose: This method uses the regex to search through the input string to find
     * the pattern. 
     *
     * @return status Pass/Fail status bit (true = success, false = fail) 
     *************************************************************************/
    public boolean findPattern()
    {   
        //Step 0: Re-init outputs to empty and false, respectively:
        this.outputPatternsFound = new ArrayList(0);
        this.sequenceFound = false;
        
        //Step 1: Check to see if either input is null. 
        //        If input is null.... terminate.
        if(this.regex == null | this.input_sequence == null)
        {
            System.out.println("RegexMatcher.findPattern() - Found NULL Input");
            return false;
        }
        
        //Step 2: if input != null, search through input sequence for regex
        else
        {
            //Step 2a. Create matcher object
            Matcher regexMatcher = this.regex.matcher(this.input_sequence);
            
            //Step 2b. Search through input squence for pattern and create
            //         an arraylist containing the sequences found
            if(regexMatcher.find())
            {
                this.sequenceFound = true;
                this.outputPatternsFound.add(regexMatcher.group().trim());
                
                while(regexMatcher.find())
                {
                    this.outputPatternsFound.add(regexMatcher.group().trim());
                }
            }
            else
            {
                // Sequence not found... but... nothing needs to be done
                // as the outputs have already been set by this point
                
            }
            
            // return true to indicate a failure did not occur
            return true;
        }
    }
    /*************************************************************************
     * <h2>wasPatternFound</h2>
     * Purpose: This method will let the user know if a pattern was found
     *
     * @return status true if pattern was found... false otherwise. 
     *************************************************************************/
    public boolean wasPatternFound()
    {
        return this.sequenceFound;
    }
    /*************************************************************************
     * <h2>print</h2>
     * Purpose: prints the contents of the outputPatternsFound arraylist
     *
     * @return status true if pattern was found... false otherwise. 
     *************************************************************************/
    public boolean print()
    {
        if(this.sequenceFound == true)
        {
            int size = this.outputPatternsFound.size();
            for(int i = 0 ; i < size; i++)
            {
                System.out.println(this.outputPatternsFound.get(i));
            }
            return true;
        }
        else
        {
            System.out.println("RegexMatcher.print() - Nothing to Print");
            return false;
        }
    }
}

