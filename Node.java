import java.util.ArrayList;


public class Node {

    private Character key;
    private ArrayList<Node> links;

    public Node(char key){
        this.key = key;
        links = new ArrayList<Node>();
    }
    public Node (){
        key= null;
        links = new ArrayList<Node>();
    }

    public void add(String word,int index){
        if (index == word.length()){
            links.add(null);
        }
        else if (links.size()== 0){
            Node leafNode = new Node(word.charAt(index));
            links.add(leafNode);
            index++;
            leafNode.add(word,index);

        }
        else {
            for (Node node: links){
                if (node != null && node.key == word.charAt(index)){
                    index++;
                    node.add(word,index);
                    return;
                }

            }

            //Add new node if the nodes don't contain the character
            Node leafNode = new Node(word.charAt(index));
            links.add(leafNode);
            index++;
            leafNode.add(word,index);
        }
        return;
    }

    public int search(String word, int index){
        if (index == word.length()){
            if (links.contains(null)){

                return(index);
            }else{
                return (-index);
            }

        }

        else if(links.size() > 0){

            for (Node node: links){
                if (node != null && node.key == word.charAt(index)){
                    index++;
                    return(node.search(word,index));
                }
            }
            return -index;

        }
        return -index;
    }

}
