package com.cloudsherpas.droolsample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "rulesversion")
@Entity
public class RulesVersion {
    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "package_name")
    private String packageName;
    @Column(name = "version")
    private String version;

    /*
     * (non-Javadoc)
     * 
     * Needed by ORM for class creation by reflection.
     */
    private RulesVersion() {
    }

    public RulesVersion(final String packageName, final String version) {
        this.packageName = packageName;
        this.version = version;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

}
