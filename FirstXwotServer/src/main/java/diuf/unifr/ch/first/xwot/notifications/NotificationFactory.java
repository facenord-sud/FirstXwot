
package diuf.unifr.ch.first.xwot.notifications;

import diuf.unifr.ch.first.xwot.rxtx.notifications.Notification;

/**
 * Factory needed to manage all the type of notification in this project.<br/>
 * As discussed last week, everything is lazy loading. A bi too much maybe.
 * @author leo
 */
public class NotificationFactory {
    
    private Notification openNotification = null;
    private Notification lockNotification = null;
    private static NotificationFactory instance = null;
    
    private NotificationFactory() {
    }
    
    /**
     * Pattern singleton with lazy method.
     * @return NotificationFactory single instance
     */
    public static synchronized NotificationFactory getInstance() {
        if(instance == null) {
            instance = new NotificationFactory();
        }
        return instance;
    }

    /**
     * Handle notification for the lock context
     * @return Notification instance for lock context
     */
    public synchronized Notification getLockNotification() {
        if(lockNotification == null) {
            lockNotification = new Notification();
            lockNotification.setBuilder(new LockNotificationBuilder());
        }
        return lockNotification;
    }

    /**
     *Handle notification for the open context
     * @return Notification instance for open context
     */
    public synchronized Notification getOpenNotification() {
        if(openNotification == null) {
            openNotification = new Notification();
            openNotification.setBuilder(new OpenNotificationBuilder());
        }
        return openNotification;
    }
    
    
}
