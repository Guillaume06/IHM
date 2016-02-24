package dvt.devint;

import static dvt.jeuchronometre.ConstantesJeu.OPTIMAL_TIME;

public abstract class Jeu extends Fenetre {
    private static final long serialVersionUID = 1L;

    /**
     * Permet de creer et initialiser la fenetre de jeu
     */
    public Jeu() {
        init();
        this.pack(); 
        this.requestFocusInWindow();
        this.setVisible(true);
    }
    
    /**
     * La loop du jeu qui permet de garder un FPS (frame per seconds) constant peu importe le PC
     */
    public void loop() {
        long lastLoopTime,timeLoop;

        // initialisation des valeurs
        reset();

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;

            // mise à jour des informations
            update();
            
            // ré-affichage à chaque tour
            // optimisation possible : afficher seulement quand certaines informations ont changé
            // nécessite de lisser l'affichage d'un temps t à t+delta
            render();

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }
    
    /**
     * méthode appelée à la création de l'objet
     * initialisation des composants graphiques et des variables de jeu
     * association des actions aux touches via les méthodes addControl
     */
    public abstract void init();
    
    
    /**
     * méthode appelée à chaque tour de boucle
     * mise à jour des variables de jeu
     */
    public abstract void update();
    
    /**
     * méthode appelée à chaque tour de boucle
     * mise à jour des composants graphiques 
     */
    public abstract void render();
    
    /**
     * méthode appelée avant la boucle
     * utilisée pour la réinitialisation
     */
    public abstract void reset();
}
