package Projects.Laboratorios.Laboratorio6;


import es.upm.aedlib.Entry;
import es.upm.aedlib.priorityqueue.*;
import es.upm.aedlib.map.*;
import es.upm.aedlib.positionlist.*;

/**
 * A registry which organizes information on airplane arrivals.
 */
public class IncomingFlightsRegistry {
	PriorityQueue<Long, String> registryQueue;
	HashTableMap<String, Entry<Long, String>> registryMap;
  /**
   * Constructs an class instance.
   */
  public IncomingFlightsRegistry() {
	  registryQueue = new HeapPriorityQueue<Long, String>();
	  registryMap = new HashTableMap<String, Entry<Long, String>>();
  }

  /**
   * A flight is predicted to arrive at an arrival time (in seconds).
   */
  public void arrivesAt(String flight, long time) {
	  Entry<Long, String> valueAnt = registryMap.get(flight);
	  if (valueAnt != null) 
		  registryQueue.remove(valueAnt);
	  Entry<Long, String> newValue = registryQueue.enqueue(time, flight);
	  registryMap.put(flight, newValue);
  }

  /**
   * A flight has been diverted, i.e., will not arrive at the airport.
   */
  public void flightDiverted(String flight) {
	  Entry<Long, String> valueAnt = registryMap.remove(flight);
	  if (valueAnt != null) 
		  registryQueue.remove(valueAnt);
  }

  /**
   * Returns the arrival time of the flight.
   * @return the arrival time for the flight, or null if the flight is not predicted
   * to arrive.
   */
  public Long arrivalTime(String flight) {
	  Entry<Long, String> valueAnt = registryMap.get(flight);
	  if (valueAnt == null)
		  return null;
	  Long res = valueAnt.getKey();
	  return res;
     
  }

  /**
   * Returns a list of "soon" arriving flights, i.e., if any 
   * is predicted to arrive at the airport within nowTime+180
   * then adds the predicted earliest arriving flight to the list to return, 
   * and removes it from the registry.
   * Moreover, also adds to the returned list, in order of arrival time, 
   * any other flights arriving withinfirstArrivalTime+120; these flights are 
   * also removed from the queue of incoming flights.
   * @return a list of soon arriving flights.
   */
  public PositionList<FlightArrival> arriving(long nowTime) {
	  PositionList<FlightArrival> res = new NodePositionList<FlightArrival>();
	  if (registryMap.isEmpty()) 
		  return res;
	  Entry<Long, String> vuelo1 = registryQueue.dequeue();
	  vuelo1 = registryMap.get(vuelo1.getValue());
	  registryMap.remove(vuelo1.getValue());
	  boolean Terminado = vuelo1.getKey() - nowTime > 180;
	  FlightArrival anadido = new FlightArrival(vuelo1.getValue(), vuelo1.getKey());
	  if (!Terminado) {
		  res.addLast(anadido);
		  if (registryMap.isEmpty()) 
			  return res;
	  }
	  else {
		  introducir(vuelo1);
		  return res;
	  }
	  Entry<Long, String> vuelo2 = registryQueue.dequeue();
	  registryMap.remove(vuelo2.getValue());
	  Terminado = vuelo2.getKey() - vuelo1.getKey() > 120;
	  if (Terminado) 
		  introducir(vuelo2);
	  while (!registryQueue.isEmpty() && !Terminado) {
		  anadido = new FlightArrival(vuelo2.getValue(), vuelo2.getKey());
		  res.addLast(anadido);
		  vuelo2 = registryQueue.dequeue();
		  registryMap.remove(vuelo2.getValue());
		  Terminado = vuelo2.getKey() - vuelo1.getKey() > 120;
		  if (Terminado)
			  introducir(vuelo2);
	  }
	  if (registryQueue.isEmpty() && !Terminado) {
		  anadido = new FlightArrival(vuelo2.getValue(), vuelo2.getKey());
		  res.addLast(anadido);
	  }
      return res;
  }
  
  private void introducir (Entry<Long, String> vuelo) {
	  registryQueue.enqueue(vuelo.getKey(), vuelo.getValue());
	  registryMap.put(vuelo.getValue(), vuelo);
  }
}