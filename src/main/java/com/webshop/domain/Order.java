package com.webshop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Domain Order
 *
 * @author Mario Ariano
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime registerDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
