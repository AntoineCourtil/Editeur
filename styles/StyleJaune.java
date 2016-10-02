package styles;

import java.awt.Color;

/**
 * <b>StyleJaune est la classe qui instancie la couleur jaune.</b>
 * 
 * @author AntoineCourtil
 * @version 1.0
 */

public class StyleJaune {
	private String nom;
	private Color couleur;
	

	//--------------------- CONSTRUCTORS ------------------------------------------	
	
	/**
     * <b>Constructeur StyleJaune</b>
     * <p>Instancie la classe par son nom et sa couleur</p>
    */
	public StyleJaune(){
		nom = "Jaune";
		couleur = Color.yellow;
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
     */
	public Color getCouleur() {
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
}
