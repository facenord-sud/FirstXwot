
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
