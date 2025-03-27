package ru.apatch.geometry;

import ru.apatch.geometry.figures.Rectangle;
import ru.apatch.geometry.figures.Square;
import ru.apatch.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0)) ;
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));
        //S=квадрату длинны стороны
        //Area of a square with a side 7.0 = Площадь квадрата со стороной 7.0

  Rectangle.printRectangleArea(2.5,3.0);
  Rectangle.printRectangleArea(9.5,2.0);

        Triangle.printTrianglePerimeter(new Triangle(5.0, 3.0,4.0));
        Triangle.printTriangleArea(new Triangle(3.0,4.0,5.0));

    }
}
