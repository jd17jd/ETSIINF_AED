/* JD17JD */
//10

//Borrar elementos repetidos en una lista indexada

package Projects.Individuales.Individual2;
import es.upm.aedlib.indexedlist.*;


public class Utils {
	
	public static <E> IndexedList<E> deleteRepeated(IndexedList<E> l) {
		IndexedList<E> resultado = new ArrayIndexedList<>();
		for (int i = 0; i < l.size(); i++) {
			if (resultado.indexOf(l.get(i)) == -1)
				resultado.add(resultado.size(), l.get(i));
		}
		return resultado;
	}
	
}
