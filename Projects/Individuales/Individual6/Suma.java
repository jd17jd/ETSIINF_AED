 /* Juan Diego Vivas Guevara, c200279 */
package Projects.Individuales.Individual6;

import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import es.upm.aedlib.map.HashTableMap;


public class Suma {
	
	/*
	 * DirectedGraph<Integer, E> :  representa el grafo en el que buscamos.
	 * 
	 * Vertex<Integer> contenido en 'graph' : representa el Vertice a
	 * partir del cual queremos encontrar la suma de todos los conexos.
	 * 
	 * Set<Vertex<Integer> : almacena los Vertices ya visitados.
	 */
	private static <E> int sumaRec
	(DirectedGraph<Integer, E> g, Vertex<Integer> startVertex, Set<Vertex<Integer>> reachable) {
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
		
	public static <E> Map<Vertex<Integer>,Integer> sumVertices(DirectedGraph<Integer,E> g) {
		Map<Vertex<Integer>, Integer> resultado = new HashTableMap<Vertex<Integer>,Integer>();		
		for (Vertex<Integer> vertice: g.vertices()) {
			Set<Vertex<Integer>> reachable = new HashTableMapSet<Vertex<Integer>>();
			int res = sumaRec(g, vertice, reachable);
			resultado.put(vertice, res);
		}
	return resultado;
	}
	
}
