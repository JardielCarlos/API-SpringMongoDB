package br.com.spring.mongodb.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.spring.mongodb.model.StudentRequest;
import br.com.spring.mongodb.model.StudentResponse;
import br.com.spring.mongodb.persistence.entity.Student;
import br.com.spring.mongodb.persistence.repository.StudentRepository;

@SpringBootTest
public class StudentServiceTest {

  @Autowired
  private StudentService service;

  @MockBean
  private StudentRepository repository;

  @Test
  public void testCreate() {
    // Crie um StudentRequest
    StudentRequest request = new StudentRequest();
    request.setName("Test");
    request.setDocument("Test Document");
    request.setBirthDate(LocalDate.now());

    // Crie um Student com os mesmos valores
    Student student = new Student();
    student.setName(request.getName());
    student.setDocument(request.getDocument());
    student.setBirthDate(request.getBirthDate());

    // Configure o repositório para retornar o Student quando o método save() for chamado
    Mockito.when(repository.save(Mockito.any(Student.class))).thenReturn(student);

    // Chame o método create() do serviço
    StudentResponse response = service.create(request);

    // Verifique se o response tem os mesmos valores que o request
    assertEquals(response.getName(), request.getName());
    assertEquals(response.getDocument(), request.getDocument());
    assertEquals(response.getBirthDate(), request.getBirthDate());
  }

  @Test
  public void testDelete() {
    // Crie um Student
    Student student = new Student();
    student.setId("1");
    student.setName("Test");
    student.setDocument("Test Document");
    student.setBirthDate(LocalDate.now());
  
    // Configure o repositório para retornar o Student quando o método findById() for chamado
    Mockito.when(repository.findById("1")).thenReturn(Optional.of(student));
  
    // Chame o método delete() do serviço
    String response = service.delete("");
  
    // Verifique se o response é "{}"
    assertEquals(response, "{}");
  }

  @Test
  public void testGetAll() {
    // Crie uma lista de Students
    List<Student> students = new ArrayList<>();
    Student student = new Student();
    student.setId("1");
    student.setName("Test");
    student.setDocument("Test Document");
    student.setBirthDate(LocalDate.now());
    students.add(student);

    // Configure o repositório para retornar a lista de Students quando o método findAll() for chamado
    Mockito.when(repository.findAll()).thenReturn(students);

    // Chame o método getAll() do serviço
    List<StudentResponse> responses = service.getAll();

    // Verifique se a lista de responses tem o mesmo tamanho que a lista de Students
    assertEquals(responses.size(), students.size());
  }

  @Test
  public void testUpdate() {
    // Crie um StudentRequest
    StudentRequest request = new StudentRequest();
    request.setName("Test Updated");
    request.setDocument("Test Document Updated");
    request.setBirthDate(LocalDate.now());
  
    // Crie um Student com os mesmos valores
    Student student = new Student();
    student.setId("1");
    student.setName("Test");
    student.setDocument("Test Document");
    student.setBirthDate(LocalDate.now());
  
    // Configure o repositório para retornar o Student quando o método findById() for chamado
    Mockito.when(repository.findById("1")).thenReturn(Optional.of(student));
  
    // Chame o método update() do serviço
    Optional<StudentResponse> response = service.update("1", request);
  
    // Verifique se o response tem os mesmos valores que o request
    assertTrue(response.isPresent());
    assertEquals(response.get().getName(), request.getName());
    assertEquals(response.get().getDocument(), request.getDocument());
    assertEquals(response.get().getBirthDate(), request.getBirthDate());
  }
}
