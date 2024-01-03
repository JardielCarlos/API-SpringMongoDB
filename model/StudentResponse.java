package br.com.spring.mongodb.model;

import java.time.LocalDate;

public class StudentResponse {
  
  private String id;
  private String name;
  private LocalDate birthDate;
  private String document;


  
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getDocument() {
    return document;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public void setDocument(String document) {
    this.document = document;
  }
}
