package org.kataew.prbdesktopjavafx;

import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.kataew.prbdesktopjavafx.repositories.PeopleEntityRepository;
import org.kataew.prbdesktopjavafx.services.Printer;
import org.kataew.prbdesktopjavafx.services.QrCodeGenerator;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class HelloController {


    @FXML
    ImageView myImageView;

    @FXML
    ListView<String> peopleListView;

    @FXML
    ImageView roomsImageView;
    @FXML
    GridPane imageGrid;


    ConfigurableApplicationContext context = ApplicationContextProvider.getContext();

    PeopleEntityRepository repository;


    QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

    Printer printer = new Printer();

    private byte[] qrCode;

    @FXML
    protected void onHelloButtonClick() throws IOException, WriterException {


        repository = context.getBean(PeopleEntityRepository.class);

        ObservableList<String> list = FXCollections.observableList(repository.findAll().stream()
                .map(Object::toString)
                .toList());

        peopleListView.setItems(list);

        peopleListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                String selectedItem = peopleListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    qrCode = qrCodeGenerator.generateQrCodeSimple(100, 100, selectedItem);
                    myImageView.setImage(new Image(new ByteArrayInputStream(qrCode)));
                }
            }
        });


    }


    @FXML
    public void onSaveAsPdfClick() {
        printer.saveAsPdf(qrCode, "C:\\Users\\serkova_eg\\Documents\\forJavaTest\\test.pdf");
    }
}