package Projects.Individuales.Individual3;

import java.util.function.Predicate;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class TestJD {
	
	public static void main (String [] args) {
		
		PositionList<Integer> listaPosition = new NodePositionList<Integer>();
		listaPosition.addLast(1);
		listaPosition.addLast(2);
		listaPosition.addLast(3);
		listaPosition.addLast(4);
		listaPosition.addLast(5);
		
		IndexedList<Integer> listaArray = new ArrayIndexedList<Integer>();
		listaArray.add(0, 1);
		listaArray.add(1, 2);
		listaArray.add(2, 3);
		listaArray.add(3, 4);
		listaArray.add(4, 5);
		
		Predicate<Integer> predicate = i -> (i > 10);
		
		System.out.println("ListaPosition = " + listaPosition);
		
		System.out.println("Prueba 1 listaPosition = " + Utils.filter(listaPosition, predicate));
		
		Predicate<Integer> predicate2 = i -> (i > 1);
		
		System.out.println("Prueba 2 listaPosition = " + Utils.filter(listaPosition, predicate2) + "\n");
		
		System.out.println("ListaArray = " + listaArray);
		
		System.out.println("Prueba 1 listaArray = " + Utils.filter(listaArray, predicate));
		
		System.out.println("Prueba 2 listaArray = " + Utils.filter(listaArray, predicate2));
		
	}

}
