package com.alerthub.demo.events;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {
    @Id
    private String id;

    private String name;
    private String description;
    private String creatorId;
    private String location;

    private Double lat;
    private Double lng;

    private List<String> images;
    private String priority;

    private Integer upVote;
    private Integer downVote;
    private Long startDate;
    private Long endDate;
    private Long createdAt;
    private Long updatedAt;

    private List<String> comments;

    private String creatorName;
    private String creatorImage;
    private String creatorEmail;
    private String creatorCountry;

    public Event() {
    }

    public Event(String id, String name, String description, String creatorId, String location, Double lat, Double lng,
            List<String> images, String priority, Integer upVote, Integer downVote, Long startDate, Long endDate,
            Long createdAt, Long updatedAt, List<String> comments, String creatorName, String creatorImage,
            String creatorEmail, String creatorCountry) {
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
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments;
        this.creatorName = creatorName;
        this.creatorImage = creatorImage;
        this.creatorEmail = creatorEmail;
        this.creatorCountry = creatorCountry;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
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

    public Integer getUpVote() {
        return upVote;
    }

    public void setUpVote(Integer upVote) {
        this.upVote = upVote;
    }

    public Integer getDownVote() {
        return downVote;
    }

    public void setDownVote(Integer downVote) {
        this.downVote = downVote;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorImage() {
        return creatorImage;
    }

    public void setCreatorImage(String creatorImage) {
        this.creatorImage = creatorImage;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getCreatorCountry() {
        return creatorCountry;
    }

    public void setCreatorCountry(String creatorCountry) {
        this.creatorCountry = creatorCountry;
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
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lng == null) ? 0 : lng.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
        result = prime * result + ((upVote == null) ? 0 : upVote.hashCode());
        result = prime * result + ((downVote == null) ? 0 : downVote.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((creatorName == null) ? 0 : creatorName.hashCode());
        result = prime * result + ((creatorImage == null) ? 0 : creatorImage.hashCode());
        result = prime * result + ((creatorEmail == null) ? 0 : creatorEmail.hashCode());
        result = prime * result + ((creatorCountry == null) ? 0 : creatorCountry.hashCode());
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
        if (lat == null) {
            if (other.lat != null)
                return false;
        } else if (!lat.equals(other.lat))
            return false;
        if (lng == null) {
            if (other.lng != null)
                return false;
        } else if (!lng.equals(other.lng))
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
        if (upVote == null) {
            if (other.upVote != null)
                return false;
        } else if (!upVote.equals(other.upVote))
            return false;
        if (downVote == null) {
            if (other.downVote != null)
                return false;
        } else if (!downVote.equals(other.downVote))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        } else if (!updatedAt.equals(other.updatedAt))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (creatorName == null) {
            if (other.creatorName != null)
                return false;
        } else if (!creatorName.equals(other.creatorName))
            return false;
        if (creatorImage == null) {
            if (other.creatorImage != null)
                return false;
        } else if (!creatorImage.equals(other.creatorImage))
            return false;
        if (creatorEmail == null) {
            if (other.creatorEmail != null)
                return false;
        } else if (!creatorEmail.equals(other.creatorEmail))
            return false;
        if (creatorCountry == null) {
            if (other.creatorCountry != null)
                return false;
        } else if (!creatorCountry.equals(other.creatorCountry))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", description=" + description + ", creatorId=" + creatorId
                + ", location=" + location + ", lat=" + lat + ", lng=" + lng + ", images=" + images + ", priority="
                + priority + ", upVote=" + upVote + ", downVote=" + downVote + ", startDate=" + startDate + ", endDate="
                + endDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", comments=" + comments
                + ", creatorName=" + creatorName + ", creatorImage=" + creatorImage + ", creatorEmail=" + creatorEmail
                + ", creatorCountry=" + creatorCountry + "]";
    }

}
