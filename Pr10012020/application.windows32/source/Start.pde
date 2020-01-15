void Start(){
 println("tombol start oke"); // for debug 
 if (detected == false){
  msgbox("Connect to Arduino first!", "Error"); 
 }else{
  // Get Value from TextBox
  P = cp5.get(Textfield.class, "Pressure").getText();
  H = cp5.get(Textfield.class, "High").getText();
  T = cp5.get(Textfield.class, "Time").getText();
  
  // Convert these value to integer
  int ip = int(P);
  int ih = int(H);
  int it = int(T);
  
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
