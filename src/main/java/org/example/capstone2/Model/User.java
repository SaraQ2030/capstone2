package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username cannot be empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
    @NotEmpty(message = "the password cannot be empty ")
    @Length(min = 6,message = "the password should be at least 6 char")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$" ,message = "the password should be letter and numbers")
    private String password;
    @Email
    @NotEmpty(message = "email cannot be empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotNull(message = "phone Number cannot be empty")
   // @Pattern(regexp = "^05\\d{8}$"  , message = "Number should start with 05xxxxxxxx and contain 10 numbers")
    @Column(columnDefinition = "int (10) not null")
    private Integer phoneNumber;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "(user|admin)" ,message = "the role should be user OR  admin")
    @Column(columnDefinition = "varchar(7) not null check(role='user' or role='company' or role='admin')")
    private String role;
    @Column(columnDefinition = "int default 0")
    private Integer userCount;

//    @Column(columnDefinition = "date not null")
//    private LocalDate registrationDate;
}
