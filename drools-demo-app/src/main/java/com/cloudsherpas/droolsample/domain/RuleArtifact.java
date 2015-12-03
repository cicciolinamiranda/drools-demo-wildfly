package com.cloudsherpas.droolsample.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author CMiranda
 */
@Entity
@Table(name = "rule_artifact")
public class RuleArtifact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "artifact_id")
    private String artifactId;

    @Column(name = "version")
    private String version;

    @Column(name = "active")
    private boolean active;

    @Column(name = "date_created")
    private Date dateCreated;

    private RuleArtifact(){}

    public RuleArtifact(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.active = false;
    }

    @PrePersist
    public void onSave(){
        if(dateCreated == null){
            dateCreated = new Date();
        }
    }

    public Long getId() {
        return id;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getDateCreated() {
        return LocalDateTime.ofInstant(this.dateCreated.toInstant(), ZoneId.systemDefault());
    }
}
