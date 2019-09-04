package com.safb.rest.dto;

public class UserDto
{
  private String publicId;
  private String firstName;
  private String lastName;

  public String getPublicId()
  {
    return publicId;
  }

  public void setPublicId(String publicId)
  {
    this.publicId = publicId;
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
}
