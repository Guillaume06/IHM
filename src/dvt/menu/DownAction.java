package dvt.menu;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Permet de gerer l'action lors de l'appuie sur l'appuie sur la touche haut
 * @author Justal Kevin
 */
public class DownAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Menu fenetre;
    
    /**
     * Le constructeur appele lors de l'appuie sur la touche "BAS"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public DownAction(Menu fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.down();
    }

}
