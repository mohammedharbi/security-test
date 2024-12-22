package com.example.jparelationi.Repository;

import com.example.jparelationi.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findProfileById(Integer id);
}
