package org.kataew.prbdesktopjavafx.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityAction {

    private Person person;
    private int lastSecurityPointNumber;
    private String lastSecurityPointDirection;


}
