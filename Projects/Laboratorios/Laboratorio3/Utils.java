package Projects.Laboratorios.Laboratorio3;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.positionlist.*;


public class Utils {
	
  public static int multiply(int a, int b) {
	  int sum = 0;
	  if (a<0 || b<0) {
		  sum = -1 * sum;
	  }
	  
	  if (a<0 && b<0) {
		  a = a * (-1);
		  b = b * (-1);
	  }
	  
	//Caso base
	  if (a == 0)
		  return 0;
	  
	  else if (a%2 ==0)
		  sum += multiply(a/2, b*2);
	  else 
		  sum += multiply(a/2, b*2) + b;
	  return sum;
  }
  
  public static <E extends Comparable<E>> int findBottom(IndexedList<E> l, int start, int end) {
	  int longitud = start + end;
	  if (longitud == 0)
		  return 0;
	  if (end - start == 1) {
		  if (l.get(start).compareTo(l.get(end)) < 0)
			  return start;
		  return end;
		  }
	  boolean tamañoImpar = (longitud % 2) == 0;
	  int middle = longitud / 2;
	  if (!tamañoImpar) {
		  if (!(l.get(middle).compareTo(l.get(middle+1)) < 0))
				  middle++;
	  }
	  E element = l.get(middle);
	  E elementPrev = l.get(middle-1);
	  E elementPost = l.get(middle+1);
	  if (element.compareTo(elementPrev) <= 0 && element.compareTo(elementPost) <= 0)
		  return middle;
	  else {
		  if (elementPrev.compareTo(elementPost)<0)
			  return findBottom(l, start, middle-1);
		  else 
			  return findBottom(l, middle+1, end);
			}
  }
  
  public static <E extends Comparable<E>> int findBottom(IndexedList<E> l) {
	  if (l.isEmpty())
		  return -1;
      return findBottom(l, 0, l.size() - 1);
  }

  public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>> joinMultiSetsAux
  (NodePositionList<Pair<E,Integer>> l1, NodePositionList<Pair<E,Integer>> l2, 
		  Position<Pair<E, Integer>> cursor1,
		  Position<Pair<E, Integer>> cursor2, 
		  NodePositionList<Pair<E,Integer>> res) {
	  boolean finl1 = cursor1 != null;
	  boolean finl2 = cursor2 != null;
	  Pair<E,Integer> elemRes = res.last().element();
	  if (finl1 && finl2)
		  return res;
	  else if (!finl2 && (finl1 || cursor2.element().getLeft().compareTo(cursor1.element().getLeft()) < 0)) {
		  if (cursor2.element().getLeft().equals(elemRes.getLeft())) 
			  elemRes.setRight(elemRes.getRight() + cursor2.element().getRight());
		  else
			  res.addLast(cursor2.element());
		  cursor2 = l2.next(cursor2);
	  }
	  else {
		  if (cursor1.element().getLeft().equals(elemRes.getLeft())) 
			  elemRes.setRight(elemRes.getRight() + cursor1.element().getRight());
		  else
			  res.addLast(cursor1.element());
		  cursor1 = l1.next(cursor1);
	  }
	  res = joinMultiSetsAux(l1, l2, cursor1, cursor2, res);
	  return joinMultiSetsAux(l1, l2, cursor1, cursor2, res);
  }
  
  public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
    joinMultiSets(NodePositionList<Pair<E,Integer>> l1,
		  NodePositionList<Pair<E,Integer>> l2) {
    NodePositionList<Pair<E,Integer>> res = new NodePositionList<Pair<E,Integer>>();
    Position<Pair<E, Integer>> cursor1 = l1.first();
    Position<Pair<E, Integer>> cursor2 = l2.first();
    if (cursor1 == null && cursor2 == null)
    	return res;
    else if (cursor2 != null && (cursor1 == null || cursor2.element().getLeft().compareTo(cursor1.element().getLeft()) < 0)) {
   	    res.addLast(cursor2.element());
   	    cursor2 = l2.next(cursor2);
    }
    else {
    	 res.addLast(cursor1.element());
		 cursor1 = l1.next(cursor1);
    }
    return joinMultiSetsAux(l1, l2, cursor1, cursor2, res);
  }




}
