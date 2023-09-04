package com.webshop.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * Class PostDTO of Customer
 */
@Data
public class CustomerPostDTO {
    private String fullName;
    @Pattern(regexp = "([0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}[-][0-9]{2})")
    private String documentNumber;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthDate;
    private String genre;
    private String email;
    private String address;
    private String phoneNumber;
}
