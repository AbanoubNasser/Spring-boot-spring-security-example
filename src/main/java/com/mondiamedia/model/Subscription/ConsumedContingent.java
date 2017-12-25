package com.mondiamedia.model.Subscription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Getter
@Setter
@Entity
@Table(name="consumedContingent")
public class ConsumedContingent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column(name="REMAINING_AMOUNT")
	@Min(0)
	private int remainingAmount;
	
	@Column(name="CONTENT_TYPE")
	@Enumerated(EnumType.STRING)
	private ContentType type;
	
	@ManyToOne
	@JoinColumn(name="SUBSCRIPTION_ID")
	@JsonIgnore
	private Subscription subscription;
}
