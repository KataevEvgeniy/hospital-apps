package org.kataew.prbdesktopjavafx;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;



public class ApplicationContextProvider {





    @Getter
    private static ConfigurableApplicationContext context;


    public static void setContext(ConfigurableApplicationContext context) {
        ApplicationContextProvider.context = context;
    }
}
