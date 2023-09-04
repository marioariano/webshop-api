package com.webshop.repository;

import com.webshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Repository of Customer
 *
 * @author Mario Ariano
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Search customers by parameters
     *
     * @param id ID of Customer
     * @param fullName Full Name of Customer
     * @param documentNumber Document Number of Customer
     * @param birthDate Birth Date of Customer
     * @param genre Genre of Customer
     * @param email Email of Customer
     * @param address Address of Customer
     * @param phoneNumber Phone Number of Customer
     * @param registerDate Register Date of Number
     * @return List of Customer
     */
    List<Customer> findByIdOrFullNameOrDocumentNumberOrBirthDateOrGenreOrEmailOrAddressOrPhoneNumberOrRegisterDate(
            long id,
            String fullName,
            String documentNumber,
            LocalDate birthDate,
            String genre,
            String email,
            String address,
            String phoneNumber,
            LocalDateTime registerDate);
}
