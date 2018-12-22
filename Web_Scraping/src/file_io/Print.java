import java.io.File;
import java.io.PrintStream;
/****************************************************************************
 * Class: Print
 * <p>
 * Purpose: Redirects System.out() from console to specified file
 * @author John Herrmann
 ***************************************************************************/
public class Print 
{
	//Private Instance Variables
	private PrintStream stream;			//PrintStream object
	private String fileName;			//String fileName
	
	//Constructor
	public Print(String fileName)
	{
		this.fileName = fileName;
		try
		{
			this.stream = new PrintStream(new File(this.fileName));
			System.setOut(this.stream);
		}catch(Exception e)
		{
			System.out.println("ERROR: PrinStream Failure");
			System.exit(0);
		}
	}
	/************************************************************************
	 * Method: close
	 * <p>
	 * Purpose: closes the PrintStream object
	 * 
	 ***********************************************************************/
	public void close()
	{
		this.stream.close();
	}
}
