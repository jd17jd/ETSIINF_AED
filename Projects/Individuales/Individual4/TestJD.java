package Projects.Individuales.Individual4;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class TestJD {
	
	public static void main (String [] args) {
		
		PositionList<Pair<Character,Integer>> list = new NodePositionList<Pair<Character, Integer>>();
		Pair<Character, Integer> par1 = new Pair<Character, Integer>('a', 4);
		Pair<Character, Integer> par2 = new Pair<Character, Integer>('b', 3);
		Pair<Character, Integer> par3 = new Pair<Character, Integer>('c', 2);
		Pair<Character, Integer> par4 = new Pair<Character, Integer>('d', 1);
		
		list.addLast(par1);
		list.addLast(par2);
		list.addLast(par3);
		list.addLast(par4);
		
		System.out.println(list);
		
		MultiSetListIterator<PositionList<Pair<Character,Integer>>> lista = new MultiSetListIterator(list);
		
		for (Pair<Character, Integer> e : list)
		System.out.println(e);
		
//		System.out.print(lista.lista(list)); No se como hacer esto
		
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());
		System.out.print(lista.next());


	}
}
