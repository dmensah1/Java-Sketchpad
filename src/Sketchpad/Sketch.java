package Sketchpad;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Sketch extends Frame {

    //when constructor is called, the frame is initialized with the buttons
    public Sketch() {

        //initializing menu bar with the items
        MenuBar mb = new MenuBar();
        Menu menu = new Menu("File");
        Menu menu1 = new Menu("Select Shape");
        Menu menu2 = new Menu("Select Color");

        MenuItem freehand = new MenuItem("Freehand");
        MenuItem line = new MenuItem("Line");
        MenuItem rect = new MenuItem("Rectangle");
        MenuItem oval = new MenuItem("Oval");
        MenuItem circle = new MenuItem("Circle");
        MenuItem square = new MenuItem("Square");

        MenuItem red = new MenuItem("Red");
        MenuItem green = new MenuItem("Green");
        MenuItem blue = new MenuItem("Blue");

        MenuItem save = new MenuItem("Save");
        save.addActionListener(this::actionPerformed);
        MenuItem open = new MenuItem("Open");
        open.addActionListener(this::actionPerformed);
        MenuItem clear = new MenuItem("Clear");
        clear.addActionListener(this::actionPerformed);
        MenuItem undo = new MenuItem("Undo");
        undo.addActionListener(this::actionPerformed);

        menu.add(undo);
        menu.add(save);
        menu.add(open);
        menu.add(clear);

        menu1.add(freehand);
        menu1.add(line);
        menu1.add(rect);
        menu1.add(oval);
        menu1.add(circle);
        menu1.add(square);

        menu2.add(red);
        menu2.add(green);
        menu2.add(blue);

        mb.add(menu);
        mb.add(menu1);
        mb.add(menu2);

        setMenuBar(mb);

        // to close the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        //setting frame size
        setSize(1000,800);
        setLayout(null);

        addMouseListener(new myMouseHandler());
        addMouseMotionListener(new myMouseMotionHandler());

        //below chunk of code adds action listeners to each of the buttons
        line.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode(1); }
        });
        rect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode(2); }
        });
        oval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode(3); }
        });
        freehand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode(4); }
        });
        circle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode(5); }
        });
        square.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode(6); }
        });

        red.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode2(1); }
        });
        green.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode2(2); }
        });
        blue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { setMode2(3); }
        });
    }

    // global array and variables used to keep track of shapes being drawn and color
    ArrayList<ObjectSketch> shapeArr = new ArrayList<>();
    ShapeMode shapeMode = ShapeMode.FREEHAND;
    Color shapeColor = Color.BLACK;

    int x0,y0,currX,currY;

    // setting the shape to be drawn from user menu selection
    public void setMode(int type) {
        if (type == 1) { this.shapeMode = ShapeMode.LINE; } //line
        else if (type == 2) { this.shapeMode = ShapeMode.RECTANGLE; } //rect
        else if (type == 3) { this.shapeMode = ShapeMode.OVAL; } //oval
        else if (type == 4) { this.shapeMode = ShapeMode.FREEHAND; } //freehand
        else if (type == 5) { this.shapeMode = ShapeMode.CIRCLE; } //circle
        else if (type == 6) { this.shapeMode = ShapeMode.SQUARE; } //square
    }

    // setting the shape color from user menu selection
    public void setMode2(int type) {
        if (type == 1) { this.shapeColor = Color.RED; } //red
        else if (type == 2) { this.shapeColor = Color.GREEN; } //green
        else if (type == 3) { this.shapeColor = Color.BLUE; } //blue
    }

    public class myMouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            x0 = e.getX();
            y0 = e.getY();
            currX = x0;
            currY = y0;

            switch (shapeMode) {
                case LINE:
                    shapeArr.add(new ObjectSketch(x0,y0, currX, currY, shapeMode, shapeColor));
                    break;
                case RECTANGLE:
                    shapeArr.add(new ObjectSketch(x0,y0, currX, currY, shapeMode, shapeColor));
                    break;
                case OVAL:
                    shapeArr.add(new ObjectSketch(x0,y0, currX, currY, shapeMode, shapeColor));
                    break;
                case SQUARE:
                    shapeArr.add(new ObjectSketch(x0,y0, currX, currY, shapeMode, shapeColor));
                    break;
                case CIRCLE:
                    shapeArr.add(new ObjectSketch(x0,y0,currX,currY,shapeMode,shapeColor));
                    break;
                default:
            }
        }

        public void mouseReleased(MouseEvent e) { }
    }

    public class myMouseMotionHandler extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {}

        public void mouseDragged(MouseEvent e) {

            Graphics g = getGraphics();
            g.setColor(shapeColor);

            switch (shapeMode) {
                case LINE:
                    currX = e.getX();
                    currY = e.getY();
                    shapeArr.get(shapeArr.size() - 1).x1 = currX;
                    shapeArr.get(shapeArr.size() - 1).y1 = currY;
                    repaint();
                    break;
                case RECTANGLE:
                    currX = e.getX();
                    currY = e.getY();
                    shapeArr.get(shapeArr.size() - 1).x1 = currX;
                    shapeArr.get(shapeArr.size() - 1).y1 = currY;
                    repaint();
                    break;
                case CIRCLE:
                    currX = e.getX();
                    currY = e.getY();
                    shapeArr.get(shapeArr.size() - 1).x1 = currX;
                    shapeArr.get(shapeArr.size() - 1).y1 = currY;
                    repaint();
                    break;
                case OVAL:
                    currX = e.getX();
                    currY = e.getY();
                    shapeArr.get(shapeArr.size() - 1).x1 = currX;
                    shapeArr.get(shapeArr.size() - 1).y1 = currY;
                    repaint();
                    break;
                case SQUARE:
                    currX = e.getX();
                    currY = e.getY();
                    shapeArr.get(shapeArr.size() - 1).x1 = currX;
                    shapeArr.get(shapeArr.size() - 1).y1 = currY;
                    repaint();
                    break;
                case FREEHAND:
                    x0 = currX;
                    y0 = currY;
                    currX = e.getX();
                    currY = e.getY();
                    g.drawLine(x0, y0, currX, currY);
                    shapeArr.add(new ObjectSketch(x0,y0,currX,currY,shapeMode,shapeColor));
                    break;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Clear Canvas
        if (e.getActionCommand().equals("Clear")) {
            shapeArr.clear();
            repaint();
            return;
        } else if (e.getActionCommand().equals("Save")) {
            FileModes.Save(this, shapeArr);
            return;
        } else if (e.getActionCommand().equals("Open")) {
            shapeArr = FileModes.Load(this);
            repaint();
            return;
        } else if ( e.getActionCommand().equals("Undo")) {
            int index = shapeArr.size() - 1;
            shapeArr.remove(index);
            repaint();
        }
    }

    public void paint(Graphics g) {
        for (ObjectSketch shape : shapeArr) {
            shape.draw(g);
        }
    }

    public static void main(String[] s) {
        new Sketch().setVisible(true);
    }
}

enum ShapeMode {
    FREEHAND,
    LINE,
    RECTANGLE,
    OVAL,
    SQUARE,
    CIRCLE
}