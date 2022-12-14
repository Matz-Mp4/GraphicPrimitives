package DataStruct.JsonData;

import DataStruct.LinkedList.DoublyLinkedList;
import DataStruct.LinkedList.Node;
import DataStruct.List.PrimitiveList;
import Primitives2D.PrimitiveGr2D;
import Primitives2D.Circle2D.Circle;
import Primitives2D.Circle2D.CircleGr;
import Primitives2D.Line2D.LineGr;
import Primitives2D.Point2D.Point;
import Primitives2D.Point2D.PointGr;
import Primitives2D.Polygon2D.Polygon;
import Primitives2D.Polygon2D.PolygonalLineGr;
import Primitives2D.Rectangle2D.RectangleGr;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 * Class that creates JSON files
 * @version 1.0.0
 */
public class JsonFile {

  private PrimitiveList list;
  private JSONObject mainJson;
  private String primitives[];
  private final String PATH = "Save/";
  private Dimension screen;

  public JsonFile(PrimitiveList list, Dimension screen) {
    this.list = list;
    this.screen = screen;
    primitives = list.getTypes();
  }

  /**
   * Given a file name, creates a JSON based on the primitive list that handles the data 
   * of primitive 2D
   * @param fileName
   */
  public void createJSON(String fileName) {
    mainJson = new JSONObject();

    JSONArray lineArray = new JSONArray();
    JSONArray circleArray = new JSONArray();
    JSONArray rectangleArray = new JSONArray();
    JSONArray polygonArray = new JSONArray();
    JSONArray polygonLineArray = new JSONArray();

    for (DoublyLinkedList primitiveList : list.getLinkedList()) {
      Node aux = primitiveList.getBegin();
      String valor = primitiveList.getType();
      switch (valor) {
        case "Line":
          lineToJson(lineArray, aux);
          mainJson.put("Line", lineArray);
          break;
        case "Circle":
          circleToJson(circleArray, aux);
          mainJson.put("Circle", circleArray);
          break;
        case "Rectangle":
          rectangleToJson(rectangleArray, aux);
          mainJson.put("Rectangle", rectangleArray);
          break;
        case "Polygon":
          polygonToJson(polygonArray, aux);
          mainJson.put("Polygon", polygonArray);
          break;
        case "Polygonal Line":
          polygonLineToJson(polygonLineArray, aux);
          mainJson.put("Polygonal Line", polygonLineArray);
          break;
      }
    }

    createFile(fileName, mainJson);
  }

