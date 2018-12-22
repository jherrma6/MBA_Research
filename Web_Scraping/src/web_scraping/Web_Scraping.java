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

public class Web_Scraping 
{

    public static void main(String[] args) 
    {
        String url = "https://www.indeed.com/jobs?q=electrical+engineer&l=Round+Lake%2C+IL";
        url_reader file = new url_reader(url);
        file.open();
        String line = file.readLine();
        indeed indeed_search = new indeed();
        
        while(line != null)
        {
            //System.out.println(indeed_search.numJobs(line));
            indeed_search.find_a_job(line);
            line = file.readLine();
        }
        file.close();
    }
}
