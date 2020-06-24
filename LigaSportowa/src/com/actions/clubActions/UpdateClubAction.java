package com.actions.clubActions;

import com.actions.Action;
import com.entities.Club;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateClubAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj Id klubu");
		
		String name = cv.read("podaj nazwê klubu");
		
		if(!"".equals(name)) {
			p.update(id, Club.class, "name", name);
		}
	}

	@Override
	public String getName() {
		return "UpdateClub";
	}

}
