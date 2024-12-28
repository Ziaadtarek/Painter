package com.mycompany.painter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintApp extends JFrame {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private Color currentColor = Color.BLACK;
    private int strokeWidth = 2;
    private boolean isFilled = false;
    private String currentTool = "Pencil";

    public PaintApp() {
        setTitle("Painter App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel controls = ToolSelector.createToolPanel(this);
        add(controls, BorderLayout.NORTH);

        DrawingPanel drawingPanel = new DrawingPanel(this);
        drawingPanel.setBackground(Color.WHITE); // Set white background
        add(drawingPanel, BorderLayout.CENTER);
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public String getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(String currentTool) {
        this.currentTool = currentTool;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaintApp app = new PaintApp();
            app.setVisible(true);
        });
    }
}
