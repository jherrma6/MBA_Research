/*****************************************************************************
 * Title      : url_reader
 * Description: 
 * Author     : John A Herrmann
 * Date       : December 20th, 2018
 ******************************************************************************/
package web_reader;
import java.net.*;
import java.io.*;


public class url_reader 
{
    public String         url_string;                          // website URL
    public URL            url_stream;                          // URL Stream
    public BufferedReader url_buffer;                          // Buffer
    
 /******************************************************************************
 * Constructor
 * Purpose: Initializes the url_reader object
 * 
 * @param  String - the webpage URL as a string
 * @return none
 * @author johnherrmann
 ******************************************************************************/ 
    public url_reader(String url_string)
    {
        this.url_string = url_string;
        this.url_stream = null;
        this.url_buffer = null;
    }
 /******************************************************************************
 * Method : open()
 * Purpose: Opens a stream containing the URL's HTML. Returns a BufferedReader
 *          reference that can be used to access the stream. 
 * 
 * @return BufferedReader object
 * @author johnherrmann
 ******************************************************************************/   
    public BufferedReader open()
    {
        if(this.url_string == null)
        {
            return null;
        }
        try
        {
            int numKB = 1000;
            int bufferSize = 1024 *numKB;
            this.url_stream = new URL(this.url_string);
            this.url_buffer = new BufferedReader(
                                new InputStreamReader(url_stream.openStream()),
                                bufferSize);
            return this.url_buffer;
        }
        catch (Exception e)
        {
            return null;
        }
    }
/******************************************************************************
 * Method       : readLine()
 * Preconditions: A valid URL must be "opened" using the open() method. 
 * Purpose      : attempts to read a line from the BufferedReader object. 
 * 
 * @param  none
 * @return String
 * @author johnherrmann
 ******************************************************************************/
  public String readLine()
  {
    if(this.url_buffer == null)
    {
        return null;
    }
    try
        {
           return this.url_buffer.readLine();
        }
        catch (Exception e)
        {
            return null;
        }
 }
/******************************************************************************
 * Method        : close()
 * Preconditions : A buffer has been opened.
 * Purpose       : Attempts to read a line from the BufferedReader object.
 * Postconditions: The buffer has been closed. 
 ******************************************************************************/
public void close()
{
    if(this.url_buffer == null)
    {
        return;
    }
    try
        {
          this.url_buffer.close();
        }
        catch (Exception e)
        {
            return;
        }   
}
}
