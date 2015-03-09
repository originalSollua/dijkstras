import java.util.HashMap;
import java.util.ArrayList;
public class graph{

    // graph class
    // a graph is a collection of linked nodes. 
    // will hash map a node's name to the nodes it can reach with the weight
    // node will be a string for the name. 
    // create an edge class.
    // map string to edges.
    class edge{
        public String from;
        public String to;
        public int weight;
        public edge(String t, String f, int w){
            from = f; 
            to = t;
            weight = w;
        }
        public String toString(){
            return to+weight;
        }

    }
    class Node{
        public String name;
        public int dist;
        public boolean visited;
        public Node(String n){
            name = n;
            dist = Integer.MAX_VALUE;
            visited = false;
        }
     
    }
    public HashMap<String, ArrayList<edge>> Graphmap; 

    // constructors
    // build an empty graph
    public graph(){
        Graphmap = new HashMap <String, ArrayList<edge>>();
    }
   
    // constructor for a list of strings.
    // the list of strings can be in the predefined format, unprocessed
    public graph(ArrayList<String> strList){
        // must process every element of the string list.
        // string form is A B1 C2 D3 ...
        // so i want to split around spaces.
        // then i can process the chunks
        Graphmap = new HashMap<String, ArrayList<edge>>();
        for(int i = 0; i < strList.size(); i++){
            addRaw(strList.get(i));
        }
    }
    // functionality to add raw stringsa to the graph on the fly
    public void addRaw(String s){
        // string in raw format. A B2 C3 D4
        String[] procThis =  s.split(" ");
        // important for j to start at 1
        // procTis[0] is the source node for all these edges.
        ArrayList<edge> elist = new ArrayList<edge>();
        for(int j = 1; j < procThis.length; j++)
            // build the array list of edges first dummy
            elist.add(new edge(""+procThis[j].charAt(0), procThis[0], Integer.parseInt(""+procThis[j].substring(1, procThis[j].length()))));
        Graphmap.put(procThis[0], elist);

    }
    
    // add an edge to the graph
    public void addEdge(String to, String from, int weight){
        if(Graphmap.get(to) !=null){
            ArrayList<edge> temp = Graphmap.get(to);
            temp.add(new edge(to, from, weight));
            Graphmap.put(to, temp);
        }
        else{
            ArrayList<edge> t = new ArrayList<edge>();
            t.add(new edge(to, from, weight));
            Graphmap.put(to ,t);
        }
    }

    // functionality to get a node
    // returns its name and all its edges
    public String getNode(String name){
        if(Graphmap.get(name) == null){
            return name=" does  not exist in this graph";
        }
        else{
            String edges = "";
            ArrayList <edge> e = Graphmap.get(name);
            for(int i = 0; i < e.size(); i++){
                edges = edges+" "+e.get(i).toString();
            }
            return name+edges;
        }
    }


    // evaluate
    // find the lowest cost path from node A to node B
    // use Dijkstra's algrotihm
    public String evaluate(String start, String end){
        // set initial node as zero distance
        HashMap<String, Node> nodeMap = new HashMap<String, Node>();
        HashMap<String, Integer> doneMap = new HashMap<String, Integer>();
        for(String a : Graphmap.keySet()){
            nodeMap.put(a, new Node(a));
        }
        Node curent = nodeMap.get(start);
        curent.dist = 0; 
        do{
            // nodemap is the unvisited set
            // get the reachable nodes from the map
            ArrayList<edge> neighbors =  Graphmap.get(curent.name);
            for(edge e : neighbors){
                String name = e.to;
                int weight = e.weight;
                int tDist = curent.dist + weight;
                if(nodeMap.get(name) !=null && nodeMap.get(name).dist > tDist){
                    Node t = nodeMap.get(name);
                    t.dist = tDist;
                    nodeMap.put(name, t);
                }
            }
            curent.visited = true;
            System.out.println(curent.name+ " " + curent.dist);
            nodeMap.remove(curent.name);
            doneMap.put(curent.name, curent.dist);
            int testDist = Integer.MAX_VALUE;
            for(String a:nodeMap.keySet()){
                if(nodeMap.get(a).dist <= testDist){
                    curent = nodeMap.get(a);
                    testDist = curent.dist;
                }
            }

        }while(nodeMap.containsKey(end));


        return "Distance of: "+doneMap.get(end);
    }

    public String toString(){
        String ret ="";
        for(String key: Graphmap.keySet()){
            ret= ret+getNode(key)+"\n";
        }
        return ret;
    }

// toDo:
    // evaluate(start, end) - placeholder in place
//


} 
