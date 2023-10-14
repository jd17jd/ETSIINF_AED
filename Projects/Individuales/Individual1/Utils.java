/* JD17JD */
//10

//Calcular el num max de ocurrencias
//consecutivas de un elemento en un array

package Projects.Individuales.Individual1;

public class Utils {
	
	public static int maxNumRepeated(Integer[] a, Integer elem) {
		int resultado = 0;
		int resultadoAuxiliar = 0; 
		
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals(elem)) {
				resultadoAuxiliar = resultadoAuxiliar+1;
				if (i == a.length-1) {
					if (resultadoAuxiliar >= resultado) {
						resultado = resultadoAuxiliar;
						}
					}
				}//de if
		
			else {
				if (resultadoAuxiliar >= 0) {
					if (resultadoAuxiliar >= resultado) {
						resultado = resultadoAuxiliar;
						}
					resultadoAuxiliar = 0; 
					}      
				}//de else
		} //de for
		
		return resultado;
	}
	
}