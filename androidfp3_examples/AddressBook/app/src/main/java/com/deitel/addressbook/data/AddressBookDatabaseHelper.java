// AddressBookDatabaseHelper.java
// SQLiteOpenHelper subclass that defines the app's database
package com.deitel.addressbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.deitel.addressbook.data.DatabaseDescription.Contact;

class AddressBookDatabaseHelper extends SQLiteOpenHelper {
   private static final String DATABASE_NAME = "AddressBook.db";
   private static final int DATABASE_VERSION = 1;

   // constructor
   public AddressBookDatabaseHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   // creates the contacts table when the database is created
   @Override
   public void onCreate(SQLiteDatabase db) {
      // SQL for creating the contacts table
      final String CREATE_CONTACTS_TABLE =
         "CREATE TABLE " + Contact.TABLE_NAME + "(" +
         Contact._ID + " integer primary key, " +
         Contact.COLUMN_NAME + " TEXT, " +
         Contact.COLUMN_PHONE + " TEXT, " +
         Contact.COLUMN_EMAIL + " TEXT, " +
         Contact.COLUMN_STREET + " TEXT, " +
         Contact.COLUMN_CITY + " TEXT, " +
         Contact.COLUMN_STATE + " TEXT, " +
         Contact.COLUMN_ZIP + " TEXT);";
      db.execSQL(CREATE_CONTACTS_TABLE); // create the contacts table
   }

   // normally defines how to upgrade the database when the schema changes
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion,
      int newVersion) { }
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
