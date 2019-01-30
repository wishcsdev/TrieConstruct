import java.util.ArrayList;

public class TrieFeatures {
	
	static int keys;
	static double totalDepth=0;
    static double count2=0;
    static double totalDepthNF=0;
    static double totalDepth2=0;
    static double count=0;
    static double totalDuration=0;
    static double totalDuration2=0;
	
    // creating array from the list of words provided 
    public void create(ArrayList<String> wordDict){
    	//accessing words from the list of words provided 
        for (String dict: wordDict){
        	Driver.lenght ++;
        	//if the lenght of the word list is greater than the height of trie so far
            if(dict.length()> Driver.currentHeight){
            	//then make the current height the same as the dictionary lenght. 
            	//this keeps track of what the height of the trie is. 
                Driver.currentHeight = dict.length();
            }
            //convert the list of words to lower case starting from index 0 and then create the root. 
            Driver.root.create(dict.toLowerCase(),0);
            //to keep track of number of keys we have utlized so far. 
            keys++;
        }
    }
    //using the find function from the driver, we provide true or false result for searching a word. 
    //
    public boolean find(String dict){
    	//calculate the duration of the time since the find began. 
        double duration = System.nanoTime();
        //to calculate the current depth as we find for the word. 
        int depth = Driver.root.find(dict.toLowerCase(),0);
        //convert the nano time into ms for easier interpretation
        duration = (System.nanoTime()-duration)/1000000;
        //Case1: if the depth is negative or 0 then we know that the word does not exist in the document. 
        if (depth<=0){
        	totalDepthNF=depth*-1;
        	//for the word that was not found, list the duration and its depth 
        	totalDepth2+=totalDepthNF;
            //duration it took the program to search and not find the word. 
            totalDuration2+=duration;
            //total number of nodes it looked at 
            count++;
        	return (false);
        }else{
        	//if the word is found then calculate the duration and depth 
            
            totalDepth+=depth;
            totalDuration+=duration;
            count2++;
   
            return (false);
        }
        
        

    }
}
