void GUI(){
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
