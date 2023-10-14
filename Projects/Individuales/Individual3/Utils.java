/* JD17JD */
//10

package Projects.Individuales.Individual3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import es.upm.aedlib.positionlist.*;

import es.upm.aedlib.indexedlist.*;


public class Utils {

  public static <E> Iterable<E> filter (Iterable<E> d, Predicate<E> pred) {
	  if(d == null) throw new IllegalArgumentException();
	  PositionList<E> nuevaLista = new NodePositionList<>();
	  Iterator <E> it = d.iterator();
	  while (it.hasNext()) {
		  E elemento = it.next();
		  if (elemento != null && pred.test(elemento))
			  nuevaLista.addLast(elemento);
	  }
	  return nuevaLista;
  }
}


