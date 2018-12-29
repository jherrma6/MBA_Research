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
        String url = "https://www.indeed.com/jobs?q=Software+Engineer&l=United+States&start=0";
        

        indeed indeed_search = new indeed(url);
        
        indeed_search.printJobs("SW_engineering_US.txt", true);
        indeed_search.printTree("SW_engineering_US_BREAKDOWN.txt", true);
        
        
        
    }
}
