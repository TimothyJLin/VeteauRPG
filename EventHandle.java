import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EventHandle implements EventHandler<KeyEvent> {
	public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
        	case Q:
        		System.out.println("q key pressed!");
        	case W:
        	case E:
        	case R:    	
        }
	}
}
