package com.rodolfosaturnino.captable.domain;

import java.math.BigDecimal;
import java.util.List;

public class Output {

	private String date;
	private BigDecimal cash_raised;
	private Long shares_sold;
	private List<Ownership> ownership;
	
	public Output() { }

	public Output(String date, BigDecimal cashRaised, Long sharesSold) {
		this.date = date;
		this.cash_raised = cashRaised;
		this.shares_sold = sharesSold;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getCash_raised() {
		return cash_raised;
	}

	public void setCash_raised(BigDecimal cash_raised) {
		this.cash_raised = cash_raised;
	}

	public Long getShares_sold() {
		return shares_sold;
	}

	public void setShares_sold(Long shares_sold) {
		this.shares_sold = shares_sold;
	}

	public List<Ownership> getOwnership() {
		return ownership;
	}

	public void setOwnership(List<Ownership> ownership) {
		this.ownership = ownership;
	}
}
