package com.example.jparelationi.Service;

import com.example.jparelationi.Api.ApiException;
import com.example.jparelationi.Model.Customer;
import com.example.jparelationi.Model.Merchant;
import com.example.jparelationi.Repository.CustomerRepository;
import com.example.jparelationi.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final CustomerRepository customerRepository;

    public List<Merchant> findAllMerchant() {
        return merchantRepository.findAll();
    }
    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }
    public void updateMerchant(Integer id, Merchant merchant) {
        Merchant merchant1 = merchantRepository.findMerchantById(id);

        if (merchant1 != null) {
            merchant1.setName(merchant.getName());
            merchantRepository.save(merchant1);
        }else throw new ApiException("Merchant not found");
    }
    public void deleteMerchant(Integer id) {
        Merchant merchant = merchantRepository.findMerchantById(id);

        if (merchant != null) {
            merchantRepository.delete(merchant);
        }else throw new ApiException("Merchant not found");
    }

    public void assignMerchantToCustomer(Integer merchant_id, Integer customer_id) {
        Merchant merchant = merchantRepository.findMerchantById(merchant_id);
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (merchant == null || customer == null) {
            throw new ApiException("Merchant or Customer not found");
        }

        merchant.getCustomers().add(customer);
        customer.getMerchants().add(merchant);
        merchantRepository.save(merchant);
        customerRepository.save(customer);
    }


}
