/* Juan Diego Vivas Guevara */
/*c200279*/

package Projects.Individuales.Individual4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class MultiSetListIterator<E> implements Iterator<E> {
	PositionList<Pair<E,Integer>> list;
	Position<Pair<E,Integer>> cursor;
	int counter;
	Position<Pair<E,Integer>> prevCursor;
	
	public MultiSetListIterator(PositionList<Pair<E,Integer>> list) {
		this.list = list;
		cursor = list.first();
		prevCursor = null;
	}
  
  
	public boolean hasNext() {
		return (cursor != null && (counter < cursor.element().getRight() || list.next(cursor) != null));
	}
		  
	public E next() {
		if (cursor == null) throw new NoSuchElementException();
		
		E element = cursor.element().getLeft();
		
		if (counter < cursor.element().getRight()) {
			element = cursor.element().getLeft();
			counter++;
		}
		
		else if (list.next(cursor) != null) {
			cursor = list.next(cursor);
			element = cursor.element().getLeft();
			counter = 1;
		}
		
		else throw new NoSuchElementException();
		return element;
	}
	
	
	//He añadido esto:
	public PositionList<E> lista (PositionList<Pair<E,Integer>> list) {
		PositionList<E> resultado = new NodePositionList<E>();
		Position<Pair<E,Integer>> cursor = list.first();
		while (cursor != null) {
			resultado.addLast(next());
		}
		return resultado;
	}
	
	public void remove() {
		// opcionalmente se puede modificar para obtener mas puntos
		// NO ES OBLIGATORIO
	}
}
