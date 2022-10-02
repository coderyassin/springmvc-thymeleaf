package org.yascode.springmvc.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name can not be null!!")
    @NotEmpty(message = "Name can not be empty!!")
    private String name;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter birth date")
    @Past(message = "Birth date should be less than current date!!")
    private LocalDate dateOfBirth;

    @Column(name = "sick")
    private boolean sick;
}
