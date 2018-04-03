import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.*;

public class JobShopFlex {

	
	
	
	public static void main(String[] args) {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge>	multiGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
	    multiGraph.addVertex("v1");
	    multiGraph.addVertex("v2");
	    DefaultWeightedEdge edge1 = multiGraph.addEdge("v1", "v2");
	  
	    
	    multiGraph.setEdgeWeight(edge1, 5);
	    System.out.println(multiGraph.getEdgeWeight(edge1));
	    
	    CycleDetector<String, DefaultWeightedEdge> cycleDetector 
	      = new CycleDetector<String, DefaultWeightedEdge>(multiGraph);
	  
	    System.out.println(cycleDetector.detectCycles());
	    
		
	}
	
	
	
	


	
	
}


