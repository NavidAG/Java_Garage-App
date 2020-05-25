
package garage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GarageTown extends Application{
    
    
    public String kindOf = null;
    public double kindOfRent;
    public boolean universalFlag = true;
    
    //styles:
    String textFieldStyle = "-fx-border-width: 2;-fx-background-color: rgba(225,225,225,.6);-fx-prompt-text-fill: Black; -fx-min-width: 300; -fx-min-height: 30;";
    String defualtButtonStyle = "-fx-background-color: \n" +
        "        rgba(0,0,0,0.08),\n" +
        "        linear-gradient(#5a61af, #51536d),\n" +
        "        linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);\n" +
        "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
        "    -fx-background-radius: 5,5,4;\n" +
        "    -fx-padding: 3 30 3 30;\n" +
        "    -fx-text-fill: #242d35;\n" +
        "    -fx-font-size: 14px;";
    String hoveredButtonStyle = "-fx-background-color: \n" +
        "        rgba(0,0,0,0.08),\n" +
        "        linear-gradient(#5a61af, #51536d),\n" +
        "        linear-gradient(#e4fbff 0%,#cee6fb 5%, #a5d3fb 40%, #88c6fb 40%, #d5faff 80%);\n" +
        "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
        "    -fx-background-radius: 5,5,4;\n" +
        "    -fx-padding: 3 30 3 30;\n" +
        "    -fx-text-fill: #242d35;\n" +
        "    -fx-font-size: 14px;";
    
    
    
    //App Fundementals:
    public static Stage window;
    

    Scene mainScene;
    
    //List items
    Managment managment = new Managment();
    ArrayList<TransformationVehicle> vehicles = new ArrayList<TransformationVehicle>();
    //ArrayList <String> vehiclesArrayList = new ArrayList<String>();
    ListView<String> vehiclesList = new ListView<String>();
    
    ArrayList<Garage> garages = new ArrayList<>();
    ListView<String> garagesList = new ListView<>();
    
    ArrayList<TransformationVehicle> vehiclesOfGarage = new ArrayList<>();
    ListView<String> vehiclesOfGarageList = new ListView<>();
    
    ArrayList<TransformationVehicle> rents = new ArrayList<>();
    ListView<String> rentsList = new ListView<>();
    
    ListView<String> records = new ListView<>();
    //Labels
    Label KindLabel = new Label();
    Label modelNameLabel = new Label();
    Label brandLabel = new Label();
    Label yearLabel = new Label();
    Label technicalSpecsLabel = new Label();
    
    Label kind = new Label("Kind:");
    Label priceLabel = new Label();
    Label brand = new Label("Brand:");
    Label model = new Label("Model:");
    Label year = new Label("Year:");
    Label technicalSpecs = new Label("Technical Specs:");
    Label price = new Label("Price:");
    
    //TextFields:
    TextField modelField;
    TextField brandField;
    TextField yearField;
    TextField technicalSpecsField;
    TextField priceField;
    
    TextField brandFieldForEdit;
    TextField modelFieldForEdit;
    TextField yearFieldForEdit;
    TextField technicalSpecsFieldForEdit;
    TextField priceFieldForEdit;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       

        window = primaryStage;
        BackgroundImage myba = new BackgroundImage(new Image(new File("background.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10));
        mainPane.setBackground(new Background(myba));
        
        mainPane.setTop(topBar());
        mainPane.setLeft(left());
        mainPane.setCenter(center());
        mainPane.setRight(right());
        
        mainScene = new Scene(mainPane, 1400, 700); 
        window.setTitle("Navid's Garage");
        window.setResizable(false);
        window.setScene(mainScene);
        window.getIcons().add(new Image(new File("icon.png").toURI().toString()));
        
        window.show();
    
    }
    
    public BorderPane topBar(){
        MediaPlayer audio = new MediaPlayer(new Media(new File("Music.mp3").toURI().toString()));
        audio.setOnEndOfMedia(new Runnable() {
            public void run() {
              audio.seek(Duration.ZERO);
            }
        });
        MediaView mediaView = new MediaView(audio);
        audio.play();
        BorderPane top = new BorderPane();
        top.setPadding(new Insets(20));
        Text title = new Text("GarageTowm Managment");
        title.setFont(Font.font("Arial Bold", 30));
        title.setFill(Color.BLACK);
        top.setTop(title);
        return top;
    }
    
    public BorderPane left(){
        BorderPane left = new BorderPane();
        left.setPrefWidth(300);
        HBox titleBox = new HBox();
        titleBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(80, 80, 80,.9);-fx-border-radius: 4;");
        titleBox.setPadding(new Insets(10));
        left.setStyle("-fx-border-width: 2;-fx-border-color: rgba(80, 80, 80,.9);-fx-border-radius: 4;");
        Text title = new Text("Vehicles");
        title.setFont(Font.font("Arial Bold", 30));
        title.setFill(Color.WHITE);
        titleBox.getChildren().add(title);
        left.setTop(titleBox);
        vehiclesList.setPrefSize(400, 600); 
        vehiclesList.setFixedCellSize(49);
        vehiclesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        try {
            vehiclesList.getItems().addAll(managment.carsList());
            vehiclesList.getSelectionModel()
                            .selectedItemProperty()
                             .addListener(
                                             (ObservableValue<? extends String> ov,
                                              final String oldvalue,
                                               final String newValue) -> {
                                
                updateProductCenter(newValue);
                
                                
            });
        } catch (IOException e) {
        }
        
        left.setCenter(vehiclesList);
        return left;
    }
    
    public BorderPane center(){
        BorderPane center = new BorderPane();
        center.setPrefWidth(7);
        center.setStyle("-fx-border-width: 2;-fx-border-color: rgba(80, 80, 80,.9);-fx-border-radius: 4;");
        HBox titleBox = new HBox();
        titleBox.setStyle("-fx-border-width: 2;-fx-background-color: rgba(224, 224, 224,.7);-fx-border-radius: 4;");
        titleBox.setPadding(new Insets(10));
        Text title = new Text("Describtion");
        title.setFont(Font.font("Arial Bold", 30));
        title.setFill(Color.BLACK);
        titleBox.getChildren().add(title);
        center.setTop(titleBox);
        VBox kindsVBox = new VBox();
        kindsVBox.setSpacing(7);
        kindsVBox.setPadding(new Insets(5));
        brand.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        model.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        year.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        technicalSpecs.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        price.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        kindsVBox.getChildren().addAll(brand, model, year, technicalSpecs, price);
        center.setLeft(kindsVBox);
        
        VBox factsVBox = new VBox();
        factsVBox.setSpacing(7);
        factsVBox.setPadding(new Insets(5));
        
        brandLabel.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        modelNameLabel.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        yearLabel.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        technicalSpecsLabel.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        priceLabel.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
        factsVBox.getChildren().addAll(brandLabel, modelNameLabel, yearLabel, technicalSpecsLabel, priceLabel);
        center.setRight(factsVBox);
        
        HBox buttonsHBox = new HBox();
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(4);
        buttonsHBox.setPadding(new Insets(4));
        Button addButton = new Button("Add");
        addButton.setCursor(Cursor.HAND);
        addButton.setPrefSize(120, 30);
        addButton.setStyle(defualtButtonStyle);
        addButton.setOnMouseEntered(e -> addButton.setStyle(hoveredButtonStyle));
        addButton.setOnMouseExited(e -> addButton.setStyle(defualtButtonStyle));
        Button removeButton = new Button("Remove");
        removeButton.setCursor(Cursor.HAND);
        removeButton.setPrefSize(120, 30);
        removeButton.setStyle(defualtButtonStyle);
        removeButton.setOnMouseEntered(e -> removeButton.setStyle(hoveredButtonStyle));
        removeButton.setOnMouseExited(e -> removeButton.setStyle(defualtButtonStyle));
        Button editButton = new Button("Edit");
        editButton.setCursor(Cursor.HAND);
        editButton.setPrefSize(120, 30);
        editButton.setStyle(defualtButtonStyle);
        editButton.setOnMouseEntered(e -> editButton.setStyle(hoveredButtonStyle));
        editButton.setOnMouseExited(e -> editButton.setStyle(defualtButtonStyle));        
        buttonsHBox.getChildren().addAll(addButton, removeButton, editButton);
        center.setBottom(buttonsHBox);
        //REMOVE
        EventHandler<ActionEvent> removeEventHandler = new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                final int selectedIdx = vehiclesList.getSelectionModel().getSelectedIndex();
                String selectedItm = vehiclesList.getSelectionModel().getSelectedItem();
                
                if (selectedIdx != -1) {
                    final int newSelectedIdx =
                            (selectedIdx == vehiclesList.getItems().size() - 1)
                            ? selectedIdx - 1
                            : selectedIdx;
                    vehiclesList.getItems().remove(selectedIdx);
                    vehiclesList.getSelectionModel().select(newSelectedIdx);
                    
                }
                
                try {
                vehicles.clear();
                vehicles.addAll(managment.carsInfo());
                } catch (IOException ex) {
                System.out.println("congrass4");
                }
                try {
                    managment.remove(vehicles.get(selectedIdx));
                } catch (IOException ex) {
                    System.out.println("congrass5");
                }
                try {
                vehicles.clear();
                vehicles.addAll(managment.carsInfo());
                } catch (IOException ex) {
                System.out.println("congrass4");
                }
                try {
                    for(int i=0; i<managment.carsInfo().size(); i++){
                        
                            System.out.println(managment.carsInfo().get(i).getBrand());
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GarageTown.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("///////////////////");
                for(int i=0; i<vehicles.size(); i++)
                    System.out.println(vehicles.get(i).getBrand());
                
                System.out.println("..........");
            }
            
            
        };
        
        removeButton.setOnAction(removeEventHandler);
        //ADD
        Stage addStage = new Stage();
        EventHandler<ActionEvent> addEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane addPane = new BorderPane();
                addPane.setPadding(new Insets(20));
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                addPane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Add");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                addPane.setTop(pagetitle);
                Label brand = new Label("Brand:");
                Label model = new Label("Model:");
                Label year = new Label("Year:");
                Label technicalSpecs = new Label("Technical Specs:");
                Label price = new Label("Price:");
                Label kind = new Label("Kind:");
                
                VBox titlesPane = new VBox();
                titlesPane.setPadding(new Insets(20,0,0,0));
                titlesPane.setSpacing(10);
                brand.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                model.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                year.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                technicalSpecs.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                price.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                kind.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                titlesPane.getChildren().addAll(brand, model, year, technicalSpecs, price, kind);
                addPane.setLeft(titlesPane);
                
                VBox newDataPane = new VBox();
                newDataPane.setPadding(new Insets(20,0,0,0));
                newDataPane.setSpacing(10);
                brandField = new TextField();
                brandField.setStyle(textFieldStyle);
                modelField = new TextField();
                modelField.setStyle(textFieldStyle);
                yearField = new TextField();
                yearField.setStyle(textFieldStyle);
                technicalSpecsField = new TextField();
                technicalSpecsField.setStyle(textFieldStyle);
                priceField = new TextField();
                priceField.setStyle(textFieldStyle);
                HBox radioButtonPane = new HBox();
                ToggleGroup group = new ToggleGroup();
                radioButtonPane.setSpacing(8);
                RadioButton kindIsCar = new RadioButton("Car");
                RadioButton kindIsBus = new RadioButton("Bus");
                RadioButton kindIsTruck = new RadioButton("Truck");
                RadioButton kindIsMotorCycle = new RadioButton("Motorcycle");
                kindIsCar.setToggleGroup(group);
                kindIsBus.setToggleGroup(group);
                kindIsTruck.setToggleGroup(group);
                kindIsMotorCycle.setToggleGroup(group);
                radioButtonPane.getChildren().addAll(kindIsCar, kindIsBus, kindIsTruck, kindIsMotorCycle);
                
                
                
                kindIsCar.setOnAction(e -> {
                if (kindIsCar.isSelected()) {
                }
                    kindOf = "car";
                });

                kindIsBus.setOnAction(e -> {
                if (kindIsBus.isSelected()) {
                }
                    kindOf = "bus";
                });
                
                kindIsMotorCycle.setOnAction(e -> {
                if (kindIsMotorCycle.isSelected()) {
                }
                    kindOf = "motorcycle";
                });
                
                kindIsTruck.setOnAction(e -> {
                if (kindIsTruck.isSelected()) {
                }
                    kindOf = "truck";
                });
                
                
                newDataPane.getChildren().addAll(brandField, modelField, yearField, technicalSpecsField, priceField, radioButtonPane);
                addPane.setRight(newDataPane);
                
               
                Button confirmButtonForAdd = new Button("Confirm");
                confirmButtonForAdd.setStyle(defualtButtonStyle);
                confirmButtonForAdd.setOnMouseEntered(e->confirmButtonForAdd.setStyle(hoveredButtonStyle));
                confirmButtonForAdd.setOnMouseExited(e-> confirmButtonForAdd.setStyle(defualtButtonStyle));
                confirmButtonForAdd.setCursor(Cursor.HAND);
                Button cancelButtonForAdd = new Button("Cancel");
                cancelButtonForAdd.setStyle(defualtButtonStyle);
                cancelButtonForAdd.setOnMouseEntered(e->cancelButtonForAdd.setStyle(hoveredButtonStyle));
                cancelButtonForAdd.setOnMouseExited(e-> cancelButtonForAdd.setStyle(defualtButtonStyle));
                cancelButtonForAdd.setCursor(Cursor.HAND);
                Label emptyLabel = new Label("The Textfields cannot be empthy!");
                emptyLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
                emptyLabel.setVisible(false);
                EventHandler<ActionEvent> confirmFunctionForAdd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(brandField.getText().equals("") ||
                        modelField.getText().equals("") ||
                        yearField.getText().equals("") ||
                        technicalSpecsField.getText().equals("") ||
                        priceField.getText().equals("")){

                            emptyLabel.setVisible(true);

                        }
                        else{
                            try {
                                managment.add(kindOf, brandField.getText(), modelField.getText(), yearField.getText(), technicalSpecsField.getText(), priceField.getText());
                            } catch (IOException ex) {
                                System.out.println("congrass1");
                            }
                            emptyLabel.setVisible(false);
                            vehiclesList.refresh();
                            vehiclesList.getItems().clear();
                            
                            try {
                                vehiclesList.getItems().addAll(managment.carsList());
                                vehicles.addAll(managment.carsInfo());
                            } catch (IOException ex) {
                                System.out.println("congrass2");
                            }
                            addStage.close();
                        }
                    }
                };
                confirmButtonForAdd.setOnAction(confirmFunctionForAdd);
                
                
                
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        addStage.close();
                    }
                };
                cancelButtonForAdd.setOnAction(cancelFunction);
                
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(emptyLabel, cancelButtonForAdd, confirmButtonForAdd);
                addPane.setBottom(buttonPane);
                addPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ENTER)
                    {
                      confirmButtonForAdd.fire();
                    }
                });
                addPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      cancelButtonForAdd.fire();
                    }
                });
                addStage.setResizable(false);
                addStage.setTitle("Add");
                Scene addScene= new Scene(addPane, 650, 500);
                addStage.setScene(addScene);
                addStage.show();
            }
            
        };
        
        addButton.setOnAction(addEventHandler);
        
        //edit ---------------------------------
        
        Stage editStage = new Stage();
        EventHandler<ActionEvent> editEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane editPane = new BorderPane();
                editPane.setPadding(new Insets(20));
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                editPane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Edit");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                editPane.setTop(pagetitle);
                Label brand = new Label("Brand:");
                Label model = new Label("Model:");
                Label year = new Label("Year:");
                Label technicalSpecs = new Label("Technical Specs:");
                Label price = new Label("Price:");
                Label kind = new Label("Kind:");
                
                VBox titlesPane = new VBox();
                titlesPane.setPadding(new Insets(20,0,0,0));
                titlesPane.setSpacing(10);
                brand.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                model.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                year.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                technicalSpecs.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                price.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                kind.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                titlesPane.getChildren().addAll(brand, model, year, technicalSpecs, price, kind);
                editPane.setLeft(titlesPane);
                
                VBox newDataPane = new VBox();
                newDataPane.setPadding(new Insets(20,0,0,0));
                newDataPane.setSpacing(10);
                brandFieldForEdit = new TextField();
                brandFieldForEdit.setStyle(textFieldStyle);
                modelFieldForEdit = new TextField();
                modelFieldForEdit.setStyle(textFieldStyle);
                yearFieldForEdit = new TextField();
                yearFieldForEdit.setStyle(textFieldStyle);
                technicalSpecsFieldForEdit = new TextField();
                technicalSpecsFieldForEdit.setStyle(textFieldStyle);
                priceFieldForEdit = new TextField();
                priceFieldForEdit.setStyle(textFieldStyle);
                HBox radioButtonPane = new HBox();
                ToggleGroup group = new ToggleGroup();
                radioButtonPane.setSpacing(8);
                RadioButton kindIsCar = new RadioButton("Car");
                RadioButton kindIsBus = new RadioButton("Bus");
                RadioButton kindIsTruck = new RadioButton("Truck");
                RadioButton kindIsMotorCycle = new RadioButton("Motorcycle");
                kindIsCar.setToggleGroup(group);
                kindIsBus.setToggleGroup(group);
                kindIsTruck.setToggleGroup(group);
                kindIsMotorCycle.setToggleGroup(group);
                radioButtonPane.getChildren().addAll(kindIsCar, kindIsBus, kindIsTruck, kindIsMotorCycle);
                

                
                
                kindIsCar.setOnAction(e -> {
                if (kindIsCar.isSelected()) {
                }
                    kindOf = "car";
                });

                kindIsBus.setOnAction(e -> {
                if (kindIsBus.isSelected()) {
                }
                    kindOf = "bus";
                });
                
                kindIsMotorCycle.setOnAction(e -> {
                if (kindIsMotorCycle.isSelected()) {
                }
                    kindOf = "motorcycle";
                });
                
                kindIsTruck.setOnAction(e -> {
                if (kindIsTruck.isSelected()) {
                }
                    kindOf = "truck";
                });
                updateProductEdit();
                
                newDataPane.getChildren().addAll(brandFieldForEdit, modelFieldForEdit, yearFieldForEdit, technicalSpecsFieldForEdit, priceFieldForEdit, radioButtonPane);
                editPane.setRight(newDataPane);
                
               
                Button confirmButtonForEdit = new Button("Confirm");
                confirmButtonForEdit.setStyle(defualtButtonStyle);
                confirmButtonForEdit.setOnMouseEntered(e->confirmButtonForEdit.setStyle(hoveredButtonStyle));
                confirmButtonForEdit.setOnMouseExited(e-> confirmButtonForEdit.setStyle(defualtButtonStyle));
                confirmButtonForEdit.setCursor(Cursor.HAND);
                Button cancelButtonForEdit = new Button("Cancel");
                cancelButtonForEdit.setStyle(defualtButtonStyle);
                cancelButtonForEdit.setOnMouseEntered(e->cancelButtonForEdit.setStyle(hoveredButtonStyle));
                cancelButtonForEdit.setOnMouseExited(e-> cancelButtonForEdit.setStyle(defualtButtonStyle));
                cancelButtonForEdit.setCursor(Cursor.HAND);
                Label emptyLabel = new Label("The Textfields cannot be empthy!");
                emptyLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
                emptyLabel.setVisible(false);
                EventHandler<ActionEvent> confirmFunctionForEdit = new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            if( (brandFieldForEdit.getText().isEmpty() ||
                            modelFieldForEdit.getText().isEmpty() ||
                            yearFieldForEdit.getText().isEmpty() ||
                            technicalSpecsFieldForEdit.getText().isEmpty() ||
                            priceFieldForEdit.getText().isEmpty()) 
                                   ){
                                    
                                emptyLabel.setVisible(true);
                                universalFlag = true;
                            }
                            else{
                                universalFlag = false;
                                emptyLabel.setVisible(false);
                                try {
                                    managment.add(kindOf, brandFieldForEdit.getText(), modelFieldForEdit.getText(), yearFieldForEdit.getText(), technicalSpecsFieldForEdit.getText(), priceFieldForEdit.getText());
                                    TransformationVehicle vehicletemp;
                                    if(kindOf.equals("car"))
                                        vehicletemp = new Car();
                                    else if (kindOf.equals("bus")) {
                                        vehicletemp = new Bus();
                                    }
                                    else if (kindOf.equals("Motorcycle")) {
                                        vehicletemp = new MotorCycle();
                                    }
                                    else{
                                        vehicletemp = new Truck();
                                    }
                                    vehicletemp.setBrand(brandLabel.getText());
                                    vehicletemp.setModelName(brandLabel.getText());
                                    vehicletemp.setYear(Integer.parseInt(yearLabel.getText()));
                                    vehicletemp.setTechnicalSpecs(technicalSpecsLabel.getText());
                                    vehicletemp.setPrice(Double.parseDouble(priceLabel.getText()));
                                    managment.remove(vehicletemp);
                                } catch (IOException ex) {
                                    System.out.println("congrass1");
                                }
                                
                                vehiclesList.refresh();
                                vehiclesList.getItems().clear();
                                vehicles.clear();
                                
                                try {
                                    vehiclesList.getItems().addAll(managment.carsList());
                                    vehicles.addAll(managment.carsInfo());
                                } catch (IOException ex) {
                                    System.out.println("congrass2");
                                }
                                editStage.close();
                            }
                        }
                };
                confirmButtonForEdit.addEventFilter(ActionEvent.ACTION, confirmFunctionForEdit);
                
                
               
                
                
                
                
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        editStage.close();
                        
                    }
                };
                cancelButtonForEdit.setOnAction(cancelFunction);
                
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(emptyLabel, cancelButtonForEdit, confirmButtonForEdit);
                editPane.setBottom(buttonPane);
                editPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ENTER)
                    {
                      confirmButtonForEdit.fire();
                    }
                });
                editPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      cancelButtonForEdit.fire();
                    }
                });
                editStage.setResizable(false);
                editStage.setTitle("Edit");
                Scene editScene= new Scene(editPane, 650, 500);
                editStage.setScene(editScene);
                editStage.show();
            }
            
        };
        
        editButton.addEventHandler(ActionEvent.ACTION, editEventHandler);
        editButton.addEventHandler(ActionEvent.ACTION, removeEventHandler);
        
        
        
        
        return center;
    }
    
    public BorderPane right(){
        BorderPane right = new BorderPane();
        BorderPane RightOfRight = new BorderPane();
        BorderPane leftOfRight = new BorderPane();
        right.setPadding(new Insets(10));
        right.setPrefWidth(500);
        right.setStyle("-fx-border-width: 2;-fx-border-color: rgba(80, 80, 80,.9);-fx-border-radius: 4;");
        RightOfRight.setStyle("-fx-border-width: 2;-fx-border-color: rgba(80, 80, 80,.9);-fx-border-radius: 4;");
        leftOfRight.setStyle("-fx-border-width: 2;-fx-border-color: rgba(80, 80, 80,.9);-fx-border-radius: 4;");
        HBox titleBox = new HBox();
        titleBox.setPadding(new Insets(5));
        Text title = new Text("Garages                Rents");
        title.setFont(Font.font("Arial Bold", 30));
        title.setFill(Color.BLACK);
        titleBox.getChildren().add(title);
        right.setTop(titleBox);
        
        VBox buttonsPane = new VBox();
        
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setPadding(new Insets(4));
        buttonsPane.setSpacing(3);
        
        Button addANewGarageButton = new Button("Add a new Garage");
        addANewGarageButton.setCursor(Cursor.HAND);
        addANewGarageButton.setPrefSize(210, 30);
        addANewGarageButton.setStyle(defualtButtonStyle);
        addANewGarageButton.setOnMouseEntered(e -> addANewGarageButton.setStyle(hoveredButtonStyle));
        addANewGarageButton.setOnMouseExited(e -> addANewGarageButton.setStyle(defualtButtonStyle));
        
        Button deleteAGarageButton = new Button("Delete the Garage");
        deleteAGarageButton.setCursor(Cursor.HAND);
        deleteAGarageButton.setPrefSize(210, 30);
        deleteAGarageButton.setStyle(defualtButtonStyle);
        deleteAGarageButton.setOnMouseEntered(e -> deleteAGarageButton.setStyle(hoveredButtonStyle));
        deleteAGarageButton.setOnMouseExited(e -> deleteAGarageButton.setStyle(defualtButtonStyle));
        
        Button addCarToGarageButton = new Button("Add The vehicle");
        addCarToGarageButton.setCursor(Cursor.HAND);
        addCarToGarageButton.setPrefSize(210, 30);
        addCarToGarageButton.setStyle(defualtButtonStyle);
        addCarToGarageButton.setOnMouseEntered(e -> addCarToGarageButton.setStyle(hoveredButtonStyle));
        addCarToGarageButton.setOnMouseExited(e -> addCarToGarageButton.setStyle(defualtButtonStyle));
        
        Button staffQuantityButton = new Button("staff's quantity");
        staffQuantityButton.setCursor(Cursor.HAND);
        staffQuantityButton.setPrefSize(210, 30);
        staffQuantityButton.setStyle(defualtButtonStyle);
        staffQuantityButton.setOnMouseEntered(e -> staffQuantityButton.setStyle(hoveredButtonStyle));
        staffQuantityButton.setOnMouseExited(e -> staffQuantityButton.setStyle(defualtButtonStyle));
        
        garagesList.setPrefSize(210, 600); 
        garagesList.setFixedCellSize(30);
        
        buttonsPane.getChildren().addAll(garagesList, addANewGarageButton, deleteAGarageButton, addCarToGarageButton, staffQuantityButton);
        
        //Add a new garage
        Stage addANewGarageStage = new Stage();
        EventHandler<ActionEvent> addANewGarageEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane addANewGaragePane = new BorderPane();
                addANewGaragePane.setPadding(new Insets(20));
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                addANewGaragePane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Enter the Garage Name");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                addANewGaragePane.setTop(pagetitle);
               
                TextField theGarageField = new TextField();
                theGarageField.setPromptText("Name");
                theGarageField.setStyle(textFieldStyle);
                TextField theGarageStaffField = new TextField();
                theGarageStaffField.setPromptText("Staff's Quantity");
                theGarageStaffField.setStyle(textFieldStyle);
                VBox fieldsPane = new VBox();
                fieldsPane.setPadding(new Insets(20));
                fieldsPane.setSpacing(7);
                fieldsPane.getChildren().addAll(theGarageField, theGarageStaffField);
                addANewGaragePane.setCenter(fieldsPane);
                
                Button confirmButtonAddANewGarage = new Button("Confirm");
                confirmButtonAddANewGarage.setStyle(defualtButtonStyle);
                confirmButtonAddANewGarage.setOnMouseEntered(e->confirmButtonAddANewGarage.setStyle(hoveredButtonStyle));
                confirmButtonAddANewGarage.setOnMouseExited(e-> confirmButtonAddANewGarage.setStyle(defualtButtonStyle));
                confirmButtonAddANewGarage.setCursor(Cursor.HAND);
                Button cancelButtonAddANewGarage = new Button("Cancel");
                cancelButtonAddANewGarage.setStyle(defualtButtonStyle);
                cancelButtonAddANewGarage.setOnMouseEntered(e->cancelButtonAddANewGarage.setStyle(hoveredButtonStyle));
                cancelButtonAddANewGarage.setOnMouseExited(e-> cancelButtonAddANewGarage.setStyle(defualtButtonStyle));
                cancelButtonAddANewGarage.setCursor(Cursor.HAND);
                Label emptyLabel = new Label("The Textfields cannot be empthy!");
                emptyLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
                emptyLabel.setVisible(false);
                Label existenceLabel = new Label("This name is already taken! try a new name.");
                existenceLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
                existenceLabel.setVisible(false);
                fieldsPane.getChildren().add(existenceLabel);
                EventHandler<ActionEvent> confirmFunctionForAdd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Garage garage;
                        if(theGarageField.getText().isEmpty() || 
                                theGarageStaffField.getText().isEmpty()){

                            emptyLabel.setVisible(true);
                            existenceLabel.setVisible(false);
                        }
                        else{
                            emptyLabel.setVisible(false);
                            garage = new Garage(theGarageField.getText(), Integer.parseInt(theGarageStaffField.getText()));
                            if(garage.createANewFile(theGarageField.getText())==false){
                                existenceLabel.setVisible(true);
                            }else{
                                garage = new Garage(theGarageField.getText(), Integer.parseInt(theGarageStaffField.getText()));
                                garage.createANewFile(garage.getGarageName());
                                existenceLabel.setVisible(false);
                                garages.add(garage);
                                garagesList.getItems().add(garage.getGarageName());
                                addANewGarageStage.close();
                                for(int i=0; i<garages.size(); i++){
                                    System.out.println(garages.get(i).getGarageName());
                                    System.out.println("..");
                                    System.out.println(garagesList.getItems().get(i));
                                    System.out.println("- - - - - ");
                                    addANewGarageStage.close();
                                }
                            }
                        }
                    }
                };
                confirmButtonAddANewGarage.setOnAction(confirmFunctionForAdd);
                

                
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        addANewGarageStage.close();
                    }
                };
                cancelButtonAddANewGarage.setOnAction(cancelFunction);
                
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(emptyLabel, cancelButtonAddANewGarage, confirmButtonAddANewGarage);
                addANewGaragePane.setBottom(buttonPane);
                addANewGaragePane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ENTER)
                    {
                      confirmButtonAddANewGarage.fire();
                    }
                });
                addANewGaragePane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      cancelButtonAddANewGarage.fire();
                    }
                });
                addANewGarageStage.setResizable(false);
                addANewGarageStage.setTitle("the Garage will be added to the company's possessions");
                Scene addANewGarageScene= new Scene(addANewGaragePane, 500, 300);
                addANewGarageStage.setScene(addANewGarageScene);
                addANewGarageStage.show();
            }
            
        };
        
        addANewGarageButton.setOnAction(addANewGarageEventHandler);
        EventHandler<ActionEvent> deleteGarageEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Garage garage = null;
                garage = getTheGarage(garagesList.getSelectionModel().getSelectedItem());
                garages.remove(garage);
                garagesList.getItems().remove(garage.getGarageName());
                Garage.removeGarage(garage.getGarageName());
            }
        };
        
        deleteAGarageButton.setOnAction(deleteGarageEventHandler);
        
        EventHandler<ActionEvent> addCarToGarageEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vehiclesOfGarageList.refresh();
                Garage garage = getTheGarage(garagesList.getSelectionModel().getSelectedItem());
                try {
                    garage.garageData(getTheVehicle(vehiclesList.getSelectionModel().getSelectedItem()), garage.getGarageName());
                } catch (IOException ex) {
                    Logger.getLogger(GarageTown.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        };
        
        addCarToGarageButton.setOnAction(addCarToGarageEventHandler);
        
        Stage staffQuantityStage = new Stage();
        EventHandler<ActionEvent> staffQuantityEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane staffQuantityPane = new BorderPane();
                staffQuantityPane.setPadding(new Insets(20));
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                staffQuantityPane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Enter the staff's quantity");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                staffQuantityPane.setTop(pagetitle);
               
                TextField staffField = new TextField();
                staffField.setStyle(textFieldStyle);
                staffField.setText(Integer.toString(getTheGarage(garagesList.getSelectionModel().getSelectedItem()).getStaff()));
                staffQuantityPane.setCenter(staffField);
                
                Button confirmButtonForAdd = new Button("Confirm");
                confirmButtonForAdd.setStyle(defualtButtonStyle);
                confirmButtonForAdd.setOnMouseEntered(e->confirmButtonForAdd.setStyle(hoveredButtonStyle));
                confirmButtonForAdd.setOnMouseExited(e-> confirmButtonForAdd.setStyle(defualtButtonStyle));
                confirmButtonForAdd.setCursor(Cursor.HAND);
                Button cancelButtonForAdd = new Button("Cancel");
                cancelButtonForAdd.setStyle(defualtButtonStyle);
                cancelButtonForAdd.setOnMouseEntered(e->cancelButtonForAdd.setStyle(hoveredButtonStyle));
                cancelButtonForAdd.setOnMouseExited(e-> cancelButtonForAdd.setStyle(defualtButtonStyle));
                cancelButtonForAdd.setCursor(Cursor.HAND);
                Label emptyLabel = new Label("The Textfields cannot be empthy!");
                emptyLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
                emptyLabel.setVisible(false);
                EventHandler<ActionEvent> confirmFunctionForAdd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Garage garage;
                        if(staffField.getText().isEmpty()){

                            emptyLabel.setVisible(true);

                        }
                        else{
                            garage = getTheGarage(garagesList.getSelectionModel().getSelectedItem());
                            garage.setStaff(Integer.parseInt(staffField.getText()));
                            emptyLabel.setVisible(false);                            
                            staffQuantityStage.close();
                        }
                    }
                };
                confirmButtonForAdd.setOnAction(confirmFunctionForAdd);
                
                
                
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        staffQuantityStage.close();
                    }
                };
                cancelButtonForAdd.setOnAction(cancelFunction);
                
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(emptyLabel, cancelButtonForAdd, confirmButtonForAdd);
                staffQuantityPane.setBottom(buttonPane);
                staffQuantityPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ENTER)
                    {
                      confirmButtonForAdd.fire();
                    }
                });
                staffQuantityPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      cancelButtonForAdd.fire();
                    }
                });
                staffQuantityStage.setResizable(false);
                staffQuantityStage.setTitle("Staff Quantity");
                Scene staffQuantityScene= new Scene(staffQuantityPane, 500, 300);
                staffQuantityStage.setScene(staffQuantityScene);
                staffQuantityStage.show();
            }
            
        };
        staffQuantityButton.setOnAction(staffQuantityEventHandler);
        leftOfRight.setCenter(buttonsPane);
        right.setLeft(leftOfRight);
        //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\|||||||||||||||||||||||||||||||||||
        VBox managePane = new VBox();
        managePane.setAlignment(Pos.CENTER);
        managePane.setPadding(new Insets(4));
        managePane.setSpacing(3);
        rentsList.setPrefSize(210, 700); 
        rentsList.setFixedCellSize(40);
        managePane.getChildren().addAll(rentsList);
        RightOfRight.setCenter(managePane);
        
        vehiclesOfGarageList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        /*vehiclesOfGarageList.getSelectionModel()
        .selectedItemProperty()
        .addListener(
        (ObservableValue<? extends String> ov,
        final String oldvalue,
        final String newValue) -> {
        
        updateVehicleListOfGarages(newValue);
        });*/
        Button rentButton = new Button("Rent");
        rentButton.setCursor(Cursor.HAND);
        rentButton.setPrefSize(210, 30);
        rentButton.setStyle(defualtButtonStyle);
        rentButton.setOnMouseEntered(e -> rentButton.setStyle(hoveredButtonStyle));
        rentButton.setOnMouseExited(e -> rentButton.setStyle(defualtButtonStyle));
        Button returnButton = new Button("Return");
        returnButton.setCursor(Cursor.HAND);
        returnButton.setPrefSize(210, 30);
        returnButton.setStyle(defualtButtonStyle);
        returnButton.setOnMouseEntered(e -> returnButton.setStyle(hoveredButtonStyle));
        returnButton.setOnMouseExited(e -> returnButton.setStyle(defualtButtonStyle));
        Button recordsButton = new Button("Records");
        recordsButton.setCursor(Cursor.HAND);
        recordsButton.setPrefSize(210, 30);
        recordsButton.setStyle(defualtButtonStyle);
        recordsButton.setOnMouseEntered(e -> recordsButton.setStyle(hoveredButtonStyle));
        recordsButton.setOnMouseExited(e -> recordsButton.setStyle(defualtButtonStyle));
        Button revenueButton = new Button("Revenue");
        revenueButton.setCursor(Cursor.HAND);
        revenueButton.setPrefSize(210, 30);
        revenueButton.setStyle(defualtButtonStyle);
        revenueButton.setOnMouseEntered(e -> revenueButton.setStyle(hoveredButtonStyle));
        revenueButton.setOnMouseExited(e -> revenueButton.setStyle(defualtButtonStyle));
        managePane.getChildren().addAll(rentButton, returnButton, recordsButton, revenueButton);
        //rent
        Stage rentStage = new Stage();
        EventHandler<ActionEvent> rentEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane rentPane = new BorderPane();
                rentPane.setPadding(new Insets(20));
                VBox radioPane = new VBox();
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                rentPane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Enter the person's name:");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                rentPane.setTop(pagetitle);
                radioPane.setPadding(new Insets(20));
                radioPane.setSpacing(10);
                TextField personField = new TextField();
                personField.setStyle(textFieldStyle);
                radioPane.getChildren().add(personField);
                ToggleGroup group1 = new ToggleGroup();
                RadioButton hourlyButton = new RadioButton("HOURLY");
                RadioButton dailyButton = new RadioButton("DAILY");
                RadioButton weeklyButton = new RadioButton("WEEKLY");
                RadioButton monthlyButton = new RadioButton("MONTHLY");
                RadioButton yearlyButton = new RadioButton("YEARLY");
                dailyButton.setToggleGroup(group1);
                hourlyButton.setToggleGroup(group1);
                weeklyButton.setToggleGroup(group1);
                monthlyButton.setToggleGroup(group1);
                yearlyButton.setToggleGroup(group1);
                
                rentPane.setCenter(radioPane);
                hourlyButton.setOnAction(e -> {
                if (hourlyButton.isSelected()) {
                }
                    kindOfRent=1;
                });
                dailyButton.setOnAction(e -> {
                if (dailyButton.isSelected()) {
                }
                    kindOfRent=2;
                });
                weeklyButton.setOnAction(e -> {
                if (weeklyButton.isSelected()) {
                }
                    kindOfRent=3;
                });
                monthlyButton.setOnAction(e -> {
                if (monthlyButton.isSelected()) {
                }
                    kindOfRent=4*.1;
                });
                yearlyButton.setOnAction(e -> {
                if (yearlyButton.isSelected()) {
                }
                    kindOfRent=5*.2;
                });
                CheckBox winteCheckBox = new CheckBox("Winter Sale");
                winteCheckBox.setOnAction(e -> {
                if (winteCheckBox.isSelected()) {
                }
                    kindOfRent *= .15;
                });
                radioPane.getChildren().addAll(hourlyButton, dailyButton, weeklyButton, monthlyButton, yearlyButton, winteCheckBox);
                Button confirmButtonForAdd = new Button("Confirm");
                confirmButtonForAdd.setStyle(defualtButtonStyle);
                confirmButtonForAdd.setOnMouseEntered(e->confirmButtonForAdd.setStyle(hoveredButtonStyle));
                confirmButtonForAdd.setOnMouseExited(e-> confirmButtonForAdd.setStyle(defualtButtonStyle));
                confirmButtonForAdd.setCursor(Cursor.HAND);
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle(defualtButtonStyle);
                cancelButton.setOnMouseEntered(e->cancelButton.setStyle(hoveredButtonStyle));
                cancelButton.setOnMouseExited(e-> cancelButton.setStyle(defualtButtonStyle));
                cancelButton.setCursor(Cursor.HAND);
                Label emptyLabel = new Label("The Textfields cannot be empthy!");
                emptyLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
                emptyLabel.setVisible(false);
                EventHandler<ActionEvent> confirmFunctionForAdd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TransformationVehicle vehicle;
                        if(personField.getText().isEmpty()){

                            emptyLabel.setVisible(true);
                            
                        }
                        else{
                            
                            vehicle = getTheVehicle(vehiclesList.getSelectionModel().getSelectedItem());
                            rents.add(vehicle);
                            rentsList.getItems().add(personField.getText() + ": " + vehicle.getBrand() + " " + vehicle.getModelName());
                            
                            rentsList.refresh();
                            try {
                                managment.rent(vehicle, personField.getText(), kindOfRent);
                            } catch (IOException ex) {
                                Logger.getLogger(GarageTown.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            emptyLabel.setVisible(false);                            
                            rentStage.close();
                        }
                    }
                };
                confirmButtonForAdd.setOnAction(confirmFunctionForAdd);
                
                
                
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        rentStage.close();
                    }
                };
                cancelButton.setOnAction(cancelFunction);
                
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(emptyLabel, cancelButton, confirmButtonForAdd);
                rentPane.setBottom(buttonPane);
                rentPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ENTER)
                    {
                      confirmButtonForAdd.fire();
                    }
                });
                rentPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      cancelButton.fire();
                    }
                });
                rentStage.setResizable(false);
                rentStage.setTitle("Rent");
                Scene rentScene = new Scene(rentPane, 500, 360);
                rentStage.setScene(rentScene);
                rentStage.show();
            }
            
        };
        rentButton.setOnAction(rentEventHandler);
        
        //return
        Stage returnStage = new Stage();
        EventHandler<ActionEvent> returnEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane returnPane = new BorderPane();
                returnPane.setPadding(new Insets(20));
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                returnPane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Returning Prosses");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                VBox finalPane = new VBox();
                finalPane.setSpacing(9);
                finalPane.setPadding(new Insets(20));
                returnPane.setTop(pagetitle);
                Label info = new Label("hi");
                info.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
