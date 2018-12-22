import java.io.*;
/****************************************************************************
 *Class: FileWrite
 *Purpose: the FileWrite class assists in writing character-based content to 
 *         a new or existing file
 *How To Use:
 *         1) Create FileWrite object
 *         2) call object.setup()
 *         3) if object.setup() is successful, write to file using
 *            object.write() command.
 * @author John Herrmann
 ***************************************************************************/
public class FileWrite {
    
    //Private Instance variables
    private String fileLocation;        //The file location to be written to
    private File fileReference;         //The reference of the file object
    private boolean append;             //flag that denotes if new content 
                                        //....will be appended to the file
    
    /************************************************************************
     * FileWrite Constructor
     * <p>
     * Method creates an object of the FileWrite class and initializes 
     * contents to null
     **********************************************************************/ 
    public FileWrite()
    {
        this.fileLocation = null;       //FileReference = null signifies 
        this.fileReference = null;      //that setup has not occurred. 
        this.append = true;
    }
    
    /***********************************************************************
     * setup
     * <p>
     * Method initializes an object of the FileWrite class and determines 
     * if the input file location is acceptable for writing character content
     * 
     * @param fileLocation : the file name in String format
     * @param append : a boolean value that denotes whether content will be
     *                 appended to file
     * @return : a boolean value indicating if file location is acceptable
     *             for writing file content
     ***********************************************************************/
    public boolean setup(String fileLocation, boolean append)
    {
        File theFile;                     //Create File Object
        theFile = new File(fileLocation); //Create stream to fileLocation
        if (fileExistsCheck(fileLocation))//Check to see if file exists
        {
          //Do Nothing
        }
        else                              //File does not exist, create file
        {
            try                           //Try-catch to catch bad file names
            {
                theFile.createNewFile();
            }catch (IOException e)
            {
                System.out.println("Error: Cannot create new file. "
                        + "Please check file name...");
                return false;              //Setup failed. Exit. return false
            }
        }
        this.fileLocation = fileLocation;  //Setup success.Exit. return true
        this.fileReference = theFile;
        this.append = append;
        return true;
    }
    
    /***********************************************************************
    *write
    *<p>
    *Method : write
    *@param input : The string to be written to file
    *@return Status Indicator :
    *                        True  : File write failure
    *                        False : File write successful
    ************************************************************************/
    public boolean write(String input)
    {
        if(this.fileReference == null)      //Check to see if setup completed
        {
            return false;                   //Exit if setup has not occurred
        }
        else
        {
            try                             //Else try to write to file
            {
                 FileWriter file = new FileWriter(this.fileReference, 
                         this.append);
                 BufferedWriter buff = new BufferedWriter(file);
                 buff.write(input);         //Write to the file
                 buff.close();              //Close the stream
            }catch(IOException e)           //If error occurs, alert the user
            {
                 System.out.println("Error: WriteToFile() failure");
                 return false;              //Write fail. Exit. return false
            }
            return true;                    //Write success.Exit. return true
         }
    }
    
   /************************************************************************
    *fileExistsCheck
    *<p>
    *Method checks to see if the file exists. The method returns true if the 
    *file exists.Otherwise returns false.
    *@param Filename - The [string] file name of the file that is checked
    *@return         - True if file exists, false otherwise. 
    ************************************************************************/
    public static boolean fileExistsCheck(String Filename)
    {
        File newFile = new File(Filename);
        return newFile.exists();             //Checks if file exists
    }
}