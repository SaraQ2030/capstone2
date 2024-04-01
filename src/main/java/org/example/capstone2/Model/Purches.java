package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Purches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "the content id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer contentId;
    @NotNull(message = "the user id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @Column(columnDefinition = "date not null")
    private LocalDate purchesDate;
//  @AssertFalse
 @Column(columnDefinition = "boolean not null default false ")
  private  Boolean inProgress;
//  @FutureOrPresent
  @Column(columnDefinition = "date not null")
  private LocalDate starDate;
//    @FutureOrPresent
    @Column(columnDefinition = "date not null")
  private LocalDate endDate;
    @NotEmpty(message = "status cannot be empty")
    @Pattern(regexp = "(not-started|in-progress|done)" ,message = "the status should be not-started OR  in-progress  OR  done ")
    @Column(columnDefinition = "varchar(11) not null check(status='not-started' or status='in-progress' or status='done')")
    private String status;
}
