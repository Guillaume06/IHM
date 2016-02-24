package dvt.jeuquizz;

import java.awt.event.ActionEvent;


import javax.swing.AbstractAction;
/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Justal Kevin
 */
public class Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuQuizz jeu;
    private boolean value;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fenetre ou se trouve le lien entre la touche et l'action
     * @param position La position dans l'array que l'on fera le changement
     * @param value La valeur que l'on souhaite modifie
     */    
    public Action(JeuQuizz jeu, boolean value) {
        this.jeu = jeu;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(value) {
            this.jeu.right();
        } else {
            this.jeu.left();
        }
    }

}