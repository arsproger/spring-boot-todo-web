package com.arsen.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    private Long id;

    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(max = 30, message = "Превышено максимальное допустимое значение для имени!")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Email не может быть пустым!")
    @Email(message = "Некорректный email!")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    @Size(min = 8, max = 30, message = "Пароль должен быть в диапазоне 8-30 символов!")
    private String password;

    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(mappedBy = "owner")
    private List<Task> tasks;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
