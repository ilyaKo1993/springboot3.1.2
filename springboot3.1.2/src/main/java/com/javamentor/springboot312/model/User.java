package com.javamentor.springboot312.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 30, message = "Имя должно быть от 3х до 30 символов")
    @Pattern(message = "Используйте латинский алфавит от A-Z (пример: Ilya): ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    private String name;

    @Column(name = "LAST_NAME")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 30, message = "Фамилия должна быть от 3х до 30 символов")
    @Pattern(message = "Используйте латинский алфавит от A-Z (пример: Korolev): ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    private String lastName;
}
