#include <aJSON.h>
#include <Servo.h>
#include <TinkerKit.h>

int pinServo0 = O1;
int pinServo1 = O2;
int pinLinearPot0 = I0;
int pinLinearPot1 = I1;

aJsonStream serial_stream(&Serial);
aJsonObject *valueJson;
boolean has_msg = false;
aJsonObject *msg;

unsigned long currentMillis;

Servo servo0;
Servo servo1;

TKPotentiometer pot0(pinLinearPot0);
TKPotentiometer pot1(pinLinearPot1);
int *previousValuePot0;
int *previousValuePot1;

void setup()
{
  Serial.begin(9600);
  servo0.attach(pinServo0);
  servo1.attach(pinServo1);
  servo0.write(93);
  servo1.write(93);
}

void send_msg() {
  aJsonObject *msg_o = aJson.createObject();
  
  encodeContiniousServo(intToCharArray(pinServo0), servo0, msg_o);
  encodeContiniousServo(intToCharArray(pinServo1), servo1, msg_o);
  encodeLinearPot(intToCharArray(pinLinearPot0), pot0.read(), msg_o);
  encodeLinearPot(intToCharArray(pinLinearPot1), pot1.read(), msg_o);
  
  aJson.print(msg_o, &serial_stream);
  Serial.println(); /* Add newline. */
  //aJson.deleteItem(msg);
  aJson.deleteItem(msg_o);
}

void encodeContiniousServo(char* pin, Servo servo, aJsonObject *msg_servo) {
  //servo motor
  aJsonObject *subMsg = aJson.createObject();
  int servoValue = servo.read();
  aJson.addNumberToObject(subMsg, "speed",  servoValue);
  char* direction = "nil";
  if(servoValue < 93) {
    direction = "CLOSING"; 
  }
  if(servoValue > 93) {
    direction = "OPENING";
  }
  aJson.addStringToObject(subMsg, "direction", direction);
  aJson.addItemToObject(msg_servo, pin, subMsg);
}

void encodeLinearPot(char* pin, int potValue, aJsonObject *msg_pot) {
    aJsonObject *subMsg = aJson.createObject();
    
    int position = map(potValue, 0, 1023, 0, 100);
    aJson.addNumberToObject(subMsg, "position", position);
    char* state = "nil";
    if(potValue == 0) {
      state = "OPEN";
    }
    if(potValue == 1023) {
      state = "CLOSED";
    }
    aJson.addStringToObject(subMsg, "state", state);
    aJson.addItemToObject(msg_pot, pin, subMsg);
}

char* intToCharArray(int value) {
  String s = String(value);
  char buf[100];
  s.toCharArray(buf, 100);
  return buf;
}

//process json string like this: {"9":{"speed":60},"10":{"speed":93}}
void processServo(int pin, Servo aServo) {
  aJsonObject* speedJson = aJson.getObjectItem(aJson.getObjectItem(msg, intToCharArray(pin)), "speed");
  if(speedJson) {
    aServo.write(speedJson->valueint);
  }
  if(has_msg) {
    send_msg();
  }  
}

void processLinearPot() {
  send_msg();
}

void loop()
{
  if (serial_stream.available()) {
    /* First, skip any accidental whitespace like newlines. */
    serial_stream.skip();
  }

  if (serial_stream.available()) {
    /* Something real on input, let's take a look. */
    msg = aJson.parse(&serial_stream);
    
    has_msg = true;
  }
  processServo(pinServo0, servo0);
  processServo(pinServo1, servo1);
  processLinearPot();
  //processLinearPot(pot1, previousValuePot1);
  
  has_msg = false;
  delay(100);
}
