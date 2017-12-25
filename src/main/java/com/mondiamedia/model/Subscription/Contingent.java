package com.mondiamedia.model.Subscription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondiamedia.enums.ContentType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="contingent")
public class Contingent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CONTINGENT_ID")
	private int contingentId;
	
	@Column(name="CONTINGENT_AMOUNT")
	@Min(value=1)
	private int contingentAmount;

	@Column(name="CONTENT_TYPE")
	@Enumerated(EnumType.STRING)
	private ContentType contentType;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUBSCRIPTION_TYPE_ID")
	@JsonIgnore
	private SubscriptionType subscriptionType;
	
}
