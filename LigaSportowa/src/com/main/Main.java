package com.main;

import java.util.ArrayList;
import java.util.List;

import com.actions.Action;
import com.actions.ExitAction;
import com.actions.clubActions.CreateClubAction;
import com.actions.clubActions.DeleteClubAction;
import com.actions.clubActions.ShowAllClubsAction;
import com.actions.clubActions.UpdateClubAction;
import com.persistenceManager.Neo4jPersistence;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

public class Main {

	private static List<Action> actions;
	private static ConsoleView cv;
	
	public static void main(String[] args) {
		
		init();
		
		while (true) {
			cv.print("Lista dostępnych akcji:");
			showActions();
			cv.print("");
			cv.print("Podaj akcję");
			runAction(cv.read());
		}
		
	}
	
	public static void init() {
		
		actions = new ArrayList<Action>();
		cv = new ConsoleView();
		
		Persistence p = new Neo4jPersistence();

		actions.add(new CreateClubAction(p, cv));
		actions.add(new ShowAllClubsAction(p, cv));
		actions.add(new UpdateClubAction(p, cv));
		actions.add(new DeleteClubAction(p, cv));
		
		actions.add(new ExitAction(p, cv));
		
	}
	
	private static void runAction(String name) {
		for (Action a : actions) {
			if (name.equals(a.getName())) {
				launchAction(a);
				return;
			}
		}
		cv.print("Nie ma takiej akcji: " + name);
	}
	
	private static void launchAction(Action a) {
			a.launch();
	}
	
	private static void showActions() {
		for (Action a : actions) {
			cv.print(" " + a.getName());
		}
	}
}
