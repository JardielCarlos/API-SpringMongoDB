package br.com.spring.mongodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.mongodb.model.StudentRequest;
import br.com.spring.mongodb.model.StudentResponse;

import br.com.spring.mongodb.persistence.entity.Student;

import br.com.spring.mongodb.persistence.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository repository;

  @Override
  public StudentResponse create(StudentRequest request){
    Student student = new Student();
    student.setName(request.getName());
    student.setDocument(request.getDocument());
    student.setBirthDate(request.getBirthDate());

    repository.save(student);

    return createResponse(student);
  }

  @Override
  public List<StudentResponse> getAll(){
    List<StudentResponse> responses = new ArrayList<>();

    List<Student> students = repository.findAll();

    if(!students.isEmpty()){
      students.forEach(student -> responses.add(createResponse(student)));
    }

    return responses;
  }
  
  private StudentResponse createResponse(Student student) {
    StudentResponse response = new StudentResponse();
    response.setId(student.getId());
    response.setName(student.getName());
    response.setDocument(student.getDocument());
    response.setBirthDate(student.getBirthDate());

    return response;
  }

  @Override
  public Optional<StudentResponse> update(String id, StudentRequest request) {

    return repository.findById(id).map(studentUpdate -> {
      studentUpdate.setName(request.getName());
      studentUpdate.setDocument(request.getDocument());
      studentUpdate.setBirthDate(request.getBirthDate());

      repository.save(studentUpdate);
      return createResponse(studentUpdate);
    });
  }

  @Override
  public String delete(String id) {
    if (repository.findById(id).isPresent()){
      repository.deleteById(id);
      return "{}";
    }else {
      return "Não foi possível localizar o id: " + id;
    }
  }
}


