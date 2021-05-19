package ru.sapteh.Controller;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

public class updateWindowController {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<Product,Integer> productIntegerDAO = new ProductService(factory);
    DAO<Warehouse,Integer> warehouseIntegerDAO = new WarehouseService(factory);
    DAO<Category,Integer> categoryIntegerDAO = new CategoryService(factory);
    ObservableList<Warehouse> warehouseObservableList = FXCollections.observableArrayList();
    ObservableList<Category> categories = FXCollections.observableArrayList();
    @FXML
    private TextField titleTextField;

    @FXML
    private ComboBox<Warehouse> warehouseComboBox;

    @FXML
    private ComboBox<Category> categoryComboBox;

    @FXML
    private Button enterButton;

    public void initialize(){
        warehouseObservableList.addAll(warehouseIntegerDAO.readByAll());
        categories.addAll(categoryIntegerDAO.readByAll());
        categoryComboBox.setItems(categories);
        warehouseComboBox.setItems(warehouseObservableList);
        enterButton.setOnAction(event -> {
            MainWindowController.product.setTitle(titleTextField.getText());
            MainWindowController.product.setWarehouse(warehouseComboBox.getValue());
            MainWindowController.product.setCategory(categoryComboBox.getValue());
            productIntegerDAO.update(MainWindowController.product);
            enterButton.getScene().getWindow().hide();
        });
    }
}
