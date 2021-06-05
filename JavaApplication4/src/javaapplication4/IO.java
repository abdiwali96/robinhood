
//Title:        
//Version:      
//Copyright:    Copyright (c) 2001
//Author:       Jon Robinson
//Company:
//Description:  Class for handling text input and output

import java.io.*;
import java.util.*;

public final class IO {

  // need to create reader object linked to the system input via a stream.

  private static final int IGNORE = 1;
  private static final int ACCEPT = 2;

  private static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
  private static LinkedList ScreenBuffer = new LinkedList();

  public IO() {}

  // this function is a wrapper to the readInteger function as it allows
  // the user to pass in a message which will be displayed before the input takes place.
  public synchronized static int readInteger( String sMessage ) {
    Out(sMessage,IGNORE);
    return getInteger(sMessage);
  }

  // this function allows the user to get an integer value back from the keyboard
  private synchronized static int getInteger(String sMessage) {
    try {
      // get line of text from user
      String sLine = input.readLine();
      // now need to convert into INT
      // send this to be stored in buffer
      addToBuffer(sMessage+sLine);
      return Integer.parseInt( (new StringTokenizer( sLine )).nextToken() );
      } catch( Exception e) {
          OutNL( e.getMessage(),ACCEPT );
          return 0;
        }
  }

  // this function is a wrapper to the main readString function.  it allows for
  // the user to pass a message to be displayed before the input takes place.
  public synchronized static String readString( String sMessage ) {
    Out(sMessage,IGNORE);
    return getString(sMessage);
    }

  // this function is to enable the user to read in a string.
  // does not display a message.
  private synchronized static String getString(String sMessage) {
    try {
      // get line of text from user
      String sLine = input.readLine();
      addToBuffer(sMessage+sLine);
      return sLine;
      } catch( Exception e) {
          OutNL( e.getMessage(),ACCEPT );
          // could return null but if no error checking is done on caller side,
          // then it would result in a general protection fault.  So to be
          // safe return an empty string.
          return "";
        }
    }


  // this function will output the message onto the screen.  It will not insert line & carridge return
  private synchronized static void Out( String sMessage, int iFlag ) {
    System.out.print( sMessage );
    if( iFlag != IGNORE )
      addToBuffer(sMessage);
    }

  // this function will output the message onto the screen and place the cursor onto the next line.
  private synchronized static void OutNL( String sMessage, int iFlag ) {
    System.out.println( sMessage );
    if(iFlag != IGNORE )
      addToBuffer(sMessage);
    }

  public synchronized static void Out( String sMessage ) {
    Out(sMessage, ACCEPT);
    }

  // this function will output the message onto the screen and place the cursor onto the next line.
  public synchronized static void OutNL( String sMessage ) {
    OutNL(sMessage, ACCEPT );
    }

  private synchronized static void addToBuffer( String sMessage ) {
    ScreenBuffer.add( sMessage );
  }

  public synchronized static void doDump() {
    fileHandle file = new fileHandle();
    String sName = IO.readString("Enter name of file to output to:");
    try {
      file.open(sName);
      for(int i=1; i<ScreenBuffer.size(); i++ ) {
        String sLine = (String)ScreenBuffer.get(i);
        file.saveLine( sLine );
        }
      file.close();
      } catch(Exception e) { System.err.println(e.getMessage()); }
  }
}