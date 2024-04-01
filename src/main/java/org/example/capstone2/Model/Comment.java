package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull(message = "content id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer contentId;
    @NotEmpty(message = "the content cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String comment;
    @Min(value = 1,message = "min value 1")
    @Max(value = 5 ,message = "max value 5")
    @NotNull(message = "the review cannot be empty")
//    @Pattern(regexp = "^[1-5]$", message = "rate the service from 1 to 5 , 5 super satisfied 1 not satisfied")
    @Column(columnDefinition = "double not null")// check(review<= 5   and  review >0)
    private Double review;
    @Column(columnDefinition = "date not null")
    private LocalDate commentDate;
    @NotNull(message = "purches id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer purchesId;
}
