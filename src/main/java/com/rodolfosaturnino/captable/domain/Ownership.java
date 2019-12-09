package com.rodolfosaturnino.captable.domain;

import java.math.BigDecimal;

public class Ownership {

	private String investor;
	private Long shares;
	private BigDecimal cashPaid;
	private Double ownership;
	
	public Ownership() { }
	
	public Ownership(String name, Long shares, BigDecimal cash) {
		this.investor = name;
		this.shares = shares;
		this.cashPaid = cash;
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

	public void addAnotherBuy(Long shares, BigDecimal cashPaid) {
		this.shares = Long.sum(this.shares, shares);
		this.cashPaid = this.cashPaid.add(cashPaid);
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

	public Double getOwnership() {
		return ownership;
	}

	public void setOwnership(Double ownership) {
		this.ownership = ownership;
	}
}
