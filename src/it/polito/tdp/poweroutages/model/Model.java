package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List< Blackout> blackoutList = new ArrayList<Blackout>();
	private List<Blackout> best;
	private int customerBest;
	
	private int anniMax;
	private int oreMax;
	private int oreTot;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public String getRisultato(Nerc nercScelto, int anni, int ore) {
		
		String risultato = "";
		anniMax = anni;
		oreMax = ore;
		oreTot = 0;
		
		best = new ArrayList<Blackout>();
		customerBest = 0;
		blackoutList = podao.getAllBlackoutNerc(nercScelto);
		
		List<Blackout> parziale = new ArrayList<Blackout>();
		recursive(parziale,0, 0, 0);
		
		risultato += "Tot people affected " + customerBest + "\n" + "Tot hours outages "+ oreTot/60 +"\n";
		for(Blackout b: best) {
			risultato = risultato + b.getDateEventBegan().getYear() +" "+ b.getDateEventBegan() +" "+b.getDateEventFinished() +" "+ b.getId() +" "+ b.getCustomerAffected() +"\n";
		}
		
		return risultato.trim();
	}

	private void recursive(List<Blackout> parziale, int customer, int ore, int i) {
		
		if(customer > customerBest) {
			best = new ArrayList<Blackout>(parziale);
			customerBest = customer;
			oreTot = ore;
		}
			
		for(Blackout b: blackoutList) {
			
			int oreBlackout =  (int)Duration.between(b.getDateEventBegan(), b.getDateEventFinished()).toMinutes();
			if(( ore +oreBlackout) <= oreMax*60 && this.calcolaAnno(b, parziale) == true && (parziale.size() <1 || b.getDateEventBegan().isAfter( parziale.get(parziale.size()-1).getDateEventBegan()))) {
				parziale.add(b);
				customer += b.getCustomerAffected();
				ore += oreBlackout;
				recursive(parziale, customer, ore, i+1);
				
				parziale.remove(b);
				customer = customer - b.getCustomerAffected();
				ore = ore - oreBlackout;
		}
		}
	}

	private boolean calcolaAnno(Blackout b, List<Blackout> parziale) {
		
		if (parziale.size()<1)
			return true;
		else {
			if(b.getDateEventBegan().getYear() - parziale.get(parziale.size()-1).getDateEventBegan().getYear() <= anniMax)
				return true;
		}
		return false;
	}

	


}
