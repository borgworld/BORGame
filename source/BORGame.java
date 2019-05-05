import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Random; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class BORGame extends PApplet {



//color's
int black = 0xff000500;
int green = 0xff0AC896;
int cleargreen=0xff66F20A;
int darkgreen=0xff0FA517;
int red = 0xffE80E45;
int blue = 0xff0410D6;
int clearblue=0xff0AB6F2;
int secondblue=0xff14A1E0;
int yellow=0xffEEF20A;
int pink= 0xffE305F0;
int purple=0xff7908CB;
int orange=0xffFA6E17;
int white = 0xffFFFFFF;

int vitesseJoueur=0;
int vitesseJoueurX=0;

// Obstacles 
Obstacle[] obstacleRect;
int numObstacles;

// BallObstacles
ObstacleEllipse[] obstacleBall;
int numBalls;

// Ball
Ball ball;

// 1
int obstacleTriangulaire1A=213;
int obstacleTriangulaire1B=390;
int obstacleTriangulaire1C=390;

// 2
int obstacleTriangulaire2A=10;
int obstacleTriangulaire2B=187;
int obstacleTriangulaire2C=10;

// 1
int obstacleTriangulaireY1A=10;
int obstacleTriangulaireY1B=10;
int obstacleTriangulaireY1C=360;

// 2
int obstacleTriangulaireY2A=10;
int obstacleTriangulaireY2B=10;
int obstacleTriangulaireY2C=358;

//Levels
int level=1;
int restart=0;

//boolean's
boolean haut= true;
boolean droiteObstacle4= true;
boolean droiteObstacle5=false;
boolean droiteObstacle6=true;
boolean hautObstacle7=true;
boolean droiteObstacle7=true;
boolean saut= false;

public void drawText(int posX, int posY, int textColor, int textSize, String theText) {
  fill(textColor);
  textSize(textSize);
  text(theText, posX, posY);
}
public boolean ballIntersectsRect(Ball inBall, Obstacle inObstacleRect) {
  int deltaX = inBall.getX() - max(inObstacleRect.getX(), min(inBall.getX(), inObstacleRect.getX() + inObstacleRect.getWidth()));
  int deltaY = inBall.getY() - max(inObstacleRect.getY(), min(inBall.getY(), inObstacleRect.getY() + inObstacleRect.getHeight()));
  return (deltaX * deltaX + deltaY * deltaY) < (inBall.getRadius() * inBall.getRadius());
}
public void crashMessage() {
  background(black);
  drawText(55, 350, white, 40, "You lost,Loser!");
  drawText(50, 390, red, 40, "To exit press 'X'");
  drawText(25, 430, secondblue, 40, "To restart press 'R'");
}
public void setup() {
  
  numObstacles = 24;
  obstacleRect = new Obstacle[numObstacles];
  obstacleRect[0] = new Obstacle(150, 300, 100, 10, pink);  // Obstacle 1
  obstacleRect[1] = new Obstacle(150, 200, 100, 10, purple);  // Obstacle 2
  obstacleRect[2] = new Obstacle(150, 300, 100, 10, darkgreen);  // Obstacle 3
  obstacleRect[3] = new Obstacle(0, 100, 10, 100, red);  // Obstacle 4
  obstacleRect[4] = new Obstacle(390, 100, 10, 100, red);  // Obstacle 5
  obstacleRect[5] = new Obstacle(50, 0, 100, 700, orange);  // Obstacle 6
  obstacleRect[6] = new Obstacle(105, 101, 10, 10, red);  // Obstacle 7
  obstacleRect[7] = new Obstacle(0, 600, 160, 100, red);  // Obstacle 8
  obstacleRect[8] = new Obstacle(230, 600, 170, 100, green);  // Obstacle 9
  obstacleRect[9] = new Obstacle(0, 550, 170, 50, red);  // Obstacle 10
  obstacleRect[10] = new Obstacle(0, 500, 270, 30, red);  // Obstacle 11
  obstacleRect[11] = new Obstacle(340, 500, 60, 50, green);  // Obstacle 12
  obstacleRect[12] = new Obstacle(340, 550, 60, 50, green);  // Obstacle 13
  obstacleRect[13] = new Obstacle(-200, 145, 200, 10, red);  // Obstacle 4
  obstacleRect[14] = new Obstacle(390, 145, 200, 10, red);  // Obstacle 5
  obstacleRect[15] = new Obstacle(50, 450, 240, 50, green);
  obstacleRect[16] = new Obstacle(350, 350, 60, 150, red);
  obstacleRect[17] = new Obstacle(110, 350, 275, 30, red);
  obstacleRect[18] = new Obstacle(0, 350, 30, 200, red);
  obstacleRect[19] = new Obstacle(340, 350, 60, 150, red);
  obstacleRect[20] = new Obstacle(0, 600, 170, 100, red); 
  obstacleRect[21] = new Obstacle(0, 500, 290, 50, red); 
  obstacleRect[22] = new Obstacle(0, 350, 50, 200, red);
  obstacleRect[23] = new Obstacle(110, 350, 290, 50, red);
///////////////////////////////////////////////////////////
  ball = new Ball(200, 690, 20, clearblue);
///////////////////////////////////////////////////////////
  numBalls = 1;
  obstacleBall = new ObstacleEllipse[numBalls];
  obstacleBall[0] = new ObstacleEllipse(30, 100, 15, red);
}

public void draw() {
   background(blue);
  ball.display();
  //jjjjjjjjjjjjjjjjjjjjjjjjjjj
  ball.move(vitesseJoueurX, vitesseJoueur + 1); 
  //jjjjjjjjjjjjjjjjjjjjjjjjjjj

  obstacleRect[0].display();  // Obstacle 1
  obstacleRect[1].display();  // Obstacle 2

  textSize(25);
  fill(0);
  text("level"+level, 1, 25);
  if (level==1) {

    textSize(20);
    fill(red);
    text("->you can move up with 'W'", 50, 390);
    text("->you can move left with'A'", 50, 410);
    text("->you can move right with'D'", 50, 430);

    textSize(20);
    fill(0);
    text("Difficulty: ", 250, 25);
    fill(clearblue);
    text("easy", 350, 25);
  }

  if (obstacleRect[1].getX() + 100 < 0) {
    obstacleRect[1].setX(499);
  }

  if (obstacleRect[0].getX() > 400) {
    obstacleRect[0].setX(-99);
  }

  obstacleRect[1].move(-3, 0);
  obstacleRect[0].move(5, 0);

  //Level
  if (ball.getY()<0) {
    level=level+1;
  }
  // Level 2
  ///////////////////////
  ////////////////
  if (level==2) {
    textSize(20);
    text("Difficulty: ", 250, 25);
    fill(clearblue);
    text("easy", 350, 25);

    obstacleRect[2].display();
    obstacleRect[0].setY(500);
    obstacleRect[1].setY(450);
    obstacleRect[0].move(11, 0);
    obstacleRect[1].move(-11, 0);
    obstacleRect[2].move(15, 0);
    if (obstacleRect[2].getX() > 400) {
      obstacleRect[2].setX(-99);
    }
    if(ballIntersectsRect(ball,obstacleRect[2])){
      crashMessage();
      noLoop();
    }
  }
  //level3
  //////////////////
  //////////////////
  //verticale level/
  //////////////////
  //////////////////
  if (level==3) {
    textSize(20);
    text("Difficulty: ", 210, 25);
    fill(orange);
    text("medium", 310, 25);

    obstacleRect[3].display();
    obstacleRect[4].display();
    obstacleRect[13].display();
    obstacleRect[14].display();
    obstacleRect[0].move(10, 0);
    obstacleRect[1].move(-10, 0);
    if(ballIntersectsRect(ball,obstacleRect[3])){
      crashMessage();
      noLoop();
    }

    if(ballIntersectsRect(ball,obstacleRect[4])){
      crashMessage();
      noLoop();
    }
  }

  //////////////
  //////////////
  //level4//////
  //////////////
  //////////////

  if (level==4) {
    obstacleRect[0].setX(1000);
    obstacleRect[1].setX(1000);
    textSize(20);
    text("Difficulty: ", 210, 25);
    fill(orange);
    text("medium", 310, 25);
    textSize(25);
    fill(0);
    text("level"+level, 1, 25);
    obstacleRect[5].setFill(orange);
    obstacleRect[5].display();
   if(ballIntersectsRect(ball,obstacleRect[5])){
     crashMessage();
      noLoop();
    }
  }

  ///////////
  ///////////
  //level5///
  ///////////
  if (level==5) {
    fill(0);
    textSize(20);
    text("Difficulty: ", 210, 25);
    fill(red);
    text("hard", 310, 25);
    obstacleRect[0].setX(1000);
    obstacleRect[1].setX(1000);
    obstacleRect[5].setFill(red);
    obstacleRect[5].display();
    if (obstacleRect[5].getX() < 0) {
      droiteObstacle6=true;
    }

    if (obstacleRect[5].getX() + 100 > 400) {
      droiteObstacle6=false;
    }
    if(ballIntersectsRect(ball,obstacleRect[5])){
      crashMessage();
      noLoop();
    }
  }
  ///////////
  //level6///
  ///////////
  if (level==6) {
    drawText(250, 25, white, 20, "Difficulty: ");
    drawText(350, 25, clearblue, 20, "easy");
    obstacleRect[0].setX(1000);
    obstacleRect[1].setX(1000);
    drawText(115, 400, black, 30, " SpawnPoint");
    drawText(1, 699, white, 30, "level"+level);
    fill(green);
    triangle(obstacleTriangulaire1A, obstacleTriangulaireY1A, obstacleTriangulaire1B, obstacleTriangulaireY1B, obstacleTriangulaire1C, obstacleTriangulaireY1C);
    fill(red);
    triangle(obstacleTriangulaire2A, obstacleTriangulaireY2A, obstacleTriangulaire2B, obstacleTriangulaireY2B, obstacleTriangulaire2C, obstacleTriangulaireY2C);
    drawText(75, 300, white, 30, "go in to the path");
    drawText(198, 270, white, 30, "|");
    drawText(194, 259, white, 30, "^");
  }

  ///////////
  //level7///
  ///////////
  if (level==7) {
    obstacleRect[0].setX(1000);
    obstacleRect[1].setX(1000);
    obstacleRect[7].display();
    obstacleRect[8].display();
    obstacleRect[9].display();
    obstacleRect[10].display();
    obstacleRect[11].display();
    obstacleRect[12].display();
    obstacleRect[15].display();
    obstacleRect[16].display();
    obstacleRect[17].display();
    obstacleRect[18].display();
    obstacleRect[19].display();
    obstacleRect[20].display();
    obstacleRect[21].display();
    obstacleRect[22].display();
    obstacleRect[23].display();
    //obstacleBall[0].displayBall();
    drawText(0,670,green,30,"Dangerous");
    drawText(270,670,red,30,"Safe");
    if (ball.getX() + 10 > obstacleRect[8].getX() && ball.getY() > obstacleRect[8].getY()) {
     
      ball.setX(220);
    }
    if (ball.getY() + 10 > obstacleRect[8].getY() && ball.getX() > obstacleRect[8].getX()) {
      
      ball.setY(590);
    }
    if (ball.getY() + 10 > obstacleRect[11].getY() && ball.getX() > obstacleRect[11].getX()) {
    
      ball.setY(491);
    }
    if (ball.getX() + 10 > obstacleRect[11].getX() && ball.getY() > obstacleRect[11].getY()) {
   
      ball.setX(330);
    }

    if (ball.getY() + 20 > obstacleRect[15].getY() && ball.getY() < obstacleRect[15].getY() + 50 && ball.getX() > obstacleRect[15].getX() && ball.getX() < obstacleRect[15].getX() + 250) {
       ball.setY(440);
    }
    if(ballIntersectsRect(ball,obstacleRect[7])){
      crashMessage();
      noLoop();
    }

    if(ballIntersectsRect(ball,obstacleRect[10])){
      crashMessage();
      noLoop();
    }
        if(ballIntersectsRect(ball,obstacleRect[9])){
      crashMessage();
      noLoop();
    }
        if(ballIntersectsRect(ball,obstacleRect[16])){
      crashMessage();
      noLoop();
    }
        if(ballIntersectsRect(ball,obstacleRect[17])){
      crashMessage();
      noLoop();
    }
        if(ballIntersectsRect(ball,obstacleRect[18])){
      crashMessage();
      noLoop();
    }
    
  }

  if(ballIntersectsRect(ball,obstacleRect[1])){
    crashMessage();
    noLoop();
  }

  if(ballIntersectsRect(ball,obstacleRect[0])){
    crashMessage();
    noLoop();
  }

  //obstacle4
  if (droiteObstacle4 == true) {
    obstacleRect[3].move(9, 0);
    obstacleRect[13].move(9, 0);
  } else if (droiteObstacle4==false) { 
    obstacleRect[3].move(-9, 0);
    obstacleRect[13].move(-9, 0);
  }
  //obstacle5
  if (droiteObstacle5 == true) {
    obstacleRect[4].move(9, 0);
    obstacleRect[14].move(9, 0);
  } else if (droiteObstacle5 == false) {
    obstacleRect[4].move(-9, 0);
    obstacleRect[14].move(-9, 0);
  }
  //obstacle4
  if (obstacleRect[3].getX() + 10 > 200) {
    droiteObstacle4=false;
  } else if (obstacleRect[3].getX() < 0) {
    droiteObstacle4=true;
  }

  //obstacle5
  if (obstacleRect[4].getX() < 200) {
    droiteObstacle5=true;
  } else if (obstacleRect[4].getX() > 390) {
    droiteObstacle5=false;
  }

  //Obstacle6
  if (droiteObstacle6 == true) {
    obstacleRect[5].move(5, 0);
  } else if (droiteObstacle6 == false) {
    obstacleRect[5].move(-5, 0);
  }
  if (obstacleRect[5].getX() > 400) {
    obstacleRect[5].setX(-101);
  }

  obstacleRect[6].move(7, 5);

  if (haut == true) {
    
    ball.move(0, -1);
  } else if (haut == false) {

    ball.move(0, 1);
  }

  if (ball.getY() > 690) {
  
    ball.setY(690);
  }
  if (ball.getX() > 400) {

    ball.setX(0);
  }
  if (ball.getX() < 0) {

    ball.setX(400);
  }

  if (ball.getY() < 0) {
  
    ball.setY(690);
  }
  if (restart > 1) {
    exit();
  }
  if (saut == true) {
    vitesseJoueur = -10;
  } else if (saut == false) {
    vitesseJoueur = +5;
  }
}
public void keyPressed() {
  if (key =='\u2044'){
  level=1;
  }
  if (key =='\u20ac'){
  level=2;
  }  
  if (key =='\u2039'){
  level=3;
  }
  if (key =='\u203a'){
  level=4;
  }
  if (key =='\ufb01'){
  level=5;
  }
  if (key =='\ufb02'){
  level=6;
  }
  if (key =='\u2021'){
  level=7;
  }
  if (key =='\u00b0'){
  level=8; 
 }
  if ( key == 'w') {
    saut=true;
  }
  if (key=='d') {
    vitesseJoueurX=+5;
  }
  if (key=='a') {
    vitesseJoueurX=-5;
  }
  if (key=='x') {
    exit();
  }
  if (key=='r' && level<6) {
    level=1;
    //positionY=690;
    ball.setY(690);
    restart=restart+1;
    loop();
  } else if (key=='r' && level>6) {
    level=6;
    //positionY=690;
    ball.setY(690);
    restart=restart+1;
    loop();
  }
}
public void keyReleased() {
  if (key=='w') {
    saut=false;
  }
  if (key=='d') {
    vitesseJoueurX=+0;
  }
  if (key=='a') {
    vitesseJoueurX=+0;
  }
}
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

  public void display(boolean randFill) {
    if (randFill) {
      Random generator = new Random(); 
      int i = generator.nextInt(10);
      fillColor = colors[i];
    }
    noStroke();
    fill(fillColor);
    ellipse(positionX, positionY, radius, radius);
  }

  public void display(int useColor) {
    fillColor = useColor;
    display(false);
  }

  public void display() {
    display(false);
  }

  public void move(int distX, int distY) {
    positionX += distX;
    positionY += distY;
  }

  public int getX() {
    return positionX;
  }

  public int getY() {
    return positionY;
  }

  public void setX(int inX) {
    positionX = inX;
  }

  public void setY(int inY) {
    positionY = inY;
  }

  public void setFill(int inFill) {
    fillColor = inFill;
  }

  public int getRadius() {
    return radius;
  }
}
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

  
  public void display() {
    fill(rectFill);
    rect(x, y, rectWidth, rectHeight);
  }
  
  public void move() {
    x += speedX;
    y += speedY;
  }
  
  public void move(int distX, int distY) {
    x += distX;
    y += distY;
  }
  
  public void moveTo(int inX, int inY){
    x = inX;
    y = inY;
  }
  
  public void setSpeed(int inSpeedX, int inSpeedY) {
    speedX = inSpeedX;
    speedY = inSpeedY;
  }
  
  public void setX(int inX){
    x = inX;
  }
  
  public void setY(int inY){
    y = inY;
  }
  
  public void setWidth(int inWidth) {
    rectWidth = inWidth;
  }
  
  public void setHeight(int inHeight) {
    rectHeight = inHeight;
  }
  
  public void setSize(int inWidth, int inHeight) {
    rectWidth = inWidth;
     rectHeight = inHeight;   
  }
  
  public void setFill(int inFill){
    rectFill = inFill;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY(){
    return y;
  }
 
  public int getWidth() {
    return rectWidth;
  }
  
  public int getHeight() {
    return rectHeight;
  }
  
}
  class ObstacleEllipse{
  
  int ballX;
  int ballY;
  int RadiusA;
  int ballFill;
  
  ObstacleEllipse(int inX,int inY, int Radius, int inFill){
  ballX = inX;
  ballY = inY;
  RadiusA = Radius;
  ballFill = inFill;
  
 
  }
    
  //int getBallX() {
  //  return ballX;
  //}
  
  //int getBallY(){
  //  return ballY;
  //}
  //int getRadius(){
  //  return RadiusA;
  //}
  //  void displayBall() {
  //  fill(ballFill);
  //  ellipse(ballX, ballY, RadiusA, RadiusA);
  //
}
 
  public void settings() {  size(400, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "BORGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
