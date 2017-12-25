package com.mondiamedia.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mondiamedia.enums.ContentType;
import com.mondiamedia.model.Subscription.ConsumedContingent;

@Repository
public interface ConsumedContingentRepository extends JpaRepository<ConsumedContingent, Long> {

	public ConsumedContingent findBySubscription_subscriptionIdAndType(Long subscriptionId,ContentType content);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE  consumedcontingent SET REMAINING_AMOUNT=REMAINING_AMOUNT-1 where id=:consumedContingentID", nativeQuery = true)
	void consumeContingentSubscription(@Param("consumedContingentID") long consumedContingentID);
}
