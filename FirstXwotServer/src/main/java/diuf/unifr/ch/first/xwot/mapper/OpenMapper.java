/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.mapper;

import diuf.unifr.ch.first.xwot.jaxb.Open;
import diuf.unifr.ch.first.xwot.components.LinearPotentiometer;

/**
 *
 * @author leo
 */
public class OpenMapper {
    private final Open open = new Open();
    private final LinearPotentiometer lp;
    
    public OpenMapper(LinearPotentiometer lp) {
        this.lp = lp;
    }
    
    public Open map() {
        open.setPosition(lp.getPercentPosition());
        if (lp.getPosition() >= LinearPotentiometer.CLOSED_POSITION - LinearPotentiometer.ERROR) {
            open.setState(Open.State.CLOSED);
        } else if (lp.getPosition() <= LinearPotentiometer.OPEN_POSITION + LinearPotentiometer.ERROR) {
            open.setState(Open.State.OPEN);
        } else if (!lp.isClosing() && lp.getDifference() != 0) {
            open.setState(Open.State.OPENING);
        } else if (lp.isClosing()) {
            open.setState(Open.State.CLOSING);
        } else {
            open.setState(Open.State.OPEN);
        }
        return open;
    }
}
