package com.mycompany.painter;

import java.awt.*;
import java.util.ArrayList;

public class Shape {
    public Point start, end;
    public Color color;
    public int strokeWidth;
    public boolean isFilled;
    public String tool;
    public ArrayList<Point> points;

    public Shape(Point start, Point end, Color color, int strokeWidth, boolean isFilled, String tool, ArrayList<Point> points) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.isFilled = isFilled;
        this.tool = tool;
        this.points = points;
    }
}
