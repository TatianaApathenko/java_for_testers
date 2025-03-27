package ru.apatch.geometry.figures;

public record Triangle(
        double a,
        double b,
        double c
) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Triangle inequality is violated");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0)
 ||    (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.b) == 0)
     ||   (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0)
             ||   (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0)
                ||   (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.b) == 0)
             ||   (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.a) == 0);

    }

    @Override
    public int hashCode() {
        return 1;
    }

    public static void printTriangleArea(Triangle s) {
        String text = String.format("Area of triangle with sides %f, %f, %f = %f", s.a, s.b, s.c, s.area());
        System.out.println(text);
    }

    public double area() {
        double pp = semiPerimeter();
        return Math.sqrt(pp * (pp - this.a) * (pp - this.b) * (pp - this.c));
    }

    public static void printTrianglePerimeter(Triangle p) {
        String text = String.format("Perimeter of triangle with sides %f, %f, %f = %f", p.a, p.b, p.c, p.perimeter());
        System.out.println(text);
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

    public double semiPerimeter() {
        return perimeter() / 2;
    }
}

