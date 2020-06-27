package com.actions.clubActions;

import com.actions.Action;
import com.entities.Club;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowFullClubInfoAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj id klubu");
		
		String attached = p.findAttached(id, Club.class);
		cv.print(attached);
	}

	@Override
	public String getName() {
		return "ShowFullClubInfo";
	}

}
