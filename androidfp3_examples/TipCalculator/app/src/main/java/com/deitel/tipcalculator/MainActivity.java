// MainActivity.java
// Calculates a bill total based on a tip percentage
package com.deitel.tipcalculator;

import android.os.Bundle; // for saving state information
import android.support.v7.app.AppCompatActivity; // base class
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing the tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text

import java.text.NumberFormat; // for currency formatting

// MainActivity class for the Tip Calculator app
public class MainActivity extends AppCompatActivity {

   // currency and percent formatter objects
   private static final NumberFormat currencyFormat =
      NumberFormat.getCurrencyInstance();
   private static final NumberFormat percentFormat =
      NumberFormat.getPercentInstance();

   private double billAmount = 0.0; // bill amount entered by the user
   private double percent = 0.15; // initial tip percentage
   private TextView amountTextView; // shows formatted bill amount
   private TextView percentTextView; // shows tip percentage
   private TextView tipTextView; // shows calculated tip amount
   private TextView totalTextView; // shows calculated total bill amount

   // called when the activity is first created
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState); // call superclass onCreate
      setContentView(R.layout.activity_main); // inflate the GUI

      // get references to programmatically manipulated TextViews
      amountTextView = (TextView) findViewById(R.id.amountTextView);
      percentTextView = (TextView) findViewById(R.id.percentTextView);
      tipTextView = (TextView) findViewById(R.id.tipTextView);
      totalTextView = (TextView) findViewById(R.id.totalTextView);
      tipTextView.setText(currencyFormat.format(0));
      totalTextView.setText(currencyFormat.format(0));

      // set amountEditText's TextWatcher
      EditText amountEditText =
         (EditText) findViewById(R.id.amountEditText);
      amountEditText.addTextChangedListener(amountEditTextWatcher);

      // set percentSeekBar's OnSeekBarChangeListener
      SeekBar percentSeekBar =
         (SeekBar) findViewById(R.id.percentSeekBar);
      percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
   }

   // calculate and display tip and total amounts
   private void calculate() {
      // format percent and display in percentTextView
      percentTextView.setText(percentFormat.format(percent));

      // calculate the tip and total
      double tip = billAmount * percent;
      double total = billAmount + tip;

      // display tip and total formatted as currency
      tipTextView.setText(currencyFormat.format(tip));
      totalTextView.setText(currencyFormat.format(total));
   }

   // listener object for the SeekBar's progress changed events
   private final OnSeekBarChangeListener seekBarListener =
      new OnSeekBarChangeListener() {
         // update percent, then call calculate
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress,
            boolean fromUser) {
            percent = progress / 100.0; // set percent based on progress
            calculate(); // calculate and display tip and total
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) { }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) { }
      };

   // listener object for the EditText's text-changed events
   private final TextWatcher amountEditTextWatcher = new TextWatcher() {
      // called when the user modifies the bill amount
      @Override
      public void onTextChanged(CharSequence s, int start,
         int before, int count) {

         try { // get bill amount and display currency formatted value
            billAmount = Double.parseDouble(s.toString()) / 100.0;
            amountTextView.setText(currencyFormat.format(billAmount));
         }
         catch (NumberFormatException e) { // if s is empty or non-numeric
            amountTextView.setText("");
            billAmount = 0.0;
         }

         calculate(); // update the tip and total TextViews
      }

      @Override
      public void afterTextChanged(Editable s) { }

      @Override
      public void beforeTextChanged(
         CharSequence s, int start, int count, int after) { }
   };
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
