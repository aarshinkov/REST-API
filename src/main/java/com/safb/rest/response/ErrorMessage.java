package com.safb.rest.response;

import java.util.*;

public class ErrorMessage
{
  private Date timestamp;
  private String message;

  public ErrorMessage()
  {
  }

  public ErrorMessage(Date timestamp, String message)
  {
    super();
    this.timestamp = timestamp;
    this.message = message;
  }

  public Date getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(Date timestamp)
  {
    this.timestamp = timestamp;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }
}
