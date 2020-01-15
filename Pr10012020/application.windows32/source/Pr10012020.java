import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 
import java.io.*; 
import java.lang.Object; 
import controlP5.*; 
import processing.serial.*; 
import meter.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Pr10012020 extends PApplet {

// Import Library









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
public void setup(){
 
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
public void draw(){
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
 int ra = PApplet.parseInt(random(0.0f,120.0f));
 m.updateMeter(ra);
 
 
 
}

public void About(){
 openInNotepad(); 
}

public void openInNotepad() {
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

public void Test(){
 if (detected == true && firstContact == false){
  ard.write('A');
  ard.write('\n');
 }else{
   msgbox("Connect to Arduino first!", "Error"); 
 }
}


public void serialEvent(Serial ard) {
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
public void GUI(){
 PFont ff;
 float dx = (400-270)/4;
 ff = createFont("Arial", 15);
 cp5 = new ControlP5(this);
  myTextlabelA = cp5.addTextlabel("label")
                    .setText("DATA INPUT")
                    .setPosition(10, 5)
                    .setColorValue(0xffffff00)
                    .setFont(ff)
                    ;
                    
  output = cp5.addTextlabel("label1")
              .setText("MONITORING")
              .setPosition(200, 5)
              .setColorValue(0xffffff00)
              .setFont(createFont("Georgia", 15))
              ;
              
  cp5.addTextfield("Pressure")
     .setPosition(15, 30)
     .setText("90")
     .setSize(70, 30)
     .setFocus(true)
     .setColor(color(255, 255, 255))
     ;
     
  cp5.addTextlabel("Psi")
     .setText("Psi")
     .setPosition(90, 35)
     .setFont(ff)
     .setColor(color(255))
     ;
  
  cp5.addTextfield("High")
     .setPosition(15, 80)
     .setText("5")
     .setSize(70, 30)
     .setFocus(true)
     .setColor(color(255, 255, 255))
     ;
  
  cp5.addTextlabel("cm")
     .setText("cm")
     .setPosition(90, 85)
     .setFont(ff)
     .setColor(color(255))
     ;
  
  cp5.addTextfield("Time")
     .setPosition(15, 130)
     .setText("5")
     .setSize(70, 30)
     .setFocus(true)
     .setColor(color(255, 255, 255))
     ;
     
  cp5.addTextlabel("second")
     .setText("Sec.")
     .setPosition(90, 135)
     .setFont(ff)
     .setColor(color(255))
     ;
     
  cp5.addButton("Start")
     .setPosition(dx, 183)
     .setSize(90, 30)
     ;
     
  cp5.addButton("Test")
     .setPosition(dx+dx+90, 183)
     .setSize(90, 30)
     ;
  
  cp5.addButton("About")
     .setPosition(3*dx+2*90, 183)
     .setSize(90, 30)
     ;
     
  //cp5.addTextfield("Output Pressure")
  //   .setPosition(205, 30)
  //   .setSize(70, 30)
  //   .setFont(ff)
  //   .setFocus(true)
  //   .setColor(color(255, 255, 255))
  //   ;
     
  //cp5.addTextfield("waktu")
  //   .setPosition(205, 90)
  //   .setSize(70, 30)
  //   .setFont(ff)
  //   .setFocus(true)
  //   .setColor(color(255, 255, 255))
  //   ;
           
}
public void Start(){
 println("tombol start oke"); // for debug 
 if (detected == false){
  msgbox("Connect to Arduino first!", "Error"); 
 }else{
  // Get Value from TextBox
  P = cp5.get(Textfield.class, "Pressure").getText();
  H = cp5.get(Textfield.class, "High").getText();
  T = cp5.get(Textfield.class, "Time").getText();
  
  // Convert these value to integer
  int ip = PApplet.parseInt(P);
  int ih = PApplet.parseInt(H);
  int it = PApplet.parseInt(T);
  
  // Ceck the value is integer and must greater than zero
  if (ip == 0){
   msgbox("'Pressure' Harus Angka dan Lebih dari Nol", "warning"); 
  }else if(ih == 0){
   msgbox("'High' Harus Angka dan Lebih dari Nol", "warning");  
  }else if(it == 0){
   msgbox("'Time' Harus Angka dan Lebih dari Nol", "warning");  
  }else{
  println(ip, ih, it);
  ard.write('#');
  ard.write(P);
  ard.write(',');
  ard.write(H);
  ard.write(',');
  ard.write(T);
  ard.write('\n');
  
  }
 }
 
}
public void msgbox(String Msg, String Titel){
  javax.swing.JOptionPane.showMessageDialog ( null, Msg, Titel, javax.swing.JOptionPane.INFORMATION_MESSAGE);
}
  public void settings() {  size(400,300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Pr10012020" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
