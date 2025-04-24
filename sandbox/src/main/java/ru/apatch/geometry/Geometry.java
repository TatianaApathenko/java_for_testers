package ru.apatch.geometry;

import ru.apatch.geometry.figures.Square;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100));
        var squares = Stream.generate(randomSquare).limit(6);

        //Consumer<Square> print = Square::printSquareArea;
        Consumer<Square> print = square -> {
            Square.printArea(square);
        };
        //Функции генераторы
        //    Square.printArea(square);
        //     Square.printPerimeter(square);


        //Функции генераторы
        //squares.peek(Square::printArea).forEach(Square::printPerimeter);
        squares.forEach(print);


//        for (Square square : squares) {
//    Square.printSquareArea(square);}
//


//
//                    Square.printSquareArea(new Square(7.0)) ;
//                Square.printSquareArea(new Square(5.0));
//        Square.printSquareArea(new Square(3.0));
//        S=квадрату длинны стороны
//        Area of a square with a side 7.0 = Площадь квадрата со стороной 7.0
//
//        ectangle.printRectangleArea(2.5,3.0);
//        Rectangle.printRectangleArea(9.5,2.0);
//     Triangle.printTrianglePerimeter(new Triangle(5.0, 3.0,4.0));
//              Triangle.printTriangleArea(new Triangle(3.0,4.0,5.0));


    }
}
