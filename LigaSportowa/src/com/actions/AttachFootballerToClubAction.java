package com.actions;

import com.entities.Club;
import com.entities.Footballer;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttachFootballerToClubAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer footballerId = cv.getValidInt("Podaj ID zwodnika");
		Integer clubId = cv.getValidInt("podaj ID klubu");
		
		p.attach(footballerId, clubId, Footballer.class, Club.class);
	}

	@Override
	public String getName() {
		return "AttachFootballerToClub";
	}

}
