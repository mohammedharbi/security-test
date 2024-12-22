package com.example.jparelationi.Repository;

import com.example.jparelationi.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

    Merchant findMerchantById(Integer id);
}
