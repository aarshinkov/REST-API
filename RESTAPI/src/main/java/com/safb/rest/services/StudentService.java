package com.safb.rest.services;

import com.safb.rest.entity.*;
import com.safb.rest.model.*;
import java.util.*;

public interface StudentService
{
  List<StudentModel> getStudents(Integer page, Integer limit);

  StudentModel getStudent(String publicId);

  StudentModel createStudent(StudentModel studentModel);

  void deleteStudent(String publicId);
}
