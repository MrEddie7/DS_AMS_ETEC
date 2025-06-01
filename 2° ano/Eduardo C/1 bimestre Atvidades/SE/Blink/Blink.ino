// C++ code
//
int Led =5;
int led =3;
void setup()
{
  pinMode(Led, OUTPUT);
  pinMode(led, OUTPUT);
  
}

void loop()
{
  
  digitalWrite(led, HIGH);
  delay(100); // Wait for 1000 millisecond(s)
  digitalWrite(led, LOW);
  delay(10); // Wait for 1000 millisecond(s)
  
  
  digitalWrite(Led, HIGH);
  delay(100); // Wait for 1000 millisecond(s)
  digitalWrite(Led, LOW);
  delay(10); // Wait for 1000 millisecond(s)
  
  
  
}
