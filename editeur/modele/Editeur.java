package editeur.modele;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import editeur.graphique.Vue;


/**
 * <b>Editeur est la classe modèle qui implemente toutes les methodes.</b>
 * 
 * @author AntoineCourtil
 * @version 1.0
 */

public class Editeur implements ITexte {
	
	/**
     * L'ArrayList regroupant l'ensemble des vues.
     */
	private ArrayList<Vue> alv;
	
	/**
     * L'objet qui recense les lignes saisies
     */
	private LignesSaisies lignes;
	
	/**
     * Le chemin absolu du fichier actuellement ouvert
     */
	private String cheminCourant;
	
	/**
     * Le nom du fichier actuellement ouvert
     */
	private String fichierCourant;
	
	/**
     * La couleur actuelle du document
     */
	private Color couleur;
	
	
	
	
	//--------------------- CONSTRUCTORS ------------------------------------------
		
	
	/**
     * <b>Constructeur Editeur</b>
     * <p>Permet d'initialiser a vide la liste de vue, les lignes du fichier, le nom et le chemin du fichier courant ainsi que la couleur</p>
    */
	public Editeur(){
		this.alv = new ArrayList<Vue>();
		this.lignes = new LignesSaisies(this);
		this.cheminCourant="";
		this.fichierCourant="";
		this.couleur = Color.black;
	}
	
	
	//--------------------------------- VUES -------------------------------
	
		
	/**
     * Ajoute une vue a la liste des vues existantes
     * 
     * @param v
     *            La vue a ajouter.
    */    
    public void ajouterVue(Vue v){
		this.alv.add(v);
	}
	
    /**
     * Sert a mettre a jour l'ensemble des vues
     * 
    */
    public void mettreAJour(){
		for(Vue v:this.alv){
			v.mettreAJour();
		}
	}
	
	//--------------------------------- SETTER -------------------------------

	
    /**
     * Initialise une ligne spécifique parmis les lignes saisies
     * 
     * @param i
     *            L'indice de la ligne.
     * @param i
     *            La nouvelle valeur de la ligne.
    */
    public void setLine(int i, String ligne) {
		this.lignes.set(i, ligne);
	}
	
	/**
     * Initialise l'objet contenant toutes les lignes saisies
     * 
     * @param liste
     *            La nouvelle instance de l'objet.
    */
	public void setListeSaisies(LignesSaisies liste){
		this.lignes=liste;
	}
	
	/**
     * Initialise le chemin courant du fichier ouvert
     * 
     * @param ch
     *            Le chemin.
    */
	public void setCheminCourant(String ch){
		this.cheminCourant=ch;
	}
	
	/**
     * Initialise le nom du fichier ouvert
     * 
     * @param str
     *            Le nom.
    */
	public void setFichierCourant(String str){
		this.fichierCourant=str;
	}
	
	/**
     * Initialise la couleur du document
     * 
     * @param c
     *            La couleur.
    */
	public void setCouleur(Color c){
		this.couleur = c;
		this.mettreAJour();
	}

	
	//--------------------------------- GETTER -------------------------------


	/**
     * Recupere la taille de la liste des lignes saisies
     * 
     */
	public int getSize() {
		return this.lignes.getSize();
	}


	/**
     * Recupere la ligne a l'indice i parmis des lignes saisies
     * 
     * @param indice
     * 				L'indice de la ligne demandee
     */
	public String getLine(int indice) {
		return this.lignes.get(indice);
	}


	/**
     * Recupere la taille de la liste des lignes saisies
     * 
     */
	@Override
	public int getLineCount() {
		return this.lignes.getSize();
	}
	
	/**
     * Recupere le chemin courant du document ouvert
     * 
     */
	public String getCheminCourant(){
		return this.cheminCourant;
	}
	
	/**
     * Recupere le nom du document ouvert
     * 
     */
	public String getFichierCourant(){
		return this.fichierCourant;
	}
	
	/**
     * Recupere la couleur du document ouvert
     * 
     */
	public Color getCouleur(){
		return this.couleur;
	}

	
	//--------------------------------- ADDER -------------------------------

    
	/**
     * Ajoute une ligne aux lignes saisies
     * 
     * @param ligne
     *            La ligne a ajouer.
     */
	public void addLine(String ligne) {
		this.lignes.add(ligne);
		this.mettreAJour();
	}
	
	
	//--------------------------------- REMOVER -------------------------------
	
	
	/**
     * Efface le document ouvert
     * 
     */
	public void clear() {
		this.lignes.clear();
		this.mettreAJour();
	}
	
	
	//--------------------------------- FILE -------------------------------
	
	
	/**
     * Ouvre un fichier et affiche toutes les lignes saisies
     * 
     * @param fichier
     *            Le fichier a ouvrir (chemin+nomfichier).
     */
	public void ouvrir(String fichier){
    	FileReader flot ;
    	BufferedReader flotFiltre ;
    	LignesSaisies liste = new LignesSaisies(this);
    	try {
	    	flot = new FileReader(fichier) ;
	    	flotFiltre = new BufferedReader(flot) ;
	    	
	    	String ligne = flotFiltre.readLine() ;	    	
	    	
	    	while (ligne != null) {
	    		//System.out.println("a");
	    		liste.add(ligne);
		    	ligne = flotFiltre.readLine() ;
	    	}
	    	
	    	this.setListeSaisies(liste);
	    	this.mettreAJour();
    	}
    	catch (IOException e){}
    	
    	this.mettreAJour();
    }
	
	/**
     * Appelle la fonction ecrireFichier(fichier)
     * en concatenant le chemin et le nom du fichier 
     * et ajoute l'extension ".editeur"
     * 
     * @param chemin
     *            Le chemin du fichier a ecrire.
     * @param nomfichier
     *            Le nom du fichier a ecrire.
     */
	public void ecrireFichier(String chemin, String nomFichier){
    	String fichier=chemin+"/"+nomFichier+".editeur";
    	this.ecrireFichier(fichier);
    	this.setFichierCourant(fichier);
    }
	
	/**
     * Ecris un fichier avec les lignes saisies
     * 
     * @param fichier
     *            Le fichier a ecrire (chemin+nom).
     */ 
	public void ecrireFichier(String fichier){
		File f = new File (fichier);
    	
    	try
    	{
    	    FileWriter fw = new FileWriter (f);

    	    for(String ligne: this.lignes){
        		fw.write(ligne);
    	        fw.write ("\r\n");
        	}
    	 
    	    fw.close();
    	}
    	catch (IOException exception)
    	{
    	    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
    	}
	}
}
