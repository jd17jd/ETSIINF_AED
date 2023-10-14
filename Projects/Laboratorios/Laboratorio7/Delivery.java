package Projects.Laboratorios.Laboratorio7;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import java.util.Iterator;

public class Delivery<V> {
	
	private DirectedGraph<V, Integer>grafo;
	private Map<Vertex<V>,Integer> camino;
	
	public Delivery(V[] places, Integer[][] gmat) {
		camino = new HashTableMap<Vertex<V>, Integer>();
		grafo = new DirectedAdjacencyListGraph<V, Integer>();
		Map<V,Vertex<V>> aux = new HashTableMap<V,Vertex<V>>();
		for (int i = 0; i < places.length; i++) {
			V vertice = places[i];
			aux.put(vertice, grafo.insertVertex(vertice));
		}
		if (!grafo.isEmpty()) {
			for (int i = 0; i < gmat.length; i++) {
				for (int j = 0; j < gmat[i].length; j++) {
					Integer valor = gmat[i][j];
					if (valor != null) {
						grafo.insertDirectedEdge(aux.get(places[i]), aux.get(places[j]), valor);
					}
				}
			}
		}
	}
	
	public DirectedGraph<V, Integer> getGraph() {
		return grafo;
	}
	
	public PositionList<Vertex<V>> tour() {
		PositionList <Vertex<V>> resultado = new NodePositionList<Vertex<V>>();
		if (!grafo.isEmpty()) {
			Iterator<Vertex<V>> vertices = grafo.vertices().iterator();
			while (vertices.hasNext()) {
				resultado = tourRec(camino, resultado, vertices.next(), 0);
				if (resultado.size() == grafo.numVertices()) {
					return resultado;
				}
			}
		}
		return null;
	}
	
	private PositionList<Vertex<V>> tourRec(Map<Vertex<V>, Integer> camino, PositionList<Vertex<V>> resultado,
			Vertex<V> vertice, Integer valor) {
		if (!camino.containsKey(vertice)) {
			camino.put(vertice, valor);
			resultado.addLast(vertice);
		
			for (Edge<Integer> arista : grafo.outgoingEdges(vertice)){
				resultado = tourRec(camino, resultado, grafo.endVertex(arista), arista.element());
			}
			
			if (resultado.size() != grafo.numVertices()) {
				vertice = resultado.remove(resultado.last());
				camino.remove(vertice);
			}
		}
		return resultado;
	}
	
	public int length(PositionList<Vertex<V>> path) {
		Integer resultado = 0;
		for (Position <Vertex<V>> cursor = path.first(); cursor != null; cursor = path.next(cursor)) {
			resultado += camino.get(cursor.element());
		}
		return resultado;
	}

	public String toString() {
		return "Delivery";
	}
}
