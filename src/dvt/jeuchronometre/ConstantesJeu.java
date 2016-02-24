package dvt.jeuchronometre;

/**
 * Permet de rassembler l'ensemble des constanstes du jeu dans une classe
 * @author Justal Kevin
 */
public final class ConstantesJeu {
    // POUR LES SI3 : Le System.getProperty("line.separator") ou \n ne
    // fonctionne pas dans un JLabel
    // Mais il faut retenir que le JLabel peut lire du HTML :) Trick powaa !
    public static final String CONSIGNE = "<html>"
            + "<center>APPUYER LE PLUS DE FOIS SUR ESPACE<br />"
            + "Vous avez 10 secondes pour faire le meilleur score<br /><br />"
            + "Le jeu commencera dès l'appuie sur espace</center></html>";
    public static final String CONSIGNE_WITHOUT_HTML = "APPUYER LE PLUS DE FOIS SUR ESPACE."
            + " Vous avez 10 secondes pour faire le meilleur score. "
            + "Le jeu commencera dès l'appuie sur espace";
    public static final int TARGET_FPS = 60;
    public static final long OPTIMAL_TIME = 1000000000L / TARGET_FPS;
    
    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private ConstantesJeu() {
        throw new AssertionError();
    }
}
