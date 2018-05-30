package com.myRestfulwebService.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestfulwebService.example.model.UserObject;

@Repository
public interface UserObjectRepository extends JpaRepository<UserObject, Integer> {

}
