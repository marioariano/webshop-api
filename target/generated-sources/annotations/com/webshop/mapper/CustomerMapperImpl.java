package com.webshop.mapper;

import com.webshop.domain.Customer;
import com.webshop.domain.Customer.CustomerBuilder;
import com.webshop.domain.dto.CustomerPutDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-04T18:36:41-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl extends CustomerMapper {

    @Override
    public Customer toCustomer(CustomerPutDTO customerPutDTO) {
        if ( customerPutDTO == null ) {
            return null;
        }

        CustomerBuilder customer = Customer.builder();

        customer.id( customerPutDTO.getId() );
        customer.fullName( customerPutDTO.getFullName() );
        customer.documentNumber( customerPutDTO.getDocumentNumber() );
        customer.birthDate( customerPutDTO.getBirthDate() );
        customer.genre( customerPutDTO.getGenre() );
        customer.email( customerPutDTO.getEmail() );
        customer.address( customerPutDTO.getAddress() );
        customer.phoneNumber( customerPutDTO.getPhoneNumber() );

        return customer.build();
    }
}
