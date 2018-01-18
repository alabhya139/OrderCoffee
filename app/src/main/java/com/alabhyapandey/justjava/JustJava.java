package com.alabhyapandey.justjava;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class JustJava extends AppCompatActivity {

    int quantity = 0;
    int price = 30;
    boolean hasWhippedCream, hasChocolate;
    Editable nameOfCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_java);
    }

    /**
     * Method for setting price.
     *
     * @return
     */

    public void onCheck(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        if (whippedCream.isChecked()) {
            price += 7;
            hasWhippedCream = true;
        } else hasWhippedCream = false;
    }

    public void onChecked2(View view) {
        CheckBox chocolate = findViewById(R.id.chocolate);
        if (chocolate.isChecked()) {
            price += 10;
            hasChocolate = true;
        } else hasChocolate = false;
    }

    private String createOrderSummary(Editable inputText) {
        String passMessage = "" + inputText;
        passMessage += "\nTotal number of Quantity : " + quantity;
        String message = hasWhippedCream ? "YES" : "NO";
        passMessage += "\nWant Whipped Cream Topping : " + message;
        message = hasChocolate ? "YES" : "NO";
        passMessage += "\nWant Chocolate Topping:" + message;
        String totalPrice = NumberFormat.getCurrencyInstance().format(quantity * price);
        passMessage += "\nTotal Price : " + totalPrice;
        passMessage += "\nThank You!";
        return passMessage;
    }

    /**
     * Method for clicking order Button
     *
     * @param view
     */
    public void submitOrder(View view) {
        EditText textView = findViewById(R.id.edit_text_view);
        nameOfCustomer = textView.getText();
        displayMessage(createOrderSummary(nameOfCustomer));
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:alabhyap@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order For Mr " + nameOfCustomer);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(nameOfCustomer));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void displayMessage(String message) {
        TextView orderSummary = findViewById(R.id.price_text_view);
        orderSummary.setText(message);
    }

    /**
     * Method to display no of quantity
     *
     * @param number
     */
    public void display(int number) {
        TextView quantity = findViewById(R.id.no_of_quantity);
        quantity.setText("" + number);
    }

    /**
     * This method displays increment in no. of quantities.
     *
     * @param view
     */
    public void increment(View view) {
        quantity += 1;
        display(quantity);
    }

    /**
     * This method displays the decrement in no of quantities.
     *
     * @param view
     */
    public void decrement(View view) {
        if (quantity < 1) {
            return;
        }
        quantity -= 1;
        display(quantity);
    }

}