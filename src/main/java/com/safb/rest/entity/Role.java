package com.safb.rest.entity;

import java.io.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @Size(max = 30)
    @Column(name = "rolename")
    private String rolename;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "display_name")
    private String displayName;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "description")
    private String description;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
