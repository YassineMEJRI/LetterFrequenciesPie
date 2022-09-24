package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;


interface MyShapeInterface {


    public abstract MyRectangle getMyBoundingRectangle();


    public abstract boolean pointInMyShape(MyPoint p);

}

class MyPoint {
    private int x, y;

    MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setColor(MyColor green) {
    }

    void draw(GraphicsContext gc) {
        gc.setFill(MyColor.GREEN.getColor());
        gc.fillOval(x, y, 1, 1);
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

enum MyColor {
    BLACK(0, 0, 0, 1.0),
    WHITE(255, 255, 255, 1.0),
    RED(255, 0, 0, 1.0),
    GREEN(0, 255, 0, 1.0),
    LIGHTGRAY(211, 211, 211, 1.0),
    DARKGREY(169, 169, 169, 1.0),
    GRAY(128, 128, 128, 1.0),
    BLUE(0, 0, 255, 1.0),
    MEDIUMAQUAMARINE(102, 205, 170, 1.0),
    DODGERBLUE(30, 144, 255, 1.0),
    DEEPSKYBLUE(0, 191, 255, 1.0),
    CORNFLOWERBLUE(100, 149, 237, 1.0),
    POWDERBLUE(176, 224, 230, 1.0),
    ROYALBLUE(65, 105, 225, 1.0),
    PALETURQUOISE(175, 238, 238, 1.0),
    LIGHTCYAN(224, 255, 255, 1.0),
    DARKSEAGREEN(143, 188, 143, 1.0);

    private int red, green, blue;
    private double a;


    MyColor(int r, int g, int b, double a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.a = a;
    }

    public Color getColor() {
        return Color.rgb(red, green, blue, a);
    }

    public int getRed(int red) {
        return red;
    }

    public int getGreen(int green) {
        return green;
    }

    public int getBlue(int blue) {
        return blue;
    }

    public Color toHex() {
        return getColor();
    }
}

abstract class MyShape implements MyShapeInterface {
    private MyPoint p;
    private MyColor color;

    MyShape(int x, int y, MyColor color) {
        this.p = new MyPoint(x, y);
        this.color = color;
    }


    public void setX(int x) {
        this.p.setX(x);
    }

    public void setY(int y) {
        this.p.setX(y);
    }

    public void setColor(MyColor color) {
        this.color = color;
    }

    public int getX() {
        return p.getX();
    }

    public int getY() {
        return p.getY();
    }

    public MyColor getColor() {
        return color;
    }

    public int area() {
        return 0;
    }


    public int perimeter() {
        return 0;
    }


    public String toString() {
        return "MyShape Object";
    }


    public void draw(GraphicsContext gc) {
        gc.setFill(getColor().getColor());
        gc.fillRect(0, 0, getX(), getY());
    }
}


class MyLine extends MyShape {
    private MyPoint p1, p2;
    MyPoint[] pLine = new MyPoint[2];
    private int startX, startY, endX, endY;


    MyLine(int startX, int startY, int endX, int endY, MyColor color) {
        super(0, 0, color);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        pLine[0] = p1;
        pLine[1] = p2;
        this.p1 = new MyPoint(startX, startY);
        this.p2 = new MyPoint(endX, endY);
    }


    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }


    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public MyPoint[] getLine() {
        return pLine;
    }



    @Override
    public int perimeter() {
        return (int) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
    }


    @Override
    public int area() {
        return 0;
    }


    public double angleToX() {
        return Math.toDegrees(Math.atan2(endY - startY, endX - startX));
    }


    @Override
    public String toString() {
        return "Line-" +
                "\nPoint 1: " + "(" + p1.getX() + " , " + p1.getY() + ") " +
                "\nPoint 2: " + "(" + p2.getX() + " , " + p2.getY() + ") " +
                "\nLength: " + perimeter() +
                "\nAngle to X Axis: " + angleToX() +
                "\nColor: " + super.getColor() +
                "\nHex of Color: " + super.getColor().getColor() +
                "\n ================================";
    }


    @Override
    public void draw(GraphicsContext gc) {
        getMyBoundingRectangle().draw(gc);
        gc.setStroke(super.getColor().getColor());
        gc.strokeLine(startX, startY, endX, endY);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(Math.min(startX, startY), Math.min(startY, endY), Math.abs(endX - startX),
                Math.abs(endY - startY), MyColor.LIGHTGRAY);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {

        return false;
    }

}


class MyRectangle extends MyShape {
    private int height, width;
    private MyPoint TLCP;


    MyRectangle(int x, int y, int w, int h, MyColor color) {
        super(x, y, color);
        this.height = h;
        this.width = w;
        this.TLCP = new MyPoint(x, y);
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MyPoint getTLCP() {
        return TLCP;
    }



    @Override
    public int area() {
        return height * width;
    }


    @Override
    public int perimeter() {
        return 2 * (height + width);
    }


    public String TLCP() {
        return "(" + getX() + " , " + getY() + ")";
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(super.getColor().getColor());
        gc.setStroke(super.getColor().getColor());
        gc.strokeRect(super.getX(), super.getY(), width, height);
        gc.fillRect(super.getX(), super.getY(), width, height);
    }

    @Override
    public String toString() {
        return "Rectangle- \nTLCP: " + TLCP() +
                "\nHeight: " + height +
                "\nWidth: " + width +
                "\nPerimeter: " + perimeter() +
                "\nArea: " + area() +
                "\nColor: " + super.getColor() +
                "\nHex of Color: " + super.getColor().getColor() +
                "\n ============================================================";
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return this;
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {

        return false;
    }

}


class MyOval extends MyShape {
    private int radiusX, radiusY, majorAxis, minorAxis;
    private MyPoint pCenter;


    MyOval(int x, int y, int radiusX, int radiusY, MyColor color) {
        super(x, y, color);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.pCenter = new MyPoint(x + radiusX / 2, y + radiusY / 2);
        this.majorAxis = majorAxis();
        this.minorAxis = majorAxis();
    }


    public void setRadX(int radiusX) {
        this.radiusX = radiusX;
    }

    public void setRadY(int radiusY) {
        this.radiusY = radiusY;
    }

    public void setCenter(int x, int y) {
        this.pCenter.setX(x);
        this.pCenter.setY(y);
    }


    public int getRadX() {
        return radiusX;
    }

    public int getRadY() {
        return radiusY;
    }

    public MyPoint getCenter() {
        return pCenter;
    }

    public int getMajorAxis() {
        return majorAxis;
    }

    public int getMinorAxis() {
        return minorAxis;
    }



    public int minorAxis() {
        return Math.min(radiusX, radiusY);
    }


    public int majorAxis() {
        return Math.max(radiusX, radiusY);
    }


    @Override
    public int perimeter() {
        int a = minorAxis();
        int b = majorAxis();
        return (int) (Math.PI * ((3 * (a + b)) - Math.sqrt((3 * a + (b)) * (3 * b + (a)))));
    }

    @Override
    public int area() {
        return (int) (Math.PI * (minorAxis()) * (majorAxis));
    }


    @Override
    public String toString() {
        return "Oval- \nSemi Minor Axis: " + minorAxis() +
                "\nSemi Major Axis: " + majorAxis() +
                "\nPerimeter: " + perimeter() +
                "\nArea: " + area() +
                "\nColor: " + super.getColor() +
                "\nHex of Color: " + super.getColor().getColor() +
                "\n ================================";
    }


    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(super.getColor().getColor());
        gc.setStroke(super.getColor().getColor());
        gc.strokeOval(super.getX(), super.getY(), radiusX, radiusY);
        gc.fillOval(super.getX(), super.getY(), radiusX, radiusY);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(super.getX(), super.getY(), radiusX, radiusY, MyColor.DARKGREY);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {

        return false;
    }

}


class MyCircle extends MyOval {
    private int radius;

    MyCircle(int x, int y, int radius, MyColor color) {
        super(x, y, radius * 2, radius * 2, color);


        this.radius = radius;
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }


    public int getRadius() {
        return radius;
    }



    @Override
    public int area() {
        return (int) (Math.PI * (Math.pow(radius, 2)));
    }


    @Override
    public int perimeter() {
        return (int) (2 * Math.PI * radius);
    }


    @Override
    public void draw(GraphicsContext gc) {


        gc.setFill(super.getColor().getColor());
        gc.strokeOval(super.getX() - radius, super.getY() - radius, radius * 2, radius * 2);
        gc.fillOval(super.getX() - radius, super.getY() - radius, radius * 2, radius * 2);

    }

    @Override
    public String toString() {
        return "Circle-" +
                "\nRadius: " + getRadius() +
                "\nArea: " + area() +
                "\nPerimeter: " + perimeter() +
                "\nColor: " + super.getColor() +
                "\nHex of Color: " + super.getColor().getColor() +
                "\n ================================";
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(super.getX() - radius, super.getY() - radius, radius * 2, radius * 2, MyColor.LIGHTGRAY);
    }

}



class MyArc extends MyShape {

    private double startAngle;
    private double angleSize;
    private final MyOval ovalObj;

    MyArc(double startAngle, double angleSize, MyOval ovalObj, MyColor color) {
        super(ovalObj.getCenter().getX(), ovalObj.getCenter().getY(), color);
        this.startAngle = startAngle;
        this.angleSize = angleSize;
        this.ovalObj = ovalObj;

        while (startAngle < 0) {
            startAngle = startAngle + 360;
        }
    }



    @Override
    public int area() {
        return (int) ((angleSize / 360) * Math.PI * Math.pow(ovalObj.getRadX(), 2));
    }

    public int length() {
        return (int) (angleSize * ovalObj.getRadX() * (Math.PI / 180));
    }

    @Override
    public String toString() {
        return "Arc-" +
                "\nRadius: " + ovalObj.getRadX() +
                "\nCenter: " + "(" + ovalObj.getX() + " , " + ovalObj.getY() + ") " +
                "\nStarting Angle: " + startAngle +
                "\nAngle Size: " + angleSize +
                "\nArc Length: " + length() +
                "\nArea: " + area() +
                "\nColor: " + super.getColor() +
                "\nHex of Color: " + super.getColor().getColor() +
                "\n ================================";
    }

    @Override
    public void draw(GraphicsContext gc) {


        gc.setFill(super.getColor().getColor());
        gc.setStroke(super.getColor().getColor());
        gc.strokeArc(ovalObj.getCenter().getX() - ovalObj.getRadX(), ovalObj.getCenter().getY() - ovalObj.getRadY(),
                ovalObj.getRadX(), ovalObj.getRadY(), startAngle, angleSize, ArcType.ROUND);
        gc.fillArc(ovalObj.getCenter().getX() - ovalObj.getRadX(), ovalObj.getCenter().getY() - ovalObj.getRadY(),
                ovalObj.getRadX(), ovalObj.getRadY(), startAngle, angleSize, ArcType.ROUND);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        int startX = (int) (Math.cos((Math.toRadians(startAngle)))) * ovalObj.getRadX();
        int startY = (int) (Math.sin((Math.toRadians(startAngle)))) * ovalObj.getRadX();
        int terminalAngle = (int) Math.abs((startAngle - angleSize)) * ovalObj.getRadX();
        int endX = (int) (Math.cos((Math.toRadians(terminalAngle)))) * ovalObj.getRadX();
        int endY = (int) (Math.sin((Math.toRadians(terminalAngle)))) * ovalObj.getRadX();

        int xCord = Math.min((Math.min(startX, endX)), ovalObj.getX());
        int yCord = Math.min((Math.min(startY, endY)), ovalObj.getY());
        int width = xCord + ovalObj.getRadX() / 2;
        int height = yCord + ovalObj.getRadY() / 2;
        return new MyRectangle(ovalObj.getX() / 2, ovalObj.getY() / 2, width, height, MyColor.LIGHTGRAY);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {

        return false;
    }

}


class HistogramAlphaBet {

    private Map<Character, Integer> frequencies;
    private Map<Character, Double> probabilities;
    MyPieChart myPieChart;

    HistogramAlphaBet(String txtFile){
        frequencies = new HashMap<Character, Integer>();
        probabilities = new HashMap<Character, Double>();

        for(int i = 0; i < txtFile.length(); i++){
            char c = txtFile.charAt(i);
            if (Character.isLetter(c))
                count(Character.toLowerCase(c));
        }

        double chrCnt = frequencies.values().stream().reduce(0, Integer::sum);

        for (Map.Entry<Character, Integer> set : frequencies.entrySet()) {
            probabilities.put(set.getKey(), set.getValue()/chrCnt);
        }


        probabilities = probabilities.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    private void count(char key) {
        frequencies.putIfAbsent(key, 0);
        frequencies.put(key, frequencies.get(key) + 1);
    }

    public Map<Character, Double> getProbabilities() {
        return probabilities;
    }

    public void draw(GraphicsContext gc, int n) {
        gc.clearRect(0, 0, App.WIDTH, App.HEIGHT);
        myPieChart = new MyPieChart(n, App.WIDTH / 2, App.HEIGHT / 2, App.WIDTH / 2, App.WIDTH / 2);
        myPieChart.draw(gc);
    }


    class MyPieChart {
        Map<Character, Slice> slices;
        MyOval oval;
        int n;

        public MyPieChart(int n, int x, int y, int radiusX, int radiusY) {
            this.slices = new HashMap<>();
            this.oval = new MyOval(x, y, radiusX, radiusY, MyColor.WHITE);
            this.n = n;

            Character[] keys = probabilities.keySet().toArray(new Character[0]);
            double sum_probabilities = 0;
            int i;
            for (i = 0; i < n; i++){
                Slice slice = new Slice(oval, probabilities.get(keys[i]), keys[i]+"", i);
                slices.put(keys[i], slice);
                sum_probabilities += probabilities.get(keys[i]);
            }

            slices.put('.', new Slice(oval, 1-sum_probabilities,"other", i));
        }

        public void draw(GraphicsContext gc) {
            for (Map.Entry<Character, Slice> entry: slices.entrySet()){
                System.out.println(entry.getValue());
                entry.getValue().draw(gc);
            }
        }
    }

}



class Slice {
    MyArc arc;
    static double startangle = 0;
    double slice_startangle;
    private double angleSize;
    private double probability;
    private String letter;
    private int index;

    MyOval oval;

    private final MyColor[] colors = {
            MyColor.MEDIUMAQUAMARINE,
            MyColor.DODGERBLUE,
            MyColor.DEEPSKYBLUE,
            MyColor.DARKSEAGREEN,
            MyColor.CORNFLOWERBLUE,
            MyColor.RED,
            MyColor.BLUE,
            MyColor.GREEN,
            MyColor.LIGHTGRAY,
            MyColor.LIGHTCYAN,
            MyColor.ROYALBLUE};

    public Slice(MyOval oval, double probability, String letter, int i) {
        this.oval = oval;
        this.probability = probability;
        this.letter = letter;
        this.index = i;
        this.angleSize = probability * 360;
        this.arc = new MyArc(startangle, angleSize,oval, colors[i]);
        slice_startangle = startangle;
        startangle+=angleSize;
    }

    public void draw(GraphicsContext gc){
        arc.draw(gc);
        gc.fillText(String.format("%s: %.3f%%", letter, probability*100), 500, 100+index * 30);
    }

    @Override
    public String toString() {
        return "Slice{" + "arc=" + arc + ", probability=" + probability + ", letter='" + letter + '\'' + ", index=" + index + '}';
    }
}



public class App extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    @Override
    public void start(Stage primaryStage) {


        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        VBox root = new VBox(canvas);

        Label label = new Label("Insert Number of letters");
        label.setFont(Font.font(20));

        TextField input = new TextField();
        input.setMaxWidth(100);

        Button button = new Button("Run");

        File file = new File("WAP.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.useDelimiter("\\Z");
        HistogramAlphaBet histogramAlphaBet = new HistogramAlphaBet(scanner.next());




        button.setOnAction(actionEvent -> {
            try {
                int n = Integer.parseInt(input.getText());
                histogramAlphaBet.draw(gc, n);
            } catch (NumberFormatException e) {
                System.out.println("not a number");
            }
        });

        root.getChildren().addAll(label, input, button);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Pie Chart Alphabet Frequencies");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}