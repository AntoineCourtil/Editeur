package editeur.modele;

/**
 * <b>ITexte est une interface pour définir les profils de fonctions.</b>
 *  
 * @author AntoineCourtil
 * @version 1.0
 */

public interface ITexte {

    public void addLine(String ligne);

    public void clear();

    public int getSize();

    public String getLine(int index);

    public int getLineCount();

    public void setLine(int i, String ligne);
}