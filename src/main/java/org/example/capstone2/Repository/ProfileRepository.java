package org.example.capstone2.Repository;

import org.example.capstone2.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findProfileById(Integer id);
    Profile findProfileByContentId(Integer id);
}
