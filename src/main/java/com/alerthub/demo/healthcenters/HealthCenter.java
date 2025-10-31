package com.alerthub.demo.healthcenters;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "health_center")
public class HealthCenter {

    @Id
    private String mongoId;
    private String userId;
    private String fullName;
    private String email;
    private String description;
    private String location;
    private Double lat;
    private Double lng;
    private List<String> images;
    private String country;
    private String helpline;
    private List<String> drivers;
    private List<String> patients;

    // Default constructor
    public HealthCenter() {
    }

    // Full constructor
    public HealthCenter(
            String fullName, String email,
            String description,
            String location, Double lat, Double lng, List<String> images,
            String country, String helpline, List<String> drivers, List<String> patients
    ) {
        this.fullName = fullName;
        this.email = email;
        this.country = country;
        this.description = description;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.images = images;
        this.country = country;
        this.helpline = helpline;
        this.drivers = drivers;
        this.patients = patients;
    }

    public HealthCenter(String id, String fullName, String email, String description,
            String location, Double lat, Double lng, List<String> images,
            String country, String helpline, List<String> drivers, List<String> patients) {
        this.userId = id;
        this.fullName = fullName;
        this.email = email;
        this.fullName = fullName;
        this.email = email;
        this.country = country;
        this.description = description;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.images = images;
        this.country = country;
        this.helpline = helpline;
        this.drivers = drivers;
        this.patients = patients;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public List<String> getImages() {
        return images;
    }

    public String getCountry() {
        return country;
    }

    public String getHelpline() {
        return helpline;
    }

    public List<String> getDrivers() {
        return drivers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHelpline(String helpline) {
        this.helpline = helpline;
    }

    public void setDrivers(List<String> drivers) {
        this.drivers = drivers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lng == null) ? 0 : lng.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((helpline == null) ? 0 : helpline.hashCode());
        result = prime * result + ((drivers == null) ? 0 : drivers.hashCode());
        result = prime * result + ((patients == null) ? 0 : patients.hashCode());
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
        HealthCenter other = (HealthCenter) obj;
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (fullName == null) {
            if (other.fullName != null) {
                return false;
            }
        } else if (!fullName.equals(other.fullName)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }

        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }

        if (location == null) {
            if (other.location != null) {
                return false;
            }
        } else if (!location.equals(other.location)) {
            return false;
        }

        if (lat == null) {
            if (other.lat != null) {
                return false;
            }
        } else if (!lat.equals(other.lat)) {
            return false;
        }

        if (lng == null) {
            if (other.lng != null) {
                return false;
            }
        } else if (!lng.equals(other.lng)) {
            return false;
        }

        if (images == null) {
            if (other.images != null) {
                return false;
            }
        } else if (!images.equals(other.images)) {
            return false;
        }

        if (country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!country.equals(other.country)) {
            return false;
        }

        if (helpline == null) {
            if (other.helpline != null) {
                return false;
            }
        } else if (!helpline.equals(other.helpline)) {
            return false;
        }

        if (drivers == null) {
            if (other.drivers != null) {
                return false;
            }
        } else if (!drivers.equals(other.drivers)) {
            return false;
        }
        if (patients == null) {
            if (other.patients != null) {
                return false;
            }
        } else if (!patients.equals(other.patients)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "HealthCenter [id=" + userId + ", fullName=" + fullName + ", email=" + email
                + ", description=" + description + ", location=" + location + ", lat=" + lat
                + ", lng=" + lng + ", images=" + images + ", country=" + country
                + ", helpline=" + helpline + ", drivers=" + drivers + ", patients=" + patients + "]";
    }
}

