package ru.apatch.geometry.figures;

public class Square {
    public static void main(String[] args) {

    }

    double side;

    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea(Square s)  {
        /*S=квадрату дины стороны
        Area of a square with a side 7.0 = Площадь квадрата со стороной 7.0*/
        String text = String.format("Area of a square with a side %f = %f", s.side, s.area());
                System.out.println(text);

    }

    public static double area(double side) {
        return side * side;
    }

    public static double perimeter(double side) {
      return 4 * side;
  }

    public static void printArea(Square square) {

    }

    public static void printPerimeter(Square square) {
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
    return 4 * this.side ;
    }
}
