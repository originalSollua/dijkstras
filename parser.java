import java.util.Scanner;
import java.util.ArrayList;
public class parser{

    // parse class
    // handles the reading of the text input
    // builds a graph and then lets the user play with it
    
    public graph testGraph;

    public static void main (String [] args){
        parser p = new parser();
        // command line arg will contain a command.
        if (args.length >=1 && args[0].equals("build")){
            // begins the graph building process.
            // accepts text input from commandline
            // will call to a method that will read in and build an
            // array list of strings
            p.listGraphBuild();
            System.out.println("Done building graph");
            System.out.println(p.testGraph.toString());
            System.out.println(p.testGraph.evaluate(args[1], args[2]));
            System.out.println(p.testGraph.printPath());
        }
        else{
            p.emptyGraph();
        }
    }
    // builds a list of strings 
    // then crams that list into the graph constructor
    public void listGraphBuild(){
        Scanner kb = new Scanner(System.in);
        ArrayList <String> procList = new ArrayList<String>();
        String proc = kb.nextLine();
        System.out.println(proc);
        while( !proc.equals("*")){
            if (!proc.equals("*")){
                // put in list
                procList.add(proc);
            }
            proc = kb.nextLine();
        }
        testGraph = new graph(procList);
        System.out.println("chicken");
    }
    public void emptyGraph(){ 
        testGraph = new graph();
    }


}
