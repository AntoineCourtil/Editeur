package editeur.graphique;

import java.awt.Color;

/**
 * <b>Couleur est la classe decrivant une couleur avec un nom et sa couleur.</b>
 *  
 * @author AntoineCourtil
 * @version 1.0
 */

public class Couleur {
	/**
     * Le nom de la couleur.
     */
	private String nom;
	
	/**
     * La couleur.
     */
	private Color couleur;
	
	
	
	//--------------------- CONSTRUCTORS ------------------------------------------
	
	/**
     * <b>Constructeur Couleur</b>
     * <p>Initialise une couleur par son nom et sa couleur en elle-même (Object awt.Color)</p>
    */
	public  Couleur(String s, Color c){
		this.nom=s;
		this.couleur=c;		
	}
	
	//--------------------- GETTER ------------------------------------------------
	
	/**
     * Renvoie le nom de la couleur
     * 
     */
	public String getNom() {
		return nom;
	}
	
	/**
     * Renvoie la couleur
     * 
     */public Color getCouleur() {
		return couleur;
	}
	
	//--------------------- SETTER ------------------------------------------------
	
     /**
      * Modifie le nom de la couleur par le nom en paramètre
      * 
      * @param nom
      *            Le nouveau nom dela couleur.
      */
 	public void setNom(String nom) {
 		this.nom = nom;
 	}
 	
 	/**
     * Modifie la couleur de l'objet couleur par celle en paramètre
     * 
     * @param couleur
     *            La nouvelle couleur.
     */
    public void setCouleur(Color couleur) {
		this.couleur = couleur;
	 }
	
	//--------------------- OTHERS ------------------------------------------------
	
	/**
     * Permet d'avoir la couleur sous format texte
     * 
     */
	public String toString() {
		return "Couleur [nom=" + nom + ", couleur=" + couleur + "]";
	}
	
	
}
