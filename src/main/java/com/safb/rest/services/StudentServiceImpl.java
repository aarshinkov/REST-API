package com.safb.rest.services;

import com.safb.rest.entity.*;
import com.safb.rest.exceptions.*;
import com.safb.rest.model.*;
import com.safb.rest.repository.*;
import com.safb.rest.response.*;
import com.safb.rest.utils.*;

import java.util.*;

import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private Utils utils;

//  @Override
//  public List<StudentModel> getStudents(Integer page, Integer limit)
//  {
//
//    if (page > 0)
//    {
//      page--;
//    }
//
//    Pageable pageableRequest = PageRequest.of(page, limit);
//
//    Page<Student> studentsPage = studentsRepository.findAll(pageableRequest);
//
//    List<Student> entityStudents = studentsPage.getContent();
//
//    List<StudentModel> studentsList = new ArrayList<>();
//
//    for (Student entityStudent : entityStudents)
//    {
//      StudentModel studentModel = new StudentModel();
//
//      BeanUtils.copyProperties(entityStudent, studentModel);
//
//      studentsList.add(studentModel);
//    }
//
//    return studentsList;
//  }
//
//  @Override
//  public StudentModel getStudent(String publicId)
//  {
//    Student student = studentsRepository.findByPublicId(publicId);
//
//    if (student == null)
//    {
//      throw new StudentServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
//    }
//
//    StudentModel studentModel = new StudentModel();
//
//    BeanUtils.copyProperties(student, studentModel);
//
//    return studentModel;
//  }
//
//  @Override
//  public StudentModel createStudent(StudentModel studentModel)
//  {
//    Student student = new Student();
//
//    BeanUtils.copyProperties(studentModel, student);
//
//    String publicId = utils.generateUserId(30);
//    student.setPublicId(publicId);
//
//    Student savedStudent = studentsRepository.save(student);
//
//    StudentModel resultStudent = new StudentModel();
//
//    BeanUtils.copyProperties(savedStudent, resultStudent);
//
//    return resultStudent;
//  }
//
//  @Override
//  public void deleteStudent(String publicId)
//  {
//    Student student = studentsRepository.findByPublicId(publicId);
//
//    if (student == null)
//    {
//      throw new StudentServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
//    }
//
//    studentsRepository.delete(student);
//  }
}
