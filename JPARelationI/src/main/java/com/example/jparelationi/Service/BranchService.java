package com.example.jparelationi.Service;

import com.example.jparelationi.Api.ApiException;
import com.example.jparelationi.DTO.BranchDTO;
import com.example.jparelationi.Model.Branch;
import com.example.jparelationi.Model.Merchant;
import com.example.jparelationi.Repository.BranchRepository;
import com.example.jparelationi.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final MerchantRepository merchantRepository;

    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }
    // flow 1
//    public void addBranch(Integer merchant_id, Branch branch) {
//        Merchant merchant = merchantRepository.findMerchantById(merchant_id);
//        if (merchant == null) {throw new ApiException("merchant not found");}
//
//
//        branch.setMerchant(merchant);
//        branchRepository.save(branch);
    //}
    public void addBranch(Branch branch) {
        branchRepository.save(branch);
    }
    public void assignMerchantToBranch(Integer merchantId, Integer branchId) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        Branch branch = branchRepository.findBranchById(branchId);
        if (merchant== null || branch == null) {throw new ApiException("merchant or branch is null");}

        branch.setMerchant(merchant);
        branchRepository.save(branch);
    }
    public void updateBranch(Integer id, Branch branch) {
        Branch findBranch = branchRepository.findBranchById(id);

        if (findBranch != null) {
            findBranch.setArea(branch.getArea());
            branchRepository.save(findBranch);
        }else throw new ApiException("Branch not found");
    }
    public void deleteBranch(Integer id) {
        Branch findBranch = branchRepository.findBranchById(id);
        if (findBranch != null) {
            branchRepository.delete(findBranch);
        }else throw new ApiException("Branch not found");
    }

    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        List<BranchDTO> branchDTOs = new ArrayList<>();
        for (Branch branch : branches) {
            BranchDTO branchDTO= new BranchDTO(branch.getArea(), (Set<Merchant>) branch.getMerchant());
            branchDTOs.add(branchDTO);
        }
        return branchDTOs;
    }

    public BranchDTO getBranchByNumber(Integer branch_number) {
        Branch branch = branchRepository.findBranchByNumber(branch_number);
        if (branch == null) {
            throw new ApiException("Branch not found");
        }
        BranchDTO branchDTO = new BranchDTO(branch.getArea(), (Set<Merchant>) branch.getMerchant());
        return branchDTO;
    }

}
