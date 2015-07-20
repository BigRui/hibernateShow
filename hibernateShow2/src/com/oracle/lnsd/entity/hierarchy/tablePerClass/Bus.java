package com.oracle.lnsd.entity.hierarchy.tablePerClass;

import javax.persistence.Entity;

@Entity
public class Bus extends Vehicle {
	private Float ticketPrice; //Æ±¼Û
	private Integer stationCount;//Õ¾Êý
	
	
	public Float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Integer getStationCount() {
		return stationCount;
	}

	public void setStationCount(Integer stationCount) {
		this.stationCount = stationCount;
	}

	private static final long serialVersionUID = -5497147065091045725L;
}
