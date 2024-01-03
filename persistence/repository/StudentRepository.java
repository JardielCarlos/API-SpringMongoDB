package br.com.spring.mongodb.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.mongodb.persistence.entity.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
  
}
