package com.safb.rest.entity;

import java.io.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student implements Serializable
{
  @Id
  @GeneratedValue
  @Column(name = "student_id")
  private Integer studentId;

  @NotNull
  @Size(max = 100)
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Size(max = 100)
  @Column(name = "last_name")
  private String lastName;

  public Integer getStudentId()
  {
    return studentId;
  }

  public void setStudentId(Integer studentId)
  {
    this.studentId = studentId;
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
