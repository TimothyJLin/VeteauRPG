import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MouseEventHandle implements EventHandler<MouseEvent> {
	UnordinaryStage us=new UnordinaryStage();
	public void handle(MouseEvent mouseEvent) {
		if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
			System.out.println("left mouse clicked");
			us.player.punch();
		}
		else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
			us.player.setCharacterDestination(mouseEvent.getX(), mouseEvent.getY());
			
			//System.out.println(us.player.getMovingToX());
//			player.moveToMouse(mouseEvent.getX(), mouseEvent.getY());
			//System.out.println("right mouse clicked");
		//	us.getMain().getChildren().clear();
			//main.getChildren().clear();
			//renderLoop.updateGame();
		}
		
	}
	
}