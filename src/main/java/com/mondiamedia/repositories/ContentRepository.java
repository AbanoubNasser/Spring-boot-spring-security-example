package com.mondiamedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mondiamedia.model.Subscription.Contingent;

@Repository
public interface ContentRepository extends JpaRepository<Contingent, Integer> {

}
