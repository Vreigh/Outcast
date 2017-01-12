package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.*;
import java.util.ArrayList;



public class AlertWindow {
    
    public static void showInfo(String title, String info){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(200);
        
        Label label = new Label();
        label.setText(info);
        
        Button btnCloseWindow = new Button("Close");
        btnCloseWindow.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, btnCloseWindow);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void showTable(TableView<Buff> table){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(".");
        window.setMinWidth(450);
        window.setMinHeight(200);
        
        BorderPane layout = new BorderPane();
        layout.setCenter(table);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public static void showTable(TableView<Dot> table, boolean flag){ // dlaczego wgle musiałem przeciążać?? Przecież argumenty są różnych typów(troche)
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(".");
        window.setMinWidth(350);
        window.setMinHeight(200);
        
        BorderPane layout = new BorderPane();
        layout.setCenter(table);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public static boolean confirmBox(String header, String cont, String conf){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(header);
        alert.setContentText(cont);

        ButtonType buttonTypeOne = new ButtonType(conf);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get().getText().equals(conf)){
            return true;
        }else return false;
    }
    public static int getTarget(ArrayList<Unit> units, Monster monster){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Choose a target");
        alert.setContentText("targets");
        
        ArrayList<ButtonType> btnTargets = new ArrayList<>();
        btnTargets.add(new ButtonType(monster.getName()));
        
        for(Unit unit : units){
            btnTargets.add(new ButtonType(unit.getFullName()));
        }
        btnTargets.add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
        alert.getButtonTypes().setAll(btnTargets);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(!result.isPresent()) return -1;
        if(result.get().getText().equals(monster.getName())) return 5;
        
        for(int i=0; i<5; i++){
            if(result.get().getText().equals(units.get(i).getFullName())){
                return i;
            }
        }
        return -1;
    }
    public static int getTarget(ArrayList<Unit> units){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Choose a target");
        alert.setContentText("targets");
        
        ArrayList<ButtonType> btnTargets = new ArrayList<>();
        
        for(Unit unit : units){
            btnTargets.add(new ButtonType(unit.getFullName()));
        }
        
        btnTargets.add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
        alert.getButtonTypes().setAll(btnTargets);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(!result.isPresent()) return -1;
        
        for(Unit unit : units){
            if(result.get().getText().equals(unit.getFullName())){
                return unit.getPosition();
            }
        }
        
        return -1;
    }
}
