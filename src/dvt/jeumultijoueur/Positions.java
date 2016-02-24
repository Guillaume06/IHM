package dvt.jeumultijoueur;

/**
 * Permet de gerer les Position des joueurs
 * @author Justal Kevin
 */
public class Positions {
    private int positionX,positionY;
    private int limitLeftX,limitRightX,limitTopY,limitBotY;
    private int speed=5;
    
    /**
     * Permet de creer un objet position pour un joueur ou monstre
     * @param positionX La position X initiale
     * @param positionY La position Y initiale
     * @param limitLeftX La limite a gauche de la position X
     * @param limitRightX La limite a droite de la position Y 
     * @param limitTopY La limite en haut de la position Y
     * @param limitBotY La limite en bas de la position Y
     */
    public Positions(int positionX,int positionY,int limitLeftX,int limitRightX,int limitTopY,int limitBotY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.limitLeftX = limitLeftX;
        this.limitRightX = limitRightX;
        this.limitTopY = limitTopY;
        this.limitBotY = limitBotY; 
    }
    
    /**
     * Permet de deplacer a droite en tenant compte de la limite du personnage
     */
    public void moveRight() {
        if(this.positionX+speed<=limitRightX-100) {
            this.positionX = this.positionX+speed;
        }
    } 
    
    /**
     * Permet de deplacer a gauche en tenant compte de la limite du personnage
     */
    public void moveLeft() {
        if(this.positionX-speed>=limitLeftX) {
            this.positionX = this.positionX-speed;
        }
    }    

    /**
     * Permet de deplacer en bas en tenant compte de la limite du personnage
     */    
    public void moveBot() {
        if(this.positionY+speed<=limitBotY-100) {
            this.positionY = this.positionY+speed;
        }
    }     

    /**
     * Permet de deplacer en haut en tenant compte de la limite du personnage
     */    
    public void moveTop() {
        if(this.positionY-speed>=limitTopY) {
            this.positionY = this.positionY-speed;
        }
    }    
    
    /**
     * Permet de set la position X du personnage
     * @param positionX La nouvelle position X du joueur
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    
    /**
     * Permet de set la position Y du personnage
     * @param positionX La nouvelle position Y du joueur
     */    
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    
    /**
     * Retourne la position X du personnage
     * @return La position X du personnage
     */
    public int getPositionX() {
        return positionX;
    }
    
    /**
     * Retourne la position Y du personnage
     * @return La position Y du personnage
     */    
    public int getPositionY() {
        return positionY;
    }
}
