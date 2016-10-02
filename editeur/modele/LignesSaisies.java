package editeur.modele;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <b>LignesSaisies est la classe qui recense les lignes saisies.</b>
 * <p>Elle possede plusieurs methodes afin de pouvoir modifier cette liste</p>
 * 
 * @author AntoineCourtil
 * @version 1.0
 */

public class LignesSaisies implements Iterable<String> {
	
	/**
     * L'application Editeur.
     */
	private Editeur appli;
	
	/**
     * La liste recensant les lignes
     */
	private ArrayList<String> als;
	
	
	
	//--------------------- CONSTRUCTORS ------------------------------------------
	
	
	/**
     * <b>Constructeur LignesSaisies</b>
     * <p>Permet d'initialiser la liste a vide</p>
    */	
	public LignesSaisies(Editeur e){
		this.appli = e;
		this.als = new ArrayList<String>();
	}
	

	//--------------------------------- ADDER --------------------------------
	
	/**
     * Ajoute une ligne a la liste des lignes saisies
     * 
     */
	public void add(String s){
		//if(!this.als.contains(s)) this.als.add(s);
		this.als.add(s);
	}
	
	
	
	//--------------------------------- SETTER -------------------------------
	
	/**
     * Initialise une ligne saisie en fonction de son indice
     * 
     * @param indice
     * 				L'indice de la ligne
     * @param s
     * 				Nouvelle valeur de la ligne
     * 
     */
	public void set(int indice, String s){
		this.als.add(indice, s);
	}
	
	//--------------------------------- GETTER -------------------------------
	
	
	/**
     * Recupere la ligne saisie en fonction de son index
     * 
     * @param index
     *            La valeur de l'index.
     */
	public String get(int index){
		return this.als.get(index);
	}
	
	/**
     * Recupere la taille de la liste des lignes saisies
     * 
     */
	public int getSize(){
		return this.als.size();
	}
	
	//--------------------------------- REMOVER ------------------------------
	
	/**
     * Supprime la ligne saisies a l'indice precise
     * 
     */
	public void remove(int index){
		this.als.remove(index);
	}
	
	/**
     * Supprime toutes les lignes saisies
     * 
     */
	public void clear(){
		this.als.removeAll(als);
	}

	//--------------------------------- OTHER --------------------------------
	
	/**
     * Permet de rendre iterable la classe LignesSaisies
     * 
     */
	public Iterator<String> iterator() {
		return this.als.iterator();
	}
}
