package com.rodolfosaturnino.captable.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ownership {

	private String investor;
	private Long shares;
	private BigDecimal cashPaid;
	private BigDecimal ownership;
	
	public Ownership() { }
	
	public Ownership(String name, Long shares, BigDecimal cash, Long sharesOwned) {
		this.investor = name;
		this.shares = shares;
		this.cashPaid = cash;
		this.ownership = new BigDecimal(sharesOwned);
	}

	public String getInvestor() {
		return investor;
	}

	public Long getShares() {
		return shares;
	}

	public BigDecimal getCashPaid() {
		return cashPaid;
	}

	public BigDecimal getOwnership() {
		return ownership;
	}
	
	public void addAnotherBuy(Long shares, BigDecimal cashPaid, Long totalShares) {
		this.shares = Long.sum(this.shares, shares);
		this.cashPaid = this.cashPaid.add(cashPaid);
		this.ownership = new BigDecimal(this.shares).divide(new BigDecimal(totalShares), 2, RoundingMode.UP);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((investor == null) ? 0 : investor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ownership other = (Ownership) obj;
		if (investor == null) {
			if (other.investor != null)
				return false;
		} else if (!investor.equals(other.investor))
			return false;
		return true;
	}

}
