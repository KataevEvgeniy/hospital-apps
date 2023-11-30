package org.kataew.prbdesktopjavafx.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    public static final String CLIENT = "Клиент";
    public static final String STAFF = "Сотрудник";

    private int personCode;
    private String personRole;

}
