package Student.NRI;


import Student.NRI.Repository.CustomerRepository;
import Student.NRI.model.Address;
import Student.NRI.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SeedData {
    private final CustomerRepository customerRepository;

    public SeedData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private final List<Customer> customers = Arrays.asList(
//
            new Customer("fehbjsknd", "dad", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Prabhudasu", "parusu", "prabhudasparusu@email.com", new Address("india", "Andhra Pradesh", " Ongole city","Mangamuru"))

    );

 @PostConstruct
    public void saveCustomers(){
        customerRepository.saveAll(customers);
    }
}
