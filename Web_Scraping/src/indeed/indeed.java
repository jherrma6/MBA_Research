/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indeed;
import java.util.ArrayList;
import regex.*;
import java.util.regex.*;

/**
 *
 * @author johnherrmann
 */
public class indeed 
{
    public ArrayList<indeed_job> jobList;
    public int                   num_jobs;
    public int               current_num;
     
    
/*******************************************************************************
 * <h2> indeed_job constructor
 ******************************************************************************/    
  public indeed()
  {
      this.jobList = new ArrayList(0);
      this.num_jobs = 0;
      this.current_num = 0;
  }
 /*************************************************************************
  * <h2>numJobs</h2>
  * Purpose: Searches indeed source and finds total number of jobs found
  *          for a given search
  *
  * @return number of jobs found OR (-1) if no jobs found 
  *************************************************************************/   
public int numJobs(String line)
{
   regexMatcher regMatch = new regexMatcher();
   regMatch.init(Pattern.compile("Page \\d+ of \\d+ jobs"), line);
   regMatch.findPattern();

   if(regMatch.wasPatternFound())
   {
       regMatch.init(Pattern.compile("\\d+"), line);
       regMatch.findPattern();
       if(regMatch.wasPatternFound())
        {
            return Integer.parseInt(regMatch.outputPatternsFound.get(1));
        }
   }
   return -1;
}
 /*************************************************************************
  * <h2>numJobs</h2>
  * Purpose: Searches indeed source and finds total number of jobs found
  *          for a given search
  *
  * @return number of jobs found OR (-1) if no jobs found 
  *************************************************************************/
public void find_a_job(String line)
{
   regexMatcher regMatch = new regexMatcher();
   regMatch.init(Pattern.compile("jobmap\\[\\d\\]="), line);
   regMatch.findPattern();

   if(regMatch.wasPatternFound())
   {
       indeed_job job = new indeed_job();
       System.out.println(job.processJob(line));
       //return true;
   }
       //return false;
}

}
