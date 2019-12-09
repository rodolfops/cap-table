package com.rodolfosaturnino.captable.domain;

import java.math.BigDecimal;

public class Company {

	private Long sharesSold;
	private BigDecimal cashRaised;

	public Company() {
		this.sharesSold = new Long(0);
		this.cashRaised = new BigDecimal("0.00");
	}

	private static Company single_instance = null;

	public static Company getInstance() {
		if (single_instance == null) {
			single_instance = new Company();
		}

		return single_instance;
	}

	public void sellShares(Long shares, BigDecimal cash) {
		this.sharesSold = Long.sum(this.sharesSold, shares);
		this.cashRaised = this.cashRaised.add(cash);
	}

	public Long getSharesSold() {
		return sharesSold;
	}

	public BigDecimal getCashRaised() {
		return cashRaised;
	}
}
