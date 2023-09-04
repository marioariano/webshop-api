package com.webshop.service;

import com.webshop.domain.Customer;
import com.webshop.domain.dto.CustomerPostDTO;
import com.webshop.domain.dto.CustomerPutDTO;
import com.webshop.mapper.CustomerMapper;
import com.webshop.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Class Service of Customer
 *
 * @author Mario Ariano
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Insert a Customer into the Database
     *
     * @param customerPostDTO CustomerPostDTO
     * @return Customer
     */
    @Transactional
    public Customer save(CustomerPostDTO customerPostDTO) {
        Customer customer = CustomerMapper.INSTANCE.toCustomer(customerPostDTO);
        return customerRepository.save(customer);
    }

    /**
     * List all customers
     *
     * @return List of Product
     */
    public List<Customer> listAll() {
        return customerRepository.findAll();
    }

    /**
     * Search a Customer by ID
     *
     * @param id ID of Customer
     * @return Customer
     */
    public Customer findById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException();
    }


    /**
     * Delete a customer by ID in the database
     *
     * @param id ID of Customer
     */
    public void delete(long id) {
        customerRepository.delete(findById(id));
    }

    /**
     * Edit a customer by ID in the database
     *
     * @param customerPutDTO Body PutDTO of Customer
     * @return Customer
     */
    public Customer update(CustomerPutDTO customerPutDTO) {
        Customer savedCustomer = findById(customerPutDTO.getId());
        Customer customer = CustomerMapper.INSTANCE.toCustomer(customerPutDTO);
        customer.setId(savedCustomer.getId()); // Double check for the ID
        customer.setRegisterDate(savedCustomer.getRegisterDate()); // fill registerDate
        return customerRepository.save(customer);
    }

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
     * @param registerDate Register Date of Customer
     * @return List of Customer
     */
    public List<Customer> findByParameter(long id, String fullName, String documentNumber, LocalDate birthDate,
                                         String genre, String email, String address, String phoneNumber,
                                         LocalDateTime registerDate) {
        return customerRepository.findByIdOrFullNameOrDocumentNumberOrBirthDateOrGenreOrEmailOrAddressOrPhoneNumberOrRegisterDate(
                id, fullName, documentNumber, birthDate, genre, email, address, phoneNumber, registerDate
        );
    }

    /**
     * Search all customers with pagination
     *
     * @param pageable Pageable
     * @return Page of Customer
     */
    public Page<Customer> listPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
