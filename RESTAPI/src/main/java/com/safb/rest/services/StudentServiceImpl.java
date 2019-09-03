package com.safb.rest.services;

import com.safb.rest.entity.*;
import com.safb.rest.exceptions.*;
import com.safb.rest.repository.*;
import com.safb.rest.response.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class StudentServiceImpl implements StudentService
{
  @Autowired
  private StudentsRepository studentsRepository;

  @Override
  public List<Student> getStudents(Integer page, Integer limit)
  {

    if (page > 0)
    {
      page--;
    }

    Pageable pageableRequest = PageRequest.of(page, limit);

    Page<Student> studentsPage = studentsRepository.findAll(pageableRequest);

    List<Student> students = studentsPage.getContent();

    return students;
  }

  @Override
  public Student getStudent(Integer studentId)
  {
    Student student = studentsRepository.findByStudentId(studentId);

    if (student == null)
    {
      throw new StudentServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    return student;
  }

  @Override
  public void deleteStudent(Integer studentId)
  {
    Student student = studentsRepository.findByStudentId(studentId);

    if (student == null)
    {
      throw new StudentServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    studentsRepository.delete(student);
  }
}
