package org.kataew.prbdesktopjavafx.ui.services;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import org.kataew.prbdesktopjavafx.entities.PeopleEntity;
import org.kataew.prbdesktopjavafx.entities.PeopleMovement;
import org.kataew.prbdesktopjavafx.entities.Room;
import org.kataew.prbdesktopjavafx.entities.SecurityAction;
import org.springframework.stereotype.Component;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


@Component
public class RoomsView {

    static final int HALL = -2;
    static final int UNDEFINED_ROOM = -1;

    static int[][] roomsMap = {
            {17, 18, 19, 20, 21, 22, UNDEFINED_ROOM, 0, 1, 2, 3, 4, 5, 6},
            {HALL},
            {16, 15, 14, 13, 12, 11, 10, 9, 8, 7}};

    static HashMap<Integer,FlowPane> roomNumFlowPane = new HashMap<>();
    static HashMap<Integer, Integer> peopleIdRoomNum = new HashMap<>();

    public static void initializeFlows(GridPane upGridPane, GridPane midGridPane, GridPane downGridPane) {
        Random random = new Random();

        generateFlows(upGridPane, random, roomsMap[0]);

        generateFlows(midGridPane, random, roomsMap[1]);

        generateFlows(downGridPane, random, roomsMap[2]);

    }

    private static void generateFlows(GridPane upGridPane, Random random, int[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if(rooms[i] == UNDEFINED_ROOM){
                continue;
            }
            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(1);
            flowPane.setVgap(1);
            Color backgroundColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 0.5);
            BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, null, null);
            Background background = new Background(backgroundFill);
            flowPane.setBackground(background);
            flowPane.setAlignment(Pos.CENTER);
            upGridPane.add(flowPane, i, 0);
            roomNumFlowPane.put(rooms[i],flowPane);
        }
    }

    public static void recordMove(List<SecurityAction> securityActions) {
        for (SecurityAction securityAction: securityActions){
            switch (securityAction.getLastSecurityPointDirection()){
                case "in" -> peopleIdRoomNum.put(securityAction.getPerson().getPersonCode(),securityAction.getLastSecurityPointNumber());
                case "out" -> peopleIdRoomNum.put(securityAction.getLastSecurityPointNumber(),HALL);
            }
        }
        drawCircles();
    }

    private static void drawCircles(){

        roomNumFlowPane.values().forEach(flowPane -> {
            flowPane.getChildren().clear();
        });

        peopleIdRoomNum.forEach((key,value) ->{
            Circle circle = new Circle(2);

            if(value != UNDEFINED_ROOM){

                FlowPane flowPane = roomNumFlowPane.get(value);
                flowPane.getChildren().add(circle);
            }

        });
    }

}
