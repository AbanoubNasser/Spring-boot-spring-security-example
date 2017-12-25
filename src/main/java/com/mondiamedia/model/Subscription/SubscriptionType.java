package com.mondiamedia.model.Subscription;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mondiamedia.enums.SubscriptionTypeCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subscriptionType")
public class SubscriptionType{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUBSCRIPTION_TYPE_ID")
	private long subscriptionTypeID;
	
	@Column(name="SUBSCRIPTION_NAME")
	private String name;
	
	@Column(name="DURATION")
	private int duration;
	
	@Column(name="SUBSCRIPTION_TYPE")
	@Enumerated(EnumType.STRING)
	private SubscriptionTypeCategory type;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="subscriptionType",cascade = CascadeType.ALL)
	private Set<Contingent> contingents;
}
