package com.mondiamedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mondiamedia.model.Subscription.Contingent;
import com.mondiamedia.model.Subscription.SubscriptionType;
import com.mondiamedia.repositories.SubscriptionTypeRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/SubscriptionType")
@Api(value="Subscription Type",description="Subscription type Endpoints")
public class SubscriptionTypeController {
	
	@Autowired
	private SubscriptionTypeRepository subscriptionTypeRepository;
	
	
	@RequestMapping(value="/createSubscriptionType",method=RequestMethod.POST)
	public ResponseEntity<SubscriptionType> createContigentSubscriptionType(@RequestBody SubscriptionType subscriptionType) {
		for(Contingent con:subscriptionType.getContingents()) {
			con.setSubscriptionType(subscriptionType);
		}
		subscriptionTypeRepository.save(subscriptionType);
		return ResponseEntity.ok(subscriptionType);
	}
	
	@RequestMapping(value="/getSubscriptionTypes" ,method=RequestMethod.GET)
	public List<SubscriptionType> getSubscriptionTypes(){
		return subscriptionTypeRepository.findAll();
	}
}
