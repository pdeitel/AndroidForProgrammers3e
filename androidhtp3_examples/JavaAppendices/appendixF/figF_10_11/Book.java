// Fig. F.10: Book.java
// Declare an enum type with constructor and explicit instance fields 
// and accessors for these fields

public enum Book
{    
   // declare constants of enum type                                
   JHTP( "Java How to Program", "2012" ),                        
   CHTP( "C How to Program", "2007" ),                           
   IW3HTP( "Internet & World Wide Web How to Program", "2008" ),
   CPPHTP( "C++ How to Program", "2012" ),                       
   VBHTP( "Visual Basic 2010 How to Program", "2011" ),
   CSHARPHTP( "Visual C# 2010 How to Program", "2011" );

   // instance fields 
   private final String title; // book title
   private final String copyrightYear; // copyright year

   // enum constructor
   Book( String bookTitle, String year ) 
   { 
      title = bookTitle;
      copyrightYear = year;
   } // end enum Book constructor

   // accessor for field title
   public String getTitle()
   {
      return title;
   } // end method getTitle

   // accessor for field copyrightYear
   public String getCopyrightYear()
   {
      return copyrightYear;
   } // end method getCopyrightYear
} // end enum Book


/**************************************************************************
 * (C) Copyright 1992-2017 by Deitel & Associates, Inc. and               *
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
