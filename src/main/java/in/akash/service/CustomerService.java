package in.akash.service;

import in.akash.entity.CustomerEntity;
import in.akash.entity.LoginRequest;
import in.akash.model.CustomerProfile;
import in.akash.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public boolean registerCustomer(CustomerEntity customer) {
        if (customerRepository.existsById(customer.getPhoneNumber())) {
            return false;
        }
        customerRepository.save(customer);
        return true;
    }

    public boolean loginCustomer(LoginRequest loginRequest) {
        if (customerRepository.checkLogin(loginRequest.getPhoneNumber(), loginRequest.getPassword()) > 0) {
            return true;
        }
        return false;
    }

    public CustomerEntity readCustomer(Long phoneNumber) {
        return customerRepository.findById(phoneNumber).orElse(null);
    }


}