package com.mondiamedia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mondiamedia.enums.ContentType;
import com.mondiamedia.repositories.SubscriptionRepository;
import com.mondiamedia.service.SubscriptionService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/subscription")
@Api(value="Subscription",description="Subscription Endpoints")
public class SubscriptionController {
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	SubscriptionService subscriptionService;

	@RequestMapping(value="/createSubscription",method=RequestMethod.POST)
	public void createSubscribtion(@RequestParam("subscriptionTypeId") Long subscriptionTypeId) {
		subscriptionService.createSubscription(subscriptionTypeId);
	}
	
	@RequestMapping(value="/consumeSubscription",method=RequestMethod.PUT)
	public void consume(@RequestParam long subscriptionID,@RequestParam ContentType content) {
		subscriptionService.consumeSubscription(subscriptionID,content);
	}
	
}
