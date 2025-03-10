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

    public static void printTriangleArea(Triangle s) {
        String text = String.format("Area of triangle with sides %f , %f, %f = %f", s.a, s.b, s.c, s.area());
        System.out.println(text);

    }

    public double area() {
        double pp = semiPerimetr();
        return Math.sqrt(pp*(pp - this.a)*(pp - this.b)*(pp - this.c));
    }


    public static void printTrianglePerimeter(Triangle p) {
        String text = String.format("Perimeter of triangle with sides %f , %f, %f = %f", p.a, p.b, p.c, p.perimeter());
        System.out.println(text);

    }
    public double perimeter() {
        return this.a + this.b + this.c;
    }

    public double semiPerimetr(){
        return perimeter()/2;
    }

}


