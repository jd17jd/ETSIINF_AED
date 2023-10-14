package Projects.Individuales.Individual1;

//Calcular el num max de ocurrencias
//consecutivas de un elemento en un array

public class TestJD {
	
	public static void main (String [] args) {
		Integer vectorNumeros[] = {1,2,3,4,5,6,7,8,9,5};
		Integer elem = 5;
		System.out.println("El num max de ocurrencias consecutivas es: " + Utils.maxNumRepeated(vectorNumeros, elem));
		
		Integer vectorNumeros2[] = {1,2,3,4,5,5,7,8,9,5};
		System.out.println("El num max de ocurrencias consecutivas es: " + Utils.maxNumRepeated(vectorNumeros2, elem));
	}
	
}
