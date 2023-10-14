package Projects.Individuales.Individual6;

import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;

public class TestJD {
	
	private static <E> int sumaRec (DirectedGraph<Integer, E> g, Vertex<Integer> startVertex, Set<Vertex<Integer>> reachable) {
		if(!reachable.contains(startVertex)) {
			Iterable<Edge<E>> edges = g.outgoingEdges(startVertex);
			int resultado = startVertex.element().intValue();
			reachable.add(startVertex);
			for(Edge<E> edge : edges) {
				resultado += sumaRec(g, g.endVertex(edge), reachable);
			}
			return resultado;
		}
		else return 0;
	}
	
	//No sabria como hacerlo iterativamente
	private static <E> int SumaIterativa (DirectedGraph<Integer, E> graph, Vertex<Integer> startVertex, Set<Vertex<Integer>> reachable) {
		//2. No sé qué condicion meter en un bucle para que cuando el de abajo haya acabado, pase con el siguiente vertice y vuelva a itrar
		Iterable<Edge<E>> edges = graph.outgoingEdges(startVertex);
		int resultado = startVertex.element();
		reachable.add(startVertex);
		//1. Este bucle me hace esto tantas veces como aritas salientes tenga el primer vertice.
		for (Edge<E> edge : edges) {
			reachable.add(startVertex);
			startVertex = graph.endVertex(edge);
			resultado += startVertex.element();
		}
		return resultado;
	}
	
	public static void main (String [] args) {
		
		  DirectedAdjacencyListGraph<Integer,Integer> graph = new DirectedAdjacencyListGraph<Integer,Integer>();
		  Vertex<Integer> v_0 = graph.insertVertex(1);
		  Vertex<Integer> v_1 = graph.insertVertex(2);
		  Vertex<Integer> v_2 = graph.insertVertex(3);
		  Vertex<Integer> v_3 = graph.insertVertex(4);
		  
		  graph.insertDirectedEdge(v_0,v_1,null);
		  graph.insertDirectedEdge(v_1,v_2,null);
		  graph.insertDirectedEdge(v_2,v_1,null);
		  graph.insertDirectedEdge(v_2,v_3,null);
		  
		  Set<Vertex<Integer>> reachable = new HashTableMapSet<Vertex<Integer>>();
		  
		  System.out.println(graph);
		  
		  System.out.println(Suma.sumVertices(graph));
		  
		  System.out.println(sumaRec(graph, v_0, reachable));

		  System.out.println(SumaIterativa(graph, v_0, reachable));
	}

}
