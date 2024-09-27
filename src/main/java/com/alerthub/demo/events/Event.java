package com.alerthub.demo.events;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event")
public class Event {
    @Id
    private String id;
    private String name;
    private String description;
    private String creatorId;
    private String location;

    private double lat;
    private double lng;

    private List<String> images;
    private String priority;

    private int upVote;
    private int downVote;
    private String availiablity;
    private int createdAt;
    private int updatedAt;

    public Event() {
    }

    public Event(String id, String name, String description, String creatorId, String location, double lat, double lng,
            List<String> images, String priority, int upVote, int downVote, String availiablity, int createdAt,
            int updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.images = images;
        this.priority = priority;
        this.upVote = upVote;
        this.downVote = downVote;
        this.availiablity = availiablity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Event(String name, String description, String creatorId, String location, double lat, double lng,
            List<String> images, String priority, int upVote, int downVote, String availiablity, int createdAt,
            int updatedAt) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.images = images;
        this.priority = priority;
        this.upVote = upVote;
        this.downVote = downVote;
        this.availiablity = availiablity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public String getAvailiablity() {
        return availiablity;
    }

    public void setAvailiablity(String availiablity) {
        this.availiablity = availiablity;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public int getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", description=" + description + ", creatorId=" + creatorId
                + ", location=" + location + ", lat=" + lat + ", lng=" + lng + ", images=" + images + ", priority="
                + priority + ", upVote=" + upVote + ", downVote=" + downVote + ", availiablity=" + availiablity
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((creatorId == null) ? 0 : creatorId.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
        result = prime * result + upVote;
        result = prime * result + downVote;
        result = prime * result + ((availiablity == null) ? 0 : availiablity.hashCode());
        result = prime * result + createdAt;
        result = prime * result + updatedAt;
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
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (creatorId == null) {
            if (other.creatorId != null)
                return false;
        } else if (!creatorId.equals(other.creatorId))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
            return false;
        if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
            return false;
        if (images == null) {
            if (other.images != null)
                return false;
        } else if (!images.equals(other.images))
            return false;
        if (priority == null) {
            if (other.priority != null)
                return false;
        } else if (!priority.equals(other.priority))
            return false;
        if (upVote != other.upVote)
            return false;
        if (downVote != other.downVote)
            return false;
        if (availiablity == null) {
            if (other.availiablity != null)
                return false;
        } else if (!availiablity.equals(other.availiablity))
            return false;
        if (createdAt != other.createdAt)
            return false;
        if (updatedAt != other.updatedAt)
            return false;
        return true;
    }

}
