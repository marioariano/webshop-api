package com.webshop.mapper;

import com.webshop.domain.Customer;
import com.webshop.domain.dto.CustomerPostDTO;
import com.webshop.domain.dto.CustomerPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Class for Customer Mapping
 *
 * @author Mario Ariano
 */
@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

    /**
     * INSTANCE
     */
    public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    /**
     * Pass the attributes of CustomerPostDTO to Customer
     *
     * @param customerPostDTO CustomerPostDTO
     * @return Customer
     */
    public Customer toCustomer(CustomerPostDTO customerPostDTO) {
        if ( customerPostDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.fullName( customerPostDTO.getFullName() );
        customer.documentNumber( customerPostDTO.getDocumentNumber() );
        customer.birthDate( customerPostDTO.getBirthDate() );
        customer.genre( customerPostDTO.getGenre() );
        customer.email( customerPostDTO.getEmail() );
        customer.address( customerPostDTO.getAddress() );
        customer.phoneNumber( customerPostDTO.getPhoneNumber() );
        customer.registerDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        return customer.build();
    }

    /**
     * Pass the attributes of CustomerPutDTO to Category
     *
     * @param customerPutDTO CustomerPutDTO
     * @return Customer
     */
    public abstract Customer toCustomer(CustomerPutDTO customerPutDTO);
}
