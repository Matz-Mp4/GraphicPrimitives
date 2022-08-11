package GraphicalTADs;
import java.awt.Color;
import java.awt.Graphics;

import TADs.Circle;

/**
 * Class that deals with the graphic design of a circle. Inherit from circle
 */
public class CircleGr extends Circle {

  private Color circleColor = Color.BLACK; // Default color
  private String circleName = "Generic Name";
  private Color circleNameColor = Color.BLACK;


  /**
   * Constructs a circle at position x, y and with attributes
   * 
   * @param x      coordenate x
   * @param y      coordenate y
   * @param radius radius of circle
   */
  public CircleGr(int x, int y, int radius) {
    super((double) x, (double) y, (double) radius);

  }

  /**
   * Constructs a circle at position x, y and with attributes
   * 
   * @param x           coordenate x
   * @param y           coordenate y
   * @param circleColor circleColor from the circle to be built
   * @param radius      radius of circle
   */
  public CircleGr(int x, int y, int radius, Color circleColor) {
    super((double) x, (double) y, (double) radius);
    this.circleColor = circleColor;
  }

  /**
   * Constructs a circle at position x, y and with attributes
   * 
   * @param x      coordenate x
   * @param y      coordenate y
   * @param name   name from the circle to be built
   * @param radius radius of circle
   */
  public CircleGr(int x, int y, int radius, String name) {
    super((double) x, (double) y, (double) radius);
    this.circleName = name;
  }

  public CircleGr(int x, int y, int radius, Color circleColor, String name) {
    super((double) x, (double) y, (double) radius);
    this.circleColor = circleColor;
    this.circleName = name;
  }

  /**
   * Take the radius of the circle and convert it to radian
   * 
   * @param radius
   * @return
   */
  public double convertToRadian(double radius) {
    return radius * (Math.PI / 180);
  }

  /**
   * Gets the circle's color
   * 
   * @return
   */
  public Color getCircleColor() {
    return circleColor;
  }

  /**
   * Sets the circle's color
   * 
   * @param circleColor
   */
  public void setCircleColor(Color circleColor) {
    this.circleColor = circleColor;
  }

  /**
   * Gets the circle's name
   * 
   * @return
   */
  public String getCircleName() {
    return circleName;
  }

  /**
   * Sets the circle's name
   * 
   * @param circleName
   */
  public void setCircleName(String circleName) {
    this.circleName = circleName;
  }

  /**
   * Gets the circle color
   * 
   * @return
   */
  public Color getCircleNameColor() {
    return circleNameColor;
  }

  /**
   * Sets the color of the border of circle
   * 
   * @param circleNameColor
   */
  public void setCircleNameColor(Color circleNameColor) {
    this.circleNameColor = circleNameColor;
  }

  public void plotPoint(double angule, PointGr point, Graphics g) {
    double radian = convertToRadian(angule);
    double cos = Math.cos(radian);
    double sin = Math.sin(radian);
    double cordX, cordY;

    // 1 quadrant
    cordX = cos * getRadius() + getX();
    cordY = sin * getRadius() + getY();
    cordX = Math.round(cordX);
    cordY = Math.round(cordY);
    point.setX(cordX);
    point.setY(cordY);
    point.drawPoint(g);

    // 2 quadrant
    cordX = getX() - (cos * getRadius());
    cordY = sin * getRadius() + getY();
    cordX = Math.round(cordX);
    cordY = Math.round(cordY);
    point.setX(cordX);
    point.setY(cordY);
    point.drawPoint(g);

    // 3 quadrant
    cordX = getX() - (cos * getRadius());
    cordY = getY() - (sin * getRadius());
    cordX = Math.round(cordX);
    cordY = Math.round(cordY);
    point.setX(cordX);
    point.setY(cordY);
    point.drawPoint(g);

    // 4 quadrant
    cordX = cos * getRadius() + getX();
    cordY = getY() - (sin * getRadius());
    cordX = Math.round(cordX);
    cordY = Math.round(cordY);
    point.setX(cordX);
    point.setY(cordY);
    point.drawPoint(g);

  }

  /**
   * Draw a circle based on it parameters (radius, x and y)
   * 
   * @param g
   */
  public void drawCircle(Graphics g) {
    double row, rowEnd;

    PointGr point = new PointGr();
    rowEnd = 2 * Math.PI * getRadius(); // length of circle
    rowEnd /= 4;

    for (row = 0; row <= rowEnd; row += 0.1) {

      plotPoint(row, point, g);

    }
  }

}