  /**
   * Method that recovers a JSON file given a file name. With this JSON.
   * It adds the JSON primitives to the application
   * For every primitive in the JSON array it iterates over and retrieves the primitives
   * @param file
   * @param list
   */
  public void getJson(File file, PrimitiveList list) {
    BufferedReader br = null;
    FileReader fr = null;

    // file locale
    if (!file.exists()) { // tests if the file is archived
      JOptionPane.showInputDialog("File does not exist");
    }
    String jsonString = "";
    try {
      String jsonString2 = "";

      fr = new FileReader(file);
      br = new BufferedReader(fr);

      while ((jsonString2 = br.readLine()) != null) {
        jsonString += jsonString2;
      }

    } catch (FileNotFoundException ex) {
      JOptionPane.showInputDialog("Files does not exist");
    } catch (Exception ex) {
      JOptionPane.showInputDialog("Erro inesperado ao tentar abrir o arquivo: ");
      ex.printStackTrace();
    } finally {

    }

    JSONObject jsonObj = new JSONObject(jsonString);

    if (jsonObj != null) {
      list.deleteAll();
      // Add lines
      JSONArray lines = jsonObj.getJSONArray("Line");
      for (int i = 0; i < lines.length(); i++) {
        JSONObject line = lines.getJSONObject(i);

        JSONObject p1 = line.getJSONObject("p1");
        double x1 = p1.getDouble("x")*screen.getWidth();
        double y1 = p1.getDouble("y")*screen.getHeight();

        JSONObject p2 = line.getJSONObject("p2");
        double x2 = p2.getDouble("x")*screen.getWidth();
        double y2 = p2.getDouble("y")*screen.getHeight();

        JSONObject color = line.getJSONObject("color");
        int r = color.getInt("r");
        int g = color.getInt("g");
        int b = color.getInt("b");

        LineGr lineGr = new LineGr((int)x1,(int) y1,(int) x2,(int) y2);
        lineGr.setLineColor(new Color(r, g, b));
        list.add(lineGr, "Line");
      }

      //Iteracting over array of circles
      JSONArray circles = jsonObj.getJSONArray("Circle");
      for (int i = 0; i < circles.length(); i++) {
        JSONObject circle = circles.getJSONObject(i);

        JSONObject point = circle.getJSONObject("point");
        double x = point.getDouble("x")*screen.getWidth();
        double y = point.getDouble("y")*screen.getHeight();

        double radius = circle.getDouble("radius");

        JSONObject color = circle.getJSONObject("color");
        int r = color.getInt("r");
        int g = color.getInt("g");
        int b = color.getInt("b");

        CircleGr circleGr = new CircleGr((int) x, (int) y, (int) radius, "");
        circleGr.setCircleColor(new Color(r, g, b));
        list.add(circleGr, "Circle");
      }

      //Iteracting over array of rectangles
      JSONArray retangles = jsonObj.getJSONArray("Rectangle");
      for (int i = 0; i < retangles.length(); i++) {
        JSONObject retangle = retangles.getJSONObject(i);

        JSONObject p1 = retangle.getJSONObject("p1");
        int x1 = (int) (p1.getDouble("x")*screen.getWidth());
        int y1 = (int) (p1.getDouble("y")*screen.getHeight());

        JSONObject p2 = retangle.getJSONObject("p2");
        int x2 = (int) (p2.getDouble("x")*screen.getWidth());
        int y2 = (int) (p2.getDouble("y")*screen.getHeight());

        JSONObject p3 = retangle.getJSONObject("p3");
        int x3 = (int) (p3.getDouble("x")*screen.getWidth());
        int y3 = (int) (p3.getDouble("y")*screen.getHeight());

        JSONObject p4 = retangle.getJSONObject("p4");
        int x4 = (int) (p4.getDouble("x")*screen.getWidth());
        int y4 = (int) (p4.getDouble("y")*screen.getHeight());


        JSONObject color = retangle.getJSONObject("color");
        int r = color.getInt("r");
        int g = color.getInt("g");
        int b = color.getInt("b");

        RectangleGr rectangle = new RectangleGr(new Point(1, 1), new Point(1, 1));
        rectangle.setRectangelColor(new Color(r, g, b));
        rectangle.createRectangle(new PointGr(x1,y1), new PointGr(x3,y3), new PointGr(x4,y4), new PointGr(x2,y2));
        list.add(rectangle, "Rectangle");
      }

      //Iteracting over array of polygons
      JSONArray polygons = jsonObj.getJSONArray("Polygon");
      for (int i = 0; i < polygons.length(); i++) {
        JSONObject polygon = polygons.getJSONObject(i);
        JSONArray pointsArray = polygon.getJSONArray("point");

        Polygon polygonGr = new Polygon();

        JSONObject firstPoint = pointsArray.getJSONObject(0);
        double xAnt = firstPoint.getDouble("x");
        double yAnt = firstPoint.getDouble("y");

        for (int j = 1; j < pointsArray.length(); j++) {
          JSONObject point = pointsArray.getJSONObject(j);
          double x = point.getDouble("x");
          double y = point.getDouble("y");

          polygonGr.getList().add(new LineGr((int) (xAnt*screen.getWidth()),
           (int) (yAnt*screen.getHeight()), (int) (x*screen.getWidth()),
            (int) (y*screen.getHeight())), "Polygon");
          xAnt = x;
          yAnt = y;
        }

        JSONObject color = polygon.getJSONObject("color");
        int r = color.getInt("r");
        int g = color.getInt("g");
        int b = color.getInt("b");

        polygonGr.setColorPolygon(new Color(r, g, b));
        list.add(polygonGr, "Polygon");
      }

      //Iteracting over array of polygonal line
      JSONArray polygonalLine = jsonObj.getJSONArray("Polygonal Line");
      for (int i = 0; i < polygonalLine.length(); i++) {
        JSONObject polygonal = polygonalLine.getJSONObject(i);
        JSONArray pointsArray = polygonal.getJSONArray("point");

        PolygonalLineGr polygonGr = new PolygonalLineGr();

        JSONObject firstPoint = pointsArray.getJSONObject(0);
        double xAnt = firstPoint.getDouble("x");
        double yAnt = firstPoint.getDouble("y");

        for (int j = 1; j < pointsArray.length(); j++) {
          JSONObject point = pointsArray.getJSONObject(j);
          double x = point.getDouble("x");
          double y = point.getDouble("y");

          polygonGr.getList().add(new LineGr((int) (xAnt*screen.getWidth()),
          (int) (yAnt*screen.getHeight()), (int) (x*screen.getWidth()),
           (int) (y*screen.getHeight())), "Polygonal Line");
          xAnt = x;
          yAnt = y;
        }

        JSONObject color = polygonal.getJSONObject("color");
        int r = color.getInt("r");
        int g = color.getInt("g");
        int b = color.getInt("b");

        polygonGr.setColorPolygonalLine(new Color(r, g, b));
        list.add(polygonGr, "Polygonal Line");
      }
    }
  }

