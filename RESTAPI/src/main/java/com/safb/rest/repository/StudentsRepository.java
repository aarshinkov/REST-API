package com.safb.rest.repository;

import com.safb.rest.entity.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.*;

@Component
public interface StudentsRepository extends PagingAndSortingRepository<Student, Integer>
{
  Student findByStudentId(Integer studentId);
}
