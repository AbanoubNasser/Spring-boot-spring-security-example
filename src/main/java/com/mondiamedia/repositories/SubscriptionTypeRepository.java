package com.mondiamedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mondiamedia.model.Subscription.SubscriptionType;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository <SubscriptionType, Long>{

	public SubscriptionType findBySubscriptionTypeID(long id);
}
