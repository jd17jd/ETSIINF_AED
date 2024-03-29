package Projects.Laboratorios.Laboratorio2;

import es.upm.aedlib.Entry;

/**
 * The OrderedMap interface specifies a map (a function) between 
 * keys and values, and where the keys are ordered.
 */
public interface OrderedMap<K,V> {
  
  /**
   * Returns true if the map contains an entry with the key argument,
   * and false otherwise.
   * @return true if the map contains an entry with the key argument,
   * and false otherwise.
   * @throws IllegalArgumentException if the key is invalid (e.g., the null value).
   */
  public boolean containsKey(K key);
  
  /**
   * Returns the value associated with the key argument,
   * or, if no mapping for the key exists, the null value.
   * @return the old value if there is a mapping for the key,
   * and null otherwise.
   * @throws IllegalArgumentException if the key is invalid (e.g., the null value).
   */
  public V get(K key);
  
  /**
   * Adds a new key-value mapping to the map.
   * If the map previously contained a mapping for the key,
   * that old value is replaced by the new.
   * @return the old value if there was a previous mapping for the key,
   * and null otherwise.
   * @throws IllegalArgumentException if the key is invalid (e.g., the null value).
   */
  public V put(K key, V value);
  
  /**
   * Removes a new key-value mapping from the map.
   * @return the old value if there was a mapping for the key,
   * and null otherwise.
   * @throws IllegalArgumentException if the key is invalid (e.g., the null value).
   */
  public V remove(K key);
  
  /**
   * Returns the size of the map (the number of key-value mappings).
   * @return the size of the map.
   */
  public int size();
  
  /**
   * Returns true if the map is empty, and false otherwise.
   * @return true if the map is empty, and false otherwise.
   */
  public boolean isEmpty();
  
 
  /**
   * Return the entry with the greatest key less than or equal to the
   * argument key, or null if no such entry exists.
   * @return the entry with the greatest key less than or equal to the
   * argument key.
   * @throws IllegalArgumentException if the key is invalid (e.g., the null value).
   */
  public Entry<K,V> floorEntry(K key);
  
  /**
   * Return the entry with the smallest key greater or equal to the
   * argument key, or null if no such entry exists.
   * @return the entry with the smallest key greater or equal to the
   * argument key.
   * @throws IllegalArgumentException if the key is invalid (e.g., the null value).
   */
  public Entry<K,V> ceilingEntry(K key);

  /**
   * Returns an iterable object over the keys in the key-value
   * mappings, where the keys are sorted in ascending order.
   * @return an iterable over the keys in the map
   */
  public Iterable<K> keys();
}
