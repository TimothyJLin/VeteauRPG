import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

class Character {
		UnordinaryStage us;
		UnordinaryUtil util;
		String name;
		double health;
		double moveSpeed;
		double strength;
		double magicResist;
		double physicalResist;
		double xPosition;
		double yPosition;
		double abilityLevel;
		
		double movingToX;
		double movingToY;
		double rotation;
		Color color;
		
		CharacterSprite charSprite;
		
		//key: type of stat, value: level of stat
		//ie: key: abilityLevel, value: 6.5
		Dictionary<String, Double> dict = new Hashtable<String, Double>();
		
		InputStream stream;
		Image image;
		
		public Character(String name, UnordinaryStage us) throws IOException {
			BufferedReader br;
			this.us=us;
			File characterFile = new File ("C:\\Users\\Jerry\\eclipse-workspace\\unordinary\\src\\UnordinaryCharacters.txt");
			br = new BufferedReader(new FileReader(characterFile));
			findRightCharacterData(name, characterFile, br);
			insertCharacterDataToDict(br);
			this.setStats();
			movingToX=xPosition;
			movingToY=yPosition;
			rotation=-90;
			renderCharacter();
			util=new UnordinaryUtil(rotation, xPosition, yPosition);
		}
		
		//moves the bufferedReader to the right line
		//reads based on the name
		public void findRightCharacterData(String name, File characterFile, BufferedReader br) throws IOException {
			try {
				String readline;
				while ((readline=br.readLine())!=null) {
					if (readline.contains(name)) {
						break;
					} else {
					//	System.out.println("readline: "+readline);
						continue;
						
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		///inserts the data of the character attribute into a dictionary
		public void insertCharacterDataToDict(BufferedReader br) throws NumberFormatException, IOException {
			String characterAttribute;
			//System.out.println("now going to read attributs");
			while (!(characterAttribute = br.readLine()).contains("**")) {
				if (characterAttribute.contains("color")) {
					color=Color.web(characterAttribute.split(":")[1]);
				} else {
					
				//System.out.println("character attribute"+characterAttribute);
					dict.put(characterAttribute.split(":")[0], Double.parseDouble(characterAttribute.split(":")[1]));
			//		System.out.println("character attribute: "+characterAttribute.split(":")[0]+" "+ "value: "+characterAttribute.split(":")[1]);
				//System.out.println("attribute:"+characterAttribute.split(":")[0]+" number: "+characterAttribute.split(":")[1]);
				}
			}
			//System.out.println("FINISHED READING INFO");
			br.close();

		}
		
		//uses the dictionary to assign value to the stats
			//why use a dictionary at all? idk bruh
			//maybe so you don't have to run through that many if statements every iteration
			///I think it just looks nice tbh
		public void setStats() {
			health=dict.get("health");
			moveSpeed=dict.get("moveSpeed");
			strength=dict.get("strength");
			magicResist=dict.get("magicResist");
			physicalResist=dict.get("physicalResist");
			xPosition=dict.get("xPosition");
			yPosition=dict.get("yPosition");
			abilityLevel=dict.get("abilityLevel");
		}
		
		///renders the character
		public void renderCharacter() {
			updateCharacterDestination(movingToX, movingToY);
			
			charSprite=new CharacterSprite(xPosition, yPosition, rotation, color, us);
			//System.out.println("rotation: "+rotation);
			charSprite.renderCharacterSprite();
			

		}
		
		public void moveLeft() {
			xPosition-=moveSpeed;
		}
		
		public void moveRight() {
			xPosition+=moveSpeed;
		}
		
		public void moveUp() {
			yPosition+=moveSpeed;
		}
		
		public void moveDown() {
			yPosition-=moveSpeed;
		}
		
		public void setCharacterDestination(double mouseXPosition, double mouseYPosition) {
			movingToX=mouseXPosition;
			movingToY=mouseYPosition;
		}
		
		public void updateCharacterDestination(double mouseXPosition, double mouseYPosition) {
			System.out.println("rotation: "+rotation);
			double xLength=mouseXPosition-xPosition;
			double yLength=mouseYPosition-yPosition;
			
			double hypotenuse=Math.sqrt(xLength*xLength+yLength*yLength);
			rotation=calculateRotation(mouseXPosition, mouseYPosition);
			if (moveSpeed>=hypotenuse) {
				//System.out.println("JUMP");
			//	System.out.println("MOVE SPEED GREATER THAN HYPOTENUSE");
				xPosition=mouseXPosition;
				yPosition=mouseYPosition;
				return;
			} else {
				if (xLength>=0) {
					xPosition+=newXCoord(mouseXPosition, hypotenuse, xLength);
					
				} else {
					xPosition-=newXCoord(mouseXPosition, hypotenuse, xLength);
				}
				if (yLength>=0) {
					yPosition+=newYCoord(mouseYPosition, hypotenuse, yLength);
				} else {
					yPosition-=newYCoord(mouseYPosition, hypotenuse, yLength);
				}
			//	System.out.println("player xCoord: "+xPosition);
				//System.out.println("player xMOUSE: "+mouseXPosition);
				//System.out.println("player yCoord: "+yPosition);
				//System.out.println("player yMOUSE: "+mouseYPosition);
			}
			
		}
		
		public double calculateRotation(double mouseXPosition, double mouseYPosition) {
			double xLength=mouseXPosition-xPosition;
			double yLength=mouseYPosition-yPosition;
			if (xLength==0) {
				return rotation;
			}
			double tangent=yLength/xLength;
			if (mouseXPosition>xPosition) {
				rotation=Math.toDegrees(Math.atan(tangent));
				return rotation;
				
			} else {
				rotation = Math.toDegrees(Math.atan(tangent))+180;
				return rotation;
			}
			
		
		}
		
		public void punch() {
			charSprite.punch(true);
		}
		
		public double newXCoord(double mouseXPosition, double hypotenuse, double xLength) {
			double triangleRatio=moveSpeed/hypotenuse;
			double xMovement=Math.abs(triangleRatio*xLength);
			if (xMovement>=Math.abs(xLength)) {
				return xLength;
			}
			return xMovement;
		}
		
		//remember y is inverted
		public double newYCoord(double mouseYPosition, double hypotenuse, double yLength) {
			double triangleRatio=moveSpeed/hypotenuse;
			double yMovement=Math.abs(triangleRatio*yLength);
			if (yMovement>=Math.abs(yLength)) {
				return yLength;
			}
			return yMovement;
		}
		
		public double getXPosition() {
			return xPosition;
		}
		
		public double getMovingToX() {
			return movingToX;
		}
}