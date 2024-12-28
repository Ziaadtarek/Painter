package com.mycompany.painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToolSelector {
    public static JPanel createToolPanel(PaintApp app) {
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());

        // Color button
        JButton colorButton = new JButton();
        colorButton.setBackground(app.getCurrentColor());
        colorButton.setPreferredSize(new Dimension(40, 40));
        colorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Choose a Color", app.getCurrentColor());
            if (selectedColor != null) {
                app.setCurrentColor(selectedColor);
                colorButton.setBackground(selectedColor);
            }
        });
        controls.add(colorButton);

        // Undo button
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            if (!app.getShapes().isEmpty()) {
                app.getShapes().remove(app.getShapes().size() - 1);
                app.repaint();
            }
        });
        controls.add(undoButton);

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            app.getShapes().clear();
            app.repaint();
        });
        controls.add(clearButton);

        // Tool Selector
        JComboBox<String> toolSelector = new JComboBox<>(new String[]{"Pencil", "Rectangle", "Oval", "Line", "Eraser"});
        toolSelector.addActionListener((ActionEvent e) -> app.setCurrentTool((String) toolSelector.getSelectedItem()));
        controls.add(toolSelector);

        // Stroke width slider
        JSlider strokeSlider = new JSlider(1, 10, app.getStrokeWidth());
        strokeSlider.addChangeListener(e -> app.setStrokeWidth(strokeSlider.getValue()));
        controls.add(new JLabel("Stroke Width"));
        controls.add(strokeSlider);

        // Filled checkbox
        JCheckBox filledCheck = new JCheckBox("Filled");
        filledCheck.addActionListener(e -> app.setFilled(filledCheck.isSelected()));
        controls.add(filledCheck);

        return controls;
    }
}
