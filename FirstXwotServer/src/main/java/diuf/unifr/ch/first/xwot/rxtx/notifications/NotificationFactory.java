/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.notifications;

/**
 *
 * @author leo
 */
public class NotificationFactory {
    private final static Notification lockNotification = Notification.getInstace();
    private final static Notification openNotification = Notification.getInstace();

    public static Notification getLockNotification() {
        return lockNotification;
    }

    public static Notification getOpenNotification() {
        return openNotification;
    }
    
    
}
