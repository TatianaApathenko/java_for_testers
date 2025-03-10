package ru.apatch.geometry.figures;

public class Triangle {

    static double a;
    static double b;
    static double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

    }
    private static void printTriangleArea(Triangle s) {
        String text = String.format("Area of triangle with sides %f , %f, %f = %f", a, b, c, s.area());
        System.out.println(text);


    public static double perimeter(double a) {
  return 3 * a;
   }
}

    public static double perimeter(double a) {
    }

    public double area() {
        return 0;}




