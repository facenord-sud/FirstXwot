/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx;

/**
 *
 * @author leo
 */
public enum TinkerShield {
    o_0(11),
    o_1(10),
    o_2(9),
    o_3(6),
    o_4(5),
    o_5(3),
    i_0(14),
    i_1(15),
    i_2(16),
    i_3(17),
    i_4(18),
    i_5(19);
    
    private int value;
    
    private TinkerShield(int value) {
        this.value = value;
    }
    
    public int toInt() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
