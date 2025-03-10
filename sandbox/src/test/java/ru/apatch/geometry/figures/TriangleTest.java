package ru.apatch.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
 @Test
 void canCalculateArea() {
var s = new Triangle(5.0,4.0,3.0);
double result = s.area();
     Assertions.assertEquals(10.825317547305483, new Triangle(5.0,5.0,5.0).area());
     Assertions.assertEquals(28.61817604250837, new Triangle(10.0,6.0,10.0).area());
     Assertions.assertEquals(7.483314773547883, new Triangle(5.0,3.0,6.0).area());



 }
    @Test
    public void canCalculatePerimetr() {
     var p = new Triangle(5.0,4.0,3.0);
     double result = p.perimeter();
        Assertions.assertEquals(12.0, new Triangle(5.0,4.0,3.0).perimeter());
        Assertions.assertEquals(26.0, new Triangle(10.0,6.0,10.0).perimeter());
        Assertions.assertEquals(30.0, new Triangle(10.0,10.0,10.0).perimeter());


    }

}
