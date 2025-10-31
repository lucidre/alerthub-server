package com.alerthub.demo.drivers;
 
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 

@Document(collection = "drivers")
public class Driver {
   
    @Id
    private String id; 
    private String fullName;
    private String email;
    private String password; 
    private String location;
    private Double lat;
    private Double lng;
    private String image; 
    private String contact;
    private String healthCenterId; 

    // Default constructor
    public Driver() {
    }

    // Full constructor
    public Driver(
 
            String fullName, String email,
            String password,
            String location, Double lat, Double lng, String image,  String contact, String healthCenterId) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.image = image;
        this.contact = contact; 
        this. healthCenterId= healthCenterId; 
    }

 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLng() {
        return lng;
    }


    public String getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(String healthCenterId) {
        this.healthCenterId = healthCenterId;
    }
   
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   
    public void setCoontact(String contact) {
        this.contact = contact;
    }
 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lng == null) ? 0 : lng.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode()); 
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
        Driver other = (Driver) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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

        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
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

        if (image == null) {
            if (other.image != null) {
                return false;
            }
        } else if (!image.equals(other.image)) {
            return false;
        }

        if (contact == null) {
            if (other.contact != null) {
                return false;
            }
        } else if (!contact.equals(other.contact)) {
            return false;
        }

        if (healthCenterId == null) {
            if (other.healthCenterId != null) {
                return false;
            }
        } else if (!healthCenterId.equals(other.healthCenterId)) {
            return false;
        }

      

        return true;
    }

    @Override
    public String toString() {
        return "Driver [id=" + id + ", fullName=" + fullName + ", email=" + email
                + ", password=" + password + ", location=" + location + ", lat=" + lat
                + ", lng=" + lng + ", image=" + image + ", contact=" + contact
                + ", healthCenterId=" + healthCenterId + "]";
    }
}
