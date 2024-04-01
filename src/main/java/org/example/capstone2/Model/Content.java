package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty (message = "the content name cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String contentName;
    @NotNull(message = "the category id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;
    @NotNull(message = "the price cannot be empty")
    @Column(columnDefinition = "double not null")
    private Double price;
    @Column(columnDefinition = "int  not null default  0")
    private Integer count;
    @Column(columnDefinition = "double  not null default  0")
    private Double reviews;
    @NotNull(message = "the user id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;
}
