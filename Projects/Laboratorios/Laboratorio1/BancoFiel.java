package Projects.Laboratorios.Laboratorio1;

import java.util.Comparator;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.indexedlist.ArrayIndexedList;


/**
 * Implements the code for the bank application.
 * Implements the client and the "gestor" interfaces.
 */


public class BancoFiel implements ClienteBanco, GestorBanco {

	
  public IndexedList<Cuenta> cuentas;
  
  
  public BancoFiel() {
    this.cuentas = new ArrayIndexedList<Cuenta>();
  }

  // ----------------------------------------------------------------------
  
  public String crearCuenta(String dni, int saldoIncial) {
	  Cuenta cuenta = new Cuenta(dni,saldoIncial);
		cuentas.add(cuentas.size(), cuenta);
		return cuenta.getId();
  }
  
  
  public int buscarCuenta (String id) {
		int resultado = -1;
		int comp = 1;
		for(int i = 0; comp != 0 && i < cuentas.size(); i++) {
			comp = id.compareTo(cuentas.get(i).getId());
			if (comp == 0)
				resultado = i;	
			}
		return resultado;
	}
  
  public void borrarCuenta(String id)
		  throws CuentaNoExisteExc, CuentaNoVaciaExc{
	  int pos = buscarCuenta(id);
	  if (pos == -1)
		  throw new CuentaNoExisteExc();
	  if (cuentas.get(pos).getSaldo() > 0)
		  throw new CuentaNoVaciaExc();
	  cuentas.removeElementAt(pos);	
	  }

	
	
	public int ingresarDinero(String id, int cantidad) throws CuentaNoExisteExc {
			int pos = buscarCuenta(id);
			if (pos == -1)
					throw new CuentaNoExisteExc(); //Hay un problema con las cuentas sin cash.
			int saldoTotal = cuentas.get(pos).ingresar(cantidad);
			return saldoTotal;
	}
	
	
	public int retirarDinero(String id, int cantidad)  throws CuentaNoExisteExc, InsuficienteSaldoExc {
		int pos = buscarCuenta(id);
		if (pos == -1)
				throw new CuentaNoExisteExc();
		if (cantidad > cuentas.get(pos).getSaldo())
			throw new InsuficienteSaldoExc();
		int saldoTotal = cuentas.get(pos).retirar(cantidad);
		return saldoTotal;
	}
	

	public int consultarSaldo(String id) throws CuentaNoExisteExc {
		int pos = buscarCuenta(id);
		if (pos == -1)
			throw new CuentaNoExisteExc(); //Si el saldo es 0 me devuelve q no existe. Por?
		int saldo = cuentas.get(pos).getSaldo();
		return saldo;
	}


	public void hacerTransferencia(String idFrom, String idTo, int cantidad) throws CuentaNoExisteExc, InsuficienteSaldoExc {
		int pos = buscarCuenta(idFrom);
		int pos2 = buscarCuenta(idTo);
		if (pos == -1 || pos2 == -1)
			throw new CuentaNoExisteExc();
		if (cantidad > cuentas.get(pos).getSaldo())
			throw new InsuficienteSaldoExc();
		if (idFrom.equals(idTo))
			ingresarDinero(idTo,0);
		ingresarDinero(idTo, cantidad);
		retirarDinero(idFrom, cantidad);
	} //O falla en la 09 o en la 29. No entiendo el orden

	
	public IndexedList<String> getIdCuentas(String dni) {
		IndexedList<String> resultado = new ArrayIndexedList<>();
		for (int i=0; i<cuentas.size(); i++) {
			if (cuentas.get(i).getDNI().equals(dni))
			resultado.add(resultado.size(), cuentas.get(i).getId());
		}
		return resultado;
	}

	
	public int getSaldoCuentas(String dni) {
		int resultado = 0;
		for (int i = 0; i<cuentas.size(); i++) {
			if (cuentas.get(i).getDNI().equals(dni))
				resultado = resultado + cuentas.get(i).getSaldo();
		}
		return resultado;
	}
	
	
	public IndexedList<Cuenta> getCuentasOrdenadas (Comparator<Cuenta> cmp) {
		IndexedList<Cuenta> resultado = new ArrayIndexedList<>();
		  for (int i =0; i<cuentas.size(); i++) {
				  resultado.add(resultado.size(), cuentas.get(i));
				  }
		  for (int i = 0; i<resultado.size(); i++) {
			  for (int j = 1 + i; j <resultado.size(); j++) {
				  int comparando = cmp.compare(resultado.get(i), resultado.get(j));
				  if (comparando > 0) {
					  Cuenta cuenta1 = resultado.get(i);
					  resultado.set(i, resultado.get(j));
					  resultado.set(j, cuenta1);
			  }
		  }
	  }
		  return resultado;
	}
	  
	  
  // ----------------------------------------------------------------------
  
  
  public String toString() {
    return "banco";
  }
  
}



