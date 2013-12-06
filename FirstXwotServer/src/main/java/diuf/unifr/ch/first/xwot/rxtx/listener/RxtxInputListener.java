/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.listener;

import java.util.EventListener;

/**
 *
 * @author leo
 */
public interface RxtxInputListener extends EventListener{
    public void jsonChanged(String oldJson, String newJsons);
}
