package com.mondiamedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mondiamedia.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String userName);
}
