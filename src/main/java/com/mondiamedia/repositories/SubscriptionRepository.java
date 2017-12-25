package com.mondiamedia.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mondiamedia.model.Subscription.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	
	 @Query("SELECT s FROM Subscription s WHERE " +
	            "s.startDate <=:subDate and " +
	            "s.endDate >=:subDate and s.user.username =:username order by s.startDate ")
	 List<Subscription> findByUsernameAndGreaterThanEqualstartDateAndLessThanEqualEndDate(@Param("username") String username,@Param("subDate")Date consumingDate);
}
