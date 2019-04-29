package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class Blackout {
	private int id;
	private int nercID;
	private int customerAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	
	public Blackout(int id, int nercID, int customerAffected, LocalDateTime dateEventBegan,
			LocalDateTime dateEventFinished) {
		super();
		this.id = id;
		this.nercID = nercID;
		this.customerAffected = customerAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNercID() {
		return nercID;
	}
	public void setNercID(int nercID) {
		this.nercID = nercID;
	}
	public int getCustomerAffected() {
		return customerAffected;
	}
	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}
	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}
	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}
	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}
	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	
	

}
