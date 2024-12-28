package com.mycompany.painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private final PaintApp app;
    private Point startPoint, endPoint;
    private ArrayList<Point> freeDrawPoints = new ArrayList<>();

    public DrawingPanel(PaintApp app) {
        this.app = app;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                if (app.getCurrentTool().equals("Pencil") || app.getCurrentTool().equals("Eraser")) {
                    freeDrawPoints.clear();
                    freeDrawPoints.add(startPoint);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                app.getShapes().add(new Shape(startPoint, endPoint, app.getCurrentColor(),
                        app.getStrokeWidth(), app.isFilled(), app.getCurrentTool(), new ArrayList<>(freeDrawPoints)));
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (app.getCurrentTool().equals("Pencil") || app.getCurrentTool().equals("Eraser")) {
                    freeDrawPoints.add(e.getPoint());
                } else {
                    endPoint = e.getPoint();
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Shape shape : app.getShapes()) {
            g2.setColor(shape.tool.equals("Eraser") ? Color.WHITE : shape.color);
            g2.setStroke(new BasicStroke(shape.strokeWidth));
            switch (shape.tool) {
                case "Line":
                    g2.drawLine(shape.start.x, shape.start.y, shape.end.x, shape.end.y);
                    break;
                case "Rectangle":
                    int x = Math.min(shape.start.x, shape.end.x);
                    int y = Math.min(shape.start.y, shape.end.y);
                    int width = Math.abs(shape.end.x - shape.start.x);
                    int height = Math.abs(shape.end.y - shape.start.y);
                    if (shape.isFilled) g2.fillRect(x, y, width, height);
                    else g2.drawRect(x, y, width, height);
                    break;
                case "Oval":
                    x = Math.min(shape.start.x, shape.end.x);
                    y = Math.min(shape.start.y, shape.end.y);
                    width = Math.abs(shape.end.x - shape.start.x);
                    height = Math.abs(shape.end.y - shape.start.y);
                    if (shape.isFilled) g2.fillOval(x, y, width, height);
                    else g2.drawOval(x, y, width, height);
                    break;
                case "Pencil":
                case "Eraser":
                    for (int i = 1; i < shape.points.size(); i++) {
                        Point p1 = shape.points.get(i - 1);
                        Point p2 = shape.points.get(i);
                        g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
