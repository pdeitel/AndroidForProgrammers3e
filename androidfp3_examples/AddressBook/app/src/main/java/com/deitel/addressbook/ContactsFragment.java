// ContactsFragment.java
// Fragment subclass that displays the alphabetical list of contact names
package com.deitel.addressbook;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deitel.addressbook.data.DatabaseDescription.Contact;

public class ContactsFragment extends Fragment
   implements LoaderManager.LoaderCallbacks<Cursor> {

   // callback method implemented by MainActivity
   public interface ContactsFragmentListener {
      // called when contact selected
      void onContactSelected(Uri contactUri);

      // called when add button is pressed
      void onAddContact();
   }

   private static final int CONTACTS_LOADER = 0; // identifies Loader

   // used to inform the MainActivity when a contact is selected
   private ContactsFragmentListener listener;

   private ContactsAdapter contactsAdapter; // adapter for recyclerView

   // configures this fragment's GUI
   @Override
   public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);
      setHasOptionsMenu(true); // fragment has menu items to display

      // inflate GUI and get reference to the RecyclerView
      View view = inflater.inflate(
         R.layout.fragment_contacts, container, false);
      RecyclerView recyclerView =
         (RecyclerView) view.findViewById(R.id.recyclerView);

      // recyclerView should display items in a vertical list
      recyclerView.setLayoutManager(
         new LinearLayoutManager(getActivity().getBaseContext()));

      // create recyclerView's adapter and item click listener
      contactsAdapter = new ContactsAdapter(
         new ContactsAdapter.ContactClickListener() {
            @Override
            public void onClick(Uri contactUri) {
               listener.onContactSelected(contactUri);
            }
         }
      );
      recyclerView.setAdapter(contactsAdapter); // set the adapter

      // attach a custom ItemDecorator to draw dividers between list items
      recyclerView.addItemDecoration(new ItemDivider(getContext()));

      // improves performance if RecyclerView's layout size never changes
      recyclerView.setHasFixedSize(true);

      // get the FloatingActionButton and configure its listener
      FloatingActionButton addButton =
         (FloatingActionButton) view.findViewById(R.id.addButton);
      addButton.setOnClickListener(
         new View.OnClickListener() {
            // displays the AddEditFragment when FAB is touched
            @Override
            public void onClick(View view) {
               listener.onAddContact();
            }
         }
      );

      return view;
   }

   // set ContactsFragmentListener when fragment attached
   @Override
   public void onAttach(Context context) {
      super.onAttach(context);
      listener = (ContactsFragmentListener) context;
   }

   // remove ContactsFragmentListener when Fragment detached
   @Override
   public void onDetach() {
      super.onDetach();
      listener = null;
   }

   // initialize a Loader when this fragment's activity is created
   @Override
   public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      getLoaderManager().initLoader(CONTACTS_LOADER, null, this);
   }

   // called from MainActivity when other Fragment's update database
   public void updateContactList() {
      contactsAdapter.notifyDataSetChanged();
   }

   // called by LoaderManager to create a Loader
   @Override
   public Loader<Cursor> onCreateLoader(int id, Bundle args) {
      // create an appropriate CursorLoader based on the id argument;
      // only one Loader in this fragment, so the switch is unnecessary
      switch (id) {
         case CONTACTS_LOADER:
            return new CursorLoader(getActivity(),
               Contact.CONTENT_URI, // Uri of contacts table
               null, // null projection returns all columns
               null, // null selection returns all rows
               null, // no selection arguments
               Contact.COLUMN_NAME + " COLLATE NOCASE ASC"); // sort order
         default:
            return null;
      }
   }

   // called by LoaderManager when loading completes
   @Override
   public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
      contactsAdapter.swapCursor(data);
   }

   // called by LoaderManager when the Loader is being reset
   @Override
   public void onLoaderReset(Loader<Cursor> loader) {
      contactsAdapter.swapCursor(null);
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
