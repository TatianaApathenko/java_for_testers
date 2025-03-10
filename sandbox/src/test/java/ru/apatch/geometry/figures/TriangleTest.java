package ru.apatch.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
 @Test
 @Test
 void canCalculateArea() {
var s = new Triangle(5.0,4.0,3.0);
public double result = s.area();

 }
    @Test
    public void canCalculatePerimetr() {
        Assertions.assertEquals(15.0, Triangle.perimeter(5.0));
    }

}

