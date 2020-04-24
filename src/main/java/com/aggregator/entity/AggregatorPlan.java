package com.aggregator.entity;

public class AggregatorPlan {

	private String planName;
	private String planId;
	private String providerName;
	private int planCoverage;
	
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public int getPlanCoverage() {
		return planCoverage;
	}
	public void setPlanCoverage(int planCoverage) {
		this.planCoverage = planCoverage;
	}
	public AggregatorPlan(String planName, String planId, String providerName, int planCoverage) {
		super();
		this.planName = planName;
		this.planId = planId;
		this.providerName = providerName;
		this.planCoverage = planCoverage;
	}
	public AggregatorPlan() {
		
	}
	@Override
	public String toString() {
		return "AggregatorPlan [planName=" + planName + ", planId=" + planId + ", providerName=" + providerName
				+ ", planCoverage=" + planCoverage + "]";
	}
	
	

}
