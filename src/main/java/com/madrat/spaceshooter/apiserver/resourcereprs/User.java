package com.madrat.spaceshooter.apiserver.resourcereprs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users_info", uniqueConstraints={
        @UniqueConstraint(columnNames = {"serveruuid" , "clientuuid"})
})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class User implements Comparable<User> {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "clientuuid", unique = true)
    private String clientuuid;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "serveruuid", unique = true)
    private String serveruuid;

    @NotBlank
    private String username;

    @NotNull
    private int score;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Generated(GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @LastModifiedDate
    private Date updatedAt;

    public User() {}

    public User(String serverUUID, String clientUUID, String userName, int score) {
        this.serveruuid = serverUUID;
        this.clientuuid = clientUUID;
        this.username = userName;
        this.score = score;

        this.createdAt = new Date();
    }

    public boolean isValid() {
        return this.getServeruuid() != null
               && this.getClientuuid() != null
               && this.getUsername() != null
               && this.getScore() != 0;
    }

    public String getServeruuid() {
        return serveruuid;
    }

    public String getClientuuid() {
        return clientuuid;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setServeruuid(String serveruuid) {
        this.serveruuid = serveruuid;
    }

    public void setClientuuid(String clientuuid) {
        this.clientuuid = clientuuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public boolean updateScore() {
        return false;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String get(String field) {
        switch (field) {
            case "clientuuid": {
                return this.clientuuid;
            }
            case "serveruuid": {
                return this.serveruuid;
            }
            case "username": {
                return this.username;
            }
            case "score": {
                return Integer.toString(this.score);
            }
        }
        return null;
    }

    public String checkFields() {
        final String uuidRegexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        final String usernameRegexp = "^[a-zA-Z0-9_]*$";
        final String scoreRegexp = "^[0-9]*$";

        Pattern uuidPattern = Pattern.compile(uuidRegexp);
        Matcher serverUUIDMatcher = uuidPattern.matcher(this.serveruuid);
        if (!serverUUIDMatcher.matches()) {
            return "serveruuid";
        }
        Matcher clientUUIDMatcher = uuidPattern.matcher(this.clientuuid);
        if (!clientUUIDMatcher.matches()) {
            return "clientuuid";
        }

        Pattern usernamePattern = Pattern.compile(usernameRegexp);
        Matcher usernameMatcher = usernamePattern.matcher(this.username);
        if (!usernameMatcher.matches()) {
            return "username";
        }

        Pattern scorePattern = Pattern.compile(scoreRegexp);
        Matcher scoreMatcher = scorePattern.matcher(Integer.toString(this.score));
        if (!scoreMatcher.matches()) {
            return "score";
        }

        return ErrorCode.OK;
    }

    @Override
    public int compareTo(User user) {
        return Integer.compare(this.score, user.score);
    }

    @Override
    public String toString() {
        String json = "";
        json += "{" + "\n" +
                "\t\"clientuuid\": \"" + clientuuid + "\"\n" +
                "\t\"serveruuid\": \"" + serveruuid + "\"\n" +
                "\t\"username\": \"" + username + "\"\n" +
                "\t\"score\": " + score + "\n" +
                "}";
        return json;
    }

    public String toString(int indentCount, String indentStr) {
        String indenter = String.join("", Collections.nCopies(indentCount, indentStr));
        String json = "";
        json += indenter + "{" + "\n" +
                indenter + "\t\"clientuuid\": \"" + clientuuid + "\"\n" +
                indenter + "\t\"serveruuid\": \"" + serveruuid + "\"\n" +
                indenter + "\t\"username\": \"" + username + "\"\n" +
                indenter + "\t\"score\": " + score + "\n" +
                indenter + "}";
        return json;
    }
}
