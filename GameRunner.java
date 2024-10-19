import java.util.ArrayList;

import javafx.animation.AnimationTimer;



public class GameRunner {
	
	ArrayList<Character> characterList;
	ArrayList<Sprite> spriteList;
	UnordinaryStage us;

	
	public GameRunner(ArrayList<Character> characterList, ArrayList<Sprite> spriteList, UnordinaryStage us) {
		this.characterList=characterList;
		this.spriteList=spriteList;
		this.us=us;
		//System.out.println("the game is epic running");
	}
	
	
	
	public void runGameLoop() {
	
	
		while (true) {
			//System.out.println("LOOP RUNNING");
			updateGame();
			  
			//System.out.println(main.getChildren().size());

			try {
				Thread.sleep(5000);
				//System.out.println("success");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	public void updateGame() {
		
		us.getMain().getChildren().clear();

		//System.out.println("character list size:" +characterList.size());
		for (int i=0; i<characterList.size(); i++) {
			characterList.get(i).renderCharacter();
		}
		for (int j=0; j<spriteList.size(); j++) {
			//render sprite
		}
		
	}
	

}