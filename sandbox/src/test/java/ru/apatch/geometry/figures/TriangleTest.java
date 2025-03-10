package ru.apatch.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    void canCalculateTriangleArea() {
        var s = new Triangle(5.0,5.0,5.0);
        double result = s.TriangleArea();

        Assertions.assertEquals(25.0, Triangle.TriangleArea(5.0, 4.0, 4.0));
    }

 }