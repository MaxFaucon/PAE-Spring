package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "courses")
public class Student {
    
    @Id 
    @GeneratedValue(generator = "sequence-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence-generator", sequenceName = "user_sequence", initialValue = 8)
    private Long matricule;
    @NotBlank(message="ne peut pas être vide")
    @NonNull
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @NonNull
    @NotNull(message="il faut sélectionner un genre")
    private Gender gender;
    @Enumerated(EnumType.ORDINAL)
    @NonNull
    @NotNull(message="il faut sélectionner une section")
    private Section section;
    @JsonIgnore
    @ManyToMany
    private Collection<Course> courses;
}
