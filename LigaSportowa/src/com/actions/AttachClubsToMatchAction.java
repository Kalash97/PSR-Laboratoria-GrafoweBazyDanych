package com.actions;

import com.entities.Club;
import com.entities.Match;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttachClubsToMatchAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer matchId = cv.getValidInt("podaj ID meczu");
		Integer homeId = cv.getValidInt("podaj ID gospodarzy");
		Integer guestId = cv.getValidInt("podaj ID goœci");
		
		p.attach(matchId, homeId, Match.class, Club.class);
		p.attach(matchId, guestId, Match.class, Club.class);
	}

	@Override
	public String getName() {
		return "AttachClubsToMatch";
	}

}
