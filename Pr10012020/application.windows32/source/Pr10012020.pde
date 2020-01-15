// Import Library
import java.util.*;
import java.io.*;
import java.lang.Object;
import controlP5.*;
import processing.serial.*;

import meter.*;


// Buat Objek
ControlP5 cp5;
Serial ard;
Meter m;


// Inisialisasi variabel
String tekanan;
String P;
String H;
String T;
Boolean detected = true;
Boolean firstContact = false;
String currentFile = "About";
String dataIn;
int NL = 10;

Textlabel myTextlabelA, output, psi, cm, second;
PFont fnt, fon;


// =============================================
void setup(){
 size(400,300);
 background(100);
 noStroke();
 GUI();
 
 fnt = createFont("Arial", 15, true);
 fon = createFont("Lucida Sans", 10, true);
 
 try{
  ard = new Serial(this, Serial.list()[0], 57600);
 }
 catch(Exception e){
  e.printStackTrace(); 
  msgbox("Connect to Arduino", "Error!");
  detected = false;
  
 }
 
 // ard = new Serial(this, Serial.list()[0], 57600);

 // Meter
 m = new Meter(this, 155, 30, false);
 m.setMeterWidth(230);
 m.setMinScaleValue(0);
 m.setMaxScaleValue(120);
 m.setDisplayDigitalMeterValue(true);
 m.setTitle("Pressure");
 
 String[] scaleLabels = {"0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120"};
 m.setScaleLabels(scaleLabels);
 m.setShortTicsBetweenLongTics(9);
  
}

// ============================================
void draw(){
 background(100);
 rect(0, 230, 400, 5, 7);
 noStroke();
 if (detected == false){
   text(">> Arduino or serial device must be unplugged.", 20, 250);
   text("     (unplug device and restart this application if not)", 20, 265);
   text(">> Plug the Arduino or serial device into a USB port.", 20, 280);
 }else{
   text(">> Arduino detected!", 20, 250);
   if (firstContact == false){
     text(">> Connection not Ready!", 20, 275);
   }else{
     text(">> Connection Ready!", 20, 275);
   }
 }
 // 
 int ra = int(random(0.0,120.0));
 m.updateMeter(ra);
 
 
 
}

void About(){
 openInNotepad(); 
}

void openInNotepad() {
  // open in notepad

  try
  {
    ProcessBuilder pb = new ProcessBuilder("notepad.exe", currentFile, "");
    Process p = pb.start();
  }
  catch ( Exception /* IOException, URISyntaxException */ e )
  {
    e.printStackTrace();
  }//catch
}//

void Test(){
 if (detected == true && firstContact == false){
  ard.write('A');
  ard.write('\n');
 }else{
   msgbox("Connect to Arduino first!", "Error"); 
 }
}


void serialEvent(Serial ard) {
  String val = ard.readStringUntil(NL);
  if (val != null){
    val = trim(val);
    print("Nilai diterima: ");
    println(val);
    
    //int cal = int(val);
    //print("Konversi Integer: ");
    //println(cal);
    if (firstContact == false){
      if (val.equals("A")){
        ard.clear();
        firstContact = true;
      }
    }else{
      print("Nilai diterima2: ");
      println(val);
    }
  }
}
