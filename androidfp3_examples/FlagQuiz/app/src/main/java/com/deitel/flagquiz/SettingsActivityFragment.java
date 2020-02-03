// SettingsActivityFragment.java
// Subclass of PreferenceFragment for managing app settings
package com.deitel.flagquiz;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsActivityFragment extends PreferenceFragment {
   // creates preferences GUI from preferences.xml file in res/xml
   @Override
   public void onCreate(Bundle bundle) {
      super.onCreate(bundle);
      addPreferencesFromResource(R.xml.preferences); // load from XML
   }
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
