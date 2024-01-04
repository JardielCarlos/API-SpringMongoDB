package br.com.spring.mongodb.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.spring.mongodb.model.StudentRequest;
import br.com.spring.mongodb.model.StudentResponse;

import br.com.spring.mongodb.service.StudentService;

@RestController
@RequestMapping("/v1")
public class StudentController {
  
  @Autowired
  private StudentService service;

  @GetMapping
  public ResponseEntity<List<StudentResponse>> getAll(){
    return ResponseEntity.ok(service.getAll());
  }

  @PostMapping
  public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest request){
    return ResponseEntity.ok(service.create(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<StudentResponse>> update(@PathVariable("id") String id, @RequestBody StudentRequest request){
    return ResponseEntity.ok(service.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") String id){
    return ResponseEntity.ok(service.delete(id));
  }
}
