package com.viktorkhon.justjava;

import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.NumberFormat;

import static com.viktorkhon.justjava.R.attr.title;
import static com.viktorkhon.justjava.R.id.end;
import static com.viktorkhon.justjava.R.id.nameText;

public class MainActivity extends AppCompatActivity {

    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * This method is called when the order button is clicked.
     */
    int qty = 2;

    public void submitOrder(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order summary for " + displayName());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price = 5;
        if (displayWhippedCream()) {
            price += 1;
        }
        if (displayChocolate()) {
            price += 2;
        }
        return qty*price;
    }

    private String createOrderSummary () {
        String priceMessage = "Name: " + displayName() +
        "\nAdd whipped cream? " + displayWhippedCream() +
        "\nAdd chocolate?" + displayChocolate() +
        "\nQuantity: " + qty +
        "\nTotal: $" + calculatePrice() + "\nThank you" +
        "\n\nPrikol, eto ya so svoey app tebe prislal :)";
        return priceMessage;
    }

    public void increment(View view) {
        qty++;
        if (qty > 100) {
            // creating a Toast object, but not storing it in a variable
            Toast.makeText(this,
                    "You cannot have more than 100 cups", Toast.LENGTH_LONG).show();
            qty = 100;
        }
        displayQuantity(qty);
    }

    public void decrement(View view) {
        if (qty == 1) {
            // creating a Toast object variable and then use show() method on an object
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You cannot have less than 1 cup", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        qty--;
        displayQuantity(qty);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private boolean displayWhippedCream() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_Cream);
        if (checkBox.isChecked()){
            return true;
            }
        return false;
    }

    private boolean displayChocolate() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_Chocolate);
        if (checkBox.isChecked()){
            return true;
        }
        return false;
    }

    private String displayName() {
        EditText name = (EditText) findViewById(R.id.nameText);
        String nameText = name.getText().toString();
        return nameText;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.viktorkhon.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.viktorkhon.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        mClient.disconnect();
    }
}
