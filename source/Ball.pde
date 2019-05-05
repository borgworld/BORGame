class Ball {
  int positionX;
  int positionY;
  int radius;
  int fillColor;
  int colors[];

  Ball(int inX, int inY, int inRadius, int inFillColor) {
    positionX = inX;
    positionY = inY;
    radius = inRadius;
    fillColor = inFillColor;
    colors = new int[10];
    colors[0] = green;
    colors[1] = cleargreen;
    colors[2] = darkgreen;
    colors[3] = red;
    colors[4] = blue;
    colors[5] = secondblue;
    colors[6] = yellow;
    colors[7] = pink;
    colors[8] = purple;
    colors[9] = orange;
  }

  void display(boolean randFill) {
    if (randFill) {
      Random generator = new Random(); 
      int i = generator.nextInt(10);
      fillColor = colors[i];
    }
    noStroke();
    fill(fillColor);
    ellipse(positionX, positionY, radius, radius);
  }

  void display(int useColor) {
    fillColor = useColor;
    display(false);
  }

  void display() {
    display(false);
  }

  void move(int distX, int distY) {
    positionX += distX;
    positionY += distY;
  }

  int getX() {
    return positionX;
  }

  int getY() {
    return positionY;
  }

  void setX(int inX) {
    positionX = inX;
  }

  void setY(int inY) {
    positionY = inY;
  }

  void setFill(int inFill) {
    fillColor = inFill;
  }

  int getRadius() {
    return radius;
  }
}