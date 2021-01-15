package Utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;



public class FiltroDecimali extends DocumentFilter {
   @Override
   public void insertString(FilterBypass fb, int offset, String string,
         AttributeSet attr) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (ControlloFloat(sb.toString())) {
         super.insertString(fb, offset, string, attr);
      } 
   }

   private boolean ControlloFloat(String text) {
      try {
         Float.parseFloat(text);
         if(Float.parseFloat(text)> 0) {System.out.println("cia");
         return true;}
         else return false;
         
      } catch (NumberFormatException e) {
         return false;
      }
   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text,
         AttributeSet attrs) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (ControlloFloat(sb.toString())) {
         super.replace(fb, offset, length, text, attrs);
      } 

   }

   @Override
   public void remove(FilterBypass fb, int offset, int length)
         throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);
      if(sb.toString().length() == 0) 	    	
    	  super.replace(fb, offset, length, "", null); 
    	  else { 		   
    		  if (ControlloFloat(sb.toString())) 
    			  super.remove(fb, offset, length); 

    	  }
   }
}