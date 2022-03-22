package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Course {
    @NotBlank(message="ne peut pas être vide")
    @NonNull
    @Id
    private String id;
    @NotBlank(message="ne peut pas être vide")
    @NonNull
    private String title;
    @NonNull
    @NotNull
    @Min(value=1, message="") @Max(30)
    private Integer credits;
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Collection<Student> students;
}
