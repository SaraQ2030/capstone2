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
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message="the image cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String image;
    @NotEmpty(message="the description cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;
    @NotNull(message = "the content id cannot be empty")
    @Column(columnDefinition = "int not null")
private Integer contentId;

}
