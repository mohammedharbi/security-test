package com.example.jparelationi.Repository;

import com.example.jparelationi.Model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    Branch findBranchById(Integer id);

    Branch findBranchByNumber(Integer number);
}
