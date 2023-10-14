package Projects.Individuales.Individual5;

import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.Entry;
import java.util.Iterator;


public class TestJD {
	
	//Esto no funciona. No se puede iterar sobre Maps.
	//Los maps no se itran porque no siguen ningun orden en concreto. No son listas.
	public static PositionList<Entry<String, Integer>> lista (Map<String, Integer> capitalesTemp) {
		PositionList<Entry<String, Integer>> resultado = new NodePositionList<Entry<String, Integer>>();

		Iterator<Entry<String, Integer>> it = capitalesTemp.iterator();
		while(it.hasNext()) {
			resultado.addLast(it.next());
			
		}
		return resultado;
	}
	
	public static void main (String [] args) {
		
		Map<String,String> comunidades = new HashTableMap<String,String>();
		comunidades.put("Sevilla", "Andalucia");
		comunidades.put("Zaragoza", "Aragon");
		comunidades.put("Oviedo", "Asturias");
		comunidades.put("Mallorca", "Baleares");
		comunidades.put("Tenerife", "Canarias");
		comunidades.put("Las Palmas", "Canarias");
		comunidades.put("Santander", "Cantabria");
		comunidades.put("Toledo", "Castilla-La Mancha");
		comunidades.put("Valladolid", "Castilla y León");
		comunidades.put("Barcelona", "Cataluña");
		comunidades.put("Ceuta", "Ceuta");
		comunidades.put("Merida", "Extremadura");
		comunidades.put("Santiago", "Galicia");
		comunidades.put("Logroño", "La Rioja");
		comunidades.put("Madrid", "Madrid");
		comunidades.put("Melilla", "Melilla");
		comunidades.put("Murcia", "Murcia");
		comunidades.put("Pamplona", "Navarra");
		comunidades.put("Valencia", "Comunidad Valenciana");
		comunidades.put("Vitoria", "Pais Vasco");
		
		
		Map<String,Integer> capitales = new HashTableMap<String,Integer>();
		capitales.put("Sevilla", 36);
		capitales.put("Zaragoza", 33);
		capitales.put("Oviedo", 24);
		capitales.put("Mallorca", 39);
		capitales.put("Tenerife", 35);
		capitales.put("Las Palmas", 34);
		capitales.put("Santander", 34);
		capitales.put("Toledo", 39);
		capitales.put("Valladolid", 38);
		capitales.put("Barcelona", 38);
		capitales.put("Ceuta", 41);
		capitales.put("Merida", 35);
		capitales.put("Santiago", 32);
		capitales.put("Logroño", 35);
		capitales.put("Madrid", 40);
		capitales.put("Melilla", 40);
		capitales.put("Murcia", 39);
		capitales.put("Pamplona", 36);
		capitales.put("Valencia", 37);
		capitales.put("Vitoria", 34);
		
		
		TempData sevilla = new TempData("Sevilla", 12, 36);
		TempData zaragoza = new TempData("Zaragoza", 24, 33);
		TempData oviedo = new TempData("Oviedo", 36, 24);
		TempData mallorca = new TempData("Mallorca", 48, 39);
		TempData tenerife = new TempData("Tenerife", 60, 35);
		TempData las_palmas = new TempData("Las Palmas", 72, 34);
		TempData santander = new TempData("Santander", 84, 34);
		TempData toledo = new TempData("Toledo", 96, 39);
		TempData valladolid = new TempData("Valladolid", 108, 38);
		TempData barcelona = new TempData("Barcelona", 120, 38);
		TempData ceuta = new TempData("Ceuta", 132, 41);
		TempData merida = new TempData("Merida", 144, 35);
		TempData santiago = new TempData("Santiago", 156, 32);
		TempData logroño = new TempData("Logroño", 168, 35);
		TempData madrid = new TempData("Madrid", 180, 40);
		TempData melilla = new TempData("Melilla", 192, 40);
		TempData murcia = new TempData("Murcia", 204, 39);
		TempData pamplona = new TempData("Pamplona", 216, 36);
		TempData valencia = new TempData("Valencia", 228, 37);
		TempData vitoria = new TempData("Vitoria", 240, 34);
		
		TempData[] datos = {sevilla, zaragoza, oviedo, mallorca,
				tenerife, las_palmas, santander, toledo, valladolid,
				barcelona, ceuta, merida, santiago, logroño, madrid,
				melilla, murcia, pamplona, valencia, vitoria};
		
		
		System.out.println(comunidades);

		System.out.println(capitales);
		
		System.out.println(TempUtils_enunciado.maxTemperatures(0, 150, datos));
		
		System.out.println(TempUtils_enunciado.maxTemperatureInComunidad(0, 150, "Canarias", datos, comunidades));
		
		System.out.println(TempUtils_enunciado.maxTemperatureInComunidad2(0, 150, "Canarias", datos, comunidades));
		
		//System.out.println(lista(capitales));
	}

}
