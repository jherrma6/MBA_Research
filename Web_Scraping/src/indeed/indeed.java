/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indeed;
import file_io.FileWrite;
import java.util.ArrayList;
import regex.*;
import java.util.regex.*;
import web_reader.url_reader;
import search_sort_tree.*;

/**
 *
 * @author johnherrmann
 */
public class indeed 
{
    public ArrayList<indeed_job> jobList;
    public data_node_tree        tree;
    public int                   num_jobs;
    public int                   current_page;
    public int                   jobs_found;
    public url_reader            source_file;
     
    
/*******************************************************************************
 * <h2> indeed_job constructor
 ******************************************************************************/    
  public indeed(String url)
  {
      this.jobs_found = 0;                      //At start, no jobs found
                                                //At start, empty jobs list
      this.jobList = new ArrayList<indeed_job>(0);
      
      this.tree = new data_node_tree();
                                                //create URL reader object
      this.source_file = new url_reader(url);
      this.source_file.open(1024);              //start transfer of contents
                                                //... to string buffer
      
      
                                                //Create a new url_reader object
      url_reader find_num_jobs_file = new url_reader(url);
                                                //Instead of reading URL... copy
                                                //... the contents of the source
                                                //... file (less expensive operation)
      find_num_jobs_file.copy(this.source_file);
                                                //Determine the number of available jobs
      this.num_jobs = numJobs(find_num_jobs_file);
                                                //Print number of available jobs
      System.out.println("Total Number of Jobs Found "+this.num_jobs);
                                                
      
      int web_page_counter = 0;
      String url_previous = "=0";
      String url_next     = "=0";
      
      while(this.jobs_found < this.num_jobs*(.95))
      {
       
        url = url.replace(url_previous, url_next);
        //System.out.println(url);
        url_reader job_search = new url_reader(url);
        job_search.open(1024);
        find_jobs(job_search);
        System.out.println(this.jobs_found);
        
        
        url_previous = url_next;
        web_page_counter += 10;
        url_next = String.format("=%d", web_page_counter);
      }
  }
 /*************************************************************************
  * <h2>numJobs</h2>
  * Purpose: Searches indeed source and finds total number of jobs found
  *          for a given search
  *
  * @param input a copy of the url webpage source
  * @return number of jobs found OR (-1) if no jobs found 
  *************************************************************************/   
private static int numJobs(url_reader input)
{
   String line = input.readLine();
   while (input != null)
   {
    regexMatcher regMatch = new regexMatcher();
    regMatch.init(Pattern.compile("Page [\\d+,]+ of [\\d+,]+ jobs"), line);
    regMatch.findPattern();

        if(regMatch.wasPatternFound())
        {
            regMatch.init(Pattern.compile("[\\d+,]+"), line);
            regMatch.findPattern();
            if(regMatch.wasPatternFound())
                {
                    String temp = regMatch.outputPatternsFound.get(1);
                    temp = temp.replace(",", "");
                    return Integer.parseInt(temp);
                }
        }
        else
        {
            line = input.readLine();
        }
   }
   return -1;
}
 /*************************************************************************
  * <h2>currentPage</h2>
  * Purpose: Determines the current page of search
  *
  * @param input a copy of the url webpage source
  * @return batch number of current jobs found OR (-1) if no jobs found 
  *************************************************************************/   
private static int currentPage(url_reader input)
{
  String line = input.readLine();
   while (input != null)
   {
    regexMatcher regMatch = new regexMatcher();
    regMatch.init(Pattern.compile("Page [\\d+,]+ of [\\d+,]+ jobs"), line);
    regMatch.findPattern();

        if(regMatch.wasPatternFound())
        {
            regMatch.init(Pattern.compile("[\\d+,]+"), line);
            regMatch.findPattern();
            if(regMatch.wasPatternFound())
                {
                    String temp = regMatch.outputPatternsFound.get(0);
                    temp = temp.replace(",", "");
                    return Integer.parseInt(temp);
                }
        }
        else
        {
            line = input.readLine();
        }
   }
   return -1;
}
 /*************************************************************************
  * <h2>find_jobs</h2>
  * Purpose: Searches indeed source and finds a job
  *
  *************************************************************************/
private void find_jobs(url_reader input)
{
   String line = input.readLine();
   while (line != null)
   {
        regexMatcher regMatch = new regexMatcher();
        regMatch.init(Pattern.compile("jobmap\\[\\d\\]="), line);
        regMatch.findPattern();

        if(regMatch.wasPatternFound())
        {
            indeed_job job = new indeed_job();
            job.processJob(line);
            this.jobList.add(job);
            this.jobs_found++;
            this.tree.insert(job, job.getState());
        }
            line = input.readLine();
   }
}
/*******************************************************************************
 * Method  : printTree
 * Purpose : prints all of the avaliable jobs to screen 
 ******************************************************************************/
public void printTree(String location, boolean append)
{
    this.tree.printKeys(location, append);
}
/*******************************************************************************
 * Method  : printJobs
 * Purpose : prints all of the avaliable jobs to screen 
 ******************************************************************************/
 public void printJobs(String location, boolean append)
 {
     //If location provided... print to file
     if(location != null)
     {
         FileWrite fwrite = new FileWrite();
         fwrite.setup(location, append);
         fwrite.write(String.format("%s|%s|%s|%s|%s|%s\n","Job Title", "Company", "Job ID",
                                    "City", "State",
                                    "Country"));
         for(int i = 0; i < this.jobs_found; i++)
         {
            fwrite.write(this.jobList.get(i).printJob());
         }
     }
     //Else print to the console
     else 
     {
         for(int i = 0; i < this.jobs_found; i++)
         {
            System.out.println(this.jobList.get(i).printJob());
         }
     } 
 }
}

