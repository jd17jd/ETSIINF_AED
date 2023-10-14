/* Juan Diego Vivas Guevara, c200279 */

package Projects.Individuales.Individual5;

import java.util.Iterator;
import es.upm.aedlib.*;

import es.upm.aedlib.Pair;
import es.upm.aedlib.map.*;


public class TempUtils {
	
	public static Map<String,Integer> maxTemperatures(long startTime, long endTime, TempData[] tempData) {
		Map<String,Integer> map = new HashTableMap<String,Integer>();
		for (int i = 0; i<tempData.length; i++) {
			if (startTime <= tempData[i].getTime() && tempData[i].getTime() <= endTime) {
				int maxTemp = tempData[i].getTemperature();
				for (int j = 0; j<tempData.length; j++) {
					if (startTime <= tempData[j].getTime() && tempData[j].getTime() <= endTime
							&& tempData[i].getLocation().equals(tempData[j].getLocation())
							&& maxTemp < tempData[j].getTemperature()) {
						maxTemp = tempData[j].getTemperature();
					} //de if
					else {
						map.put(tempData[i].getLocation(), maxTemp);
					} //de else
				} //de for2
			} //de if
		} //de for1
		return map;
	}
	
	public static Pair<String,Integer> maxTemperatureInComunidad(long startTime, long endTime, String region, 
				TempData[] tempData, Map<String,String> comunidadMap) {
		int maxTemp = 0;
		String lugar = null;
		for (int i = 0; i < tempData.length; i++) {
			if (startTime <= tempData[i].getTime() && tempData[i].getTime() <= endTime
					&& maxTemp < tempData[i].getTemperature() 
					&& comunidadMap.get(tempData[i].getLocation()).equals(region)) {
				maxTemp = tempData[i].getTemperature();
				lugar = tempData[i].getLocation();
			}
		}
		Pair<String, Integer> pair = new Pair<String, Integer>(lugar, maxTemp);
		if (lugar == null) return null;
		else return pair;
	}
	
	public static Pair<String,Integer> maxTemperatureInComunidad2(long startTime, long endTime, String region, 
			TempData[] tempData, Map<String,String> comunidadMap) {
		Map<String, Integer> mapAux = maxTemperatures(startTime, endTime, tempData);
		String ciudad = null;
		Integer maxTemp = 0;
		Iterator<Entry<String, Integer>> itAux = mapAux.iterator();
		Iterator<Entry<String, String>> itComunidadMap = comunidadMap.iterator();
		while(itAux.hasNext()) {
			while (itComunidadMap.hasNext()) {
				if(itAux.next().getKey().equals(itComunidadMap.next().getKey()) && itComunidadMap.next().getValue().equals(region)) {
					ciudad = itAux.next().getKey();
					if (itAux.next().getValue() > maxTemp) {
						maxTemp = itAux.next().getValue();
					}// de if2
				}// de if
			}// de while2
		}// de while
		
		Pair<String, Integer> resultado = new Pair<String, Integer>(ciudad, maxTemp);
		return resultado;
	}

}
