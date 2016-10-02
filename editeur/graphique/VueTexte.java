package editeur.graphique;

import java.awt.*;
import javax.swing.*;

import editeur.modele.Editeur;

/**
 * <b>VueTexte est la vue qui affiche les lignes saisies.</b>
 * 
 * @author AntoineCourtil
 * @version 1.0
 */

public class VueTexte extends JPanel implements Vue{
	
	/**
     * L'application Editeur.
     */
	protected Editeur appli;


	//--------------------- CONSTRUCTORS ------------------------------------------
	
	
	/**
     * <b>Constructeur VueTexte</b>
     * <p>Initialise le Panel qui affichera les lignes saisies</p>
    */
	public VueTexte(Editeur e){
		super();
		e.ajouterVue(this);
		
		this.appli = e;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(300, 300));
		this.add(Box.createVerticalGlue());		
		this.setBackground(Color.white);
	}
	
	
	//--------------------- OTHERS ------------------------------------------------
	
	/**
     * Permet de mettre à jour les lignes saisies.
     * 
     */
	public void mettreAJour() {
		this.removeAll();
		for(int i=0; i<this.appli.getLineCount(); i++){
			JLabel txt = new JLabel(this.appli.getLine(i));
			txt.setForeground (this.appli.getCouleur());
			txt.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(txt);
		}
		revalidate();
		repaint();
	}
}
