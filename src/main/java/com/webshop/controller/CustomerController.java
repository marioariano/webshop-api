package com.webshop.controller;

import com.webshop.domain.Customer;
import com.webshop.domain.dto.CustomerPostDTO;
import com.webshop.domain.dto.CustomerPutDTO;
import com.webshop.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class to manage customer routes
 *
 * @author Mario Ariano
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Insert a new Customer
     *
     * @param customerPostDTO Body PostDTO of Customer
     * @return Customer
     */
    @ApiOperation(value = "Insert a new Customer.")
    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody @Valid CustomerPostDTO customerPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerPostDTO));
    }

    /**
     * Search for all customers
     *
     * @return List of Customers
     */
    @ApiOperation(value = "Search for all customers.")
    @GetMapping
    public ResponseEntity<List<Customer>> list() {
        return ResponseEntity.ok(customerService.listAll());
    }

    /**
     * Search for a customer by id
     *
     * @param id ID of Customer
     * @return Customer
     */
    @ApiOperation(value = "Search for a customer by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    /**
     * Search a customer by parameters
     *
     * @param id ID of Customer
     * @param fullName Full Name of Customer
     * @param documentNumber Document Number of Customer
     * @param birthDate Birth Date of Customer
     * @param genre Gênte of Customer
     * @param email Email of Customer
     * @param address Address of Customer
     * @param phoneNumber Telephone of Customer
     * @param registerDate Register Date of Customer
     * @return List of Customers
     */
    @ApiOperation(value = "Search a customer by parameters.")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Customer>> findByParameter(
            @RequestParam(required = false, defaultValue = "0") Long id,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) @Pattern(regexp = "([0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}[-][0-9]{2})") String documentNumber,
            @DateTimeFormat(pattern = "yyyy/MM/dd") @Pattern(regexp = "([0-9]{4}[/][0-1][0-9][/][0-3][0-9])") @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber,
            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") @Pattern(regexp = "([0-9]{4}[/][0-1][0-9][/][0-3][0-9][ ][0-2][0-9][:][0-5][0-9])") @RequestParam(required = false) LocalDateTime registerDate) {

        List<Customer> list = customerService.findByParameter(id, fullName, documentNumber, birthDate, genre, email, address, phoneNumber, registerDate);
        return ResponseEntity.ok(list);
    }

    /**
     * Search for all customers with pagination
     * You can use "size", "page" and "sort" parameters
     *
     * @param pageable Pageable
     * @return Page of Customer
     */
    @ApiOperation(value = "Search for all customers with pagination." +
            " You can use \"size\", \"page\" e \"sort\" parameters.")
    @GetMapping(path = "/page")
    public ResponseEntity<Page<Customer>> listPage(Pageable pageable) {
        return ResponseEntity.ok(customerService.listPage(pageable));
    }

    /**
     * Search a Customer by ID
     *
     * @param id ID of Customer
     * @return NULL
     */
    @ApiOperation(value = "Delete a Customer by ID.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Edit a customer by ID
     *
     * @param customerPutDTO Body PutDTO of Customer
     * @return Customer
     */
    @ApiOperation(value = "Edit a customer by ID.")
    @PutMapping
    public ResponseEntity<Customer> updateById(@RequestBody @Valid CustomerPutDTO customerPutDTO) {
        return ResponseEntity.ok(customerService.update(customerPutDTO));
    }

}
