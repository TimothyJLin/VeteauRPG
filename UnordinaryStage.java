import javafx.scene.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
public class UnordinaryStage extends Application {
	private static Pane main=new Pane();
	ArrayList<Character> characterList=new ArrayList<Character>();
	ArrayList<Sprite> spriteList=new ArrayList<Sprite>();
//	Character player=new Character("generic name", "placeholder string name", 1000, 5, 3, 1, 2, 500, 700, 2.0);
	public static Character player;
	GameRunner renderLoop=new GameRunner(characterList, spriteList, this);
	private Timeline t;
    //final static Canvas canvas = new Canvas(30, 30);
    //final static GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

	public Parent startScene() throws IOException {
		
		main.setPrefSize(1000, 1000);
		player=new Character("test", this);
		Character arlo=new Character("arlo", this);
		Character blyke=new Character("blyke", this);
		characterList.add(player);
		characterList.add(arlo);
		characterList.add(blyke);
		System.out.println("before update game");
		renderLoop.updateGame();
		//characterList.clear();
		System.out.println("after update game");
		
		System.out.println("after game loop");
		//main.getChildren().clear();
		//arlo=new Character("Arlo", "C:\\Users\\Jerry\\eclipse-workspace\\unordinary\\sprites\\arlo.jpg", 1200, 5, 6, 4, 8, 200, 100, 6.5);
		
		
		return main;
	}
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(startScene());
		stage.setScene(scene);
		initTimeline();
		stage.setTitle("Unordinary battle arena");
		EventHandle eventHandle=new EventHandle();
		MouseEventHandle mouseHandle=new MouseEventHandle();
		scene.setOnKeyPressed(eventHandle);
		scene.setOnMousePressed(mouseHandle);
		
		stage.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
		
	private void initTimeline() {
		t = new Timeline(new KeyFrame(Duration.millis(10),
				ae -> renderLoop.updateGame()));
		t.setCycleCount(Animation.INDEFINITE);
		t.play();
		//lblDuration.setText(time);
		//TODO: Code this method
	
	}
		
	public Pane getMain() {
		return main;
	}
	
	public Character getCharacter() {
		return player;
	}
 }


/**
 * image code; unused for now
 */
		    /**
		    this.image = new Image(stream);
		 
		    ImageView imageView = new ImageView();
		      
		      imageView.setImage(image);
		      
		      imageView.setX(10);
		      imageView.setY(10);
		      imageView.setFitWidth(575);
		      imageView.setPreserveRatio(true);
		     
		    
			renderCharacter(image, xPosition, yPosition);
			**/