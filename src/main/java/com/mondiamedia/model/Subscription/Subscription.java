package com.mondiamedia.model.Subscription;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mondiamedia.model.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUBSCRIPTION_ID")
	private long subscriptionId;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUBSCRIPTION_TYPE_ID")
	private SubscriptionType subscriptionType;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="subscription",cascade = CascadeType.ALL)
	private Set<ConsumedContingent> comsumedContingents =new HashSet<>();
}
