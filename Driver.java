//Vishaal Bakshi
//UCID: 00305550
//CPSC 335 Assignment4


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.text.rtf.RTFEditorKit;

import com.sun.xml.internal.txw2.Document;

//Citations
//https://www.geeksforgeeks.org/trie-insert-and-search/
//https://github.com/joniles/rtfparserkit
//https://github.com/apache/tika/blob/master/tika-parsers/src/main/java/org/apache/tika/parser/rtf/RTFParser.java
//https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
//http://homepage.divms.uiowa.edu/~sriram/21/spring07/code/Trie.java



public class Driver {
	
	
    public static int lenght=0;
    public static Node root;
    public static int currentHeight=0;
    public static int calculate=0;
    public static Character letters;
    public static ArrayList<Node> links;
    
    static TrieFeatures features=new TrieFeatures();
    // driver function 
    public static void main (String[] args) throws IOException{

        if(args.length !=2) {
            System.out.print("File not provided");
        }
        //take the first argument and pass to method for generating arrayList
        Driver Driver = new Driver(GetDictionary(args[0]));
        
        //take second argument which is the document and pass to method 
        ArrayList<String> GetDoc = Document(args[1]);
        for (String imported:GetDoc){
            features.find(imported.toLowerCase());
        }
        System.out.println("The Height of the trie is "+currentHeight);
        System.out.println("There are " +TrieFeatures.keys+" number of keys");
        System.out.println("Total Depth for all keys found " +TrieFeatures.totalDepth);
        System.out.println("Total number of Keys Found " +TrieFeatures.count2);
        System.out.println("The avergae depth for words found is:" +((TrieFeatures.totalDepth)/TrieFeatures.count2));
        System.out.println("The avergae time for words found is: "+((TrieFeatures.totalDuration)/TrieFeatures.count2+" ms"));
        
        System.out.println("Total Depth of all keys Not found " +TrieFeatures.totalDepth2);
        System.out.println("Total number of Keys Not Found " +TrieFeatures.count);
        System.out.println("The average depth for words not found:" +((TrieFeatures.totalDepth2)/TrieFeatures.count));
        System.out.println("The avergae time for words not found is: "+((TrieFeatures.totalDuration2)/TrieFeatures.count2+" ms"));
	}

    private static ArrayList<String> Document(String fileName) throws IOException {
        ArrayList<String> strings = new ArrayList<String>();

        try {//InputStream is = new FileInputStream("/path/to/my/file.rtf");
        	//IRtfSource source = new RtfStreamSource(is)
        	//IRtfParser parser = new StandardRtfParser();
        	//MyRtfListener listener = new MyRtfListener();
        	//parser.parse(source, listener);
            //Scanner filename = new Scanner(new FileReader(fileName));
        	Scanner filename = new Scanner(new FileReader(fileName));
            while (filename.hasNextLine()) {
                strings.addAll(new ArrayList<String>(Arrays.asList(filename.nextLine().split("\\W+"))));
            }
            //RTFEditorKit rtfParser = new RTFEditorKit();
            //Document document = rtfParser.createDefaultDocument();
            //rtfParser.read(new ByteArrayInputStream(rtfBytes), document, 0);
            //String text = document.getText(0, document.getLength());
            //System.out.print(args[0]);
        	//get the document from arguments provided
            while(strings.remove(""));
            //Remove empty spaces
            
        } catch (FileNotFoundException e) {
            System.out.print("No such file exists");
            System.exit(1);
        }
        return strings;
    }
    //get list of words to be used as a dictionary and put them into an string array list
    private static ArrayList<String> GetDictionary(String fileName) throws IOException{
        ArrayList<String> dictionaryList = new ArrayList<String>();
        try {
        	//InputStream is = new FileInputStream("/path/to/my/file.rtf");
        	//IRtfSource source = new RtfStreamSource(is)
        	//IRtfParser parser = new StandardRtfParser();
        	//MyRtfListener listener = new MyRtfListener();
        	//parser.parse(source, listener);
            Scanner filename = new Scanner(new FileReader(fileName));
            //RTFEditorKit rtfParser = new RTFEditorKit();
            //Document document = rtfParser.createDefaultDocument();
            //rtfParser.read(new ByteArrayInputStream(rtfBytes), document, 0);
            //String text = document.getText(0, document.getLength());
            //System.out.print(args[0]);
            while (filename.hasNextLine()) {
                dictionaryList.add(filename.nextLine());
            }
            filename.close();
            // when file not found, throw exception and exit 
        } catch (FileNotFoundException e) {
            System.out.print("File not found. Please retry");
            System.exit(1);
        }
        //return the dict list of all words added into array
        return dictionaryList;
        
    }
  //create nodes for Trie
    public Driver(ArrayList<String> NodeCreate){
        root = new Node();
        features.create(NodeCreate);
    }
    public class Node {
    	private Character letters;
	    private ArrayList<Node> links;
	    // constructor for the class that initializes automatically when char not provided. 
	    public Node (){
	        letters= null;
	        //link is not connected to anythin as the char is null
	        links = new ArrayList<Node>();
	    }
	    //take character and create links to the Node Arraylist
	    //creates links between each node for trie 
	    public Node(char letters){
	        this.letters = letters;
	        //link connection generated using arraylist. 
	        links = new ArrayList<Node>();
	    }
	    //method to find for words by moving through trie
	    public int find(String letters, int loc){
	        //the location of the letter is the same as the lenght of the word
	    	if (loc == letters.length()){
	    		////links index is empty then return the location and exit
	            if (links.contains(null)){
	            	return(loc);
	            }else{
	            	//move backwards 
	                return (-loc);
	            }
	        }
	    	//if the size of the pointers exist 
	        else if(links.size() > 0){
	        	//for each with links we will check for the condition
	            for (Node n: links){
	            	//if the node is not null and location of links are the same for the letters
	                if (n != null && n.letters == letters.charAt(loc)){
	                    loc++;
	                    //recursively find for the letters using the chars of the word
	                    return(n.find(letters,loc));
	                }
	            }
	            return -loc;
	        }
	        return -loc;
	    }
	    //method to generate nodes from characters of letters. 
	    public void create(String letters,int loc){
	        //if the index is 0 and there is noting to add then keep pointer at null 
	    	if (loc == letters.length()){
	    		//add null to the link to end find since there is nothing to look for
	            links.add(null);
	        }
	    	//if the size is 0, meaning no links then proceed to create a leaf 
	        else if (links.size()== 0){
	        	//the last node uses the character from the location passed into constructor.
	        	//in this case it would be zero.
	            Node endNode = new Node(letters.charAt(loc));
	            //after creating the final node we can increment the location and add the link to the nodes
	            links.add(endNode);
	            loc++;
	            //we use the recursive function to continue to add the words until one of the conditions are met. 
	            endNode.create(letters,loc);

	        }
	    	//for when the links do exist and it is not a null pointer. 
	        else {
	        	//using Node links.
	            for (Node node: links){
	            	//for the case when node is not empty and letters exist at location
	                if (node != null && node.letters == letters.charAt(loc)){
	                    loc++;
	                    //again we recursively find for the location of the file. 
	                    node.create(letters,loc);
	                    return;
	                }
	            }
	            //for the case when the node doesnt exist. we generate a new node with the letters 
	            Node endNode = new Node(letters.charAt(loc));
	            // add the leaf node to the link and then increement the location of the arrayList of char
	            links.add(endNode);
	            loc++;
	            //recursively call the function to add word. 
	            endNode.create(letters,loc);
	        }
	        return;
	    }
    }
}
