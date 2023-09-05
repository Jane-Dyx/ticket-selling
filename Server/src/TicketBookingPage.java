import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TicketBookingPage extends Application {
    private static final int NUM_ROWS = 9;
    private static final int NUM_SEATS_PER_ROW = 7;
    private static final double SEAT_SIZE = 30.0;
    private static final Color COLOR_AVAILABLE = Color.LIGHTPINK;
    private static final Color COLOR_SELECTED = Color.LIGHTPINK.deriveColor(1, 1, 1, 0.5);
    private static final Color COLOR_SOLD = Color.GRAY;

    private Button confirmButton;
    private Rectangle[][] seats;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ticket Booking");

        // Create labels for title, time, venue, and price categories
        Label titleLabel = new Label("Event Title: Sample Event");
        Label timeLabel = new Label("Time: 8:00 PM");
        Label venueLabel = new Label("Venue: Sample Venue");
        Label priceCategoryLabel = new Label("Price Categories: (Row A-B, $30), (Row C-D, $20), (Row F-I, $10)");

        // Create a grid pane to hold the seats
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Initialize the seats array
        seats = new Rectangle[NUM_ROWS][NUM_SEATS_PER_ROW];

        // Create seats and add them to the grid pane
        for (int row = 0; row < NUM_ROWS; row++) {
            // Create a label for the row
            Label rowLabel = new Label(Character.toString((char) ('A' + row)));

            // Set label style
            rowLabel.setStyle("-fx-font-weight: bold;");

            // Add row label to the grid pane
            gridPane.add(rowLabel, 0, row);

            for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
                Rectangle rectangle = new Rectangle(SEAT_SIZE, SEAT_SIZE, COLOR_AVAILABLE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(1);
                seats[row][seat] = rectangle;

                final int currentRow = row;
                final int currentSeat = seat;
                rectangle.setOnMouseClicked(event -> {
                    if (rectangle.getFill().equals(COLOR_AVAILABLE)) {
                        rectangle.setFill(COLOR_SELECTED);
                    } else if (rectangle.getFill().equals(COLOR_SELECTED)) {
                        rectangle.setFill(COLOR_AVAILABLE);
                    }
                });

                gridPane.add(rectangle, seat + 1, row);
            }
        }

        // Create a HBox to hold the color indicators and labels
        HBox colorIndicators = new HBox(10);
        colorIndicators.setAlignment(Pos.CENTER);
        colorIndicators.setPadding(new Insets(10));

        // Create color indicator circles
        Circle availableIndicator = new Circle(5, COLOR_AVAILABLE);
        Circle selectedIndicator = new Circle(5, COLOR_SELECTED);
        Circle soldIndicator = new Circle(5, COLOR_SOLD);

        // Create labels for color indicators
        Label availableLabel = new Label("Available");
        Label selectedLabel = new Label("Selected");
        Label soldLabel = new Label("Sold");

        // Add color indicators and labels to the HBox
        colorIndicators.getChildren().addAll(
                new HBox(5, availableIndicator, availableLabel),
                new HBox(5, selectedIndicator, selectedLabel),
                new HBox(5, soldIndicator, soldLabel)
        );

        // Create the confirm button
        confirmButton = new Button("Confirm");

        // Create a VBox to hold the labels, grid pane, color indicators, and confirm button
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(
                titleLabel,
                timeLabel,
                venueLabel,
                priceCategoryLabel,
                gridPane,
                colorIndicators,
                confirmButton
        );

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
