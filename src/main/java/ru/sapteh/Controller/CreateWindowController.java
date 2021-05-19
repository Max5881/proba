package ru.sapteh.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.DAO.DAO;
import ru.sapteh.Model.Category;
import ru.sapteh.Model.Product;
import ru.sapteh.Model.Warehouse;
import ru.sapteh.Service.CategoryService;
import ru.sapteh.Service.ProductService;
import ru.sapteh.Service.WarehouseService;


public class CreateWindowController {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<Product,Integer> productIntegerDAO = new ProductService(factory);
    DAO<Warehouse,Integer>warehouseIntegerDAO = new WarehouseService(factory);
    DAO<Category,Integer>categoryIntegerDAO = new CategoryService(factory);

    ObservableList<Warehouse> warehouses = FXCollections.observableArrayList();
    ObservableList<Category> categories = FXCollections.observableArrayList();
    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<Warehouse> wareHouseBox;

    @FXML
    private ComboBox<Category> categoryBox;

    @FXML
    private Button createButton;

    public void initialize(){
        warehouses.addAll(warehouseIntegerDAO.readByAll());
        categories.addAll(categoryIntegerDAO.readByAll());

        wareHouseBox.setItems(warehouses);
        categoryBox.setItems(categories);

        createButton.setOnAction(event -> {
            productIntegerDAO.create(new Product(nameTextField.getText(),wareHouseBox.getValue(),categoryBox.getValue()));
            createButton.getScene().getWindow().hide();
        });
    }
}
