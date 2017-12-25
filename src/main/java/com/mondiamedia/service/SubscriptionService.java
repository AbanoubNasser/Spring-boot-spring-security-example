package com.mondiamedia.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.mondiamedia.enums.ContentType;
import com.mondiamedia.exception.SubscriptionException;
import com.mondiamedia.model.Subscription.ConsumedContingent;
import com.mondiamedia.model.Subscription.Contingent;
import com.mondiamedia.model.Subscription.Subscription;
import com.mondiamedia.model.Subscription.SubscriptionType;
import com.mondiamedia.repositories.ConsumedContingentRepository;
import com.mondiamedia.repositories.SubscriptionRepository;
import com.mondiamedia.repositories.SubscriptionTypeRepository;
import com.mondiamedia.repositories.UserRepository;
import com.mondiamedia.util.DateUtil;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionTypeRepository subscriptionTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private ConsumedContingentRepository consumedContingentRepository;

	/**
	 * CreateSubscription is used to create subscription for current session user
	 * with specific subscription type
	 * 
	 * @param subscriptionTypeId
	 * @return
	 */
	public void createSubscription(long subscriptionTypeId) {
		SubscriptionType subscriptionType = subscriptionTypeRepository.findBySubscriptionTypeID(subscriptionTypeId);
		Subscription subscription = new Subscription();
		User user = (User) org.springframework.security.core.context.SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		subscription.setUser(userRepository.findByUsername(user.getUsername()));
		subscription.setSubscriptionType(subscriptionType);
		subscription.setStartDate(new Date());
		subscription.setEndDate(DateUtil.addDays(subscription.getStartDate(), subscriptionType.getDuration()));
		for (Contingent con : subscriptionType.getContingents()) {
			ConsumedContingent consumedContingent = new ConsumedContingent();
			consumedContingent.setRemainingAmount(con.getContingentAmount());
			consumedContingent.setType(con.getContentType());
			consumedContingent.setSubscription(subscription);
			subscription.getComsumedContingents().add(consumedContingent);
		}
		subscriptionRepository.save(subscription);
	}

	/**
	 * Consume credit valid subscription of current logged in user
	 */
	public void consumeSubscription(long subscriptionID, ContentType content) {

		ConsumedContingent consumedCon = consumedContingentRepository
				.findBySubscription_subscriptionIdAndType(subscriptionID, content);
		if(consumedCon!=null) {
			consumedContingentRepository.consumeContingentSubscription(consumedCon.getId());
		}else {
			throw new SubscriptionException(HttpStatus.NOT_FOUND, "No Content type "+content+" for subscription id "+subscriptionID);
		}

	}
}
