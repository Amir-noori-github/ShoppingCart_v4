package org.example.shoppingcart;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ItemRow extends HBox {

    private final TextField priceField;
    private final TextField quantityField;

    public ItemRow(String priceLabel, String quantityLabel) {
        super(10); // spacing

        Label lblPrice = new Label(priceLabel);
        priceField = new TextField();
        priceField.setPromptText(priceLabel);

        Label lblQuantity = new Label(quantityLabel);
        quantityField = new TextField();
        quantityField.setPromptText(quantityLabel);

        this.getChildren().addAll(lblPrice, priceField, lblQuantity, quantityField);
    }

    public TextField getPriceField() {
        return priceField;
    }

    public TextField getQuantityField() {
        return quantityField;
    }
}
