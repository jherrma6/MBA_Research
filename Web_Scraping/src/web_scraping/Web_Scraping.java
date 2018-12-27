/*****************************************************************************
 * Title      : Web_Scraping Main 
 * Description: Main function
 * Author     : John A Herrmann
 * Date       : December 20th, 2018
 * 
 ******************************************************************************/
package web_scraping;
import web_reader.*;
import indeed.*;

import file_io.*;

public class Web_Scraping 
{

    public static void main(String[] args) 
    {
        String url = "https://www.indeed.com/jobs?q=electrical+engineer&l=Round+Lake%2C+IL";
        url_reader file1 = new url_reader(url);
        file1.open(1024);
        
        url_reader file2 = new url_reader(url);
        file2.copy(file1);
        
        
        
        String lineA = file1.readLine();
        String lineB = file2.readLine();
        //indeed indeed_search = new indeed();
        
        while(lineA != null | lineB != null)
        {
            System.out.println(lineA);
            System.out.println(lineB);
            
            lineA = file1.readLine();
            lineB = file2.readLine();
        }
    }
}
