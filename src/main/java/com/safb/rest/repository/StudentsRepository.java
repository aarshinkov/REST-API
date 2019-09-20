package com.safb.rest.repository;

import com.safb.rest.entity.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends PagingAndSortingRepository<Student, Integer>
{
  Student findByStudentId(Integer studentId);

  Student findByPublicId(String publicId);
}
