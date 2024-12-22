package com.example.jparelationi.Service;

import com.example.jparelationi.Api.ApiException;
import com.example.jparelationi.DTO.BranchDTO;
import com.example.jparelationi.DTO.CustomerDTO;
import com.example.jparelationi.Model.Customer;
import com.example.jparelationi.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    public void updateCustomer(Integer id,Customer customer) {
        Customer customer1 = customerRepository.findCustomerById(id);

        if (customer1 != null) {
            customer1.setName(customer.getName());
            customer1.setEmail(customer.getEmail());
            customerRepository.save(customer1);
        }else throw new ApiException("Customer not found");
    }
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);

        if (customer != null) {
            customerRepository.delete(customer);
        }else throw new ApiException("Customer not found");
    }

    public List<CustomerDTO> findCustomers() {
        List<Customer> customer1 = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : customer1) {
            CustomerDTO customerDTO= new CustomerDTO(customer.getName());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

}
