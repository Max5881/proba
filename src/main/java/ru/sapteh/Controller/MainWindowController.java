package ru.sapteh.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.DAO.DAO;
import ru.sapteh.Model.Category;
import ru.sapteh.Model.Product;
import ru.sapteh.Model.Warehouse;
import ru.sapteh.Service.ProductService;
import ru.sapteh.Service.WarehouseService;

import java.io.IOException;

public class MainWindowController {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<Product,Integer> productIntegerDAO = new ProductService(factory);
    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    public static Product product;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button buttonRefresh;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<Product, String> adresSkladaColum;

    @FXML
    private TableColumn<Product, String> nameProductColumn;

    @FXML
    private TableColumn<Product, String> typeProductaColumn;

    public void initialize(){
        search();
        productObservableList.addAll(productIntegerDAO.readByAll());
        adresSkladaColum.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getWarehouse().getAdres()));
        nameProductColumn.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getTitle()));
        typeProductaColumn.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getCategory().getName()));
        productTableView.setItems(productObservableList);

        productTableView.getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue)->
                updateButton.setOnAction(event -> {
                    product=newValue;
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/updateWindow.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.setTitle("Изминение");
                    stage.setScene(new Scene(root));
                    stage.show();
                }));
        productTableView.getSelectionModel().selectedItemProperty().addListener(((observableValue, product1, t1) ->
                deleteButton.setOnAction(event -> {
                    productIntegerDAO.delete(t1);
                    productObservableList.remove(t1);
                })));
        createButton.setOnAction(event -> {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/View/createWindow.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Добавление");
            stage.setScene(new Scene(root));
            stage.show();
        });
        buttonRefresh.setOnAction(event -> {
            productObservableList.clear();
            productObservableList.addAll(productIntegerDAO.readByAll());
        });

    }
    private void search(){
        searchTxt.textProperty().addListener((obj,oldValue,newValue)->{
            FilteredList<Product> filteredList = new FilteredList<>(productObservableList,p->{
                if (newValue == null){
                    return true;
                }
                if (p.getTitle().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                }
                if (p.getWarehouse().getAdres().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                }
                if (p.getCategory().getName().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                }return false;
            });
            productTableView.setItems(filteredList);
        });
    }


}
