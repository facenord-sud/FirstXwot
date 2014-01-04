/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import diuf.unifr.ch.first.xwot.jaxb.Lock;

/**
 *
 * @author leo
 */
public class JaxbTestFactory {

    public Lock getlockOpen() {
        Lock lock = new Lock();
        lock.setState(Lock.State.OPEN);
        return lock;
    }
}
