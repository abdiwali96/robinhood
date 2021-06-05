//Title:        fileHandle.java
//Version:      v1.0.0
//Debugged:     Checked.
//Copyright:    Copyright (c) Dec 2001
//Author:       Jon Robinson
//Company:      None
//Description:  This is the base class for the FileAccess class.  It handles the low
//              level functionality for reading and writing bytes to a file.

import java.io.*;
 
public class fileHandle { 
 
  private RandomAccessFile file;
 
  public boolean bDisplayText = false;
 
  public fileHandle() { } 
 
  public void open( String sName ) {
    try { 
      file = new RandomAccessFile( sName, "rw" );
    } catch( Exception e ) { file = null; } 
  } 

  public long returnSize() {
    try {
      return file.length();
      } catch( Exception e ) { return 0; }
    }

  public boolean EOF() {
    try {
      if( file.getFilePointer() >= file.length() )
        return true;
      else return false;
      } catch( Exception e ) { return true; }
    }

  public boolean moveFP( long lPos ) {
    try {
      if(file != null) {
        file.seek(lPos);
        return true;
      } else return false;
    } catch( Exception e ) { return false; }
  }

  public boolean writeBytes( String sText, int iBytes ) {
    try {
      if(file != null) {
        for(int i=0; i<iBytes; i++ ) {
          char c = sText.charAt(i);
          file.writeByte(c);
          }
        return true;
      } else return false;
    } catch( Exception e ) { return false; }
  }

  public String readBytes() {
    try {
      String sTemp = "";
      if(file != null) {
        for(long l = 0;l<returnSize(); l++) {
	         char c = (char)file.readByte();
          sTemp += c;
          }
        return sTemp;
      } else return "";
    } catch( Exception e ) { return ""; }
  }

  public void close() {
    try {
      file.close();
      } catch( Exception e ) { }
  }

  public boolean saveLine( String sLine ) {
    try {
      if(file != null) {
        file.writeBytes(sLine);
        file.writeByte( 13 );
        return true;
        } else return false;
    } catch( Exception e ) { return false; }
  }
}
