package br.com.spring.mongodb.service;

import java.util.List;
import java.util.Optional;

import br.com.spring.mongodb.model.StudentRequest;
import br.com.spring.mongodb.model.StudentResponse;

public interface StudentService {
  
  StudentResponse create(StudentRequest request);

  List<StudentResponse> getAll();

  Optional<StudentResponse> update(String id, StudentRequest studentRequest);

  String delete(String id);
}
