package com.actions.clubActions;

import com.actions.Action;
import com.entities.Club;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteClubAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID klubu");
		p.delete(id, Club.class);
	}
	
	@Override
	public String getName() {
		return "DeleteClub";
	}
}
