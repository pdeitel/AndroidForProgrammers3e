// EraseImageDialogFragment.java
// Allows user to erase image
package com.deitel.doodlz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment; //******************************
import android.content.DialogInterface;
import android.os.Bundle;

// class for the Erase Image dialog
public class EraseImageDialogFragment extends DialogFragment {
   // create an AlertDialog and return it
   @Override
   public Dialog onCreateDialog(Bundle bundle) {
      AlertDialog.Builder builder =
         new AlertDialog.Builder(getActivity());

      // set the AlertDialog's message
      builder.setMessage(R.string.message_erase);

      // add Erase Button
      builder.setPositiveButton(R.string.button_erase,
         new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               getDoodleFragment().getDoodleView().clear(); // clear image
            }
         }
      );

      // add cancel Button
      builder.setNegativeButton(android.R.string.cancel, null);
      return builder.create(); // return dialog
   }

   // gets a reference to the MainActivityFragment
   private MainActivityFragment getDoodleFragment() {
      return (MainActivityFragment) getFragmentManager().findFragmentById(
         R.id.doodleFragment);
   }

   // tell MainActivityFragment that dialog is now displayed
   @Override
   public void onAttach(Activity activity) {
      super.onAttach(activity);
      MainActivityFragment fragment = getDoodleFragment();

      if (fragment != null)
         fragment.setDialogOnScreen(true);
   }

   // tell MainActivityFragment that dialog is no longer displayed
   @Override
   public void onDetach() {
      super.onDetach();
      MainActivityFragment fragment = getDoodleFragment();

      if (fragment != null)
         fragment.setDialogOnScreen(false);
   }
}

/**************************************************************************
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
 **************************************************************************/
