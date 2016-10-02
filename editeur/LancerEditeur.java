package editeur;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import editeur.graphique.VueMenu;
import editeur.graphique.VueSaisie;
import editeur.graphique.VueTexte;
import editeur.modele.Editeur;

/**
 * <b>LancerEditeur est la classe permettant de lancer l'application Editeur.</b>
 *  
 * @author AntoineCourtil
 * @version 1.0
 */

public class LancerEditeur extends JFrame{
	
	/**
     * L'application Editeur.
     */
	private Editeur appli;
	
	/**
     * La vue du Menu.
     */
	private VueMenu menu;
	
	/**
     * La vue du texte saisi.
     */
	private VueTexte texte;
	
	/**
     * La vue de la saisie du texte.
     */
	private VueSaisie saisie;
	
	
	//--------------------- CONSTRUCTORS ------------------------------------------
	
	
	/**
     * <b>Constructeur LancerEditeur</b>
     * <p>Permet d'instancier le modele et les vues et d'executer l'application</p>
    */
	public LancerEditeur(){
		super("Editeur de texte");
		
		this.appli = new Editeur();
		this.menu = new VueMenu(this.appli);
		this.texte = new VueTexte(this.appli);
		this.saisie = new VueSaisie(this.appli);

		this.add(this.texte,BorderLayout.CENTER);
		this.add(this.saisie,BorderLayout.SOUTH);
		this.setJMenuBar(menu);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		pack() ;
		setVisible(true) ;
	}
	
	public static void main(String[] args){
		LancerEditeur appli = new LancerEditeur();
	}
}
