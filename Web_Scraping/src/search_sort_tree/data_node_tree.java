package search_sort_tree;
import file_io.FileWrite;
import indeed.indeed_job;

    
public class data_node_tree 
{
    private data_node head;                     //Head data_node
    private int       size;                     //Size of structure
    
    /***************************************************************************
     * Constructor
     * Purpose: initializes the tree
     * 
     **************************************************************************/
    public data_node_tree()
    {
        this.head = null;                      //Initialize empty tree head to null
        this.size = 0;                         //Initialize size to '0'
    }
    /***************************************************************************
     * Method:  insert
     * Purpose: inserts data into tree based on insertion key  * 
     * @param insert     the data_node to insert in the tree
     * @param insert_key the primary key used to sort tree
     **************************************************************************/
    public void insert(indeed_job insert, String insert_key)
    {
        if(this.size == 0)                      //if tree is empty          
        {                                       // :
            data_node temp = new data_node();   // : Create new data node 'temp'
            temp.init(insert, insert_key);      // : Populate 'temps' variables
            this.head = temp;                   // : Insert 'temp' into head
            this.size++;                        // : Set size to '1'
        }
        else                                    //If tree is NOT empty
        {                                       // : use recursive algorithm
                                                // : to determine where to store
                                                // : the data_node into the tree
           recursive_insert(this.head, insert, insert_key);
        }
    }
    /***************************************************************************
     * Method  : recursive_insert
     * Purpose : recursively inserts data_node into tree 
     * 
     **************************************************************************/
    private static void recursive_insert(data_node head, indeed_job insert, String insert_key)
    {
                                                //Compare head key to insert key
        int comparison = head.key.compareTo(insert_key);
        
                                                //Check for exact match
        if(comparison == 0)                     // :
        {                                       // :
            head.add(insert);                   // : add to head node
        }
                                                //Check if string is LARGER
        else if(comparison > 0)                 // :
        {                                       // :
            if(head.right == null)              // : Check if right is empty
            {                                   //      :
                head.insert_right(insert, insert_key);
                                                //      : If so, insert right
            }                                   // :
            else                                // :
            {                                   // : Else, continue search right
                recursive_insert(head.right, insert, insert_key);
            }
        }
                                                //Check if string is SMALLER
        else                                    // :
        {                                       // :
            if(head.left == null)               // : Check if left is empty
            {                                   //      :
                                                //      : If so, insert left
                head.insert_left(insert, insert_key);
                                                // :
            }                                   // :
            else                                // :
            {                                   // : Else, continue search left
                recursive_insert(head.left, insert, insert_key);
            }
        }
    }
    /***************************************************************************
     * Method  : printKeys()
     * Purpose : recursively prints the keys
     * 
     **************************************************************************/
    public void printKeys(String location, boolean append)
    {
        recursivePrint(this.head,location, append);
    }
    /***************************************************************************
     * Method  : recursivePrint()
     * Purpose : recursively prints the keys
     * 
     **************************************************************************/
    private static void recursivePrint(data_node head, String location, boolean append)
    {
        if(head == null)
        {
            //Do Nothing
        }
        else
        {
            FileWrite fwrite = new FileWrite();
            fwrite.setup(location, append);
            String temp = String.format("%s|%d\n", head.key, head.length);
            fwrite.write(temp);
        }
        if(head.right != null)
        {
            recursivePrint(head.right, location, append);
        }
        if(head.left != null)
        {
            recursivePrint(head.left, location, append);
        }
    } 
}
