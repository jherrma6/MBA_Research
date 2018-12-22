/******************************************************************************
 * <h1>indeed_job</h1>
 * Purpose: Creates indeed_job datatype to store relevant job information
 *          ... also creates applicable getter and setter methods
 * 
 * @author John Herrmann
 * @version 1.0
 * @since 2019-12-21
 *****************************************************************************/
package indeed;

import java.util.regex.Pattern;
import regex.regexMatcher;

/*******************************************************************************
 * <h2>indeed_job</h2>
 * Purpose: This method instantiates the indeed_job class
 ******************************************************************************/
public class indeed_job 
{
    String company; 
    String job_title;
    String location_city;
    String location_state;
    String location_country;
    String jobID; 
/*******************************************************************************
 * <h2> indeed_job constructor
 ******************************************************************************/   
public indeed_job()
{
    this.company          = "Not Provided";
    this.job_title        = "Not Provided";
    this.location_city    = "Not Provided";
    this.location_state   = "Not Provided";
    this.location_country = "Not Provided";
    this.jobID            = "Not Provided";
}
/*************************************************************************
  * <h2>numJobs</h2>
  * Purpose: Searches string and attempts to find a indeed_job object
  *
  * @return true if job datatype found.... False otherwise
  *************************************************************************/
public boolean processJob(String line)
{
   regexMatcher regMatch = new regexMatcher();
   regMatch.init(Pattern.compile("jobmap\\[\\d\\]="), line);
   regMatch.findPattern();

   if(regMatch.wasPatternFound())
   {
        this.company          = find_company(line);
        this.job_title        = find_job_title(line);
        this.location_city    = find_city(line);
        this.location_state   = find_state(line);
        this.location_country = find_country(line);
        this.jobID            = find_jobID(line);
        
        return true;
   }
   return false;
}










/*******************************************************************************
 * <h2>find_company</h2>
 * Purpose: attempts to find the company parameter from a given string
 * 
 * @param input string to search
 * @return company 
 ******************************************************************************/
 String find_company(String input) 
    {
        String company;
        regexMatcher regMatch = new regexMatcher();
        regMatch.init(Pattern.compile("cmp:'[^']+[']"), input);
        regMatch.findPattern();
        if(regMatch.wasPatternFound())
        {
           company = regMatch.outputPatternsFound.get(0).replace("cmp:", "");
           company = company.replace("'", "");
           return company;
        }
        return "Not Provided";
    }
/*******************************************************************************
 * <h2>setCompany</h2>
 * Purpose: sets the company instance variable
 * 
 * @param input company 
 ******************************************************************************/
 public void setCompany(String input) 
    {
        if(input == null)
        {
            return;
        }
        this.company = input;
    }
/*******************************************************************************
 * <h2>getCompany</h2>
 * Purpose: returns the company instance variable
 * 
 * @return company 
 ******************************************************************************/
 public String getCompany() 
    {
        return this.company;
    }
 
 
 
