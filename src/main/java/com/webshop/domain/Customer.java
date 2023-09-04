package com.webshop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/**
 * Domain Customer
 *
 * @author Mario Ariano
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Pattern(regexp = "([0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}[-][0-9]{2})")
    private String documentNumber;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthDate;
    private String genre;
    private String email;
    private String address;
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime registerDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

}
