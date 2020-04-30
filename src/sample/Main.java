package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.*;

public class Main extends Application {

    ArrayList<Point> points = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        TextField textField = new TextField("3");
        textField.setMaxWidth(100);
        textField.setPrefColumnCount(11);
        textField.setPadding(new Insets(5.0));

        Canvas canvas = new Canvas(900, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        CreateArr(3, 300);
        piant(gc);


        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textField, canvas);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.matches("[-+]?\\d+")) {
                if(Integer.valueOf(newValue) > 100 || Integer.valueOf(newValue) < 3)
                    newValue = "3";
                canvas.getGraphicsContext2D().clearRect(0, 0, 900, 700);
                CreateArr(Integer.valueOf(newValue), 300);
                piant(canvas.getGraphicsContext2D());
            }
        });
        Scene scene = new Scene(root, 900, 900);
        stage.setScene(scene);
        stage.setTitle("n-Угольник");
        stage.show();
    }

    void CreateArr(int size, int r) {
        double ug = 0;
        points = new ArrayList<>();
        double ugdelta = 2 * PI / size;
        for (int i = 0; i < size; i++) {
            double x = cos(ug) * r + 450;
            double y = sin(ug) * r + 350;
            Point p = new Point((int)x, (int)y);
            points.add(p);
            ug += ugdelta;
        }
    }

    void piant(GraphicsContext gc){

        gc.beginPath();
        for (int i = 0; i < points.size(); i++) {

            for (int j = 0; j < points.size(); j++)
                if (j != i) {
                    gc.moveTo(points.get(i).x, points.get(i).y);
                    gc.lineTo(points.get(j).x, points.get(j).y);
                }
        }
        gc.stroke();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
