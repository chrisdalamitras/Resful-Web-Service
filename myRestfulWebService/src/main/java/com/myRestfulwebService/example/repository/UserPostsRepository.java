package com.myRestfulwebService.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestfulwebService.example.model.UserPosts;

@Repository
public interface UserPostsRepository extends JpaRepository<UserPosts, Integer> {

}
