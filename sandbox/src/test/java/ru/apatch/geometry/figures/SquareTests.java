package ru.apatch.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea() {
        var s = new Square(5.0);
        double result = s.area();

        Assertions.assertEquals(25.0, Square.area(5.0));
    }

    @Test
    void canCalculatePerimetr() {
        Assertions.assertEquals(20.0, Square.perimeter(5.0));
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }
}