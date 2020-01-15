#include <Stepper.h>

// Buat Object Stepper
const int StepPerRev = 200;
Stepper myStepper(StepPerRev, 8, 9, 10, 11);

const int led = 13;


int high;
int pressure;
int waktu;
String dataIn;
String dt[4];
boolean parsing = false;

void setup() {
  // put your setup code here, to run once:
  myStepper.setSpeed(60);
  Serial.begin(57600);
  dataIn = '#';
  pinMode(led, OUTPUT);
  digitalWrite(led, LOW);
  

}

void loop() {
  // put your main code here, to run repeatedly:
  if (Serial.available() > 0){
    String in = Serial.readStringUntil('\n');
    if (in == "A"){
      Serial.write("A");
      Serial.write('\n');
      digitalWrite(led, HIGH);
    }else{
      Serial.println(in);
      dataIn = in;
      parsing = true;
      if (parsing == true){
      parsingData();
      Serial.print(dt[0]);
      Serial.print('\n');
      Serial.print(dt[1]);
      Serial.print('\n');
      Serial.print(dt[2]);
      Serial.print('\n');
      parsing = false;
      dataIn = '#'; 
 }
    }
//    
// first coding
//    char inChar = (char)Serial.read();
//    dataIn += inChar;
//    if (inChar == '\n'){
//      parsing = true;
//      
//     // second if
//    }
//  // first if
  
  }
// end void loop  

}

void parsingData(){
  int i;
  int j = 0;
  dt[j] = "";
  // proses parsing
  for (i = 1; i < dataIn.length(); i++){
    // pengecekan tiap karakter dengan karakter (#) dan (,)
    if ((dataIn[i]) == '#' || (dataIn[i]) == ','){
      // increment variabel j, digunakan untuk menampuk index array
      j++;
      dt[j] = "";  
    }else{
      // proses tampung data
      dt[j] = dt[j] + dataIn[i];
    }
  }
}
