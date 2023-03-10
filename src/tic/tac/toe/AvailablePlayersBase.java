package tic.tac.toe;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AvailablePlayersBase extends AnchorPane {
    public static OnLineGameBoard boardGameOnline;
    protected final ImageView imageView;
    protected final Rectangle rectangle;
    protected final Button backBtn;
    protected final ImageView imageView0;
    protected final Rectangle rectangle0;
    protected final Text text;
    protected static ListView availablePlayerslistView=new ListView();
    public  static ArrayList<String> avaliable ;
    public static boolean closeInvite=false,checkforclose=false;
    
    public AvailablePlayersBase() {
        
        TicTacToe.player.stop();
        TicTacToe.player=new MediaPlayer(new Media(getClass().getResource("/sounds/serveronline.mp3").toExternalForm()));
        TicTacToe.player.play();

        avaliable=new ArrayList<>();

        imageView = new ImageView();
        rectangle = new Rectangle();
        backBtn = new Button();
        imageView0 = new ImageView();
        rectangle0 = new Rectangle();
        text = new Text();
        availablePlayerslistView = new ListView();
        
            try {
                LoginFXMLBase.playerConnection.sendMessage("Avaliable");
            } catch (Exception ex) {
                Logger.getLogger(MainPageScreenBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        setId("AnchorPane");
        setPrefHeight(480.0);
        setPrefWidth(750.0);
        getStyleClass().add("mainFxmlClass");
        getStylesheets().add("/tic/tac/toe/css/available%20players.css");

        imageView.setFitHeight(480.0);
        imageView.setFitWidth(750.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("Photos/background.jpg").toExternalForm()));

        rectangle.setArcHeight(100.0);
        rectangle.setArcWidth(100.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#2a47ad"));
        rectangle.setHeight(390.0);
        rectangle.setLayoutX(139.0);
        rectangle.setLayoutY(58.0);
        rectangle.setStroke(javafx.scene.paint.Color.TRANSPARENT);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(473.0);

        backBtn.setLayoutX(12.0);
        backBtn.setLayoutY(398.0);
        backBtn.setMnemonicParsing(false);
        backBtn.setPrefHeight(70.0);
        backBtn.setPrefWidth(70.0);
        backBtn.getStyleClass().add("backBtn");
        backBtn.getStylesheets().add("/tic/tac/toe/css/available%20players.css");
        backBtn.setOnAction(e->{
             
             TicTacToe.scene.setRoot(new MainPageScreenBase());
         
         });
        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(70.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("Photos/back.png").toExternalForm()));
        backBtn.setGraphic(imageView0);

        rectangle0.setArcHeight(50.0);
        rectangle0.setArcWidth(50.0);
        rectangle0.setFill(javafx.scene.paint.Color.valueOf("#ffffff5f"));
        rectangle0.setHeight(58.0);
        rectangle0.setLayoutX(174.0);
        rectangle0.setLayoutY(21.0);
        rectangle0.setStroke(javafx.scene.paint.Color.TRANSPARENT);
        rectangle0.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle0.setWidth(403.0);

        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setLayoutX(194.0);
        text.setLayoutY(65.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Available Players");
        text.setFont(new Font("Serif Regular", 51.0));

        availablePlayerslistView.setLayoutX(157.0);
        availablePlayerslistView.setLayoutY(114.0);
        availablePlayerslistView.setPrefHeight(306.0);
        availablePlayerslistView.setPrefWidth(437.0);
        availablePlayerslistView.getStyleClass().add("mylistview");
        availablePlayerslistView.getStylesheets().add("/tic/tac/toe/css/available%20players.css");
        
        getChildren().add(imageView);
        getChildren().add(rectangle);
        getChildren().add(backBtn);
        getChildren().add(rectangle0);
        getChildren().add(text);
        getChildren().add(availablePlayerslistView);
        

    }
    
    public static void preperList(String name){
    
        ItemBase itemBase = new ItemBase();
        itemBase.playerNameText.setText(name);
        availablePlayerslistView.getItems().add(itemBase);
        availablePlayerslistView.refresh();
    }
    
    public static void showDialog(String name ,String PlayerIdOfTheInvitation){
    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.NONE,"Attention",ButtonType.OK,ButtonType.CANCEL); 
        alert.setTitle("Attention");
        alert.setContentText("you are invited to play with "+name+"\n"+"if you accept the invitation press ok button");
         Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                if (alert.isShowing()) {
                    if(!checkforclose){
                        Platform.runLater(() -> alert.close());
                       checkforclose=true;
                    }
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        alert.showAndWait().ifPresent(rs->{
            if(rs==ButtonType.OK){
                if(!checkforclose){
                    String repleyMessage="acceptInvitation,"+PlayerIdOfTheInvitation;
                    LoginFXMLBase.playerConnection.sendMessage(repleyMessage);
                    checkforclose=true;
                }else checkforclose=false;
                        
            }
            else{
                if(!checkforclose){
                    String repleyMessage="rejectInvitation,"+PlayerIdOfTheInvitation;
                    LoginFXMLBase.playerConnection.sendMessage(repleyMessage);
                    checkforclose=true;
                }else checkforclose=false;
            }
           

        });
    });
    }

    public static void removeFromList(String name)
    {
        for (Object item : availablePlayerslistView.getItems()) {
            if(((ItemBase)item).playerNameText.getText().equals(name))
            {
                availablePlayerslistView.getItems().remove(item);
                availablePlayerslistView.refresh();
                break;
            }
        }
    }

}
