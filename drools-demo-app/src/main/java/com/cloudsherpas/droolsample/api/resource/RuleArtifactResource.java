package com.cloudsherpas.droolsample.api.resource;

/**
 * @author CMiranda
 */
public class RuleArtifactResource {

    private Long id;
    private String groupId;
    private String artifactId;
    private String version;
    private boolean active; 

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public void setArtifactId(final String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}