import java.util.ArrayList;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.*;

public class JobShopFlex {

	private DirectedWeightedMultigraph<Point, DefaultWeightedEdge> multiGraph = new DirectedWeightedMultigraph<Point, DefaultWeightedEdge>(
			DefaultWeightedEdge.class);
	Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
	Set<DefaultWeightedEdge> setedge;
	Set<Point> setpoint;

	public JobShopFlex() {
		// TODO Auto-generated constructor stub
		p1 = new Point("A", 0);
		p2 = new Point("o1.1", -1);
		p3 = new Point("o1.2", -1);
		p4 = new Point("o1.3", -1);
		p5 = new Point("o2.1", -1);
		p6 = new Point("o2.2", -1);
		p7 = new Point("o2.3", -1);
		p8 = new Point("o3.1", -1);
		p9 = new Point("o3.2", -1);
		p10 = new Point("S", -1);

		multiGraph.addVertex(p1);
		multiGraph.addVertex(p2);
		multiGraph.addVertex(p3);
		multiGraph.addVertex(p4);
		multiGraph.addVertex(p5);
		multiGraph.addVertex(p6);
		multiGraph.addVertex(p7);
		multiGraph.addVertex(p8);
		multiGraph.addVertex(p9);
		multiGraph.addVertex(p10);

		DefaultWeightedEdge edgeA1 = multiGraph.addEdge(p1, p2);
		DefaultWeightedEdge edgeA2 = multiGraph.addEdge(p1, p3);
		DefaultWeightedEdge edgeA3 = multiGraph.addEdge(p1, p4);

		DefaultWeightedEdge edgeI1 = multiGraph.addEdge(p2, p5);
		DefaultWeightedEdge edgeI2 = multiGraph.addEdge(p5, p8);
		DefaultWeightedEdge edgeI3 = multiGraph.addEdge(p3, p6);
		DefaultWeightedEdge edgeI4 = multiGraph.addEdge(p6, p9);
		DefaultWeightedEdge edgeI5 = multiGraph.addEdge(p4, p7);

		DefaultWeightedEdge edgeS1 = multiGraph.addEdge(p8, p10);
		DefaultWeightedEdge edgeS2 = multiGraph.addEdge(p9, p10);
		DefaultWeightedEdge edgeS3 = multiGraph.addEdge(p7, p10);

		multiGraph.setEdgeWeight(edgeA1, 0);
		multiGraph.setEdgeWeight(edgeA2, 0);
		multiGraph.setEdgeWeight(edgeA3, 0);

		multiGraph.setEdgeWeight(edgeI1, 3);
		multiGraph.setEdgeWeight(edgeI2, 2);
		multiGraph.setEdgeWeight(edgeI3, 4);
		multiGraph.setEdgeWeight(edgeI4, 2);
		multiGraph.setEdgeWeight(edgeI5, 2);

		multiGraph.setEdgeWeight(edgeS1, 5);
		multiGraph.setEdgeWeight(edgeS2, 2);
		multiGraph.setEdgeWeight(edgeS3, 3);

		// ajouter les edges conflit

		DefaultWeightedEdge conflitM1 = multiGraph.addEdge(p2, p6);
		multiGraph.setEdgeWeight(conflitM1, 3);

		DefaultWeightedEdge conflitM21 = multiGraph.addEdge(p3, p5);
		multiGraph.setEdgeWeight(conflitM21, 4);

		DefaultWeightedEdge conflitM22 = multiGraph.addEdge(p5, p7);
		multiGraph.setEdgeWeight(conflitM22, 2);

		DefaultWeightedEdge conflitM31 = multiGraph.addEdge(p4, p9);
		multiGraph.setEdgeWeight(conflitM31, 2);

		DefaultWeightedEdge conflitM32 = multiGraph.addEdge(p9, p8);
		multiGraph.setEdgeWeight(conflitM32, 2);

		// System.out.println(multiGraph.getEdgeWeight(edge1));

		CycleDetector<Point, DefaultWeightedEdge> cycleDetector = new CycleDetector<Point, DefaultWeightedEdge>(
				multiGraph);

		System.out.println(cycleDetector.detectCycles());

	}

	// trouver les points précedents

	public ArrayList<Point> pointsprecedents(Point porigi) {

		ArrayList<Point> result = new ArrayList<>();

		for (DefaultWeightedEdge d : setedge) {

			if (porigi.getLabel().equals(multiGraph.getEdgeTarget(d).getLabel())) {

				result.add(multiGraph.getEdgeSource(d));

			}

		}

		return result;

	}

	public int iteration(Point f) {

		ArrayList<Point> arrayList = pointsprecedents(f);
		ArrayList<Integer> tempvaleur  =  new ArrayList<>();

		for (Point point : arrayList) {

			if (point.getValue() == -1) {

				iteration(point);

			}
		}
		int j = -1;
		
		for (Point point : arrayList) {

			DefaultWeightedEdge d = null;
			
			for (DefaultWeightedEdge di : setedge) {
				//System.out.println(f.getLabel()  + point.getLabel());
				if (multiGraph.getEdgeSource(di).getLabel().equals(point.getLabel())
						&& multiGraph.getEdgeTarget(di).getLabel().equals(f.getLabel())) {
					
					d = di;
				}
			}

			int length = (int) (point.getValue() + multiGraph.getEdgeWeight(d));
			//System.out.println(point.getLabel()+ "  "+point.getValue());
			if (length > j) {
				j = length;
			}

		}
		
		 
		
		f.setValue(j);
		
		if(arrayList.size()==0) {
			
			f.setValue(0);;
			
		}
		return j;

	}

	public int calculer() {

		setedge = multiGraph.edgeSet();
		setpoint = multiGraph.vertexSet();

		for (Point ptocalculer : setpoint) {

			iteration(ptocalculer);

		}

		for (Point p : setpoint) {

			System.out.println(p.getLabel() + ":" + p.getValue());

		}

		return 0;

	}

}