//                TransformationVehicle thevehicle = getTheVehicle(vehiclesList.getSelectionModel().getSelectedItem());
                info.setText(rentsList.getSelectionModel().getSelectedItem());
                CheckBox damageCheckBox = new CheckBox("the borrowed car is damaged");
                finalPane.getChildren().addAll(info, damageCheckBox);
                returnPane.setCenter(finalPane);
                
                
                Label emptyLabel = new Label("This client will pay 2000$ more");
                emptyLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
                emptyLabel.setVisible(false);
                EventHandler<ActionEvent> handler = e -> {
                    if (damageCheckBox.isSelected()) {
                        emptyLabel.setVisible(true);
                    }
                    else{
                        emptyLabel.setVisible(false);
                    }
                };
                damageCheckBox.setOnAction(handler);

                Button confirmButtonForAdd = new Button("Confirm");
                confirmButtonForAdd.setStyle(defualtButtonStyle);
                confirmButtonForAdd.setOnMouseEntered(e->confirmButtonForAdd.setStyle(hoveredButtonStyle));
                confirmButtonForAdd.setOnMouseExited(e-> confirmButtonForAdd.setStyle(defualtButtonStyle));
                confirmButtonForAdd.setCursor(Cursor.HAND);
                Button cancelButton = new Button("Cancel");
                cancelButton.setStyle(defualtButtonStyle);
                cancelButton.setOnMouseEntered(e->cancelButton.setStyle(hoveredButtonStyle));
                cancelButton.setOnMouseExited(e-> cancelButton.setStyle(defualtButtonStyle));
                cancelButton.setCursor(Cursor.HAND);
                
                
                EventHandler<ActionEvent> confirmFunctionForAdd = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TransformationVehicle vehicle;
                        rents.remove(rentsList.getSelectionModel().getSelectedIndex());
                        rentsList.getItems().remove(rentsList.getSelectionModel().getSelectedIndex());
                        returnStage.close();
                    }
                };
                confirmButtonForAdd.setOnAction(confirmFunctionForAdd);
                
                
                
                EventHandler<ActionEvent> cancelFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        returnStage.close();
                    }
                };
                cancelButton.setOnAction(cancelFunction);
                
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(emptyLabel, cancelButton, confirmButtonForAdd);
                returnPane.setBottom(buttonPane);
                returnPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ENTER)
                    {
                      confirmButtonForAdd.fire();
                    }
                });
                returnPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      cancelButton.fire();
                    }
                });
                returnStage.setResizable(false);
                returnStage.setTitle("Return");
                Scene returnScene = new Scene(returnPane, 500, 300);
                returnStage.setScene(returnScene);
                returnStage.show();
            }
            
        };
        returnButton.setOnAction(returnEventHandler);
        
        //records
        Stage recordsStage = new Stage();
        EventHandler<ActionEvent> recordsEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane recordsPane = new BorderPane();
                recordsPane.setPadding(new Insets(20));
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                recordsPane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Records");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                VBox finalPane = new VBox();
                
                records.getItems().clear();
                
                recordsPane.setTop(pagetitle);
                try {
                    records.getItems().addAll(managment.rentInfo());
                } catch (IOException ex) {
                    Logger.getLogger(GarageTown.class.getName()).log(Level.SEVERE, null, ex);
                }
                recordsPane.setCenter(records);
                
                
                

                Button doneButton = new Button("Done");
                doneButton.setStyle(defualtButtonStyle);
                doneButton.setOnMouseEntered(e->doneButton.setStyle(hoveredButtonStyle));
                doneButton.setOnMouseExited(e-> doneButton.setStyle(defualtButtonStyle));
                doneButton.setCursor(Cursor.HAND);
                
                
                
                EventHandler<ActionEvent> doneFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        recordsStage.close();
                    }
                };
                doneButton.setOnAction(doneFunction);
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(doneButton);
                recordsPane.setBottom(buttonPane);
                recordsPane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      doneButton.fire();
                    }
                });
                recordsStage.setResizable(false);
                recordsStage.setTitle("Records");
                Scene recordsScene = new Scene(recordsPane, 500, 300);
                recordsStage.setScene(recordsScene);
                recordsStage.show();
            }
            
        };
        recordsButton.setOnAction(recordsEventHandler);
        
        //revenue
        Stage revenueStage = new Stage();
        EventHandler<ActionEvent> revenueEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    managment.profit();
                } catch (IOException ex) {
                    Logger.getLogger(GarageTown.class.getName()).log(Level.SEVERE, null, ex);
                }
                BorderPane revenuePane = new BorderPane();
                revenuePane.setPadding(new Insets(20));
                BackgroundImage addBackground = new BackgroundImage(new Image(new File("background3.png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                revenuePane.setBackground(new Background(addBackground));
                Text pagetitle = new Text("Revenue");
                pagetitle.setFont(Font.font("Arial Bold", 30));
                pagetitle.setFill(Color.BLACK);
                VBox finalPane = new VBox();
                finalPane.setPadding(new Insets(5));
                finalPane.setSpacing(9);
                Label carProfit = new Label("Car: ");
                Label busProfit = new Label("Bus: ");
                Label truckProfit = new Label("Truck: ");
                Label motorProfit = new Label("Motor: ");
                
                revenuePane.setTop(pagetitle);
                carProfit.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                busProfit.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                truckProfit.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                motorProfit.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                finalPane.getChildren().addAll(carProfit, busProfit, truckProfit, motorProfit);
                revenuePane.setLeft(finalPane);
                
                VBox finalPane2 = new VBox();
                finalPane2.setPadding(new Insets(5));
                finalPane2.setSpacing(9);
                Label carProfitNumber = new Label();
                Label busProfitNumber = new Label();
                Label truckProfitNumber = new Label();
                Label motorProfitNumber = new Label();
                finalPane2.getChildren().addAll(carProfitNumber, busProfitNumber, truckProfitNumber, motorProfitNumber);
                revenuePane.setRight(finalPane2);
                
                carProfitNumber.setText(Double.toString(managment.getCarProfit()));
                busProfitNumber.setText(Double.toString(managment.getBusProfit()));
                truckProfitNumber.setText(Double.toString(managment.getTruckProfit()));
                motorProfitNumber.setText(Double.toString(managment.getMotorProfit()));
                carProfitNumber.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                busProfitNumber.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                truckProfitNumber.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                motorProfitNumber.setFont(Font.font("Helvetica",FontWeight.LIGHT, 24));
                
                Button doneButton = new Button("Done");
                doneButton.setStyle(defualtButtonStyle);
                doneButton.setOnMouseEntered(e->doneButton.setStyle(hoveredButtonStyle));
                doneButton.setOnMouseExited(e-> doneButton.setStyle(defualtButtonStyle));
                doneButton.setCursor(Cursor.HAND);
                
                
                
                EventHandler<ActionEvent> doneFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        revenueStage.close();
                    }
                };
                doneButton.setOnAction(doneFunction);
                HBox buttonPane = new HBox();
                buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
                buttonPane.setSpacing(3);
                buttonPane.getChildren().addAll(doneButton);
                revenuePane.setBottom(buttonPane);
                revenuePane.setOnKeyPressed(e -> {
                    if(e.getCode()== KeyCode.ESCAPE)
                    {
                      doneButton.fire();
                    }
                });
                revenueStage.setResizable(false);
                revenueStage.setTitle("Revenue");
                Scene revenueScene = new Scene(revenuePane, 500, 300);
                revenueStage.setScene(revenueScene);
                revenueStage.show();
            }
            
        };
        revenueButton.setOnAction(revenueEventHandler);
        
        right.setRight(RightOfRight);
        return right;
    }
    
    
    public static void main(String[] args){
        Application.launch(args);
    }
    
    public void updateProductCenter(String vehicleName){
        ArrayList<TransformationVehicle> vehiclesArray = null;
        try {
            
            vehiclesArray = (ArrayList<TransformationVehicle>) managment.carsInfo();
        } catch (IOException ex) {
            System.out.println("congrass3");
        }
        
        TransformationVehicle vehicle = null ;
        
        for (int i = 0 ;  i < vehiclesArray.size(); i++ ){
          
            TransformationVehicle temp = vehiclesArray.get(i);
            if (vehicleName.equals(temp.getBrand() + " " + temp.getModelName())){
                vehicle = temp;
            }
        }
        
       
        modelNameLabel.setText(vehicle.getModelName());
        brandLabel.setText(vehicle.getBrand());
        yearLabel.setText(Integer.toString(vehicle.getYear()));
        technicalSpecsLabel.setText(vehicle.getTechnicalSpecs());
        priceLabel.setText(String.format("%.0f", vehicle.getPrice()));
        
        
    }
    
    public void updateVehicleListOfGarages(String vehicleName){
        ArrayList<Garage> allGarages = null;
        allGarages.addAll(garages);
        Garage garage;
        garage = getTheGarage(garagesList.getSelectionModel().getSelectedItem());
        try {
            vehiclesOfGarage.addAll(garage.carsInfo(garage.getGarageName()));
            vehiclesOfGarageList.getItems().addAll(garage.carsList(garage.getGarageName()));            
        } catch (IOException ex) {
            Logger.getLogger(GarageTown.class.getName()).log(Level.SEVERE, null, ex);
        }
        vehiclesOfGarageList.refresh();
        for(int i=0; i<vehiclesOfGarage.size(); i++){
                System.out.println("*************************************");
                System.out.println(vehiclesOfGarage.get(i).getBrand());
                System.out.println(".");
                System.out.println(vehiclesOfGarageList.getItems().get(i));
                System.out.println("**************************************");
            }
    }
    
    public TransformationVehicle getTheVehicle(String vehicleName){
        ArrayList<TransformationVehicle> vehiclesArray = null;
        try { 
            vehiclesArray = (ArrayList<TransformationVehicle>) managment.carsInfo();
        } catch (IOException ex) {
            System.out.println("congrass3");
        }       
        TransformationVehicle vehicle = null ;
        for (int i = 0 ;  i < vehiclesArray.size(); i++ ){
          
            TransformationVehicle temp = vehiclesArray.get(i);
            if (vehicleName.equals(temp.getBrand() + " " + temp.getModelName())){
                vehicle = temp;
            }
        }
        return vehicle;
    }
    public Garage getTheGarage(String garageName){
        Garage garage = null;
            for(int i=0; i<garages.size(); i++){
                if(garagesList.getSelectionModel().getSelectedItem().equals(garageName)){
                    garage = garages.get(i);
                }
            }
        return garage;
    }
    
    public void updateProductEdit(){
        modelFieldForEdit.setText(modelNameLabel.getText());
        brandFieldForEdit.setText(brandLabel.getText());
        yearFieldForEdit.setText(yearLabel.getText());
        technicalSpecsFieldForEdit.setText(technicalSpecsLabel.getText());
        priceFieldForEdit.setText(priceLabel.getText());
    }
    
    
}
