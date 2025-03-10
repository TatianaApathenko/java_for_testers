package ru.apatch.geometry.figures;

public class Triangle {
    public static void main(String[] args) {
        printTrianglePerimeter(5.0, 3.0, 4.0);
        printTriangleArea(40.0,30.0,40.0);
    }

    private static void printTriangleArea(double a,double b, double c) {
        String text = String.format("Area of triangle with sides %f , %f, %f = %f", a, b, c, TriangleArea(a, b, c));
        System.out.println(text);
    }

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void printTrianglePerimeter(double a, double b, double c){
        String text = String.format("Perimeter of triangle with sides %f , %f, %f = %f", a, b, c, TrianglePerimeter(a, b, c));
        System.out.println(text);
    }

    public static double TrianglePerimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static double  TriangleSemiperimeter(double a, double b, double c){
        return (a + b + c)/2;
    }

    public static double TriangleArea(double a, double b, double c) {
        return Math.sqrt(TriangleSemiperimeter(a, b, c) - a) * (TriangleSemiperimeter(a, b, c) - b) * (TriangleSemiperimeter(a, b, c) - c);
    }
}



