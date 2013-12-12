
package diuf.unifr.ch.first.xwot.rxtx.notifications;

/**
 *
 * @author leo
 */
public class NotificationFactory {
    
    private Notification openNotification = null;
    private Notification lockNotification = null;
    private static NotificationFactory instance = null;
    
    private NotificationFactory() {}
    
    public static synchronized NotificationFactory getInstance() {
        if(instance == null) {
            instance = new NotificationFactory();
        }
        return instance;
    }

    public synchronized Notification getLockNotification() {
        if(openNotification == null) {
            openNotification = new Notification();
            openNotification.setBuilder(new LockNotificationBuilder());
        }
        return openNotification;
    }

    public synchronized Notification getOpenNotification() {
        if(lockNotification == null) {
            lockNotification = new Notification();
            lockNotification.setBuilder(new OpenNotificationBuilder());
        }
        return lockNotification;
    }
    
    
}
