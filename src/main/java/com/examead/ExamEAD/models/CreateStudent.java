package com.examead.ExamEAD.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudent {
    @NotBlank(message = "Student code cannot be blank")
    private String studentCode;
    @NotBlank(message = "Full name cannot be blank")
    private String fullName;
    private String address;
}
