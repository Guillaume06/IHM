package dvt.devint;

/**
 * L'ensemble des constantes pour le projet entier
 * @author Justal Kevin - SI5
 */
public final class ConstantesDevint {
    public static final String F1_SON = "../ressources/sons/aideF1.wav";
    public static final String F2_SON = "../ressources/sons/aideF2.wav";
    public static final String ACCUEIL_SON = "../ressources/sons/accueil.wav";

    public static final String FONT_TYPE_DEFAULT = "Arial";
    public static final int TAILLE_DEFAULT = 50;

    public static final int NBR_SYNTHESE_NIVEAU = 3;
    public static final int SYNTHESE_MAXIMALE = 2;
    public static final int SYNTHESE_MOYENNE = 1;
    public static final int SYNTHESE_MINIMALE = 0;

    /**
     * Constructeur prive qui ne dois jamais etre utilise.
     */
    private ConstantesDevint() {
        throw new AssertionError();
    }
}
