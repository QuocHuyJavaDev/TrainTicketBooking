package sample;

import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {
    public static void NotifSuccess(String title,String msg){

        NotificationType type = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(msg);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(2));
    }
    public static void NotifError(String title,String msg){
        NotificationType type = NotificationType.ERROR;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(msg);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(2));
    }
}
