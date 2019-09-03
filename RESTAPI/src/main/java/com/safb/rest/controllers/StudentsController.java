package com.safb.rest.controllers;

import com.safb.rest.entity.*;
import com.safb.rest.response.*;
import com.safb.rest.services.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/students")
public class StudentsController
{
  @Autowired
  private StudentService studentService;

  @GetMapping(produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public List<Student> getStudents(@RequestParam(value = "page", defaultValue = "0") Integer page,
          @RequestParam(value = "limit", defaultValue = "5") Integer limit)
  {
    return studentService.getStudents(page, limit);
  }

  @GetMapping(value = "/{studentId}", produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public Student getStudent(@PathVariable("studentId") Integer studentId)
  {
    return studentService.getStudent(studentId);
  }

  @DeleteMapping(value = "/{studentId}", produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public OperationStatus deleteStudent(@PathVariable("studentId") Integer studentId)
  {
    OperationStatus result = new OperationStatus();

    result.setOperationName(OperationNames.DELETE.name());

    studentService.deleteStudent(studentId);

    result.setOperationResult(OperationStatuses.SUCCESS.name());

    return result;
  }
}
