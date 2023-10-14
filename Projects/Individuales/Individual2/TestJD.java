package Projects.Individuales.Individual2;

//Borrar elementos repetidos en una lista indexada

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;

public class TestJD {
	
	public static void main (String [] args) {
		
		IndexedList<Integer> lista = new ArrayIndexedList<Integer>();
		lista.add(0, 1);
		lista.add(1, 1);
		lista.add(2, 2);
		lista.add(3, 3);
		lista.add(4, 3);
		lista.add(5, 3);
		System.out.println(lista);
		
		lista = Utils.deleteRepeated(lista);
		System.out.println(lista);
	}
}
