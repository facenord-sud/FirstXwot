/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.components;

/**
 * 0 mean fully open 1023 mean fully closed
 *
 * @author leo
 */
public class LinearPotentiometer {

    private int oldPosition;
    private int position;

    public static final int OPEN_POSITION = 20;
    public static final int CLOSED_POSITION = 1000;
    public static final int MIDDLE_POSITION = 490;
    public static final int ERROR = 15;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(int oldPosition) {
        this.oldPosition = oldPosition;
    }

    /**
     * The difference between the old and the new position of the door. the
     * calcul is : oldPosition-newPosition
     *
     * @return smaller than 0 if door is closing
     * <br/> 0 if door is not closing or not opening
     * <br/> bigger than 0 if door is opening
     */
    public int getDifference() {
        return oldPosition - position;
    }

    /**
     * To know if the door is closing or not
     *
     * @return true if the door is closing. Else return false, as well when the
     * door is not moving
     */
    public boolean isClosing() {
        if (getDifference() < 0) {
            return true;
        }
        return false;
    }

    /**
     * The postition of the door in percent
     *
     * @return the position of the door in percent
     */
    public int getPercentPosition() {
        return Math.round(((float)position/(float)CLOSED_POSITION)*100);
    }

    public void setFromPercentPosition(int percent) {
        position = Math.round(((float)percent / (float)100) * 1023);
    }

    @Override
    public String toString() {
        return "LinearPotentiometer{" + "oldPosition=" + oldPosition + ", "
                + "position=" + position + ", percentPosition=" + 
                getPercentPosition() + ", isClosing=" + isClosing()+", difference="+getDifference();
    }

}
