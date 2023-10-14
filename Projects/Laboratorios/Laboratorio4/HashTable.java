package Projects.Laboratorios.Laboratorio4;

import java.util.Iterator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;


/**
 * A hash table implementing using open addressing to handle key collisions.
 */
public class HashTable<K, V> implements Map<K, V> {
    Entry<K, V>[] buckets;
    int size;

    public HashTable(int initialSize) {
        this.buckets = createArray(initialSize);
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object o) {
        return search(o) != -1;
    }

    public Iterable<K> keys() {
        PositionList<K> iterable = new NodePositionList<>();
        for (Entry<K, V> bucket : buckets) {
            if (bucket != null)
                iterable.addLast(bucket.getKey());
        }
        return iterable;
    }

    public Iterator<Entry<K, V>> iterator() {
        return entries().iterator();
    }

    public Iterable<Entry<K, V>> entries() {
        PositionList<Entry<K, V>> iterable = new NodePositionList<>();
        for (Entry<K, V> bucket : buckets)
            if (bucket != null)
                iterable.addLast(bucket);
        return iterable;
    }

    public V put(K key, V val) {
        int pos = realIndex(key);
        //Si esta lleno el mapa
        if (pos == -1) {
            rehash();
            pos = realIndex(key);
        } 
        //Si la posicion que ponemos esta libre
        if (buckets[pos] == null) {
            buckets[pos] = new EntryImpl(key, val);
            size++;
            return null;
        }
        //Si la Clave que queremos añadir ya tiene algo
        //buckets[i].getKey().equals(key)
        else {
            Entry<K, V> anterior = buckets[pos];
            buckets[pos] = new EntryImpl(key, val);
            return anterior.getValue();
        }
    }

    public V get(Object obj) {
        int pos = search(obj);
        return pos == -1 ? null : buckets[pos].getValue();
    }

    public V remove(Object obj) {
        int indice = search(obj);
        if (indice == -1) return null;
        Entry<K, V> anterior = buckets[indice];
        buckets[indice] = null;
        size--;
        colapsar(indice);
        return anterior.getValue();
    }


    // Examples of auxilliary methods: IT IS NOT REQUIRED TO IMPLEMENT THEM

    @SuppressWarnings("unchecked")

    private void colapsar(int inicio) {
        int i = (inicio == buckets.length - 1 ? 0 : inicio + 1);
        while (buckets[i] != null && i != inicio) {
            int correcta = realIndex(buckets[i].getKey());
            if (correcta != i) {
                buckets[correcta] = buckets[i];
                buckets[i] = null;
            }
            i = (i == buckets.length - 1 ? 0 : i + 1);
        }
    }


    private Entry<K, V>[] createArray(int size) {
        Entry<K, V>[] buckets = (Entry<K, V>[]) new Entry[size];
        return buckets;
    }

    // Returns the ideal bucket index of an object
    private int index(Object obj) {
        return Math.abs(obj.hashCode()) % buckets.length;
    }

    // Devuelve el indice real donde tiene que ponerse el objeto (esté ya o no en los buckets), devuelve -1 si no hay hueco
    private int realIndex(Object obj) {
        int inicio = index(obj);
        int i = inicio;
        do {
            if (buckets[i] == null || buckets[i].getKey().equals(obj)) return i;
            i = (i == buckets.length - 1 ? 0 : i + 1);
        } while (inicio != i);
        return -1;
    }

    // Returns the index where an entry with the key is located,
    // or if no such entry exists, the "next" bucket with no entry,
    // or if all buckets stores an entry, -1 is returned.
    private int search(Object obj) {
        int index = realIndex(obj);
        if (index >= 0 && buckets[index] != null)
            return index;
        else return -1;
    }

    // Doubles the size of the bucket array, and inserts all entries present
    // in the old bucket array into the new bucket array, in their correct
    // places. Remember that the index of an entry will likely change in
    // the new array, as the size of the array changes.
    private void rehash() {
        HashTable<K, V> nuevo = new HashTable<K,V>(buckets.length * 2);
        for (int i = 0; i < buckets.length; i++)
            nuevo.put(buckets[i].getKey(), buckets[i].getValue());
        buckets = nuevo.buckets;
    }
}

