import javafx.scene.Group; 
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

public class CharacterSprite {
    Rotate characterRotate;
	double xPosition;
	double yPosition;
	double rotation;
	Color color;
	double frontX;
	double frontY;
	Circle mainCircle;
	Circle rightArmCircle; 
	Circle leftArmCircle;
	
	UnordinaryStage us;
	UnordinaryUtil util;
	public CharacterSprite(double xCoord, double yCoord, double rotation, Color color, UnordinaryStage us) {
		characterRotate=new Rotate(rotation+90, xCoord, yCoord);
		xPosition=xCoord;
		yPosition=yCoord;
		this.rotation=rotation;
		this.color=color;
		this.us=us;
		util=new UnordinaryUtil(this.rotation, xPosition, yPosition);
		
	}
	public void renderCharacterSprite() {
		Group spriteGroup = new Group();
		mainCircle = new Circle(xPosition, yPosition, 10, color);
		rightArmCircle = new Circle(xPosition+10, yPosition-5, 5, color);
		leftArmCircle = new Circle(xPosition-10, yPosition-5, 5, color);
		Rectangle frontRectangle=new Rectangle(10, 10, Color.BLUE);
		frontRectangle.setX(xPosition);
		frontRectangle.setY(yPosition);
		//frontRectangle.setWidth(50);
		//frontRectangle.setHeight(60);
		spriteGroup.getChildren().addAll(mainCircle, rightArmCircle, leftArmCircle, frontRectangle);
/*        
		rightArmCircle.rotateProperty().bind(mainCircle.rotateProperty());
		leftArmCircle.rotateProperty().bind(mainCircle.rotateProperty());
		mainCircle.getTransforms().add(characterRotate);
	
		*/
//		rightArmCircle.getTransforms().add(characterRotate);
	//	leftArmCircle.getTransforms().add(characterRotate);
		
		//mainCircle.setFill(color);
		
		//System.out.println("xPosition: "+xPosition+"ypos: "+yPosition+"colo: "+color);
		spriteGroup.getTransforms().add(characterRotate);
		
		us.getMain().getChildren().addAll(spriteGroup);
        //us.getMain().getChildren().addAll(mainCircle, rightArmCircle, leftArmCircle);
		
		//us.characterList.add(this);

	}
	
	public void punch(boolean leftArmUsed) {
		if (leftArmUsed) {
			leftArmCircle.setCenterX(util.forwardX(10));
			leftArmCircle.setCenterY(util.forwardY(10));
		} else {
			rightArmCircle.setCenterX(util.forwardX(10));
			rightArmCircle.setCenterY(util.forwardY(10));
		}
		
	}
	
}