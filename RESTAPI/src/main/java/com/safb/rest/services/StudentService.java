package com.safb.rest.services;

import com.safb.rest.entity.*;
import java.util.*;

public interface StudentService
{
  List<Student> getStudents(Integer page, Integer limit);

  Student getStudent(Integer studentId);

  void deleteStudent(Integer studentId);
}