  /**
   * Adds to JSON array every polygon
   * @param polygonArray
   * @param aux
   */
  private void polygonToJson(JSONArray polygonArray, Node aux) {
    while (aux != null) {
      JSONObject polygon = new JSONObject();
      JSONArray polygonPoints = new JSONArray();
      Polygon polygonAux = (Polygon) aux.getItem();

      LineGr line = null;

      Node node = polygonAux.getList().getBegin();
      while (node != null) {
        line = (LineGr) node.getItem();
        JSONObject p = new JSONObject();
        p.put("x", line.getP1().getX()/screen.getWidth());
        p.put("y", line.getP1().getY()/screen.getHeight());

        polygonPoints.put(p);
        node = node.getNext();
      }

      // Placing the last point
      if (line != null) { // Need to leave it because it always captures the first point. At the end of the loop we will have the 
                          // last point
        JSONObject p = new JSONObject();
        p.put("x", line.getP2().getX()/screen.getWidth());
        p.put("y", line.getP2().getY()/screen.getHeight());
        polygonPoints.put(p);
      }

      polygon.put("point", polygonPoints);

      JSONObject color = new JSONObject();
      color.put("r", polygonAux.getColorPolygon().getRed());
      color.put("g", polygonAux.getColorPolygon().getGreen());
      color.put("b", polygonAux.getColorPolygon().getBlue());
      polygon.put("color", color);

      polygonArray.put(polygon);
      aux = aux.getNext();
    }
  }

  /**
   * Adds to JSONarray every polygonal line
   * @param polygonLineArray
   * @param aux
   */
  private void polygonLineToJson(JSONArray polygonLineArray, Node aux) {
    while (aux != null) {
      JSONObject polygon = new JSONObject();
      JSONArray polygonPoints = new JSONArray();
      PolygonalLineGr polygonAux = (PolygonalLineGr) aux.getItem();

      LineGr line = null;

      Node node = polygonAux.getList().getBegin();
      while (node != null) {
        line = (LineGr) node.getItem();
        JSONObject p = new JSONObject();
        p.put("x", line.getP1().getX()/screen.getWidth());
        p.put("y", line.getP1().getY()/screen.getHeight());

        polygonPoints.put(p);
        node = node.getNext();
      }

      // Placing the last point
      if (line != null) { // Need to leave it because it always captures the first point. At the end of the loop we will have the 
                          // last point
        JSONObject p = new JSONObject();
        p.put("x", line.getP2().getX()/screen.getWidth());
        p.put("y", line.getP2().getY()/screen.getHeight());
        polygonPoints.put(p);
      }

      polygon.put("point", polygonPoints);

      JSONObject color = new JSONObject();
      color.put("r", polygonAux.getColorPolygonalLine().getRed());
      color.put("g", polygonAux.getColorPolygonalLine().getGreen());
      color.put("b", polygonAux.getColorPolygonalLine().getBlue());
      polygon.put("color", color);

      polygonLineArray.put(polygon);
      aux = aux.getNext();
    }
  }

