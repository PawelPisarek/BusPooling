package BusPooling.rest.domain;

import java.util.*;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "person")
public class Person {
    private String id;
    private String username;

    private String password;

    private String name;

    private String surname;

    private Date birthday;

    private String gender;

    private boolean active = false;

    private String facebookUID;

    private String geoLat;

    private String geoLng;

    public Person() {
    }

    public Person(String id,
                  String username,
                  String password,
                  String name,
                  String surname,
                  Date birthdate,
                  String gender,
                  boolean active,
                  String facebookUID,
                  String geoLat,
                  String geoLon) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthdate;
        this.gender = gender;
        this.active = active;
        this.facebookUID = facebookUID;
        this.geoLat = geoLat;
        this.geoLng = geoLon;
    }

    @ApiModelProperty(value = "person id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "person username", required = true)
    public String getUsername() {
        return username;
    }

    @ApiModelProperty(value = "person password", required = true)
    public String getPassword() {
        return password;
    }

    @ApiModelProperty(value = "person name", required = true)
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "person surname", required = true)
    public String getSurname() {
        return surname;
    }

    @ApiModelProperty(value = "person birthday", required = true)
    public Date getBirthday() {
        return birthday;
    }

    @ApiModelProperty(value = "person gender", required = true)
    public String getGender() {
        return gender;
    }

    @ApiModelProperty(value = "person active", required = true)
    public boolean isActive() {
        return active;
    }

    @ApiModelProperty(value = "person facebookUID", required = true)
    public String getFacebookUID() {
        return facebookUID;
    }

    @ApiModelProperty(value = "person geoLat", required = true)
    public String getGeoLat() {
        return geoLat;
    }

    @ApiModelProperty(value = "person geoLng", required = true)
    public String getGeoLng() {
        return geoLng;
    }

}
