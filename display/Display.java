package display;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import sorts.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display extends Application
{	
	public static void main(String[] args) throws MissingDataException
	{
		launch(args);
	}
	
	private final List<Sortable> algs = new ArrayList<Sortable>(Arrays.asList(new BubbleSort(), new InsertionSort(), new IterativeSort(), new QuickSort1(), new QuickSort2()));
	Stage stage;
	Scene scene2;
	TextField length;
	TextField min;
	TextField max;
	ChoiceBox<Sortable> options;
	
	@Override
	public void start(Stage x)
	{
		stage = x;
		stage.setResizable(false);
		stage.setTitle("Another Shitty Program");
		stage.setOnCloseRequest(e ->
		{
			e.consume();
			closeClick();
		});
		
		//	SCENE 1
		Button but1 = new Button("Continue");
		but1.setOnAction(e -> scene1To2());
		Text text1 = new Text("This program allows you to create a randomized array and sort it using an algorithm of your choice.");
		
		VBox pane1 = new VBox();
		pane1.getChildren().addAll(text1, but1);
		pane1.setPadding(new Insets(5));
		pane1.setAlignment(Pos.CENTER);

		for(Node n : pane1.getChildren())
		{
			VBox.setMargin(n, new Insets(5));
		}
		
		Scene scene1 = new Scene(pane1);
		//	SCENE 1
		
		//	SCENE 2
		Text notice = new Text("Data must be in integer form.");
		Text text2 = new Text("Algorithm:");
		Text text3 = new Text("Array length:");
		Text text4 = new Text("Minimum array number:");
		Text text5 = new Text("Maximum array number:");
		Button but2 = new Button("Continue");
		but2.setOnAction(e -> scene2To3());
		
		options = new ChoiceBox<Sortable>();
		options.getItems().addAll(algs);
		options.getSelectionModel().selectFirst();
		length = new TextField();
		length.setPromptText("length of array");
		min = new TextField();
		min.setPromptText("minimum number");
		max = new TextField();
		max.setPromptText("maximum number");
		
		GridPane pane2 = new GridPane();
		pane2.setPrefWidth(300);
		pane2.setVgap(5);
		pane2.setHgap(5);
		pane2.setPadding(new Insets(5));
		
		pane2.addRow(0, notice);
		GridPane.setColumnSpan(notice, 2);
		GridPane.setHalignment(notice, HPos.CENTER);
		pane2.addRow(1, text2, options);
		pane2.addRow(2, text3, length);
		pane2.addRow(3, text4, min);
		pane2.addRow(4, text5, max);
		pane2.addRow(5, but2);
		GridPane.setColumnSpan(but2, 2);
		GridPane.setHalignment(but2, HPos.CENTER);
		
		GridPane.setHalignment(text2, HPos.RIGHT);
		GridPane.setHalignment(text3, HPos.RIGHT);
		GridPane.setHalignment(text4, HPos.RIGHT);
		GridPane.setHalignment(text5, HPos.RIGHT);

		
		for(Node n : pane2.getChildren())
		{
			GridPane.setMargin(n, new Insets(5));
		}
		
		scene2 = new Scene(pane2);
		//	SCENE 2
		
		stage.setScene(scene1);
		stage.show();
	}
	
	private void scene1To2()
	{
		stage.setScene(scene2);
	}
	
	private void closeClick()
	{	
		Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
		Optional <ButtonType> response = a.showAndWait();
		if(response.get() == ButtonType.YES)
		{
			stage.close();
		}
	}
	
	private void scene2To3()
	{
		boolean bLength = length.getText().trim().length() == 0;
		boolean bMin = min.getText().trim().length() == 0;
		boolean bMax = max.getText().trim().length() == 0;

		try
		{
			if(bLength || bMin || bMax)
			{
				String msg = "";
				if(bLength)
				{
					msg += '\n' + "- length of array";
				}
				if(bMin)
				{
					msg += '\n' + "- minimum number";
				}
				if(bMax)
				{
					msg += '\n' + "- maximum number";
				}
				
				throw new MissingDataException(msg);
			}
			else
			{
				int length_num = Integer.parseInt(length.getText().trim());
				int min_num = Integer.parseInt(min.getText().trim());
				int max_num = Integer.parseInt(max.getText().trim());
				Sortable alg = options.getValue();
				
				if(max_num < min_num)
				{
					throw new MinMaxException(min_num, max_num);
				}
				
				int[] arr = AlgsTest.randomArray(length_num, min_num, max_num);
				String msg = "original: " + Arrays.toString(arr) + '\n' + '\n';
				long t0 = System.nanoTime();
				alg.sort(arr);
				long t1 = System.nanoTime();
				double time = (t1 - t0) / 10e9;
				BigDecimal f = new BigDecimal(time);
				f.round(new MathContext(5));
				
				msg += "sorted: " + Arrays.toString(arr) + '\n' + '\n';
				msg += "time: " + time + " seconds";
				
				Text results = new Text(msg);
				Button again = new Button("Try again?");
				again.setOnAction(e -> scene1To2());
				
				VBox pane_results = new VBox();
				pane_results.setPadding(new Insets(5));
				pane_results.setAlignment(Pos.CENTER);
				pane_results.getChildren().addAll(results, again);
				
				for(Node n : pane_results.getChildren())
				{
					VBox.setMargin(n, new Insets(5));
				}
				
				Scene scene3 = new Scene(pane_results);
				stage.setScene(scene3);
			}
		}
		catch(NumberFormatException e)
		{
			Alert a = new Alert(Alert.AlertType.ERROR, "Data must be in integer form.");
			a.show();
		}
		catch(MissingDataException | MinMaxException e)
		{
			Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage());
			a.show();
		}
	}
	
	private class MissingDataException extends Exception
	{
		private static final long serialVersionUID = 9110655009450050347L;

		MissingDataException(String msg)
		{
			super("The following data is missing:" + '\n' + msg + '\n' + '\n' + "Fill in the data correctly.");
		}
	}
	
	private class MinMaxException extends Exception
	{
		private static final long serialVersionUID = 5962284361500771978L;

		MinMaxException(int min, int max)
		{
			super(min + " isn't bigger than " + max + ".");
		}
	}
}