package dvt.jeututo;


/**
 * Created by Guillaume on 10/02/2016.
 */
public class Position {
    private int positionX,positionY;
    private int leftMax, rightMax, topMax, botMax;
    private int speed=5;
    
    /**
     * Create a player or monster object
     * @param X     X position
     * @param Y     Y position
     * @param leftBorder    Left border for X
     * @param rightBorder   Right border for X
     * @param topBorder     Top border for Y
     * @param bottomBorder     Bottom border for Y
     */
    public Position(int X, int Y, int leftBorder, int rightBorder, int topBorder, int bottomBorder) {
        this.positionX = X;
        this.positionY = Y;
        this.leftMax = leftBorder;
        this.rightMax = rightBorder;
        this.topMax = topBorder;
        this.botMax = bottomBorder;
    }
    
    /**
     * Handle the right movement
     */
    public void moveRight() {
        if(this.positionX+speed<= rightMax -100) {this.positionX = this.positionX+speed;}
    } 
    
    /**
     * Handle the left movement
     */
    public void moveLeft() {
        if(this.positionX-speed>= leftMax) {this.positionX = this.positionX-speed;}
    }    

    /**
     * Handle the character movement
     */    
    public void moveBot() {
        if(this.positionY+speed<= botMax -100) {this.positionY = this.positionY+speed;}
    }     

    /**
     * Handle the top movement
     */    
    public void moveTop() {
        if(this.positionY-speed>= topMax) {this.positionY = this.positionY-speed;}
    }    
    
    /**
     * Set the X position of the character
     * @param X The new X
     */
    public void setPositionX(int X) {
        this.positionX = X;
    }
    
    /**
     * Set the Y position of the character
     * @param Y The new Y
     */    
    public void setPositionY(int Y) {
        this.positionY = Y;
    }
    
    // Getters
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
}
