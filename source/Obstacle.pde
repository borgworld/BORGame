class Obstacle {
  int x;
  int y;
  int rectWidth;
  int rectHeight;
  int rectFill;
  int speedX;
  int speedY;
  
  Obstacle(int inX, int inY, int inWidth, int inHeight, int inFill) {
    x = inX;
    y = inY;
    rectWidth = inWidth;
    rectHeight = inHeight;
    rectFill = inFill;
    speedX = speedY = 0;
  }

  
  void display() {
    fill(rectFill);
    rect(x, y, rectWidth, rectHeight);
  }
  
  void move() {
    x += speedX;
    y += speedY;
  }
  
  void move(int distX, int distY) {
    x += distX;
    y += distY;
  }
  
  void moveTo(int inX, int inY){
    x = inX;
    y = inY;
  }
  
  void setSpeed(int inSpeedX, int inSpeedY) {
    speedX = inSpeedX;
    speedY = inSpeedY;
  }
  
  void setX(int inX){
    x = inX;
  }
  
  void setY(int inY){
    y = inY;
  }
  
  void setWidth(int inWidth) {
    rectWidth = inWidth;
  }
  
  void setHeight(int inHeight) {
    rectHeight = inHeight;
  }
  
  void setSize(int inWidth, int inHeight) {
    rectWidth = inWidth;
     rectHeight = inHeight;   
  }
  
  void setFill(int inFill){
    rectFill = inFill;
  }
  
  int getX() {
    return x;
  }
  
  int getY(){
    return y;
  }
 
  int getWidth() {
    return rectWidth;
  }
  
  int getHeight() {
    return rectHeight;
  }
  
}