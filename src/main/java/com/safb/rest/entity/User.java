package com.safb.rest.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userId;

  @NotNull
  @Size(max = 50)
  @Column(name = "public_id")
  private String publicId;

  @Email
  @NotNull
  @Size(min = 2, max = 200)
  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "password")
  private String password;

  @NotNull
  @Size(min = 2, max = 50)
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Size(min = 2, max = 50)
  @Column(name = "last_name")
  private String lastName;

  @ManyToMany
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rolename"))
  private List<Role> roles = new ArrayList<>();

  public Integer getUserId()
  {
    return userId;
  }

  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }

  public String getPublicId()
  {
    return publicId;
  }

  public void setPublicId(String publicId)
  {
    this.publicId = publicId;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public List<Role> getRoles()
  {
    return roles;
  }

  public void setRoles(List<Role> roles)
  {
    this.roles = roles;
  }
}
