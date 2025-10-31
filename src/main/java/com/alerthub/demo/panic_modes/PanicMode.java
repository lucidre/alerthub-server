package com.alerthub.demo.panic_modes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.alerthub.demo.users.User;

@Document(collection = "panic_modes")
public class PanicMode {

    @Id
    private String id;
    private String uid;
    private Boolean isOnOrOff;
    private Boolean broadcastToCommunity;
    private Boolean broadcastToProviders;
    private Boolean broadcastToContacts;
    private Double latitude;
    private Double longitude;
    private Long updatedAt;
    private User user;

    public PanicMode() {
    }

    public PanicMode(
            String uid,
            Boolean isOnOrOff,
            Boolean broadcastToCommunity,
            Boolean broadcastToProviders,
            Boolean broadcastToContacts,
            Double latitude,
            Double longitude,
            Long updatedAt
    ) {

        this.uid = uid;
        this.isOnOrOff = isOnOrOff;
        this.broadcastToCommunity = broadcastToCommunity;
        this.broadcastToProviders = broadcastToProviders;
        this.broadcastToContacts = broadcastToContacts;
        this.latitude = latitude;
        this.longitude = longitude;
        this.updatedAt = updatedAt;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getBroadcastToContacts() {
        return broadcastToContacts;
    }

    public void setBroadcastToContacts(Boolean broadcastToContacts) {
        this.broadcastToContacts = broadcastToContacts;
    }

    public Boolean getBroadcastToProviders() {
        return broadcastToProviders;
    }

    public void setBroadcastToProviders(Boolean broadcastToProviders) {
        this.broadcastToProviders = broadcastToProviders;
    }

    public Boolean getBroadcastToCommunity() {
        return broadcastToCommunity;
    }

    public void setBroadcastToCommunity(Boolean broadcastToCommunity) {
        this.broadcastToCommunity = broadcastToCommunity;
    }

    public Boolean getIsOnOrOff() {
        return isOnOrOff;
    }

    public void setIsOnOrOff(Boolean isOnOrOff) {
        this.isOnOrOff = isOnOrOff;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        result = prime * result + ((isOnOrOff == null) ? 0 : isOnOrOff.hashCode());
        result = prime * result + ((broadcastToCommunity == null) ? 0 : broadcastToCommunity.hashCode());
        result = prime * result + ((broadcastToProviders == null) ? 0 : broadcastToProviders.hashCode());
        result = prime * result + ((broadcastToContacts == null) ? 0 : broadcastToContacts.hashCode());
        result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PanicMode other = (PanicMode) obj;

        if (updatedAt == null) {
            if (other.updatedAt != null) {
                return false;
            }
        } else if (!updatedAt.equals(other.updatedAt)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }

        if (longitude == null) {
            if (other.longitude != null) {
                return false;
            }
        } else if (!longitude.equals(other.longitude)) {
            return false;
        }

        if (broadcastToContacts == null) {
            if (other.broadcastToContacts != null) {
                return false;
            }
        } else if (!broadcastToContacts.equals(other.broadcastToContacts)) {
            return false;
        }

        if (latitude == null) {
            if (other.latitude != null) {
                return false;
            }
        } else if (!latitude.equals(other.latitude)) {
            return false;
        }

        if (broadcastToProviders == null) {
            if (other.broadcastToProviders != null) {
                return false;
            }
        } else if (!broadcastToProviders.equals(other.broadcastToProviders)) {
            return false;
        }

        if (broadcastToCommunity == null) {
            if (other.broadcastToCommunity != null) {
                return false;
            }
        } else if (!broadcastToCommunity.equals(other.broadcastToCommunity)) {
            return false;
        }

        if (isOnOrOff == null) {
            if (other.isOnOrOff != null) {
                return false;
            }
        } else if (!isOnOrOff.equals(other.isOnOrOff)) {
            return false;
        }

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        if (uid == null) {
            if (other.uid != null) {
                return false;
            }
        } else if (!uid.equals(other.uid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PanicMode [id=" + id + ", uid=" + uid + ", isOnOrOff=" + isOnOrOff + ", broadcastToCommunity=" + broadcastToCommunity + ", broadcastToProviders=" + broadcastToProviders + ", broadcastToContacts=" + broadcastToContacts + ", latitude=" + latitude + ", longitude=" + longitude + ", user=" + user + ", updatedAt=" + updatedAt + "]";
    }

}
