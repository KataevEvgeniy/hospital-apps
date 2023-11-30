package org.kataew.prbdesktopjavafx.ui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.kataew.prbdesktopjavafx.entities.MoveStatus;
import org.kataew.prbdesktopjavafx.entities.PeopleMovement;
import org.kataew.prbdesktopjavafx.entities.SecurityAction;
import org.kataew.prbdesktopjavafx.ui.services.RoomsView;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomsController {


    @FXML
    AnchorPane gridPaneContainer;

    @FXML
    GridPane upGridPane;

    @FXML
    GridPane midGridPane;
    @FXML
    GridPane downGridPane;

    WebClient webClient;

    @FXML
    public void initialize(){
        RoomsView.initializeFlows(upGridPane,midGridPane,downGridPane);
        webClient = WebClient.create();
    }

    @FXML
    public void generateRooms() {
        ParameterizedTypeReference<List<SecurityAction>> responseType = new ParameterizedTypeReference<List<SecurityAction>>() {};
        webClient.get().uri("http://localhost:8080/PersonLocations").retrieve().bodyToMono(responseType).subscribe(
                responseBody->{
                    Platform.runLater(()->{
                        RoomsView.recordMove(responseBody);
                    });

                }
        );
    }
}
