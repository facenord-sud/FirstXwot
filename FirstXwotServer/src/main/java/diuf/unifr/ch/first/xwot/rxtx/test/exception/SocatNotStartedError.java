/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.test.exception;

/**
 *
 * @author leo
 */
public class SocatNotStartedError extends Error{

    public SocatNotStartedError() {
    }

    public SocatNotStartedError(String message) {
        super(message);
    }
}
