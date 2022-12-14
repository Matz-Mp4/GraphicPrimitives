package Primitives2D.Rectangle2D;

import Primitives2D.Line2D.Line;
import Primitives2D.Point2D.Point;

/**
 * Math rectangle
 *
 * @author Gabriel Cavalcanti
 * @version 15/08/2021
 */
public class Rectangle {
  // Diagonal
  private Line diagonal;
  private Line[] lines = new Line[4];
  private int thickness;

  public int getThickness() {
    return thickness;
  }

  public void setThickness(int thickness) {
    this.thickness = thickness;
  }

  public Rectangle(double x1, double y1, double x2, double y2) {

    setDiagonal(new Line(x1, y1, x2, y2));
    convertToRectangle(x1, y1, x2, y2);
    thickness = 2;
  }

  public Rectangle(Line diagonal) {
    convertToRectangle(diagonal.getP1().getX(), diagonal.getP1().getY(), diagonal.getP2().getX(),
        diagonal.getP2().getY());
    setDiagonal(
        new Line(diagonal.getP1().getX(), diagonal.getP1().getY(), diagonal.getP2().getX(), diagonal.getP2().getY()));

    thickness = 2;
  }

  public void setDiagonal(Line line) {
    this.diagonal = line;
  }

  public Line getDiagonal() {
    return diagonal;
  }

public void updateLines(RectangleGr rectangle){
    lines[0] = rectangle.getLine(0);
    lines[1] = rectangle.getLine(1);
    lines[2] = rectangle.getLine(2);
    lines[3] = rectangle.getLine(3);
  }


  public boolean belongs(Point p) {
    boolean veri = false;
    int i = 0;
    while ((veri == false) && (i < 4)) {
      veri = lines[i].belongs(p);
      i++;
    }

    return veri;
  }

  /*
   * public Line getL1(){ return lines[0]; }
   * 
   * public Line getL2(){ return lines[1]; }
   * 
   * public Line getL3(){ return lines[2]; }
   * 
   * public Line getL4(){ return lines[3]; }
   */
  public Line getLine(int i) {
    return lines[i];
  }

  public void convertToRectangle(double x1, double y1, double x2, double y2) {
    // Vertical
    lines[3] = new Line(x1, y1, x1, y2);
    // Vertical
    lines[2] = new Line(x2, y1, x2, y2);
    // Horizonal
    lines[1] = new Line(x1, y1, x2, y1);
    // Horizontal
    lines[0] = new Line(x1, y2, x2, y2);
  }



}
