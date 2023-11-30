package org.kataew.prbdesktopjavafx.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleMovement {

    private MoveStatus moveStatus;
    private int peopleId;
    private int roomNum;


}
