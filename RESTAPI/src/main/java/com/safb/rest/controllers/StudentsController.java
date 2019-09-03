package com.safb.rest.controllers;

import com.safb.rest.entity.*;
import com.safb.rest.exceptions.*;
import com.safb.rest.model.*;
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
  public List<StudentModel> getStudents(@RequestParam(value = "page", defaultValue = "0") Integer page,
          @RequestParam(value = "limit", defaultValue = "5") Integer limit)
  {
    return studentService.getStudents(page, limit);
  }

  @GetMapping(value = "/{publicId}", produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public StudentModel getStudent(@PathVariable("publicId") String publicId)
  {
    return studentService.getStudent(publicId);
  }

  @PostMapping(consumes =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  }, produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public StudentModel createStudent(@RequestBody StudentModel studentModel) throws Exception
  {
    if (studentModel.getFirstName().isEmpty() || studentModel.getLastName().isEmpty())
    {
      throw new StudentServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    StudentModel resultStudent = studentService.createStudent(studentModel);

    return resultStudent;
  }

  @DeleteMapping(value = "/{publicId}", produces =
  {
    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
  })
  public OperationStatus deleteStudent(@PathVariable("publicId") String publicId
  )
  {
    OperationStatus result = new OperationStatus();

    result.setOperationName(OperationNames.DELETE.name());

    studentService.deleteStudent(publicId);

    result.setOperationResult(OperationStatuses.SUCCESS.name());

    return result;
  }
}
