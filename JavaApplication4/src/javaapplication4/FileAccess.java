//Title:        FileAccess.java
//Version:      v1.0.0
//Debugged:     Checked.
//Copyright:    Copyright (c) Dec 2001
//Author:       Jon Robinson
//Company:      None
//Description:  This class is for allowing disk accessing.  Yoou can create a file and
//              save contents.  This has been slightly modified from its initial incarnation
//              as we only need to use sequential file access, instead of direct access to any
//              point in the file.  However, the base functionality still exists to do this.

import java.io.File;
 
public class FileAccess extends fileHandle { 
 
  public FileAccess() {
    super(); 
    } 
 
  public void openFile( String sName ) {
    if( doesFileExist( sName ) ) 
      open(sName); 
    else 
      createFile(sName); 
  } 
 
  public void closeFile() {
    close(); 
  } 
 
  public void createFile( String sName ) {
    open(sName); 
    moveFP(0); 
  } 
 
  public boolean doesFileExist( String sFileName ) {
    // need to see if the file exists. 
    File f = new File( sFileName ); 
    if( f.exists() ) return true; 
    else return false; 
  } 
 
  public synchronized void writeLine( String sText ) {
    char c = 13;
    writeBytes( (sText + c), sText.length() ); 
 
  } 
 
  public synchronized String readLine() {

    return readBytes();

  }

  private String format( String sText, int iSize ) {
    String sTemp = "";
    sText = sText.trim();
    for(int i = 0; i< iSize; i++ ) {
      if( i<sText.length() )
        sTemp += sText.charAt(i);
      else break;
    }
    for(int i = sTemp.length(); i<iSize; i++ ) sTemp+=" ";
    return sTemp;
  }

  private String format( int iText, int iSize ) {
    return format( Integer.toString( iText ).trim(), iSize );
  }

  private String decode( String sText, int iStart, int iEnd ) {
    String sTemp = "";
    for(int i=iStart;i<(iStart+iEnd); i++ ) {
      // check to see if there is no boundary errors
      if(i<sText.length())
        sTemp+=sText.charAt(i);
      }
    // now get rid of any spaces and return the value back
    return sTemp.trim();
  }

  private int StoI( String sText ) {
    if( bDisplayText ) System.err.println("Converting >"+sText+"<");
    // make sure there is something to do
    int iReturn = 0;
    try {
      iReturn = Integer.parseInt(sText);
      } catch(Exception e ) {}
    return iReturn;
  }

}
