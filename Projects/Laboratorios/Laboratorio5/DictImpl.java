package Projects.Laboratorios.Laboratorio5;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


public class DictImpl implements Dictionary {
	// A boolean because we need to know if a word ends in a node or not
	GeneralTree<Pair<Character,Boolean>> tree;

	public DictImpl() {
		tree = new LinkedGeneralTree<>();
		tree.addRoot(new Pair<Character,Boolean>(null,false));
	}
  
	/*
	 * Adds a new word. 
	 * Raises exception IllegalArgumentException if word is null or the empty string.
	 */
	public void add(String word) throws IllegalArgumentException {
		if (word == null || word.length()==0) throw new IllegalArgumentException();
		Position<Pair<Character, Boolean>> parent = tree.root();
		for (int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			Position<Pair<Character, Boolean>> cursor = searchChildLabelledBy(c, parent);
			boolean end = i == word.length() - 1;
			Pair<Character, Boolean> anadido = new Pair<Character, Boolean>(c, end);
			if (cursor == null)
				parent = tree.addChildLast(parent, anadido);
			else if (cursor.element().getLeft().equals(c)) { 
				if (i == word.length() - 1) {
                  cursor.element().setRight(true);
				}
				parent = cursor;
			}
			else {
				parent = tree.insertSiblingBefore(cursor, anadido);
			}
		}
	}
  
	/**
	 * Returns true if a word is included in the dictionary, and false otherwise.
	 * Raises exception IllegalArgumentException if word is null or the empty string.
	 */
	public boolean isIncluded(String word) throws IllegalArgumentException {
		boolean resultado = false;
		if (word == null || word.length()==0) throw new IllegalArgumentException();
		if ((int)word.charAt(0) <= 90) return false;
		else {
			Position<Pair<Character, Boolean>> nodo = findPos(word);
			if (nodo == null) return false;
			else {
				resultado = nodo.element().getRight();
			}
		}
		return resultado;
	}
  
	/**
	 * Deletes a word. 
	 * Raises exception IllegalArgumentException if word is null or the empty string.
	 */
	public void delete(String word) throws IllegalArgumentException {
		if (word == null || word.length()==0) throw new IllegalArgumentException();
		else {
			if (isIncluded(word)) {
				Position<Pair<Character, Boolean>> nodo = findPos(word);
				nodo.element().setRight(false);
			}
		}
	}
  
	/**
	 * Returns, in alphabetic order, a list with all words which begin with the prefix argument.
	 * Raises exception IllegalArgumentException if prefix is null. Note that the empty string
	 * is an acceptable argument.
	 */
	public PositionList<String> wordsBeginningWithPrefix (String prefix) throws IllegalArgumentException {
		if (prefix.equals(null)) throw new IllegalArgumentException();
		PositionList<String> resultado = new NodePositionList<>();
		resultado = wordsBeginningWithPrefix(prefix, findPos(prefix), resultado);
		return resultado;
		}
  
	public PositionList<String> wordsBeginningWithPrefix 
	(String prefix, Position<Pair<Character, Boolean>> parent, PositionList<String> resultado) {
		String palabra = prefix;
		if (isIncluded2(palabra)) resultado.addLast(palabra);
		for (Position<Pair<Character, Boolean>> child : tree.children(parent)) {
			String palabraActualizada = palabra + child.element().getLeft();
			wordsBeginningWithPrefix(palabraActualizada, child, resultado);
			}
		return resultado;
	}

	/*-----------------------------------------------------*/
  
	// Devuelve el nodo cuyo camino desde la raiz contiene
	// la palabra prefix. Si no existe el camino devuelve null.
	private <E> Position<Pair<Character, Boolean>> findPos(String prefix) {
		Position<Pair<Character, Boolean>> nodo = tree.root();
		for (int i =0; i<prefix.length(); i++) {
			Character ch = prefix.charAt(i);
			nodo = searchChildLabelledBy(ch,nodo);
		}
		return nodo;
	}
	
	// Devuelve el hijo del nodo padre que contiene el caracter ch.
	private <E> Position<Pair<Character, Boolean>> searchChildLabelledBy
	(Character ch, Position<Pair<Character, Boolean>> padre) {
		Iterator<Position<Pair<Character, Boolean>>> children = tree.children(padre).iterator();
		boolean foundChild = false;
		Position<Pair<Character, Boolean>> child = null;
		while (children.hasNext() && !foundChild) {
			child = children.next();
			Character c = child.element().getLeft();
			if (c.compareTo(ch) >= 0) foundChild = true;
			else {
				child = null;
			}
		}
		return child;
	}
	
	//Para poder utilizar este metodo en wordsBeginningWithPrefix, pues ahi si que acepta palabras vacias
	private boolean isIncluded2(String word) {
		boolean resultado = false;
		if (word == null) throw new IllegalArgumentException();
		else {
			Position<Pair<Character, Boolean>> nodo = findPos(word);
			if (nodo == null) return false;
			else {
				resultado = nodo.element().getRight() == true ? true : false;
			}
		}
		return resultado;
	}
}
