/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.components;

/**
 *
 * @author leo
 */
public class ContiniousServo{
    
    private int speed;
    public final static int NULL_SPEED = 93;
    public final static int OPEN_MAX_SPEED = 180;
    public final static int CLOSE_MAX_SPEED = 0;
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
