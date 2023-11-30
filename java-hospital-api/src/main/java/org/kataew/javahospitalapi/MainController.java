package org.kataew.javahospitalapi;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    List<Person> people = new ArrayList<>(Arrays.asList(
            new Person(1, Person.STAFF),
            new Person(2, Person.STAFF),
            new Person(3, Person.STAFF),
            new Person(4, Person.STAFF),
            new Person(5, Person.STAFF),
            new Person(6, Person.STAFF),
            new Person(7, Person.STAFF),
            new Person(8, Person.STAFF),
            new Person(9, Person.CLIENT),
            new Person(10, Person.CLIENT),
            new Person(11, Person.CLIENT),
            new Person(12, Person.CLIENT),
            new Person(13, Person.CLIENT),
            new Person(14, Person.CLIENT),
            new Person(15, Person.CLIENT),
            new Person(16, Person.CLIENT),
            new Person(17, Person.CLIENT),
            new Person(18, Person.CLIENT),
            new Person(19, Person.CLIENT),
            new Person(20, Person.CLIENT),
            new Person(21, Person.CLIENT),
            new Person(22, Person.CLIENT),
            new Person(23, Person.CLIENT)
    ));


    @GetMapping("/PersonLocations")
    public ResponseEntity<?> personLocations() {

        return new ResponseEntity<>(getSecurityActions(), HttpStatus.OK);
    }

    private List<SecurityAction> getSecurityActions() {
        Random random = new Random();
        List<SecurityAction> securityActions = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            if(random.nextInt(100)>50){
                securityActions.add(new SecurityAction(people.get(i),random.nextInt(22),"in"));
            }else {
                securityActions.add(new SecurityAction(people.get(i),random.nextInt(22),"out"));
            }

        }
        return securityActions;

    }


}
