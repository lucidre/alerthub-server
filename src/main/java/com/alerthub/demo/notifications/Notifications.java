package com.alerthub.demo.notifications;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notifications {
    @Id
    private String id;

    private String uid;

    private List<Notification> notifications;

    public Notifications() {
    }

    public Notifications(String id, String uid, List<Notification> notifications) {
        this.id = id;
        this.uid = uid;
        this.notifications = notifications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Notifications other = (Notifications) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (uid == null) {
            if (other.uid != null)
                return false;
        } else if (!uid.equals(other.uid))
            return false;
        if (notifications == null) {
            if (other.notifications != null)
                return false;
        } else if (!notifications.equals(other.notifications))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Notifications [id=" + id + ", uid=" + uid + ", notifications=" + notifications + "]";
    }

}