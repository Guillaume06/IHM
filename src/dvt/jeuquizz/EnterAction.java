package dvt.jeuquizz;


import java.awt.event.ActionEvent;


import javax.swing.AbstractAction;
/**
 * Permet de gerer l'action lors de l'appuie sur certaines touches
 * @author Justal Kevin
 */
public class EnterAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient JeuQuizz jeu;

    /**
     * L'objet qui sera cree lors de l'appuie sur une touche
     * @param jeu2 La fenetre ou se trouve le lien entre la touche et l'action
     * @param position La position dans l'array que l'on fera le changement
     * @param value La valeur que l'on souhaite modifie
     */    
    public EnterAction(JeuQuizz jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
           this.jeu.valid();
    }

}