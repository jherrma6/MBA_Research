import java.io.*;
/***************************************************************************
 *Class: FileRead
 *Purpose: the FileRead class assists in reading character-based content from 
 *         an existing file
 *         
 *How To Use:
 *         1) Create FileRead object
 *         2) call object.setup()
 *         3) if object.setup() is successful, read from file using
 *            object.readLine() command. 
 * @author John Herrmann
 ***************************************************************************/
public class FileRead {
    //Instance Variables
    private String filename;                  //Name of the file read
    private BufferedReader br;                //Reference to BufferReader obj
    private FileReader file;                  //Reference to FileReader obj
    
    /***********************************************************************
    *Method:  FileWrite
    *Purpose: initializes the FileWrite class object to null to signify
    *         setup has not occurred
    ************************************************************************/
    public FileRead(){
        this.filename = null;                  //Read setup has not occurred
        this.br = null;                        //Read setup has not occurred
        this.file = null;                      //Read setup has not occurred
    }
    
    /***********************************************************************
    *Method:  setup()
    *Purpose: Creates a character input string from the file.
    *<p>
    *@param         filename String filename giving the location
    *                        of the file of interest 
    *@return        status indicator of successful read file setup
    ************************************************************************/
    public boolean setup(String filename)
    {
        try{                                            //Use try/cath to try                                                  
        FileReader theFile = new FileReader(filename);  //to create stream
        this.file = theFile;                            
        this.br =  new BufferedReader(theFile);         //create char stream
        } catch (FileNotFoundException e)                                   
        {
            System.out.println("Error: File Not Found");//send error to user
            this.file = null;                           //Setup fail. Set
            this.br = null;                             //variables to null
            return false;                               
        }
        this.filename = filename;                       //Update obj filename
        return true;                                    //Setup success.
    }
    /***********************************************************************
    *Method:  readLine
    *Purpose: returns the next line in the buffered character stream
    *<p> 
    *@return String - the next line in the buffered character stream
    ************************************************************************/
    public String readLine()
    {
        
        if(this.br == null)              //Check to see if setup has occurred
        {
            return null;                 //Exit. return null to signify fail
        }
        else                             //Setup has successfully occurred
        {
            try{                         //Try to read line
            return br.readLine();
            } catch(IOException E)
            {
                System.out.println("Error: Cannot complete read operation");
                return null;             //Exit. Return null to signify fail
            }
        }
    }
    
    /***********************************************************************
    *Method:  close
    *Purpose: close the stream
    *@return Status: Returns true if close was successful. false, otherwise. 
    ***********************************************************************/
    public boolean close()
    {
        try{                               //Try to close the file
            this.br.close();
        }catch (IOException e)             //If error occurs, notify of error
        {
            System.out.println("Error: Cannot Close File");
            return false;                  //Exit. Return false 2 notify fail 
        }
        return true;                       //Exit.Return true 2 show success
    }
}