 /*******************************************************************************
 * <h2>find_job_title</h2>
 * Purpose: attempts to find the job title parameter from a given string
 * 
 * @param input
 * @return job_title
 ******************************************************************************/
 String find_job_title(String input) 
    {
        String title;
        regexMatcher regMatch = new regexMatcher();
        regMatch.init(Pattern.compile("title:'[^']+[']"), input);
        regMatch.findPattern();
        if(regMatch.wasPatternFound())
        {
           title = regMatch.outputPatternsFound.get(0).replace("title:", "");
           title = title.replace("'", "");
           return title;
        }
        return "Not Provided";
    }
/*******************************************************************************
 * <h2>setJobTitle</h2>
 * Purpose: sets the job instance variable
 * 
 * @param input jobTitle 
 ******************************************************************************/
 public void setJobTitle(String input) 
    {
        if(input == null)
        {
            return;
        }
        this.job_title = input;
    }
 /******************************************************************************
 * <h2>getJobTitle</h2>
 * Purpose: returns the job instance variable
 * 
 * @return jobTitle 
 ******************************************************************************/
 public String getJobTitle() 
    {
        return this.job_title;
    }
 
 
 
 
 /*******************************************************************************
 * <h2>find_city</h2>
 * Purpose: attempts to find the city parameter from a given string
 * 
 * @param input
 * @return city
 ******************************************************************************/
 String find_city(String input) 
    {
        String city;
        regexMatcher regMatch = new regexMatcher();
        regMatch.init(Pattern.compile("city:'[^']+[']"), input);
        regMatch.findPattern();
        if(regMatch.wasPatternFound())
        {
           city = regMatch.outputPatternsFound.get(0).replace("city:", "");
           city = city.replace("'", "");
           return city;
        }
        return "Not Provided";
    }
 
/*******************************************************************************
 * <h2>setCity</h2>
 * Purpose: sets the city instance variable
 * 
 * @param input city  
 ******************************************************************************/
 public void setCity(String input) 
    {
        if(input == null)
        {
            return;
        }
        this.location_city = input;
    }
/*******************************************************************************
 * <h2>getCity</h2>
 * Purpose: returns the city instance variable
 * 
 * @return city
 ******************************************************************************/
 public String getCity() 
 {     
       return this.location_city;
 }
 
 
 
 
 /*******************************************************************************
 * <h2>find_state</h2>
 * Purpose: attempts to find the state parameter from a given string
 * 
 * @param input
 * @return state
 ******************************************************************************/
 String find_state(String input) 
    {
        regexMatcher regMatch = new regexMatcher();
        regMatch.init(Pattern.compile("loc:'[^']+[']"), input);
        regMatch.findPattern();
        if(regMatch.wasPatternFound())
        {
            String temp = regMatch.outputPatternsFound.get(0);
            regexMatcher state_regMatch = new regexMatcher();
            state_regMatch.init(Pattern.compile("[,]([.\\D]+)"), temp);
            state_regMatch.findPattern();
            if(state_regMatch.wasPatternFound())
            {
                temp = state_regMatch.outputPatternsFound.get(0);
                temp = temp.replaceAll(",", "");
                temp = temp.replaceAll("'", "");
                temp = temp.replaceAll("\\s", "");
                return temp;
            }
            return temp;
        }
        return "Not Provided";
    }
 
/*******************************************************************************
 * <h2>setState</h2>
 * Purpose: sets the city instance variable
 * 
 * @param input state  
 ******************************************************************************/
 public void setState(String input) 
    {
        if(input == null)
        {
            return;
        }
        this.location_state = input;
    }
/*******************************************************************************
 * <h2>getState</h2>
 * Purpose: return the city instance variable
 * 
 * @return state  
 ******************************************************************************/
 public String getState() 
    {
        return this.location_state;
    }
 
 
 
 
 /*******************************************************************************
 * <h2>find_country</h2>
 * Purpose: attempts to find the country parameter from a given string
 * 
 * @param input
 * @return country
 ******************************************************************************/
 String find_country(String input) 
    {
        String country;
        regexMatcher regMatch = new regexMatcher();
        regMatch.init(Pattern.compile("country:'[^']+[']"), input);
        regMatch.findPattern();
        if(regMatch.wasPatternFound())
        {
           country = regMatch.outputPatternsFound.get(0).replace("country:", "");
           country = country.replace("'", "");
           return country;
        }
        return "Not Provided";
    }
/*******************************************************************************
 * <h2>setCountry</h2>
 * Purpose: sets the country instance variable
 * 
 * @param input country  
 ******************************************************************************/
 public void setCountry(String input) 
    {
        if(input == null)
        {
            return;
        }
        this.location_country = input;
    }
/*******************************************************************************
 * <h2>getCountry</h2>
 * Purpose: returns the country instance variable
 * 
 * @return country  
 ******************************************************************************/
 public String getCountry() 
    {
        return this.location_country;
    }
 
 
 
 
 /*******************************************************************************
 * <h2>find_jobID</h2>
 * Purpose: attempts to find the jobID parameter from a given string
 * 
 * @param input
 * @return jobID
 ******************************************************************************/
 String find_jobID(String input) 
    {
        String jobID;
        regexMatcher regMatch = new regexMatcher();
        regMatch.init(Pattern.compile("jk:'[^']+[']"), input);
        regMatch.findPattern();
        if(regMatch.wasPatternFound())
        {
           jobID = regMatch.outputPatternsFound.get(0).replace("jk:", "");
           jobID = jobID.replace("'", "");
           return jobID;
        }
        return "Not Provided";
    }
/*******************************************************************************
 * <h2>setJobID</h2>
 * Purpose: sets the jobID instance variable
 * 
 * @param input jobID  
 ******************************************************************************/
 public void setJobID(String input) 
    {
        if(input == null)
        {
            return;
        }
        this.jobID = input;
    }
/*******************************************************************************
 * <h2>getJobID</h2>
 * Purpose: returns the jobID instance variable
 * 
 * @return jobID  
 ******************************************************************************/
 public String getJobID() 
    {
        return this.jobID;
    } 
}
