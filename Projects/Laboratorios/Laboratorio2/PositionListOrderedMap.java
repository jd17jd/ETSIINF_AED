package Projects.Laboratorios.Laboratorio2;

import java.util.Comparator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class PositionListOrderedMap<K,V> implements OrderedMap<K,V> {
    private Comparator<K> cmp;
    private PositionList<Entry<K,V>> elements;
  
    /* Acabar de codificar el constructor */
    public PositionListOrderedMap(Comparator<K> cmp) {
	  this.cmp = cmp;
	  elements = new NodePositionList<Entry<K,V>>();
    }

    /* Ejemplo de un posible mÃ©todo auxiliar: */
  
    /* If key is in the map, return the position of the corresponding
     * entry.  Otherwise, return the position of the entry which
     * should follow that of key.  If that entry is not in the map,
     * return null.  Examples: assume key = 2, and l is the list of
     * keys in the map.  For l = [], return null; for l = [1], return
     * null; for l = [2], return a ref. to '2'; for l = [3], return a
     * reference to [3]; for l = [0,1], return null; for l = [2,3],
     * return a reference to '2'; for l = [1,3], return a reference to
     * '3'. */

    private Position<Entry<K,V>> findKeyPlace(K key) throws IllegalArgumentException {
    	if (key == null) throw new IllegalArgumentException();
    	if (elements.isEmpty()) return null;
    	int comp;
    	Position<Entry<K,V>> cursor = elements.first();
    	while (cursor != null) {
    		comp = cmp.compare(key, cursor.element().getKey());
    		if (comp <= 0) return cursor;
			cursor = elements.next(cursor);
    	}
    	return null;
    }

    /* Podeis añadir mas metodos auxiliares */
  
    public boolean containsKey(K key) {
    	Position<Entry<K,V>> cursor = findKeyPlace(key);
    	if (cursor == null || !cursor.element().getKey().equals(key))
    		return false;
    	else
    		return true;
    }
  
    public V get(K key) {
    	Position<Entry<K,V>> cursor = findKeyPlace(key);
    	if (cursor == null || !cursor.element().getKey().equals(key))
    		return null;
    	return cursor.element().getValue();
    }
  
    public V put(K key, V value) {
    	Entry<K,V> element = new EntryImpl(key,value);
    	Position<Entry<K,V>> cursor = findKeyPlace(key);
    	if(cursor == null) {
    		elements.addLast(element);
			return null;
    	}
    	if (cursor.element().getKey().equals(key)) {
    		elements.addBefore(cursor, element);
    		return elements.remove(cursor).getValue();
    	}
    	else {
    		elements.addBefore(cursor, element);
    		return null;
    	}
    }
  
    public V remove(K key) {
    	Position<Entry<K,V>> cursor = findKeyPlace(key);
    	if (cursor == null || !cursor.element().getKey().equals(key))
    		return null;
    	else 
    		return elements.remove(cursor).getValue();
    }
  
    public int size() {
    	return elements.size();
    }
  
    public boolean isEmpty() {
    	return elements.isEmpty();
    }
  
    public Entry<K,V> floorEntry(K key) {
    	Position<Entry<K,V>> cursor = findKeyPlace(key);
    	if (cursor == null) {
    		if (elements.isEmpty())
    			return null;
    		else
    			return elements.last().element();
    	}
    	else if (!cursor.element().getKey().equals(key)) {
    		if (elements.prev(cursor) == null)
    			return null;
    		else
    			return elements.prev(cursor).element();
    	}
    	else
    		return cursor.element();
    }
  
    public Entry<K,V> ceilingEntry(K key) {
    	Position<Entry<K,V>> cursor = findKeyPlace(key);
    	if (cursor == null)
    		return null;
    	else
    		return cursor.element();
    }
  
    public Iterable<K> keys() {
    	PositionList<K> resultado = new NodePositionList<K>();
    	Position<Entry<K,V>> cursor = elements.first();
    	while (cursor != null) {
    		resultado.addLast(cursor.element().getKey());
    		cursor = elements.next(cursor);
    	}
    	return resultado;
    }
    
    //Another way
//    public Iterable<K> keys() {
//    	PositionList<K> lista = new NodePositionList<>();
//    	for(Entry<K,V> element : elements) {
//    		if (element != null) {
//    			lista.addLast(element.getKey());
//    		}
//    	}
//	return lista;
//    }
  
    public String toString() {
    	return elements.toString();
    }
}
