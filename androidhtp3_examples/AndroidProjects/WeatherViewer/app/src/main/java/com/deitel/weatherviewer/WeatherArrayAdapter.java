// WeatherArrayAdapter.java
// An ArrayAdapter for displaying a List<Weather>'s elements in a ListView
package com.deitel.weatherviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WeatherArrayAdapter extends ArrayAdapter<Weather> {
   // class for reusing views as list items scroll off and onto the screen
   private static class ViewHolder {
      ImageView conditionImageView;
      TextView dayTextView;
      TextView lowTextView;
      TextView hiTextView;
      TextView humidityTextView;
   }

   // stores already downloaded Bitmaps for reuse
   private Map<String, Bitmap> bitmaps = new HashMap<>();

   // constructor to initialize superclass inherited members
   public WeatherArrayAdapter(Context context, List<Weather> forecast) {
      super(context, -1, forecast);
   }

   // creates the custom views for the ListView's items
   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      // get Weather object for this specified ListView position
      Weather day = getItem(position);

      ViewHolder viewHolder; // object that reference's list item's views

      // check for reusable ViewHolder from a ListView item that scrolled
      // offscreen; otherwise, create a new ViewHolder
      if (convertView == null) { // no reusable ViewHolder, so create one
         viewHolder = new ViewHolder();
         LayoutInflater inflater = LayoutInflater.from(getContext());
         convertView =
            inflater.inflate(R.layout.list_item, parent, false);
         viewHolder.conditionImageView =
            (ImageView) convertView.findViewById(R.id.conditionImageView);
         viewHolder.dayTextView =
            (TextView) convertView.findViewById(R.id.dayTextView);
         viewHolder.lowTextView =
            (TextView) convertView.findViewById(R.id.lowTextView);
         viewHolder.hiTextView =
            (TextView) convertView.findViewById(R.id.hiTextView);
         viewHolder.humidityTextView =
            (TextView) convertView.findViewById(R.id.humidityTextView);
         convertView.setTag(viewHolder);
      }
      else { // reuse existing ViewHolder stored as the list item's tag
         viewHolder = (ViewHolder) convertView.getTag();
      }

      // if weather condition icon already downloaded, use it;
      // otherwise, download icon in a separate thread
      if (bitmaps.containsKey(day.iconURL)) {
         viewHolder.conditionImageView.setImageBitmap(
            bitmaps.get(day.iconURL));
      }
      else {
         // download and display weather condition image
         new LoadImageTask(viewHolder.conditionImageView).execute(
            day.iconURL);
      }

      // get other data from Weather object and place into views
      Context context = getContext(); // for loading String resources
      viewHolder.dayTextView.setText(context.getString(
         R.string.day_description, day.dayOfWeek, day.description));
      viewHolder.lowTextView.setText(
         context.getString(R.string.low_temp, day.minTemp));
      viewHolder.hiTextView.setText(
         context.getString(R.string.high_temp, day.maxTemp));
      viewHolder.humidityTextView.setText(
         context.getString(R.string.humidity, day.humidity));

      return convertView; // return completed list item to display
   }

   // AsyncTask to load weather condition icons in a separate thread
   private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
      private ImageView imageView; // displays the thumbnail

      // store ImageView on which to set the downloaded Bitmap
      public LoadImageTask(ImageView imageView) {
         this.imageView = imageView;
      }

      // load image; params[0] is the String URL representing the image
      @Override
      protected Bitmap doInBackground(String... params) {
         Bitmap bitmap = null;
         HttpURLConnection connection = null;

         try {
            URL url = new URL(params[0]); // create URL for image

            // open an HttpURLConnection, get its InputStream
            // and download the image
            connection = (HttpURLConnection) url.openConnection();

            try (InputStream inputStream = connection.getInputStream()) {
               bitmap = BitmapFactory.decodeStream(inputStream);
               bitmaps.put(params[0], bitmap); // cache for later use
            }
            catch (Exception e) {
               e.printStackTrace();
            }
         }
         catch (Exception e) {
            e.printStackTrace();
         }
         finally {
            connection.disconnect(); // close the HttpURLConnection
         }

         return bitmap;
      }

      // set weather condition image in list item
      @Override
      protected void onPostExecute(Bitmap bitmap) {
         imageView.setImageBitmap(bitmap);
      }
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
