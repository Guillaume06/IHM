package dvt.devint;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action pour la touche "Echap"
 * @author Justal Kevin
 */
public class EchapAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Fenetre fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "Echap"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public EchapAction(Fenetre fenetre) {
        this.fenetre = fenetre;
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.dispose();
    }

}
