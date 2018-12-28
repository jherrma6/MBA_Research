/*****************************************************************************
 * Title      : url_reader
 * Description: 
 * Author     : John A Herrmann
 * Date       : December 20th, 2018
 ******************************************************************************/
package web_reader;
import file_io.string_buffer;
import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class url_reader 
{
    private String         url_string;                          // website URL
    private URL            url_stream;                          // URL Stream
    private BufferedReader url_buffer;                          // Buffer
    private string_buffer  url_contents;                        // Contents of the url
    private int            buffer_size;
    
 /******************************************************************************
 * Constructor
 * Purpose: Initializes the url_reader object
 * 
 * @param  url_string - the webpage URL as a string
 * @author johnherrmann
 ******************************************************************************/ 
    public url_reader(String url_string)
    {
        this.url_string   = url_string;
        this.url_stream   = null;
        this.url_buffer   = null;
        this.url_contents = null;
        this.buffer_size  = 1000*1024;
    }
 /******************************************************************************
 * Method : open()
 * Purpose: Opens a stream containing the URL's HTML. Returns a BufferedReader
 *          reference that can be used to access the stream. 
 * 
 * @param  size
 * @author johnherrmann
 ******************************************************************************/   
    public void open(int size)
    {
        if(this.url_string == null)             //check if url does not exist
        {
            return;
        }
        try                                     //Attempt to open url and 
                                                //... transfer contents to 
                                                //... string buffer
        {
            this.buffer_size = 1024 * size;       //Set the  BufferedReader size
            this.url_stream = new URL(this.url_string);
            this.url_buffer = new BufferedReader(
                                new InputStreamReader(url_stream.openStream()),
                                this.buffer_size);
                                                //Transfer contents of URL
                                                //...to the string_buffer obj
            this.url_contents = new string_buffer(this.url_buffer);
            
            try                                 //Close the BufferedReader
            {
                this.url_buffer.close();
                return;
            }
            catch (Exception e)
            {
            return;
            }
        }
        catch (Exception e)
        {
            return;
        }
    }
/******************************************************************************
 * Method       : readLine()
 * Preconditions: (1) A valid URL must be "opened" using the open() method. 
 *                (2) Contents must be transferred to string buffer
 * Purpose      : attempts to read a line from the string_buffer object. 
 * 
 * @return String
 * @author johnherrmann
 ******************************************************************************/
  public String readLine()
  {
      if(this.url_contents == null)
      {
          return null;
      }
      else
      {
          return this.url_contents.readLine();
      }
      
  }
/******************************************************************************
 * Method       : get_url_string();
 * Purpose      : returns the current url_string
 * 
 * @return      : url_string
 ******************************************************************************/
 public String get_url_string()
 {
     return this.url_string;
 }
 /******************************************************************************
 * Method       : get_url_stream();
 * Purpose      : returns the current url_stream
 * 
 * @return      : url_stream
 ******************************************************************************/
 public URL get_url_stream()
 {
     return this.url_stream;
 }
 /******************************************************************************
 * Method       : get_url_buffer();
 * Purpose      : returns the current url_buffer
 * 
 * @return      : url_buffer
 ******************************************************************************/
 public BufferedReader get_url_buffer()
 {
     return this.url_buffer;
 }
 /******************************************************************************
 * Method       : get_url_contents();
 * Purpose      : returns the current url_contents
 * 
 * @return      : url_contents
 ******************************************************************************/
 public string_buffer get_url_contents()
 {
     return this.url_contents;
 }
/******************************************************************************
 * Method       : copy()
 * Preconditions: none
 *                
 * Purpose      : creates a copy of the object
 * 
 * @return String
 * @author johnherrmann
 ******************************************************************************/
 public void copy(url_reader source)
 {
        this.url_string    = source.get_url_string();
        this.url_stream    = source.get_url_stream();
        this.url_buffer    = source.get_url_buffer();
        string_buffer temp = new string_buffer(null);
        temp.buffer = (ArrayList<String>)source.get_url_contents().buffer.clone();
        temp.buffer_size = source.get_url_contents().buffer_size;
        this.url_contents = temp;
 }
}
