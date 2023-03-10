package tic.tac.toe;



import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public  class WinnerScreenBase extends AnchorPane {

    protected final MediaView mediaView;
    protected final Button playAgainButtton;
    protected final ImageView imageView0;
    protected final Button mainPageButton;
    protected final ImageView imageView1;

    public WinnerScreenBase() {

        mediaView = new MediaView();
        playAgainButtton = new Button();
        imageView0 = new ImageView();
        mainPageButton = new Button();
        imageView1 = new ImageView();

        setId("AnchorPane");
        setPrefHeight(480.0);
        setPrefWidth(750.0);
        getStylesheets().add("/tic/tac/toe/css/winnerscreen.css");

        mediaView.setFitHeight(480.0);
        mediaView.setFitWidth(750.0);

       
       

        playAgainButtton.setLayoutX(14.0);
        playAgainButtton.setLayoutY(390.0);
        playAgainButtton.setMnemonicParsing(false);
        playAgainButtton.setPrefHeight(50.0);
        playAgainButtton.setPrefWidth(129.0);
        playAgainButtton.getStyleClass().add("playagainbtn");
        playAgainButtton.getStylesheets().add("/tic/tac/toe/css/winnerscreen.css");
        playAgainButtton.setText("Play Again");
        playAgainButtton.setFont(new Font("Serif Regular", 22.0));

        playAgainButtton.setOnMouseClicked(e->{



            mediaView.getMediaPlayer().stop();
            switch(PickYourSideScreenBase.level)
            {
                case 0:
                    Easy easy=new Easy();
                    TicTacToe.scene.setRoot(easy.boardScreen);
                    break;
                case 1:

                    Medium medium=new Medium();
                    
                    TicTacToe.scene.setRoot(medium.boardScreenBase);
                    break;
                case 2:
                    LevelHardClass hard=new LevelHardClass();
                    TicTacToe.scene.setRoot(hard.boardScreen);
                    break;
                case 3:
                    LocalMode localMode=new LocalMode();
                    TicTacToe.scene.setRoot(localMode.boardScreenBase);
                    break;
                case 4:
                        if(LoginFXMLBase.playerConnection!=null)
                        {
                            LoginFXMLBase.playerConnection.sendMessage("EndGameSession,");
                        }
                        TicTacToe.scene.setRoot(new AvailablePlayersBase());
                        
                    break;
            }
        
        });
        imageView0.setFitHeight(150.0);
        imageView0.setFitWidth(200.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("Photos/buttonbackground.png").toExternalForm()));
        playAgainButtton.setGraphic(imageView0);

        mainPageButton.setLayoutX(495.0);
        mainPageButton.setLayoutY(389.0);
        mainPageButton.setMnemonicParsing(false);
        mainPageButton.setPrefHeight(76.0);
        mainPageButton.setPrefWidth(129.0);
        mainPageButton.getStyleClass().add("returnbtn");
        mainPageButton.getStylesheets().add("/tic/tac/toe/css/winnerscreen.css");
        mainPageButton.setText("Main Page");
        mainPageButton.setFont(new Font("Serif Regular", 22.0));
        

        mainPageButton.setOnMouseClicked(e->{

            mediaView.getMediaPlayer().stop();
            Easy.computerScore=0;
            Easy.computerScore=0;

            Medium.player = 0;
            Medium.computer = 0;
            LevelHardClass.playerRes=0;
            LevelHardClass.computerRes=0;
            LocalMode.player1Score=0;
            LocalMode.player2Score=0;
            if(PickYourSideScreenBase.level==4)
            {
                if(LoginFXMLBase.playerConnection!=null)
                {
                    LoginFXMLBase.playerConnection.sendMessage("EndGameSession,");
                }
            }
            TicTacToe.scene.setRoot(new MainPageScreenBase());
            


        });
        imageView1.setFitHeight(150.0);
        imageView1.setFitWidth(200.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("Photos/buttonbackground.png").toExternalForm()));
        mainPageButton.setGraphic(imageView1);

        getChildren().add(mediaView);
        getChildren().add(playAgainButtton);
        getChildren().add(mainPageButton);

    }
     public void PrepareWinnerScreen(String Name,int mode)
     {
         
        
        String path="";


        if(mode==-1)//lose
        {
          path ="Photos/lose.mp4";
        }else if(mode==0)//draw
        {
          path ="Photos/draw.mp4";
             
        }else if(mode==1)//win
        {
          path ="Photos/winner.mp4";
        }
        Media media = new Media(getClass().getResource(path).toExternalForm());  


         
        MediaPlayer mediaPlayer = new MediaPlayer(media); 
        mediaView.setMediaPlayer(mediaPlayer);
        if(mode==0)
        {
            mediaView.setScaleX(1.19);
            mediaView.setScaleY(1.7);
        }else 
        {
          mediaView.setScaleY(1.4);
        }
        mediaPlayer.setAutoPlay(true);
        TicTacToe.scene.setRoot(this);
    }
}
