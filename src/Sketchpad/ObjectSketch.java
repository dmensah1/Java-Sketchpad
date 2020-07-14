package Sketchpad;

import java.awt.*;

public class ObjectSketch {
    public int x0, y0, x1, y1;
    ShapeMode mode;
    Color color;

    public ObjectSketch (int x0, int y0, int x1, int y1, ShapeMode mode, Color color) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.mode = mode;
        this.color = color;
    }

    public void draw(Graphics g) {
        int startX, startY, width, height;
        g.setColor(color);

        switch(mode) {
            case FREEHAND:
            case LINE:
                g.drawLine(x0, y0, x1, y1);
                break;
            case RECTANGLE:
                startX = x1 - x0 > 0 ? x0 : x1;
                startY = y1 - y0 > 0 ? y0 : y1;
                width = x1 - x0 > 0 ? x1 - x0 : x0 - x1;
                height = y1 - y0 > 0 ? y1 - y0 : y0 - y1;
                g.drawRect(startX, startY, width, height);
                break;
            case OVAL:
                startX = x1 - x0 > 0 ? x0 : x1;
                startY = y1 - y0 > 0 ? y0 : y1;
                width = x1 - x0 > 0 ? x1 - x0 : x0 - x1;
                height = y1 - y0 > 0 ? y1 - y0 : y0 - y1;
                g.drawOval(startX, startY, width, height);
                break;
            case SQUARE:
                width = x1 - x0;
                height = y1 - y0;
                int side = Math.abs(width) > Math.abs(height) ? Math.abs(width) : Math.abs(height);
                startX = width > 0 ? x0 : x0 - side;
                startY = height > 0 ? y0 : y0 - side;
                g.drawRect(startX, startY, side, side);
                break;
            case CIRCLE:
                width = x1 - x0;
                height = y1 - y0;
                int diameter = Math.abs(width) > Math.abs(height) ? Math.abs(width) : Math.abs(height);
                startX = width > 0 ? x0 : x0 - diameter;
                startY = height > 0 ? y0 : y0 - diameter;
                g.drawArc(startX, startY, diameter, diameter, 0, 360);
                break;
            default:
        }
    }
}
