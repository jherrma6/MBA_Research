/******************************************************************************
 * <h1>string_buffer</h1>
 * Purpose: The string_buffer class allows the caller to transfer the contents
 * of a BufferedReader object in the OS kernel space to a "string_buffer" 
 * object in the OS user space. 
 * 
 * @author John Herrmann
 * @version 1.0
 * @since 2018-12-27
 *****************************************************************************/
package file_io;
import java.io.BufferedReader;
import java.util.ArrayList;

public class string_buffer 
{
   public  ArrayList<String> buffer;                //creates the "buffer"
   public int buffer_size;                          //maintains current buffer size
   public int current_index;                        //maintains current buffer index
   public String current_line;                      //maintains the current line
   public BufferedReader input_buffer;              //stores the BufferedReader
                                                    //... input
   
    /*************************************************************************
     * <h2>constructor</h2>
     * Purpose: This method initializes the class. 
     * Precondition : BufferedReader object has been created and is populated.
     * Postcondition: Contents of BufferedReader object have been transferred
     *                to the string_buffer object.
     * 
     * @param BufferedReader  an initialized BufferedReader object
     * @return status Pass/Fail status bit (true = success, false = fail) 
     *************************************************************************/
   
   public string_buffer(BufferedReader input)
   {
                                                //Initialize private instance
                                                // ... variables
       this.buffer          = new ArrayList<String>();
       this.buffer_size     = 0;
       this.current_index   = 0;
       this.current_line    = null;
       this.input_buffer    = input;
       
       if(this.input_buffer == null)            //Check for null input       
        {
            return;                             //if null... Exit
        }
    
       String temp = null;                      //Stores current String from
                                                //... BufferedReader object
                                                
       do                                       //Do/While Loop transfer contents 
                                                // ... of the BufferedReader 
                                                // ... object to the StringBuffer
       {
           
           try
            {
               temp = this.input_buffer.readLine();
            }
            catch (Exception e)
            {
               System.out.println("string_buffer: Error: BufferedReader Exception");
            }
           if(temp != null)
           {
               this.buffer.add(temp);          //transfer contents to string buff
               this.buffer_size++;             //increment buffer size counter
           }
       } while(temp != null);
   }
    /*************************************************************************
     * <h2>size</h2>
     * Purpose: This method returns the current size of the buffer 
     * Precondition : BufferedReader object has been created and is populated.
     * Postcondition: no changes made to object.
     * 
     * @return the size of the buffer
     *************************************************************************/    
     public int size()
     {
         return this.buffer_size; 
     }
     /*************************************************************************
     * <h2>readLine</h2>
     * Purpose: This method returns the next line in the string_buffer
     * Precondition : (1) BufferedReader Object has been populated
     *                (2) BufferedReader contents have been transferred to the 
     *                    ... string_buffer
     * Postcondition: (1) string_buffer index has been incremented 
     * 
     * @return the current line
     *************************************************************************/  
    public String readLine()
    {
       if(this.current_index < this.buffer_size)
       {
           int temp = this.current_index; 
           this.current_index++;
           this.current_line = this.buffer.get(temp);
           return this.current_line;
       }
       else
       {
           return null;
       }
    }
    
       
}
   
   
   

