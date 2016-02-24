package dvt.devint;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import static dvt.devint.ConstantesDevint.*;

/**
 * Permet de gerer l'action pour la touche "F1"
 * @author Justal Kevin
 */
public class F1Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient Fenetre fenetre;

    /**
     * Le constructeur appele lors de l'appuie sur la touche "F1"
     * @param fenetre La fenetre que l'on utilise
     * @author Justal Kevin
     */
    public F1Action(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fenetre.getSIVOX().playWav(F1_SON);
    }

}
