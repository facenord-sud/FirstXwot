
package diuf.unifr.ch.first.xwot.rxtx.notifications;

/**
 *
 * @author leo
 */
public class NotificationFactory {
    
    private final static OpenNotification openNotification = OpenNotification.getInstance();
    private final static LockNotification lockNotification = LockNotification.getInstance();

    public static Notification getLockNotification() {
        return lockNotification;
    }

    public static Notification getOpenNotification() {
        return openNotification;
    }
    
    
}
