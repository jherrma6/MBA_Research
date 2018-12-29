/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search_sort_tree;

import indeed.indeed_job;
import java.util.ArrayList;


public class data_node 
{
    public String key;                          //The primary key for searching
    public ArrayList<indeed_job> jobList;       //List used to hold jobs
    public int length;                          //Number of jobs in data_node
    public data_node left;                      //Left Child Node
    public data_node right;                     //Right Child Node
    
    
    /***************************************************************************
     * Constructor
     * Purpose: Creates an empty data_node object.
     * 
     **************************************************************************/
    public data_node()
    {
        this.key            = null;
        this.jobList        = new ArrayList<indeed_job>();
        this.length         = 0;
        this.left           = null;
        this.right          = null;
    }
    /***************************************************************************
     * Method  : init
     * Purpose : initializes the data_node object
     * 
     * @param insert
     * @param insert_key the 
     **************************************************************************/
    public void init(indeed_job insert, String insert_key)
    {
            this.key = insert_key;
            this.jobList.add(insert);
            this.length ++;
    }
    /***************************************************************************
     * Method  : insertLeft
     * Purpose : inserts data node to the left of the head
     * 
     * @param insert     the indeed_job to insert into the tree
     * @param insert_key the string parameter which acts as primary key
     **************************************************************************/
    public void insert_left(indeed_job insert, String insert_key)
    {
        data_node temp = new data_node();
        temp.init(insert, insert_key);
        this.left = temp;
    }
    /***************************************************************************
     * Method  : insertRight
     * Purpose : inserts data node to the right of the head
     * 
     * @param insert     the indeed_job to insert into the tree
     * @param insert_key the string parameter which acts as primary key 
     **************************************************************************/
    public void insert_right(indeed_job insert, String insert_key)
    {
        data_node temp = new data_node();
        temp.init(insert, insert_key);
        this.right = temp;
    }
    /***************************************************************************
     * Method : add
     * Purpose: inserts data node to current Head node
     * 
     * @param insert 
     **************************************************************************/
    public void add(indeed_job insert)
    {
        this.jobList.add(insert);
        this.length++;
    }
    
    
    
    
    
}
