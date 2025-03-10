package ru.apatch.geometry.figures;

public record Rectangle (double a, double b) {
    //Area of a square with a side 7.0 = Площадь прямоугольника со сторонами 7.0\\
    public static void printRectangleArea(double a, double b) {
        System.out.println("Area of a rectangle with a sides "  + a + " and " + b + " = " + rectangleArea(a,b)) ;

    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