  /**
   * Adds to JSONarray every rectangle
   * @param rectangleArray
   * @param aux
   */
  private void rectangleToJson(JSONArray rectangleArray, Node aux) {
    while (aux != null) {
      JSONObject rectangle = new JSONObject();
      RectangleGr rectangleAux = (RectangleGr) aux.getItem();

      JSONObject p1 = new JSONObject();
      p1.put("x", rectangleAux.getLineGr(1).getP1().getX()/screen.getWidth());
      p1.put("y", rectangleAux.getLineGr(1).getP1().getY()/screen.getHeight());

      JSONObject p2 = new JSONObject();
      p2.put("x", rectangleAux.getLineGr(2).getP2().getX()/screen.getWidth());
      p2.put("y", rectangleAux.getLineGr(2).getP2().getY()/screen.getHeight());

      JSONObject p3 = new JSONObject();
      p3.put("x", rectangleAux.getLineGr(1).getP2().getX()/screen.getWidth());
      p3.put("y", rectangleAux.getLineGr(1).getP2().getY()/screen.getHeight());

      JSONObject p4 = new JSONObject();
      p4.put("x", rectangleAux.getLineGr(0).getP1().getX()/screen.getWidth());
      p4.put("y", rectangleAux.getLineGr(0).getP1().getY()/screen.getHeight());

      JSONObject color = new JSONObject();
      color.put("r", rectangleAux.getRectangelColor().getRed());
      color.put("g", rectangleAux.getRectangelColor().getGreen());
      color.put("b", rectangleAux.getRectangelColor().getBlue());

      rectangle.put("p1", p1);
      rectangle.put("p2", p2);
      rectangle.put("p3", p3);
      rectangle.put("p4", p4);
      rectangle.put("color", color);

      rectangleArray.put(rectangle);
      aux = aux.getNext();
    }
  }

  /**
   * Adds to JSONarray every circle
   * @param circleArray
   * @param aux
   */
  private void circleToJson(JSONArray circleArray, Node aux) {
    while (aux != null) {
      JSONObject circle = new JSONObject();
      CircleGr circleAux = (CircleGr) aux.getItem();

      JSONObject point = new JSONObject();
      point.put("x", circleAux.getX()/screen.getWidth());
      point.put("y", circleAux.getY()/screen.getHeight());

      JSONObject color = new JSONObject();
      color.put("r", circleAux.getCircleColor().getRed());
      color.put("g", circleAux.getCircleColor().getGreen());
      color.put("b", circleAux.getCircleColor().getBlue());

      circle.put("point", point);
      circle.put("radius", circleAux.getRadius());
      circle.put("color", color);

      circleArray.put(circle);

      aux = aux.getNext();
    }
  }
/**
 * Adds to JSoNarray every line
 * @param lineArray
 * @param aux
 */
  private void lineToJson(JSONArray lineArray, Node aux) {
    while (aux != null) {
      JSONObject line = new JSONObject();
      LineGr lineAux = (LineGr) aux.getItem();

      double x = lineAux.getP1().getX();
      double y = lineAux.getP1().getY();
      JSONObject p1 = new JSONObject();
      p1.put("x", x/screen.getWidth());
      p1.put("y", y/screen.getHeight());

      x = lineAux.getP2().getX();
      y = lineAux.getP2().getY();
      JSONObject p2 = new JSONObject();
      p2.put("x", x/screen.getWidth());
      p2.put("y", y/screen.getHeight());

      JSONObject color = new JSONObject();
      color.put("r", lineAux.getLineColor().getRed());
      color.put("g", lineAux.getLineColor().getGreen());
      color.put("b", lineAux.getLineColor().getBlue());

      line.put("p1", p1);
      line.put("p2", p2);
      line.put("color", color);
      lineArray.put(line);// Placing the last point
      aux = aux.getNext();
    }
  }

  /**
 * Creates a JSON file
 * @param fileName
 * @param json
 */
  private void createFile(String fileName, JSONObject json) {
    FileWriter writeFile;
    System.out.println(PATH + fileName + ".json");
    try {
      writeFile = new FileWriter(PATH + fileName + ".json");
      writeFile.write(json.toString());
      writeFile.close();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

}
