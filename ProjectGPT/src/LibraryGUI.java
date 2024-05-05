import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class LibraryGUI extends Application {

    private Stage primaryStage;

    private String otp;
    private String UserUsername_String;
    private String UserPassword_String;
    private String AdminUsername_String;
    private String ForgotPasswordEmailField_String;
    private String UserUsernameRegister_String;
    private String UserPasswordRegister_String;
    private String UserEmailRegister_String;
    private String UserPhoneNumberRegister_String;
    private Animation countdownTimer;
    private Timeline countdownTimer1;


    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Library System");
        Image icon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\icon.png");
        primaryStage.getIcons().add(icon);

        // ---------------------------------------------------------------------------------------------- //
        // Redirecting Window //
        Stage secondStage = new Stage();

        secondStage.setTitle("Library System");
        secondStage.getIcons().add(icon);

        StackPane root2 = new StackPane();
        Scene scene2 = new Scene(root2, 632, 352);
        Background RedirectingBackground = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\RedirectingFrame.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        root2.setBackground(RedirectingBackground);
        secondStage.setScene(scene2);
        secondStage.setResizable(false);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            secondStage.close();
            primaryStage.show();
        }));
        timeline.setCycleCount(1);

        timeline.play();

        // ---------------------------------------------------------------------------------------------- //
        // Modes Window //

        Background background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));


        Image ModesPanelIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\ModesPage.png");
        ImageView ModesPanelImage = new ImageView(ModesPanelIcon);
        StackPane ModesPane = new StackPane(ModesPanelImage);

        ImageView AdminImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\AdminIcon.png"));
        Button AdminLoginButton = new Button();
        AdminLoginButton.setPrefSize(276, 70);
        AdminLoginButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminLoginPane = new StackPane(AdminImage, AdminLoginButton);
        StackPane.setAlignment(AdminLoginButton, Pos.CENTER);

        ImageView LoginImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\LoginIcon.png"));
        Button UserLoginButton = new Button();
        UserLoginButton.setPrefSize(276, 70);
        UserLoginButton.setStyle("-fx-background-color: transparent;");
        StackPane UserLoginPane = new StackPane(LoginImage, UserLoginButton);
        StackPane.setAlignment(UserLoginButton, Pos.CENTER);

        ImageView RegisterImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\RegisterIcon.png"));
        Button RegisterLoginButton = new Button();
        RegisterLoginButton.setPrefSize(276, 70);
        RegisterLoginButton.setStyle("-fx-background-color: transparent;");
        StackPane RegisterLoginPane = new StackPane(RegisterImage, RegisterLoginButton);
        StackPane.setAlignment(RegisterLoginButton, Pos.CENTER);

        GridPane choicePane = new GridPane();
        choicePane.setHgap(10);
        choicePane.setVgap(5);
        choicePane.setAlignment(Pos.CENTER);

        choicePane.add(AdminLoginPane, 0, 0);
        choicePane.add(UserLoginPane, 0, 1);
        choicePane.add(RegisterLoginPane, 0, 2);

        HBox ModesLayout = new HBox(20);
        ModesLayout.setPadding(new Insets(20, 20, 20, 0));
        ModesLayout.setAlignment(Pos.CENTER);

        ModesLayout.getChildren().addAll(ModesPane, choicePane);
        ModesLayout.setBackground(background);


        Scene ModesScene = new Scene(ModesLayout,632, 352);

        primaryStage.setScene(ModesScene);
        primaryStage.setResizable(false);
        secondStage.show();

        // ---------------------------------------------------------------------------------------------- //
        // Admin Login Window //

        Background AdminBackground = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        Image AdminLoginPanelIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\AdminLoginPanelIcon.png");
        ImageView AdminLoginPanelImage = new ImageView();
        AdminLoginPanelImage.setImage(AdminLoginPanelIcon);

        HBox AdminUsernameField_hBox = new HBox(5);
        TextField AdminUsernameField = new TextField();
        AdminUsernameField.setPromptText("Admin Username");
        AdminUsernameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage1 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\AdminUsernameFieldIcon.png");
        ImageView iconImageView1 = new ImageView(iconImage1);
        AdminUsernameField_hBox.getChildren().addAll(iconImageView1, AdminUsernameField);
        AdminUsernameField_hBox.setAlignment(Pos.CENTER);

        HBox AdminPasswordField_hBox = new HBox(5);
        PasswordField AdminPasswordField = new PasswordField();
        AdminPasswordField.setPromptText("Password");
        AdminPasswordField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage2 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\PasswordFieldIcon.png");
        ImageView iconImageView2 = new ImageView(iconImage2);
        AdminPasswordField_hBox.getChildren().addAll(iconImageView2, AdminPasswordField);
        AdminPasswordField_hBox.setAlignment(Pos.CENTER);


        Button AdminLogin_LoginButton = new Button("Login");
        AdminLogin_LoginButton.setPrefSize(200,20);
        AdminLogin_LoginButton.setStyle("-fx-background-color: transparent; -fx-border-color: #ff0000; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #ff0000; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        Button AdminLogin_BackButton = new Button("Back");
        AdminLogin_BackButton.setPrefSize(200,20);
        AdminLogin_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");


        VBox AdminLoginForm = new VBox(10);
        AdminLoginForm.setPadding(new Insets(217));
        AdminLoginForm.setAlignment(Pos.CENTER);
        AdminLoginForm.getChildren().addAll(AdminLoginPanelImage, AdminUsernameField_hBox, AdminPasswordField_hBox, AdminLogin_LoginButton, AdminLogin_BackButton);
        AdminLoginForm.setBackground(AdminBackground);

        Scene AdminScene = new Scene(AdminLoginForm, 632, 352);


        // ---------------------------------------------------------------------------------------------- //
        // User Login Window //

        Background UserBackground = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        Image UserLoginPanelIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\LoginPanelIcon.png");
        ImageView UserLoginPanelImage = new ImageView();
        UserLoginPanelImage.setImage(UserLoginPanelIcon);


        HBox UserUsernameField_hBox = new HBox(5);
        TextField UserUsernameField = new TextField();
        UserUsernameField.setPromptText("Username");
        UserUsernameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage3 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UsernameFieldIcon.png");
        ImageView iconImageView3 = new ImageView(iconImage3);
        UserUsernameField_hBox.getChildren().addAll(iconImageView3, UserUsernameField);
        UserUsernameField_hBox.setAlignment(Pos.CENTER);

        HBox UserPasswordField_hBox = new HBox(10);
        PasswordField UserPasswordField = new PasswordField();
        UserPasswordField.setPromptText("Password");
        UserPasswordField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage4 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\PasswordFieldIcon.png");
        ImageView iconImageView4 = new ImageView(iconImage4);
        UserPasswordField_hBox.getChildren().addAll(iconImageView4, UserPasswordField);
        UserPasswordField_hBox.setAlignment(Pos.CENTER);


        Button UserLogin_ForgotPasswordButton = new Button("Forgot Password?");
        UserLogin_ForgotPasswordButton.setPrefSize(120,5);
        UserLogin_ForgotPasswordButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-family: 'Century Gothic';");

        Button UserLogin_LoginButton = new Button("Login");
        UserLogin_LoginButton.setPrefSize(200,20);
        UserLogin_LoginButton.setStyle("-fx-background-color: transparent; -fx-border-color: #7098ff; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #7098ff; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        Button UserLogin_BackButton = new Button("Back");
        UserLogin_BackButton.setPrefSize(200,20);
        UserLogin_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        VBox UserLoginForm = new VBox(10);
        UserLoginForm.setPadding(new Insets(217));
        UserLoginForm.setAlignment(Pos.CENTER);
        UserLoginForm.getChildren().addAll(UserLoginPanelImage, UserUsernameField_hBox, UserPasswordField_hBox, UserLogin_ForgotPasswordButton, UserLogin_LoginButton, UserLogin_BackButton );
        UserLoginForm.setBackground(UserBackground);


        Scene UserLoginScene = new Scene(UserLoginForm, 632, 352);


        // ---------------------------------------------------------------------------------------------- //
        // Registration Window //

        Background RegisterBackground = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        Image RegisterPanelIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\RegisterPanelIcon.png");
        ImageView RegisterPanelImage = new ImageView();
        RegisterPanelImage.setImage(RegisterPanelIcon);

        HBox RegisterUsernameField_hBox = new HBox(10);
        TextField RegisterUsernameField = new TextField();
        RegisterUsernameField.setPromptText("Username");
        RegisterUsernameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage5 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UsernameFieldIcon.png");
        ImageView iconImageView5 = new ImageView(iconImage5);
        RegisterUsernameField_hBox.getChildren().addAll(iconImageView5, RegisterUsernameField);
        RegisterUsernameField_hBox.setAlignment(Pos.CENTER);

        HBox RegisterPasswordField_hBox = new HBox(10);
        PasswordField RegisterPasswordField = new PasswordField();
        RegisterPasswordField.setPromptText("Password");
        RegisterPasswordField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage6 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\PasswordFieldIcon.png");
        ImageView iconImageView6 = new ImageView(iconImage6);
        RegisterPasswordField_hBox.getChildren().addAll(iconImageView6, RegisterPasswordField);
        RegisterPasswordField_hBox.setAlignment(Pos.CENTER);

        HBox RegisterEmailField_hBox = new HBox(10);
        TextField RegisterEmailField = new TextField();
        RegisterEmailField.setPromptText("Email");
        RegisterEmailField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage7 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\EmailFieldIcon.png");
        ImageView iconImageView7 = new ImageView(iconImage7);
        RegisterEmailField_hBox.getChildren().addAll(iconImageView7, RegisterEmailField);
        RegisterEmailField_hBox.setAlignment(Pos.CENTER);

        HBox RegisterPhoneField_hBox = new HBox(5);
        TextField CountryCodeField = new TextField("+20");
        CountryCodeField.setDisable(true);
        CountryCodeField.setPrefWidth(99);
        CountryCodeField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px; -fx-border-width: 0 0 2 0; -fx-border-color: #ffffff;");
        TextField RegisterPhoneField = new TextField();
        RegisterPhoneField.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-font-family: Century Gothic;");
        RegisterPhoneField.setPromptText("Phone Number");
        RegisterPhoneField.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-border-width: 0 0 2 0; -fx-border-color: #ffffff;");
        Image iconImage8 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\PhoneFieldIcon.png"); // Load the icon image
        ImageView iconImageView8 = new ImageView(iconImage8);
        RegisterPhoneField_hBox.getChildren().addAll(iconImageView8, CountryCodeField, RegisterPhoneField);
        RegisterPhoneField_hBox.setAlignment(Pos.CENTER);

        Button RegisterPanel_LoginButton = new Button("Register");
        Button RegisterPanel_BackButton = new Button("Back");

        RegisterPanel_LoginButton.setPrefSize(200, 20);
        RegisterPanel_LoginButton.setStyle("-fx-background-color: transparent; -fx-border-color: #00ff00; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #00ff00; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        RegisterPanel_BackButton.setPrefSize(200, 20);
        RegisterPanel_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");


        VBox RegisterForm = new VBox(10);
        RegisterForm.setPadding(new Insets(217));
        RegisterForm.setAlignment(Pos.CENTER);
        RegisterForm.getChildren().addAll(RegisterPanelImage, RegisterUsernameField_hBox, RegisterPasswordField_hBox, RegisterEmailField_hBox, RegisterPhoneField_hBox, RegisterPanel_LoginButton, RegisterPanel_BackButton );
        RegisterForm.setBackground(RegisterBackground);

        Scene RegisterScene = new Scene(RegisterForm, 632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // Forgot Password Window //

        Background ForgotPasswordBackground = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        Image ForgotPasswordPanelIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\ResetPasswordIcon.png");
        ImageView ForgotPasswordPanelImage = new ImageView();
        ForgotPasswordPanelImage.setImage(ForgotPasswordPanelIcon);

        Label ForgotPassword_EmailLabel = new Label("Enter your email address:");
        ForgotPassword_EmailLabel.setFont(Font.font("Century Gothic"));
        ForgotPassword_EmailLabel.setTextFill(Color.WHITE);
        HBox ForgotPassword_EmailLabelHBox = new HBox(5);
        ForgotPassword_EmailLabelHBox.getChildren().addAll(ForgotPassword_EmailLabel);
        ForgotPassword_EmailLabelHBox.setAlignment(Pos.CENTER_LEFT);

        HBox ForgotPasswordEmailField_hBox = new HBox(5);
        ForgotPasswordEmailField_hBox.setPrefWidth(197);
        TextField ForgotPasswordEmailField = new TextField();
        ForgotPasswordEmailField.setPromptText("Email");
        ForgotPasswordEmailField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image ForgotPasswordEmail_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\EmailFieldIcon.png");
        ImageView ForgotPasswordEmail_iconImageView = new ImageView(ForgotPasswordEmail_iconImage);
        ForgotPasswordEmailField_hBox.getChildren().addAll(ForgotPasswordEmail_iconImageView, ForgotPasswordEmailField);
        ForgotPasswordEmailField_hBox.setAlignment(Pos.CENTER);

        GridPane ForgotPasswordEmailField_GridPane = new GridPane();
        ForgotPasswordEmailField_GridPane.setVgap(10);
        ForgotPasswordEmailField_GridPane.add(ForgotPassword_EmailLabel,0,0);
        ForgotPasswordEmailField_GridPane.add(ForgotPasswordEmailField_hBox,0,1);

        Button ForgotPassword_SendOTPButton = new Button("Send OTP");
        ForgotPassword_SendOTPButton.setPrefSize(200,10);
        ForgotPassword_SendOTPButton.setStyle("-fx-background-color: transparent; -fx-border-color: #7098ff; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #7098ff; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        Button ForgotPassword_BackButton = new Button("Back");
        ForgotPassword_BackButton.setPrefSize(200,20);
        ForgotPassword_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");


        GridPane ForgotPasswordLeftSide = new GridPane();
        ForgotPasswordLeftSide.setHgap(10);
        ForgotPasswordLeftSide.setVgap(10);
        ForgotPasswordLeftSide.setAlignment(Pos.CENTER);
        ForgotPasswordLeftSide.add(ForgotPasswordEmailField_GridPane,0,0);
        ForgotPasswordLeftSide.add(ForgotPassword_SendOTPButton,0,1);
        ForgotPasswordLeftSide.add(ForgotPassword_BackButton,0,2);

        // ----------------------------------- //

        HBox ForgotPasswordOTPField_hBox = new HBox(5);
        ForgotPasswordOTPField_hBox.setMaxWidth(197);
        TextField ForgotPasswordOTPField = new TextField();
        ForgotPasswordOTPField.setPromptText("OTP");
        ForgotPasswordOTPField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        ForgotPasswordOTPField.setDisable(true);
        Image ForgotPasswordOTPField_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OTPFieldIcon.png");
        ImageView ForgotPasswordOTP_iconImageView = new ImageView(ForgotPasswordOTPField_iconImage);
        ForgotPasswordOTPField_hBox.getChildren().addAll(ForgotPasswordOTP_iconImageView, ForgotPasswordOTPField);
        ForgotPasswordOTPField_hBox.setAlignment(Pos.CENTER);

        HBox ForgotPasswordNewPasswordField_hBox = new HBox(5);
        ForgotPasswordNewPasswordField_hBox.setMaxWidth(197);
        PasswordField ForgotPasswordNewPasswordField = new PasswordField();
        ForgotPasswordNewPasswordField.setPromptText("Enter New Password");
        ForgotPasswordNewPasswordField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        ForgotPasswordNewPasswordField.setDisable(true);
        Image ForgotPasswordNewPassword_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\PasswordFieldIcon.png");
        ImageView ForgotPasswordNewPassword_iconImageView = new ImageView(ForgotPasswordNewPassword_iconImage);
        ForgotPasswordNewPasswordField_hBox.getChildren().addAll(ForgotPasswordNewPassword_iconImageView, ForgotPasswordNewPasswordField);
        ForgotPasswordNewPasswordField_hBox.setAlignment(Pos.CENTER);

        HBox ForgotPasswordRe_NewPasswordField_hBox = new HBox(5);
        ForgotPasswordRe_NewPasswordField_hBox.setMaxWidth(197);
        PasswordField ForgotPasswordRe_NewPasswordField = new PasswordField();
        ForgotPasswordRe_NewPasswordField.setPromptText("Re-Enter New Password");
        ForgotPasswordRe_NewPasswordField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        ForgotPasswordRe_NewPasswordField.setDisable(true);
        Image ForgotPasswordRe_NewPassword_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\PasswordFieldIcon.png");
        ImageView ForgotPasswordRe_NewPassword_iconImageView = new ImageView(ForgotPasswordRe_NewPassword_iconImage);
        ForgotPasswordRe_NewPasswordField_hBox.getChildren().addAll(ForgotPasswordRe_NewPassword_iconImageView, ForgotPasswordRe_NewPasswordField);
        ForgotPasswordRe_NewPasswordField_hBox.setAlignment(Pos.CENTER);

        Button ForgotPassword_SetPasswordButton = new Button("Set Password");
        ForgotPassword_SetPasswordButton.setPrefSize(200,20);
        ForgotPassword_SetPasswordButton.setStyle("-fx-background-color: transparent; -fx-border-color: #00FF00; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #00FF00; -fx-font-family: 'Century Gothic';");
        ForgotPassword_SetPasswordButton.setDisable(true);

        Button ForgotPassword_ReSendOTPButton = new Button("Re-Send OTP");
        ForgotPassword_ReSendOTPButton.setPrefSize(200,20);
        ForgotPassword_ReSendOTPButton.setStyle("-fx-background-color: transparent; -fx-border-color: #7098ff; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #7098ff; -fx-font-family: 'Century Gothic';");
        ForgotPassword_ReSendOTPButton.setDisable(true);

        GridPane ForgotPasswordRightSide = new GridPane();
        ForgotPasswordRightSide.setHgap(10);
        ForgotPasswordRightSide.setVgap(8);
        ForgotPasswordRightSide.setAlignment(Pos.CENTER);
        ForgotPasswordRightSide.add(ForgotPasswordOTPField_hBox,0,0);
        ForgotPasswordRightSide.add(ForgotPasswordNewPasswordField_hBox,0,1);
        ForgotPasswordRightSide.add(ForgotPasswordRe_NewPasswordField_hBox,0,2);
        ForgotPasswordRightSide.add(ForgotPassword_SetPasswordButton,0,3);
        ForgotPasswordRightSide.add(ForgotPassword_ReSendOTPButton,0,4);

        HBox ForgotPassword_Form = new HBox(40);
        ForgotPassword_Form.setPadding(new Insets(20, 40, 20, 40));
        ForgotPassword_Form.setAlignment(Pos.CENTER);
        ForgotPassword_Form.getChildren().addAll(ForgotPasswordLeftSide,ForgotPasswordRightSide);

        VBox ForgotPassword_Final = new VBox(5);
        ForgotPassword_Final.setAlignment(Pos.CENTER);
        ForgotPassword_Final.getChildren().addAll(ForgotPasswordPanelImage,ForgotPassword_Form);
        ForgotPassword_Final.setBackground(ForgotPasswordBackground);



        Scene ForgotPassword_Scene = new Scene(ForgotPassword_Final, 632, 352);


        // ---------------------------------------------------------------------------------------------- //
        // User Login OTP Window :

        Background LoginOTPBackground = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        Image LoginOTPPanelIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OTPLoginIcon.png");
        ImageView LoginOTPPanelImage = new ImageView();
        LoginOTPPanelImage.setImage(LoginOTPPanelIcon);

        Label LoginOTP_Label = new Label("");
        LoginOTP_Label.setFont(Font.font("Century Gothic"));
        LoginOTP_Label.setTextFill(Color.WHITE);
        HBox LoginOTP_LabelHBox = new HBox(5);
        LoginOTP_LabelHBox.getChildren().addAll(LoginOTP_Label);
        LoginOTP_LabelHBox.setAlignment(Pos.CENTER);

        HBox LoginOTPEmailField_hBox = new HBox(5);
        LoginOTPEmailField_hBox.setPrefWidth(197);
        TextField LoginOTPField = new TextField();
        LoginOTPField.setPromptText("OTP");
        LoginOTPField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image LoginOTP_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OTPFieldIcon.png");
        ImageView LoginOTP_iconImageView = new ImageView(LoginOTP_iconImage);
        LoginOTPEmailField_hBox.getChildren().addAll(LoginOTP_iconImageView, LoginOTPField);
        LoginOTPEmailField_hBox.setAlignment(Pos.CENTER);

        GridPane LoginOTPField_GridPane = new GridPane();
        LoginOTPField_GridPane.setVgap(10);
        LoginOTPField_GridPane.add(LoginOTP_LabelHBox,0,0);
        LoginOTPField_GridPane.add(LoginOTPEmailField_hBox,0,1);

        Button LoginOTP_LoginOTPButton = new Button("Login");
        LoginOTP_LoginOTPButton.setPrefSize(200,10);
        LoginOTP_LoginOTPButton.setStyle("-fx-background-color: transparent; -fx-border-color: #7098ff; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #7098ff; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");


        Button LoginOTP_ReSendOTPButton = new Button("Re-Send OTP");
        LoginOTP_ReSendOTPButton.setPrefSize(200,10);
        LoginOTP_ReSendOTPButton.setStyle("-fx-background-color: transparent; -fx-border-color: #70ffd6; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #70ffd6; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        Button LoginOTP_BackButton = new Button("Back");
        LoginOTP_BackButton.setPrefSize(200,20);
        LoginOTP_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");


        GridPane LoginOTP_GridPane = new GridPane();
        LoginOTP_GridPane.setHgap(10);
        LoginOTP_GridPane.setVgap(10);
        LoginOTP_GridPane.setAlignment(Pos.CENTER);
        LoginOTP_GridPane.add(LoginOTPField_GridPane,0,0);
        LoginOTP_GridPane.add(LoginOTP_LoginOTPButton,0,1);
        LoginOTP_GridPane.add(LoginOTP_ReSendOTPButton,0,2);
        LoginOTP_GridPane.add(LoginOTP_BackButton,0,3);


        VBox LoginOTP_Final = new VBox(10);
        LoginOTP_Final.setAlignment(Pos.CENTER);
        LoginOTP_Final.getChildren().addAll(LoginOTPPanelImage,LoginOTP_GridPane);
        LoginOTP_Final.setBackground(LoginOTPBackground);

        Scene LoginOTP_Scene = new Scene(LoginOTP_Final, 632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // User Register OTP Window :

        Background RegisterOTPBackground = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        Image RegisterOTPPanelIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OTPLoginIcon.png");
        ImageView RegisterOTPPanelImage = new ImageView();
        RegisterOTPPanelImage.setImage(RegisterOTPPanelIcon);

        Label RegisterOTP_Label = new Label("");
        RegisterOTP_Label.setFont(Font.font("Century Gothic"));
        RegisterOTP_Label.setTextFill(Color.WHITE);
        HBox RegisterOTP_LabelHBox = new HBox(5);
        RegisterOTP_LabelHBox.getChildren().addAll(RegisterOTP_Label);
        RegisterOTP_LabelHBox.setAlignment(Pos.CENTER);

        HBox RegisterOTPEmailField_hBox = new HBox(5);
        RegisterOTPEmailField_hBox.setPrefWidth(197);
        TextField RegisterOTPField = new TextField();
        RegisterOTPField.setPromptText("OTP");
        RegisterOTPField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image RegisterOTP_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OTPFieldIcon.png");
        ImageView RegisterOTP_iconImageView = new ImageView(RegisterOTP_iconImage);
        RegisterOTPEmailField_hBox.getChildren().addAll(RegisterOTP_iconImageView, RegisterOTPField);
        RegisterOTPEmailField_hBox.setAlignment(Pos.CENTER);

        GridPane RegisterOTPField_GridPane = new GridPane();
        RegisterOTPField_GridPane.setVgap(10);
        RegisterOTPField_GridPane.add(RegisterOTP_LabelHBox,0,0);
        RegisterOTPField_GridPane.add(RegisterOTPEmailField_hBox,0,1);

        Button RegisterOTP_RegisterOTPButton = new Button("Register");
        RegisterOTP_RegisterOTPButton.setPrefSize(200,10);
        RegisterOTP_RegisterOTPButton.setStyle("-fx-background-color: transparent; -fx-border-color: #00FF00; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #00FF00; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        Button RegisterOTP_ReSendOTPButton = new Button("Re-Send OTP");
        RegisterOTP_ReSendOTPButton.setPrefSize(200,10);
        RegisterOTP_ReSendOTPButton.setStyle("-fx-background-color: transparent; -fx-border-color: #70ffd6; -fx-border-width: 1.5px; -fx-border-radius: 5px; -fx-text-fill: #70ffd6; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        Button RegisterOTP_EmptyButton = new Button("");
        RegisterOTP_EmptyButton.setPrefSize(200,20);
        RegisterOTP_EmptyButton.setStyle("-fx-background-color: transparent; -fx-border-width: 0px;");


        GridPane RegisterOTP_GridPane = new GridPane();
        RegisterOTP_GridPane.setHgap(10);
        RegisterOTP_GridPane.setVgap(10);
        RegisterOTP_GridPane.setAlignment(Pos.CENTER);
        RegisterOTP_GridPane.add(RegisterOTPField_GridPane,0,0);
        RegisterOTP_GridPane.add(RegisterOTP_RegisterOTPButton,0,1);
        RegisterOTP_GridPane.add(RegisterOTP_ReSendOTPButton,0,2);
        RegisterOTP_GridPane.add(RegisterOTP_EmptyButton,0,3);


        VBox RegisterOTP_Final = new VBox(10);
        RegisterOTP_Final.setAlignment(Pos.CENTER);
        RegisterOTP_Final.getChildren().addAll(RegisterOTPPanelImage,RegisterOTP_GridPane);
        RegisterOTP_Final.setBackground(RegisterOTPBackground);

        Scene RegisterOTP_Scene = new Scene(RegisterOTP_Final, 632, 352);



        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard Window :

        Background AdminDashboard_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        ImageView AdminDashboard_Banner = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\AdminPanelBanner.png"));

        ImageView AdminDashboard_ShowBooksImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\ShowBooksButton.png"));
        Button AdminDashboard_ShowBooksButton = new Button();
        AdminDashboard_ShowBooksButton.setPrefSize(186, 50);
        AdminDashboard_ShowBooksButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminDashboard_ShowBooksPane = new StackPane(AdminDashboard_ShowBooksImage, AdminDashboard_ShowBooksButton);
        StackPane.setAlignment(AdminDashboard_ShowBooksButton, Pos.CENTER);


        ImageView AdminDashboard_AddBooksImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\AddBooksButton.png"));
        Button AdminDashboard_AddBooksButton = new Button();
        AdminDashboard_AddBooksButton.setPrefSize(186, 50);
        AdminDashboard_AddBooksButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminDashboard_AddBooksPane = new StackPane(AdminDashboard_AddBooksImage, AdminDashboard_AddBooksButton);
        StackPane.setAlignment(AdminDashboard_AddBooksButton, Pos.CENTER);

        ImageView AdminDashboard_RemoveBookImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\RemoveBookButton.png"));
        Button AdminDashboard_RemoveBookButton = new Button();
        AdminDashboard_RemoveBookButton.setPrefSize(186, 50);
        AdminDashboard_RemoveBookButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminDashboard_RemoveBookPane = new StackPane(AdminDashboard_RemoveBookImage, AdminDashboard_RemoveBookButton);
        StackPane.setAlignment(AdminDashboard_RemoveBookButton, Pos.CENTER);

        ImageView AdminDashboard_BorrowedBooksImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\BorrowedBooksButton.png"));
        Button AdminDashboard_BorrowedBooksButton = new Button();
        AdminDashboard_BorrowedBooksButton.setPrefSize(186, 50);
        AdminDashboard_BorrowedBooksButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminDashboard_BorrowedBooksPane = new StackPane(AdminDashboard_BorrowedBooksImage, AdminDashboard_BorrowedBooksButton);
        StackPane.setAlignment(AdminDashboard_BorrowedBooksButton, Pos.CENTER);


        final ImageView AdminDashboard_CloseOpenBorrowingPeriodImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\Close-OpenBorrowingPeriodButton.png"));
        ToggleButton AdminDashboard_CloseOpenBorrowingPeriodButton = new ToggleButton();
        AdminDashboard_CloseOpenBorrowingPeriodButton.setPrefSize(186, 50);
        AdminDashboard_CloseOpenBorrowingPeriodButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminDashboard_CloseOpenBorrowingPeriodButtonPane = new StackPane(AdminDashboard_CloseOpenBorrowingPeriodImage, AdminDashboard_CloseOpenBorrowingPeriodButton);
        StackPane.setAlignment(AdminDashboard_CloseOpenBorrowingPeriodButton, Pos.CENTER);

        ImageView AdminDashboard_ViewAccountsDetailsImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\ViewAccountsDetailsButton.png"));
        Button AdminDashboard_ViewAccountsDetailsButton = new Button();
        AdminDashboard_ViewAccountsDetailsButton.setPrefSize(186, 50);
        AdminDashboard_ViewAccountsDetailsButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminDashboard_ViewAccountsDetailsPane = new StackPane(AdminDashboard_ViewAccountsDetailsImage, AdminDashboard_ViewAccountsDetailsButton);
        StackPane.setAlignment(AdminDashboard_ViewAccountsDetailsButton, Pos.CENTER);

        ImageView AdminDashboard_LogoutImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\LogoutButton.png"));
        Button AdminDashboard_LogoutButton = new Button();
        AdminDashboard_LogoutButton.setPrefSize(186, 50);
        AdminDashboard_LogoutButton.setStyle("-fx-background-color: transparent;");
        StackPane AdminDashboard_LogoutPane = new StackPane(AdminDashboard_LogoutImage, AdminDashboard_LogoutButton);
        StackPane.setAlignment(AdminDashboard_LogoutButton, Pos.CENTER);


        GridPane AdminPanelPane = new GridPane();
        AdminPanelPane.setHgap(10);
        AdminPanelPane.setVgap(5);
        AdminPanelPane.setAlignment(Pos.CENTER);

        AdminPanelPane.add(AdminDashboard_ShowBooksPane, 0, 0);
        AdminPanelPane.add(AdminDashboard_ViewAccountsDetailsPane, 1, 0);
        AdminPanelPane.add(AdminDashboard_AddBooksPane, 0, 1);
        AdminPanelPane.add(AdminDashboard_RemoveBookPane, 1, 1);
        AdminPanelPane.add(AdminDashboard_BorrowedBooksPane, 0, 2);
        AdminPanelPane.add(AdminDashboard_CloseOpenBorrowingPeriodButtonPane, 1, 2);


        VBox AdminDashboard_VBox = new VBox(10);
        AdminDashboard_VBox.setPadding(new Insets(217));
        AdminDashboard_VBox.setAlignment(Pos.CENTER);
        AdminDashboard_VBox.getChildren().addAll(AdminDashboard_Banner,AdminPanelPane,AdminDashboard_LogoutPane);
        AdminDashboard_VBox.setBackground(AdminDashboard_Background);


        Scene AdminDashboard = new Scene(AdminDashboard_VBox, 632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // Admin - Show Books Window :

        Background AdminShowBooks_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        TableView<Book> adminShowBooksTable = new TableView<>();
        TableColumn<Book, Integer> bookIDColumn = new TableColumn<>("ID");
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        TableColumn<Book, String> bookNameColumn = new TableColumn<>("Book Name");
        TableColumn<Book, String> authorNameColumn = new TableColumn<>("Author Name");
        TableColumn<Book, String> publisherColumn = new TableColumn<>("Publisher");

        bookIDColumn.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().IdProperty().getValue(),
                    cellData.getValue().IdProperty()));
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        bookNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        authorNameColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());


        bookIDColumn.setMinWidth(78);
        categoryColumn.setMinWidth(128);
        bookNameColumn.setMinWidth(128);
        authorNameColumn.setMinWidth(128);
        publisherColumn.setMinWidth(128);

        adminShowBooksTable.getColumns().addAll(
            bookIDColumn,
            categoryColumn,
            bookNameColumn,
            authorNameColumn,
            publisherColumn);

        Button AdminShowBooks_BackButton = new Button("Back");
        AdminShowBooks_BackButton.setPrefSize(200, 20);
        AdminShowBooks_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        VBox AdminShowBooks_VBox = new VBox(10);
        AdminShowBooks_VBox.setPadding(new Insets(20, 20, 20, 20));
        AdminShowBooks_VBox.setAlignment(Pos.CENTER);
        AdminShowBooks_VBox.getChildren().addAll(adminShowBooksTable, AdminShowBooks_BackButton);
        AdminShowBooks_VBox.setBackground(AdminShowBooks_Background);

        Scene AdminShowBooks = new Scene(AdminShowBooks_VBox, 632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // Admin -  View Accounts Window :

        Background AdminViewAccounts_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        TableView<Account> AdminViewAccounts_Table = new TableView<>();

        TableColumn<Account, Integer> AdminViewAccounts_AccountIDColumn = new TableColumn<>("UserID");
        TableColumn<Account, String> AdminViewAccounts_UsernameColumn = new TableColumn<>("Username");
        TableColumn<Account, String> AdminViewAccounts_EmailColumn = new TableColumn<>("Email");
        TableColumn<Account, String> AdminViewAccounts_PhoneColumn = new TableColumn<>("Phone Number");
        TableColumn<Account, Integer> AdminViewAccounts_TotalBorrowedBooksColumn = new TableColumn<>("Borrowed Books");

        AdminViewAccounts_AccountIDColumn.setCellValueFactory(cellData ->
            Bindings.createObjectBinding(() -> Integer.parseInt(cellData.getValue().idProperty().get()),
            cellData.getValue().idProperty())
        );

        AdminViewAccounts_UsernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        AdminViewAccounts_EmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        AdminViewAccounts_PhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        AdminViewAccounts_TotalBorrowedBooksColumn.setCellValueFactory(cellData ->
            Bindings.createObjectBinding(() -> Account.getUserBooksCount(cellData.getValue().getUsername()),
            cellData.getValue().usernameProperty())
        );

        AdminViewAccounts_AccountIDColumn.setMinWidth(78);
        AdminViewAccounts_UsernameColumn.setMinWidth(128);
        AdminViewAccounts_EmailColumn.setMinWidth(128);
        AdminViewAccounts_PhoneColumn.setMinWidth(128);
        AdminViewAccounts_TotalBorrowedBooksColumn.setMinWidth(128);

        AdminViewAccounts_Table.getColumns().addAll(
            AdminViewAccounts_AccountIDColumn,
            AdminViewAccounts_UsernameColumn,
            AdminViewAccounts_EmailColumn,
            AdminViewAccounts_PhoneColumn,
            AdminViewAccounts_TotalBorrowedBooksColumn
            );

        Button AdminViewAccounts_BackButton = new Button("Back");
        AdminViewAccounts_BackButton.setPrefSize(200, 20);
        AdminViewAccounts_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        VBox AdminViewAccounts_VBox = new VBox(10);
        AdminViewAccounts_VBox.setPadding(new Insets(20, 20, 20, 20));
        AdminViewAccounts_VBox.setAlignment(Pos.CENTER);
        AdminViewAccounts_VBox.getChildren().addAll(AdminViewAccounts_Table, AdminViewAccounts_BackButton);
        AdminViewAccounts_VBox.setBackground(AdminViewAccounts_Background);

        Scene AdminViewAccounts_Scene = new Scene(AdminViewAccounts_VBox, 632, 352);


        // ---------------------------------------------------------------------------------------------- //
        // Admin - Add Book Window //

        Image AddBookIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\AddBookIcon.png");
        ImageView AddBookImage = new ImageView(AddBookIcon);
        StackPane AddBookStackPane = new StackPane(AddBookImage);

        Label BookNameField_Label = new Label("Book Name:");
        BookNameField_Label.setFont(Font.font("Century Gothic"));
        BookNameField_Label.setTextFill(Color.WHITE);
        TextField BookNameField = new TextField();
        BookNameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BookNameField.setPrefWidth(276);
        GridPane BookNameFieldPane = new GridPane();
        BookNameFieldPane.add(BookNameField_Label,0,0);
        BookNameFieldPane.add(BookNameField,0,1);

        Label BookCategoryField_Label = new Label("Category:");
        BookCategoryField_Label.setFont(Font.font("Century Gothic"));
        BookCategoryField_Label.setTextFill(Color.WHITE);
        TextField BookCategoryField = new TextField();
        BookCategoryField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BookCategoryField.setPrefWidth(276);
        GridPane BookCategoryFieldPane = new GridPane();
        BookCategoryFieldPane.add(BookCategoryField_Label,0,0);
        BookCategoryFieldPane.add(BookCategoryField,0,1);

        Label BookAuthorField_Label = new Label("Author:");
        BookAuthorField_Label.setFont(Font.font("Century Gothic"));
        BookAuthorField_Label.setTextFill(Color.WHITE);
        TextField BookAuthorField = new TextField();
        BookAuthorField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BookAuthorField.setPrefWidth(276);
        GridPane BookAuthorFieldPane = new GridPane();
        BookAuthorFieldPane.add(BookAuthorField_Label,0,0);
        BookAuthorFieldPane.add(BookAuthorField,0,1);

        Label BookPublisherField_Label = new Label("Publisher:");
        BookPublisherField_Label.setFont(Font.font("Century Gothic"));
        BookPublisherField_Label.setTextFill(Color.WHITE);
        TextField BookPublisherField = new TextField();
        BookPublisherField.setEditable(false);
        BookPublisherField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BookPublisherField.setPrefWidth(276);
        GridPane BookPublisherFieldPane = new GridPane();
        BookPublisherFieldPane.add(BookPublisherField_Label,0,0);
        BookPublisherFieldPane.add(BookPublisherField,0,1);

        Button AddBook_BackButton = new Button("Back");
        AddBook_BackButton.setPrefSize(138,20);
        AddBook_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        Button AddBook_AddButton = new Button("Add");
        AddBook_AddButton.setPrefSize(138,20);
        AddBook_AddButton.setStyle("-fx-background-color: transparent; -fx-border-color: #00FF00; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #00FF00; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        GridPane AddBookButtonsPane = new GridPane();
        AddBookButtonsPane.add(AddBook_BackButton,0,0);
        AddBookButtonsPane.add(AddBook_AddButton,1,0);

        GridPane AddingBookGridPane = new GridPane();
        AddingBookGridPane.setHgap(10);
        AddingBookGridPane.setVgap(15);
        AddingBookGridPane.setAlignment(Pos.CENTER);
        AddingBookGridPane.add(BookNameFieldPane, 0, 0);
        AddingBookGridPane.add(BookCategoryFieldPane, 0, 1);
        AddingBookGridPane.add(BookAuthorFieldPane, 0, 2);
        AddingBookGridPane.add(BookPublisherFieldPane, 0, 3);
        AddingBookGridPane.add(AddBookButtonsPane, 0, 4);

        HBox AddingBookLayout = new HBox(20);
        AddingBookLayout.setPadding(new Insets(20, 20, 20, 0));
        AddingBookLayout.setAlignment(Pos.CENTER);

        AddingBookLayout.getChildren().addAll(AddBookStackPane, AddingBookGridPane);
        AddingBookLayout.setBackground(background);


        Scene AddingBookScene = new Scene(AddingBookLayout,632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // Admin - Remove Book //

        Image RemoveBookIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\RemoveBookIcon.png");
        ImageView RemoveBookImage = new ImageView(RemoveBookIcon);
        StackPane RemoveBookStackPane = new StackPane(RemoveBookImage);

        Label BookNameField_RemoveLabel = new Label("Book Name:");
        BookNameField_RemoveLabel.setFont(Font.font("Century Gothic"));
        BookNameField_RemoveLabel.setTextFill(Color.WHITE);
        TextField BookNameField_Remove = new TextField();
        BookNameField_Remove.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BookNameField_Remove.setPrefWidth(276);
        GridPane BookNameFieldPane_Remove = new GridPane();
        BookNameFieldPane_Remove.add(BookNameField_RemoveLabel,0,0);
        BookNameFieldPane_Remove.add(BookNameField_Remove,0,1);

        Label BookIDField_Label = new Label("Book ID:");
        BookIDField_Label.setFont(Font.font("Century Gothic"));
        BookIDField_Label.setTextFill(Color.WHITE);
        TextField BookIDField = new TextField();
        BookIDField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BookIDField.setPrefWidth(276);
        GridPane BookIDFieldPane = new GridPane();
        BookIDFieldPane.add(BookIDField_Label,0,0);
        BookIDFieldPane.add(BookIDField,0,1);

        Button RemoveBook_BackButton = new Button("Back");
        RemoveBook_BackButton.setPrefSize(138,20);
        RemoveBook_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        Button RemoveBook_RemoveButton = new Button("Remove");
        RemoveBook_RemoveButton.setPrefSize(138,20);
        RemoveBook_RemoveButton.setStyle("-fx-background-color: transparent; -fx-border-color: #FF0000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #FF0000; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        GridPane RemoveBookButtonsPane = new GridPane();
        RemoveBookButtonsPane.add(RemoveBook_BackButton,0,0);
        RemoveBookButtonsPane.add(RemoveBook_RemoveButton,1,0);

        GridPane RemovingBookGridPane = new GridPane();
        RemovingBookGridPane.setHgap(10);
        RemovingBookGridPane.setVgap(15);
        RemovingBookGridPane.setAlignment(Pos.CENTER);
        RemovingBookGridPane.add(BookIDFieldPane, 0, 0);
        RemovingBookGridPane.add(BookNameFieldPane_Remove, 0, 1);
        RemovingBookGridPane.add(RemoveBookButtonsPane, 0, 2);

        HBox RemoveBookLayout = new HBox(20);
        RemoveBookLayout.setPadding(new Insets(20, 20, 20, 0));
        RemoveBookLayout.setAlignment(Pos.CENTER);

        RemoveBookLayout.getChildren().addAll(RemoveBookStackPane, RemovingBookGridPane);
        RemoveBookLayout.setBackground(background);


        Scene RemovingBookScene = new Scene(RemoveBookLayout,632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // Admin - Show Borrowed Books Window //

        Background AdminShowBorrowedBooks_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));


        TableView<BorrowedBook> AdminShowBorrowedBooks_Table = new TableView<>();

        TableColumn<BorrowedBook, Integer> AdminShowBorrowedBooks_UserIDColumn = new TableColumn<>("User ID");
        TableColumn<BorrowedBook, String> AdminShowBorrowedBooks_UserNameColumn = new TableColumn<>("User Name");
        TableColumn<BorrowedBook, Integer> AdminShowBorrowedBooks_BookIDColumn = new TableColumn<>("Book ID");
        TableColumn<BorrowedBook, String> AdminShowBorrowedBooks_BookNameColumn = new TableColumn<>("Book Name");
        TableColumn<BorrowedBook, Integer> AdminShowBorrowedBooks_TicketNoColumn = new TableColumn<>("Ticket No.");

        AdminShowBorrowedBooks_UserIDColumn.setCellValueFactory(cellData ->
            Bindings.createObjectBinding(() -> cellData.getValue().userIdProperty().getValue(),
                cellData.getValue().userIdProperty()));

        AdminShowBorrowedBooks_UserNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());

        AdminShowBorrowedBooks_BookIDColumn.setCellValueFactory(cellData ->
            Bindings.createObjectBinding(() -> cellData.getValue().bookIdProperty().getValue(),
                cellData.getValue().bookIdProperty()));

        AdminShowBorrowedBooks_BookNameColumn.setCellValueFactory(cellData -> cellData.getValue().bookNameProperty());

        AdminShowBorrowedBooks_TicketNoColumn.setCellValueFactory(cellData ->
            Bindings.createObjectBinding(() -> cellData.getValue().TicketNoProperty().getValue(),
                cellData.getValue().TicketNoProperty()));


        AdminShowBorrowedBooks_UserIDColumn.setMinWidth(78);
        AdminShowBorrowedBooks_UserNameColumn.setMinWidth(144.6666666666667);
        AdminShowBorrowedBooks_BookIDColumn.setMinWidth(78);
        AdminShowBorrowedBooks_BookNameColumn.setMinWidth(144.6666666666667);
        AdminShowBorrowedBooks_TicketNoColumn.setMinWidth(144.6666666666667);

        AdminShowBorrowedBooks_Table.getColumns().addAll(
            AdminShowBorrowedBooks_UserIDColumn,
            AdminShowBorrowedBooks_UserNameColumn,
            AdminShowBorrowedBooks_BookIDColumn,
            AdminShowBorrowedBooks_BookNameColumn,
            AdminShowBorrowedBooks_TicketNoColumn
            );

        Button AdminShowBorrowedBooks_BackButton = new Button("Back");
        AdminShowBorrowedBooks_BackButton.setPrefSize(200, 20);
        AdminShowBorrowedBooks_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        VBox AdminShowBorrowedBooks_VBox = new VBox(10);
        AdminShowBorrowedBooks_VBox.setPadding(new Insets(20, 20, 20, 20));
        AdminShowBorrowedBooks_VBox.setAlignment(Pos.CENTER);
        AdminShowBorrowedBooks_VBox.getChildren().addAll(AdminShowBorrowedBooks_Table, AdminShowBorrowedBooks_BackButton);
        AdminShowBorrowedBooks_VBox.setBackground(AdminShowBorrowedBooks_Background);

        Scene AdminShowBorrowedBooks_Scene = new Scene(AdminShowBorrowedBooks_VBox, 632, 352);

        

        // ---------------------------------------------------------------------------------------------- //
        // User Dashboard //

        Background UserPanel_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        ImageView UserPanel_Banner = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\UserPanelBanner.png"));

        final ImageView UserPanel_BorrowBookImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\BorrowBook.png"));
        Button UserPanel_BorrowBookButton = new Button();
        UserPanel_BorrowBookButton.setPrefSize(186, 50);
        UserPanel_BorrowBookButton.setStyle("-fx-background-color: transparent;");
        StackPane UserPanel_BorrowBookPane = new StackPane(UserPanel_BorrowBookImage, UserPanel_BorrowBookButton);
        StackPane.setAlignment(UserPanel_BorrowBookButton, Pos.CENTER);

        ImageView UserPanel_ReturnBookImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\ReturnBook.png"));
        Button UserPanel_ReturnBookButton = new Button();
        UserPanel_ReturnBookButton.setPrefSize(186, 50);
        UserPanel_ReturnBookButton.setStyle("-fx-background-color: transparent;");
        StackPane UserPanel_ReturnBookPane = new StackPane(UserPanel_ReturnBookImage, UserPanel_ReturnBookButton);
        StackPane.setAlignment(UserPanel_ReturnBookButton, Pos.CENTER);

        ImageView UserPanel_ShowMyBooksImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\ShowMyBooks.png"));
        Button UserPanel_ShowMyBooksButton = new Button();
        UserPanel_ShowMyBooksButton.setPrefSize(186, 50);
        UserPanel_ShowMyBooksButton.setStyle("-fx-background-color: transparent;");
        StackPane UserPanel_ShowMyBooksPane = new StackPane(UserPanel_ShowMyBooksImage, UserPanel_ShowMyBooksButton);
        StackPane.setAlignment(UserPanel_ShowMyBooksButton, Pos.CENTER);

        ImageView UserPanel_infoImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\UserPanel.png"));
        Button UserPanel_infoButton = new Button();
        UserPanel_infoButton.setPrefSize(186, 50);
        UserPanel_infoButton.setStyle("-fx-background-color: transparent;");
        StackPane UserPanel_infoPane = new StackPane(UserPanel_infoImage, UserPanel_infoButton);
        StackPane.setAlignment(UserPanel_infoButton, Pos.CENTER);

        ImageView UserPanel_LogoutImage = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\UserLogout.png"));
        Button UserPanel_LogoutButton = new Button();
        UserPanel_LogoutButton.setPrefSize(186, 50);
        UserPanel_LogoutButton.setStyle("-fx-background-color: transparent;");
        StackPane UserPanel_LogoutPane = new StackPane(UserPanel_LogoutImage, UserPanel_LogoutButton);
        StackPane.setAlignment(UserPanel_LogoutButton, Pos.CENTER);


        GridPane UserPanelPane = new GridPane();
        UserPanelPane.setHgap(10);
        UserPanelPane.setVgap(5);
        UserPanelPane.setAlignment(Pos.CENTER);

        UserPanelPane.add(UserPanel_BorrowBookPane, 0, 0);
        UserPanelPane.add(UserPanel_ReturnBookPane, 1, 0);
        UserPanelPane.add(UserPanel_ShowMyBooksPane, 0, 1);
        UserPanelPane.add(UserPanel_infoPane, 1, 1);


        VBox UserPanel_VBox = new VBox(10);
        UserPanel_VBox.setPadding(new Insets(217));
        UserPanel_VBox.setAlignment(Pos.CENTER);
        UserPanel_VBox.getChildren().addAll(UserPanel_Banner,UserPanelPane,UserPanel_LogoutPane);
        UserPanel_VBox.setBackground(UserPanel_Background);


        Scene UserPanelScene = new Scene(UserPanel_VBox, 632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // User - Borrow Book Window //

        Image BorrowBookIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\BorrowBookIcon.png");
        ImageView BorrowBookImage = new ImageView(BorrowBookIcon);
        StackPane BorrowBookStackPane = new StackPane(BorrowBookImage);

        Label BorrowBookIDField_Label = new Label("Book ID:");
        BorrowBookIDField_Label.setFont(Font.font("Century Gothic"));
        BorrowBookIDField_Label.setTextFill(Color.WHITE);
        TextField BorrowBookIDField = new TextField();
        BorrowBookIDField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BorrowBookIDField.setPrefWidth(276);
        GridPane BorrowBookIDFieldPane = new GridPane();
        BorrowBookIDFieldPane.add(BorrowBookIDField_Label,0,0);
        BorrowBookIDFieldPane.add(BorrowBookIDField,0,1);

        Label BorrowBookNameField_Label = new Label("Book Name:");
        BorrowBookNameField_Label.setFont(Font.font("Century Gothic"));
        BorrowBookNameField_Label.setTextFill(Color.WHITE);
        TextField BorrowBookNameField = new TextField();
        BorrowBookNameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        BorrowBookNameField.setPrefWidth(276);
        GridPane BorrowBookNameFieldPane = new GridPane();
        BorrowBookNameFieldPane.add(BorrowBookNameField_Label,0,0);
        BorrowBookNameFieldPane.add(BorrowBookNameField,0,1);

        Label BorrowBookReturnDateField_Label = new Label("Return Date:");
        BorrowBookReturnDateField_Label.setFont(Font.font("Century Gothic"));
        BorrowBookReturnDateField_Label.setTextFill(Color.WHITE);
        DatePicker BorrowBookReturnDateField = new DatePicker();
        BorrowBookReturnDateField.setEditable(false);
        BorrowBookReturnDateField.getStyleClass().add("date-picker");
        BorrowBookReturnDateField.setPrefWidth(276);
        GridPane BorrowBookReturnDateFieldPane = new GridPane();
        BorrowBookReturnDateFieldPane.add(BorrowBookReturnDateField_Label,0,0);
        BorrowBookReturnDateFieldPane.add(BorrowBookReturnDateField,0,1);

        Button BorrowBook_BackButton = new Button("Back");
        BorrowBook_BackButton.setPrefSize(138,20);
        BorrowBook_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        Button BorrowBook_BorrowButton = new Button("Borrow");
        BorrowBook_BorrowButton.setPrefSize(138,20);
        BorrowBook_BorrowButton.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFF00; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #FFFF00; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        Button BorrowBook_BrowseButton = new Button("Browse Available Books");
        BorrowBook_BrowseButton.setPrefSize(276,20);
        BorrowBook_BrowseButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        GridPane BorrowBookButtonsPane = new GridPane();
        BorrowBookButtonsPane.add(BorrowBook_BackButton,0,0);
        BorrowBookButtonsPane.add(BorrowBook_BorrowButton,1,0);

        VBox BorrowBookButtonsVbox = new VBox(5);
        BorrowBookButtonsVbox.setPadding(new Insets(0,0,0,0));
        BorrowBookButtonsVbox.setAlignment(Pos.CENTER);
        BorrowBookButtonsVbox.getChildren().addAll(BorrowBook_BrowseButton, BorrowBookButtonsPane);


        GridPane BorrowingBookGridPane = new GridPane();
        BorrowingBookGridPane.setHgap(10);
        BorrowingBookGridPane.setVgap(15);
        BorrowingBookGridPane.setAlignment(Pos.CENTER);
        BorrowingBookGridPane.add(BorrowBookIDFieldPane, 0, 0);
        BorrowingBookGridPane.add(BorrowBookNameFieldPane, 0, 1);
        BorrowingBookGridPane.add(BorrowBookReturnDateFieldPane, 0, 2);
        BorrowingBookGridPane.add(BorrowBookButtonsVbox, 0, 3);


        HBox BorrowingBookLayout = new HBox(20);
        BorrowingBookLayout.setPadding(new Insets(20, 20, 20, 0));
        BorrowingBookLayout.setAlignment(Pos.CENTER);

        BorrowingBookLayout.getChildren().addAll(BorrowBookStackPane, BorrowingBookGridPane);
        BorrowingBookLayout.setBackground(background);


        Scene BorrowingBookScene = new Scene(BorrowingBookLayout,632, 352);
        BorrowingBookScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());


        // ---------------------------------------------------------------------------------------------- //
        // User - Return Book Window //

        Image ReturnBookIcon = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\ReturnBookIcon.png");
        ImageView ReturnBookImage = new ImageView(ReturnBookIcon);
        StackPane ReturnBookStackPane = new StackPane(ReturnBookImage);

        Label ReturnBookIDField_Label = new Label("Book ID:");
        ReturnBookIDField_Label.setFont(Font.font("Century Gothic"));
        ReturnBookIDField_Label.setTextFill(Color.WHITE);
        TextField ReturnBookIDField = new TextField();
        ReturnBookIDField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        ReturnBookIDField.setPrefWidth(276);
        GridPane ReturnBookIDFieldPane = new GridPane();
        ReturnBookIDFieldPane.add(ReturnBookIDField_Label,0,0);
        ReturnBookIDFieldPane.add(ReturnBookIDField,0,1);

        Label ReturnBookNameField_Label = new Label("Book Name:");
        ReturnBookNameField_Label.setFont(Font.font("Century Gothic"));
        ReturnBookNameField_Label.setTextFill(Color.WHITE);
        TextField ReturnBookNameField = new TextField();
        ReturnBookNameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        ReturnBookNameField.setPrefWidth(276);
        GridPane ReturnBookNameFieldPane = new GridPane();
        ReturnBookNameFieldPane.add(ReturnBookNameField_Label,0,0);
        ReturnBookNameFieldPane.add(ReturnBookNameField,0,1);

        Button ReturnBook_BackButton = new Button("Back");
        ReturnBook_BackButton.setPrefSize(138,20);
        ReturnBook_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        Button ReturnBook_ReturnButton = new Button("Return");
        ReturnBook_ReturnButton.setPrefSize(138,20);
        ReturnBook_ReturnButton.setStyle("-fx-background-color: transparent; -fx-border-color: #67fdd4; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #67fdd4; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold;");

        GridPane ReturnBookButtonsPane = new GridPane();
        ReturnBookButtonsPane.add(ReturnBook_BackButton,0,0);
        ReturnBookButtonsPane.add(ReturnBook_ReturnButton,1,0);

        GridPane ReturningBookGridPane = new GridPane();
        ReturningBookGridPane.setHgap(10);
        ReturningBookGridPane.setVgap(15);
        ReturningBookGridPane.setAlignment(Pos.CENTER);
        ReturningBookGridPane.add(ReturnBookIDFieldPane, 0, 0);
        ReturningBookGridPane.add(ReturnBookNameFieldPane, 0, 1);
        ReturningBookGridPane.add(ReturnBookButtonsPane, 0, 2);

        HBox ReturningBookLayout = new HBox(20);
        ReturningBookLayout.setPadding(new Insets(20, 20, 20, 0));
        ReturningBookLayout.setAlignment(Pos.CENTER);

        ReturningBookLayout.getChildren().addAll(ReturnBookStackPane, ReturningBookGridPane);
        ReturningBookLayout.setBackground(background);


        Scene ReturningBookScene = new Scene(ReturningBookLayout,632, 352);




        // ---------------------------------------------------------------------------------------------- //
        // User - View My Books Page  :

        Background UserViewMyBooks_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        TableView<BorrowedBook> UserViewMyBooks_Table = new TableView<>();

        TableColumn<BorrowedBook, Integer> UserViewMyBooks_BookIDColumn = new TableColumn<>("Book ID");
        TableColumn<BorrowedBook, String> UserViewMyBooks_BookNameColumn = new TableColumn<>("Book Name");
        TableColumn<BorrowedBook, Integer> UserViewMyBooks_TicketNumColumn = new TableColumn<>("Ticket No.");
        TableColumn<BorrowedBook, LocalDate> UserViewMyBooks_ReturnDateColumn = new TableColumn<>("Return Date");

        UserViewMyBooks_BookIDColumn.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty().asObject());
        UserViewMyBooks_BookNameColumn.setCellValueFactory(cellData -> cellData.getValue().bookNameProperty());
        UserViewMyBooks_TicketNumColumn.setCellValueFactory(cellData -> cellData.getValue().TicketNoProperty().asObject());
        UserViewMyBooks_ReturnDateColumn.setCellValueFactory(cellData -> cellData.getValue().returnDateProperty());



        UserViewMyBooks_BookIDColumn.setMinWidth(78);
        UserViewMyBooks_BookNameColumn.setMinWidth(172);
        UserViewMyBooks_TicketNumColumn.setMinWidth(170);
        UserViewMyBooks_ReturnDateColumn.setMinWidth(170);

        UserViewMyBooks_Table.getColumns().addAll(
            UserViewMyBooks_BookIDColumn,
            UserViewMyBooks_BookNameColumn,
            UserViewMyBooks_TicketNumColumn,
            UserViewMyBooks_ReturnDateColumn
        );

        Button UserViewMyBooks_BackButton = new Button("Back");
        UserViewMyBooks_BackButton.setPrefSize(200, 20);
        UserViewMyBooks_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        VBox UserViewMyBooks_VBox = new VBox(10);
        UserViewMyBooks_VBox.setPadding(new Insets(20, 20, 20, 20));
        UserViewMyBooks_VBox.setAlignment(Pos.CENTER);
        UserViewMyBooks_VBox.getChildren().addAll(UserViewMyBooks_Table, UserViewMyBooks_BackButton);
        UserViewMyBooks_VBox.setBackground(UserViewMyBooks_Background);

        Scene UserViewMyBooks_Scene = new Scene(UserViewMyBooks_VBox, 632, 352);


        // ---------------------------------------------------------------------------------------------- //
        // User - User Panel Page  :

        Background UserInfoPanel_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        Label UserInfoPanel_IDLabel = new Label("ID:");
        UserInfoPanel_IDLabel.setFont(Font.font("Century Gothic"));
        UserInfoPanel_IDLabel.setTextFill(Color.WHITE);

        HBox UserInfoPanel_IDField_hBox = new HBox(10);
        TextField UserInfoPanel_IDField = new TextField();
        UserInfoPanel_IDField.setMinWidth(247);
        UserInfoPanel_IDField.setPrefWidth(247);
        UserInfoPanel_IDField.setEditable(false);
        UserInfoPanel_IDField.setPromptText("Username");
        UserInfoPanel_IDField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image iconImage9 = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\IDFieldIcon.png");
        ImageView iconImageView9 = new ImageView(iconImage9);
        UserInfoPanel_IDField_hBox.getChildren().addAll(iconImageView9, UserInfoPanel_IDField);
        UserInfoPanel_IDField_hBox.setAlignment(Pos.CENTER);

        Label UserInfoPanel_UsernameLabel = new Label("Username:");
        UserInfoPanel_UsernameLabel.setFont(Font.font("Century Gothic"));
        UserInfoPanel_UsernameLabel.setTextFill(Color.WHITE);

        HBox UserInfoPanel_UsernameField_hBox = new HBox(10);
        TextField UserInfoPanel_UsernameField = new TextField();
        UserInfoPanel_UsernameField.setMinWidth(247);
        UserInfoPanel_UsernameField.setPrefWidth(247);
        UserInfoPanel_UsernameField.setEditable(false);
        UserInfoPanel_UsernameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image UserInfoPanel_UsernameField_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UsernameFieldIcon.png");
        ImageView UserInfoPanel_UsernameField_iconImageView = new ImageView(UserInfoPanel_UsernameField_iconImage);
        UserInfoPanel_UsernameField_hBox.getChildren().addAll(UserInfoPanel_UsernameField_iconImageView, UserInfoPanel_UsernameField);
        UserInfoPanel_UsernameField_hBox.setAlignment(Pos.CENTER);

        Label UserInfoPanel_EmailLabel = new Label("Email:" );
        UserInfoPanel_EmailLabel.setFont(Font.font("Century Gothic"));
        UserInfoPanel_EmailLabel.setTextFill(Color.WHITE);

        HBox UserInfoPanel_EmailField_hBox = new HBox(10);
        TextField UserInfoPanel_EmailField = new TextField();
        UserInfoPanel_EmailField.setMinWidth(247);
        UserInfoPanel_EmailField.setPrefWidth(247);
        UserInfoPanel_EmailField.setEditable(false);
        UserInfoPanel_EmailField.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-family: Century Gothic; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #FFFFFF;");
        Image UserInfoPanel_EmailField_iconImage = new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UsernameFieldIcon.png");
        ImageView UserInfoPanel_EmailField_iconImageView = new ImageView(UserInfoPanel_EmailField_iconImage);
        UserInfoPanel_EmailField_hBox.getChildren().addAll(UserInfoPanel_EmailField_iconImageView, UserInfoPanel_EmailField);
        UserInfoPanel_EmailField_hBox.setAlignment(Pos.CENTER);

        Button UserInfoPanel_BackButton = new Button("Back");
        UserInfoPanel_BackButton.setPrefSize(250, 20);
        UserInfoPanel_BackButton.setMinWidth(250);
        UserInfoPanel_BackButton.setPrefWidth(250);
        UserInfoPanel_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");


        GridPane UserInfoPanel_GridPane = new GridPane();
        UserInfoPanel_GridPane.setVgap(10);
        UserInfoPanel_GridPane.setAlignment(Pos.CENTER_LEFT);
        UserInfoPanel_GridPane.add(UserInfoPanel_IDLabel, 0, 1);
        UserInfoPanel_GridPane.add(UserInfoPanel_IDField_hBox, 0, 2);
        UserInfoPanel_GridPane.add(UserInfoPanel_UsernameLabel, 0, 3);
        UserInfoPanel_GridPane.add(UserInfoPanel_UsernameField_hBox, 0, 4);
        UserInfoPanel_GridPane.add(UserInfoPanel_EmailLabel, 0, 5);
        UserInfoPanel_GridPane.add(UserInfoPanel_EmailField_hBox, 0, 6);


        VBox UserInfoPanel_VBox = new VBox(10);
        UserInfoPanel_VBox.setPadding(new Insets(217));
        UserInfoPanel_VBox.setAlignment(Pos.CENTER);
        UserInfoPanel_VBox.getChildren().addAll(UserInfoPanel_GridPane, UserInfoPanel_BackButton);
        UserInfoPanel_VBox.setBackground(UserInfoPanel_Background);


        Scene UserInfoPanel_Scene = new Scene(UserInfoPanel_VBox, 632, 352);

        // ---------------------------------------------------------------------------------------------- //
        // User - Borrow Book - Show Available Books Window //

        Background UserShowAvailableBooks_Background = new Background(new BackgroundImage(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OfficialBackground1.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        TableView<Book> UserShowAvailableBooks_Table = new TableView<>();

        TableColumn<Book, Integer> UserShowAvailableBooks_BookIDColumn = new TableColumn<>("Book ID");
        TableColumn<Book, String> UserShowAvailableBooks_BookNameColumn = new TableColumn<>("Book Name");
        TableColumn<Book, String> UserShowAvailableBooks_BookCategoryColumn = new TableColumn<>("Category");
        TableColumn<Book, String> UserShowAvailableBooks_BookAuthorColumn = new TableColumn<>("Author");
        TableColumn<Book, String> UserShowAvailableBooks_BookPublisherColumn = new TableColumn<>("Publisher");
        TableColumn<Book, String> UserShowAvailableBooks_BookAvailabilityColumn = new TableColumn<>("Availability");

        UserShowAvailableBooks_BookIDColumn.setCellValueFactory(cellData ->
            Bindings.createObjectBinding(() -> cellData.getValue().IdProperty().getValue(),
                cellData.getValue().IdProperty()));
        UserShowAvailableBooks_BookNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        UserShowAvailableBooks_BookCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        UserShowAvailableBooks_BookAuthorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        UserShowAvailableBooks_BookPublisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());
        UserShowAvailableBooks_BookAvailabilityColumn.setCellValueFactory(cellData -> cellData.getValue().AvailabilityProperty());

        UserShowAvailableBooks_BookAvailabilityColumn.setCellFactory(column -> new TableCell<Book, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    if ("Borrowed".equals(item)) {
                        setTextFill(Color.web("#e6d800"));
                        setFont(Font.font("System", FontWeight.BOLD, 12));
                    } else if (item.equals("Available")) {
                        setTextFill(Color.web("#00ff00"));
                        setFont(Font.font("System", FontWeight.BOLD, 12));

                    }
                }
            }
        });


        UserShowAvailableBooks_BookIDColumn.setMinWidth(78);
        UserShowAvailableBooks_BookNameColumn.setMinWidth(102.4);
        UserShowAvailableBooks_BookCategoryColumn.setMinWidth(102.4);
        UserShowAvailableBooks_BookAuthorColumn.setMinWidth(102.4);
        UserShowAvailableBooks_BookPublisherColumn.setMinWidth(102.4);
        UserShowAvailableBooks_BookAvailabilityColumn.setMinWidth(102.4);

        UserShowAvailableBooks_Table.getColumns().addAll(
            UserShowAvailableBooks_BookIDColumn,
            UserShowAvailableBooks_BookNameColumn,
            UserShowAvailableBooks_BookCategoryColumn,
            UserShowAvailableBooks_BookAuthorColumn,
            UserShowAvailableBooks_BookPublisherColumn,
            UserShowAvailableBooks_BookAvailabilityColumn
            );

        Button UserShowAvailableBooks_BackButton = new Button("Back");
        UserShowAvailableBooks_BackButton.setPrefSize(200, 20);
        UserShowAvailableBooks_BackButton.setStyle("-fx-background-color: transparent; -fx-border-color: #DEDEDE; -fx-border-width: 0.5px; -fx-border-radius: 5px; -fx-text-fill: #f5f5f5; -fx-font-family: 'Century Gothic';");

        VBox UserShowAvailableBooks_VBox = new VBox(10);
        UserShowAvailableBooks_VBox.setPadding(new Insets(20, 20, 20, 20));
        UserShowAvailableBooks_VBox.setAlignment(Pos.CENTER);
        UserShowAvailableBooks_VBox.getChildren().addAll(UserShowAvailableBooks_Table, UserShowAvailableBooks_BackButton);
        UserShowAvailableBooks_VBox.setBackground(UserShowAvailableBooks_Background);

        Scene UserShowAvailableBooks_Scene = new Scene(UserShowAvailableBooks_VBox, 632, 352);



        // ---------------------------------------------------------------------------------------------- //
                                            // Buttons Functions //
        // ---------------------------------------------------------------------------------------------- //
        // Modes page buttons :

        AdminLoginButton.setOnAction(e -> primaryStage.setScene(AdminScene));

        UserLoginButton.setOnAction(e -> primaryStage.setScene(UserLoginScene));

        RegisterLoginButton.setOnAction(e -> primaryStage.setScene(RegisterScene));

        // ---------------------------------------------------------------------------------------------- //
        // User Login page buttons :

        UserLogin_BackButton.setOnAction(e -> {
            UserUsernameField.clear();
            UserPasswordField.clear();
            primaryStage.setScene(ModesScene);
        });

        UserLogin_LoginButton.setOnAction(e -> {
            UserUsername_String = UserUsernameField.getText();
            UserPassword_String = UserPasswordField.getText();

            try {
                if (UserUsername_String != null && UserPassword_String != null) {
                    if (User.userlogin(UserUsername_String, UserPassword_String)) {
                        primaryStage.setScene(LoginOTP_Scene);
                        String userEmail = User.getUserEmail(UserUsername_String);

                        if (isValidEmail(userEmail)) {
                            LoginOTP_Label.setText("OTP has been sent to \n" + maskEmail(userEmail));
                            otp = generateOTP();
                            LoginOTP_ReSendOTPButton.setDisable(true);
                            EmailSender.sendOTP(userEmail, otp);
                            startCountdown(LoginOTP_ReSendOTPButton);
                            UserUsernameField.clear();
                            UserPasswordField.clear();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Invalid email format. Please update your email.");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username and/or Password Is Wrong, \nTry Again.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Username and/or Password Is Wrong, \nTry Again.");
                    alert.showAndWait();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        UserLogin_ForgotPasswordButton.setOnAction(e -> primaryStage.setScene(ForgotPassword_Scene));

        LoginOTP_LoginOTPButton.setOnAction(e -> {

            String LoginOTPField_String = LoginOTPField.getText();

                if (LoginOTPField_String.equals(otp)){
                stopCountdown();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Welcome Back @ " + UserUsername_String);
                alert.showAndWait();
                otp = null;
                primaryStage.setScene(UserPanelScene);
                LoginOTPField.clear();
                UserUsernameField.clear();
                UserPasswordField.clear();
                } else {
                    LoginOTPField.clear();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong OTP, \nTry Again.");
                    alert.showAndWait();
                }

        });

        LoginOTP_ReSendOTPButton.setOnAction(e -> {

            try {
                LoginOTP_ReSendOTPButton.setDisable(true);
                EmailSender.sendOTP(User.getUserEmail(UserUsername_String), otp);
                startCountdown(LoginOTP_ReSendOTPButton);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

        });

        LoginOTP_BackButton.setOnAction(e ->{

            otp = null;
            UserUsername_String = null;
            UserPassword_String = null;
            stopCountdown();
            primaryStage.setScene(ModesScene);
            LoginOTPField.clear();
            UserUsernameField.clear();
            UserPasswordField.clear();


        });

        // ---------------------------------------------------------------------------------------------- //
        // Admin Login page buttons :

        AdminLogin_BackButton.setOnAction(e -> primaryStage.setScene(ModesScene));

        AdminLogin_LoginButton.setOnAction(e -> {

            AdminUsername_String = AdminUsernameField.getText();
            String AdminPassword_String = AdminPasswordField.getText();

            try {
                if (Admin.Adminlogin(AdminUsername_String,AdminPassword_String)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Welcome Back @ " + AdminUsername_String);
                    alert.showAndWait();
                    primaryStage.setScene(AdminDashboard);
                    AdminUsernameField.clear();
                    AdminPasswordField.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Username and/or Password Is Wrong, \n Try Again.");
                    alert.showAndWait();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // ---------------------------------------------------------------------------------------------- //
        // Register User page buttons :

        RegisterPanel_BackButton.setOnAction(e -> primaryStage.setScene(ModesScene));

        RegisterPanel_LoginButton.setOnAction(e-> {
            UserUsernameRegister_String = RegisterUsernameField.getText();
            UserPasswordRegister_String = RegisterPasswordField.getText();
            UserEmailRegister_String = RegisterEmailField.getText().toLowerCase();
            UserPhoneNumberRegister_String = RegisterPhoneField.getText();

                if(!UserUsernameRegister_String.isEmpty() && !UserPasswordRegister_String.isEmpty() && !UserEmailRegister_String.isEmpty() && !UserPhoneNumberRegister_String.isEmpty() && UserPhoneNumberRegister_String.length() == 10){

                    if (isValidEmail(UserEmailRegister_String)) {
                        RegisterOTP_Label.setText("OTP has been sent to \n" + maskEmail(UserEmailRegister_String));
                        otp = generateOTP();
                        primaryStage.setScene(RegisterOTP_Scene);
                        RegisterOTP_ReSendOTPButton.setDisable(true);
                        EmailSender.sendOTP(UserEmailRegister_String, otp);
                        startCountdown(RegisterOTP_ReSendOTPButton);
                        RegisterUsernameField.clear();
                        RegisterPasswordField.clear();
                        RegisterEmailField.clear();
                        RegisterPhoneField.clear();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid email format.\nPlease update your email.");
                        alert.showAndWait(); }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error,\nCheck Your Information and Try Again.");
                    alert.showAndWait();
                }
            });

        RegisterPhoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidInteger(newValue)) {
                vibrateTextField(RegisterPhoneField);
            } else {
            RegisterPhoneField.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-border-width: 0 0 2 0; -fx-border-color: #ffffff;");
            }
        });

        RegisterOTP_RegisterOTPButton.setOnAction(e -> {

            String RegisterOTPField_String = RegisterOTPField.getText();

            try{
                if (RegisterOTPField_String.equals(otp)){
                    stopCountdown();
                    User user = new User();
                    user.signUp(UserUsernameRegister_String, UserPasswordRegister_String, UserEmailRegister_String, UserPhoneNumberRegister_String);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Welcome @ " + UserUsernameRegister_String + "\n# Your ID: " + user.getUserId());
                    alert.showAndWait();
                    primaryStage.setScene(ModesScene);
                    otp = null;
                    UserUsername_String = null;
                    RegisterOTPField.clear();
                    UserUsernameField.clear();
                    UserPasswordField.clear();
                } else {
                    RegisterOTPField.clear();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong OTP, \nTry Again.");
                    alert.showAndWait();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


    });

        // ---------------------------------------------------------------------------------------------- //
        // Forgot Password buttons :

        ForgotPassword_BackButton.setOnAction(e -> {
            ForgotPasswordEmailField.clear();
            primaryStage.setScene(UserLoginScene);
        });

        ForgotPassword_SendOTPButton.setOnAction(e -> {

            ForgotPasswordEmailField_String = ForgotPasswordEmailField.getText().toLowerCase();

            try{
                if(!ForgotPasswordEmailField_String.isEmpty() && User.ForgotPassword_EmailCheck(ForgotPasswordEmailField_String)){

                    ForgotPassword_SendOTPButton.setDisable(true);
                    ForgotPassword_BackButton.setDisable(true);
                    ForgotPasswordEmailField.setDisable(true);
                    ForgotPasswordOTPField.setDisable(false);
                    ForgotPasswordNewPasswordField.setDisable(false);
                    ForgotPasswordRe_NewPasswordField.setDisable(false);
                    ForgotPassword_SetPasswordButton.setDisable(false);
                    startCountdown(ForgotPassword_ReSendOTPButton);

                    otp = generateOTP();
                    EmailSender.sendOTP(ForgotPasswordEmailField_String, otp);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# OTP Sent Successfully !\n# Please Check your Inbox / Junk Mail .");
                    alert.showAndWait();

            } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error\nCheck Your Email and Try Again.");
                    alert.showAndWait();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }});

        ForgotPassword_SetPasswordButton.setOnAction(e -> {

            String ForgotPasswordEmailField_String = ForgotPasswordEmailField.getText();
            String ForgotPasswordOTPField_String = ForgotPasswordOTPField.getText();
            String ForgotPasswordNewPasswordField_String = ForgotPasswordNewPasswordField.getText();
            String ForgotPasswordRe_NewPasswordField_String = ForgotPasswordRe_NewPasswordField.getText();
            try {
                if(ForgotPasswordNewPasswordField_String.equals(ForgotPasswordRe_NewPasswordField_String) && ForgotPasswordOTPField_String.equals(otp)
                        && !ForgotPasswordOTPField_String.isEmpty() && !ForgotPasswordNewPasswordField_String.isEmpty() && !ForgotPasswordRe_NewPasswordField_String.isEmpty()) {

                            ForgotPassword_SendOTPButton.setDisable(false);
                            ForgotPassword_BackButton.setDisable(false);
                            ForgotPasswordEmailField.setDisable(false);
                            ForgotPasswordOTPField.setDisable(true);
                            ForgotPasswordNewPasswordField.setDisable(true);
                            ForgotPasswordRe_NewPasswordField.setDisable(true);
                            ForgotPassword_SetPasswordButton.setDisable(true);
                            ForgotPassword_ReSendOTPButton.setDisable(true);
                            stopCountdown();
                            User.ForgotPassword_NewPaswword(ForgotPasswordNewPasswordField_String, ForgotPasswordEmailField_String);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("# Password Changed Successfully .");
                            alert.showAndWait();
                            primaryStage.setScene(UserLoginScene);
                            ForgotPasswordEmailField_String = null;
                            ForgotPasswordEmailField.clear();
                            ForgotPasswordOTPField.clear();
                            ForgotPasswordNewPasswordField.clear();
                            ForgotPasswordRe_NewPasswordField.clear();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error\nCheck Your Email/OTP and Try Again.");
                    alert.showAndWait();
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            });

        ForgotPassword_ReSendOTPButton.setOnAction(e -> {


            try{
                if(!ForgotPasswordEmailField_String.isEmpty() && User.ForgotPassword_EmailCheck(ForgotPasswordEmailField_String)){

                    ForgotPassword_SendOTPButton.setDisable(true);
                    ForgotPassword_BackButton.setDisable(true);
                    ForgotPasswordEmailField.setDisable(true);
                    ForgotPasswordOTPField.setDisable(false);
                    ForgotPasswordNewPasswordField.setDisable(false);
                    ForgotPasswordRe_NewPasswordField.setDisable(false);
                    ForgotPassword_SetPasswordButton.setDisable(false);
                    startCountdown(ForgotPassword_ReSendOTPButton);

                    otp = generateOTP();
                    EmailSender.sendOTP(ForgotPasswordEmailField_String, otp);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# OTP Sent Successfully !\n# Please Check your Inbox / Junk Mail .");
                    alert.showAndWait();

                } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Error\nCheck Your Email and Try Again.");
                        alert.showAndWait();
                    }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


            });

        // ---------------------------------------------------------------------------------------------- //
        // User Dashboard buttons :

        UserPanel_BorrowBookButton.setOnAction(e -> {
            primaryStage.setScene(BorrowingBookScene);
        });

        UserPanel_ReturnBookButton.setOnAction(e -> {
            primaryStage.setScene(ReturningBookScene);
        });

        UserPanel_infoButton.setOnAction(e -> {

            int id = 0;
            String username = null;
            String email = null;

            try {
                id = User.getUserID(UserUsername_String);
                username = User.getUserUsername(UserUsername_String);
                email = User.getUserEmail(UserUsername_String);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            UserInfoPanel_IDField.setText(String.valueOf(id));
            UserInfoPanel_UsernameField.setText(username);
            UserInfoPanel_EmailField.setText(email);

            primaryStage.setScene(UserInfoPanel_Scene);
        });

        UserPanel_ShowMyBooksButton.setOnAction(e-> {
            try {
                User user = new User();
                ObservableList<BorrowedBook> UserBooksList = user.getUserBorrowedBooks(UserUsername_String);
                UserViewMyBooks_Table.setItems(UserBooksList);
                primaryStage.setScene(UserViewMyBooks_Scene);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });

        UserPanel_LogoutButton.setOnAction(e -> {
            UserUsername_String = null;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have successfully logged out of the application.\nCome Back Soon!");
            alert.showAndWait();
            primaryStage.setScene(ModesScene);
        });

        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard buttons :

        AdminDashboard_AddBooksButton.setOnAction(e -> {

            BookPublisherField.setText(AdminUsername_String);
            primaryStage.setScene(AddingBookScene);

        });

        AdminDashboard_RemoveBookButton.setOnAction(e -> primaryStage.setScene(RemovingBookScene));

        AdminDashboard_ViewAccountsDetailsButton.setOnAction(e -> {

            Admin admin = new Admin();
            ObservableList<Account> accountList = admin.getUsersFromDatabase();
            AdminViewAccounts_Table.setItems(accountList);
            primaryStage.setScene(AdminViewAccounts_Scene);

        });

        AdminDashboard_ShowBooksButton.setOnAction(e -> {

            Admin admin = new Admin();
            ObservableList<Book> bookList = admin.getBooksFromDatabase();
            adminShowBooksTable.setItems(bookList);
            primaryStage.setScene(AdminShowBooks);
        });


        AdminDashboard_BorrowedBooksButton.setOnAction(e -> {

            Admin borrowedBookList = new Admin();
            ObservableList<BorrowedBook> borrowedBook_List = borrowedBookList.getBorrowedBooks();
            AdminShowBorrowedBooks_Table.setItems(borrowedBook_List);
            primaryStage.setScene(AdminShowBorrowedBooks_Scene);

        });

        AdminDashboard_LogoutButton.setOnAction(e-> {

            AdminUsername_String = null;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have successfully logged out of the Admin Panel.\nCome Back Soon!");
            alert.showAndWait();
            primaryStage.setScene(ModesScene);
        });


        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard - Add Book Page buttons :

        AddBook_BackButton.setOnAction(e ->{
            primaryStage.setScene(AdminDashboard);
            BookNameField.clear();
            BookCategoryField.clear();
            BookAuthorField.clear();
        });

        AddBook_AddButton.setOnAction(e ->{

            String BookNameField_String = BookNameField.getText();
            String BookCategoryField_String = BookCategoryField.getText();
            String BookAuthorField_String = BookAuthorField.getText();
            String BookPublisherField_String = AdminUsername_String;
            String BookAvailability = "Available";
            Admin admin = new Admin();


            if(!BookNameField_String.isEmpty() && !BookCategoryField_String.isEmpty() && !BookAuthorField_String.isEmpty() && !BookPublisherField_String.isEmpty()){
                if(admin.addBook(BookNameField_String,BookCategoryField_String,BookAuthorField_String,BookPublisherField_String,BookAvailability)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# '"  + BookNameField_String + "' Added Successfully .");
                    alert.showAndWait();
                    BookNameField.clear();
                    BookCategoryField.clear();
                    BookAuthorField.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("# Error While Adding The Book\nPlease Try Again.");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("# Error While Adding The Book\n# Please Check Your Inputs.");
                alert.showAndWait();
                }
        });

        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard - Remove Book Page buttons :

        RemoveBook_BackButton.setOnAction(e -> primaryStage.setScene(AdminDashboard));

        RemoveBook_RemoveButton.setOnAction(e -> {


            String BookNameField_Remove_String = BookNameField_Remove.getText();
            String BookIDField_String = BookIDField.getText();

            try {
                int BookIDField_intValue = Integer.parseInt(BookIDField_String);
                if(!BookNameField_Remove_String.isEmpty() && !BookIDField_String.isEmpty()){
                    if(!User.isBookBooked(BookIDField_intValue)){
                        if(Admin.deleteBook(BookIDField_intValue,BookNameField_Remove_String)){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("# '"  + BookNameField_Remove_String + "' Removed Successfully .");
                            alert.showAndWait();
                            BookNameField_Remove.clear();
                            BookIDField.clear();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("# Error While Deleting The Book\n# Please Try Again.");
                            alert.showAndWait();
                            BookNameField_Remove.clear();
                            BookIDField.clear();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("# Error While Deleting The Book\n# Please Try Again.");
                        alert.showAndWait();
                        BookNameField_Remove.clear();
                        BookIDField.clear();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("# Error While Deleting The Book\n# Please Check Your Inputs.");
                    alert.showAndWait();
                    BookNameField_Remove.clear();
                    BookIDField.clear();
                }
            } catch (NumberFormatException ex1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("# Invalid Input\n# Please Try Again.");
                alert.showAndWait();
                BookNameField_Remove.clear();
                BookIDField.clear();
            } catch (SQLException ex2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("# Error While Deleting The Book\n# Please Try Again.");
                alert.showAndWait();
                BookNameField_Remove.clear();
                BookIDField.clear();        
            }
            

        });

        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard - Show Books Page buttons :

        AdminShowBooks_BackButton.setOnAction(e -> primaryStage.setScene(AdminDashboard));

        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard - View Accounts Page buttons :

        AdminViewAccounts_BackButton.setOnAction(e -> primaryStage.setScene(AdminDashboard));

        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard - View Borrowed Books Page buttons :

        AdminShowBorrowedBooks_BackButton.setOnAction(e-> primaryStage.setScene(AdminDashboard));

        // ---------------------------------------------------------------------------------------------- //
        // Admin Dashboard - Close/Open Borrowing Period Page buttons :

        final ImageView UserPanel_BorrowingOpened = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\BorrowBook.png"));
        final ImageView UserPanel_BorrowingClosed = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\UserPanel\\BorrowingClosed.png"));
        final ImageView AdminDashboard_CloseBorrowingPeriod = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\CloseBorrowingPeriodButton.png"));
        final ImageView AdminDashboard_OpenBorrowingPeriod = new ImageView(new Image("E:\\Uni\\Fall Semesters\\2023 Fall\\CSCI-313 Software Engineering\\Project\\LibrarySystem(Ver#1)\\ProjectGPT\\Images\\OpenBorrowingPeriodButton.png"));

        AdminDashboard_CloseOpenBorrowingPeriodButton.setOnAction(event -> {
            if (AdminDashboard_CloseOpenBorrowingPeriodButton.isSelected()) {
                AdminDashboard_CloseOpenBorrowingPeriodImage.setImage(AdminDashboard_OpenBorrowingPeriod.getImage());
                UserPanel_BorrowBookImage.setImage(UserPanel_BorrowingClosed.getImage());
                Alert buttonStateAlert = new Alert(Alert.AlertType.INFORMATION);
                buttonStateAlert.setTitle("Borrowing Period Status");
                UserPanel_BorrowBookButton.setDisable(true);
                buttonStateAlert.setContentText("Borrowing Period is Closed Now.");
                buttonStateAlert.showAndWait();
            } else {
                AdminDashboard_CloseOpenBorrowingPeriodImage.setImage(AdminDashboard_CloseBorrowingPeriod.getImage());
                UserPanel_BorrowBookImage.setImage(UserPanel_BorrowingOpened.getImage());
                Alert buttonStateAlert = new Alert(Alert.AlertType.INFORMATION);
                buttonStateAlert.setTitle("Borrowing Period Status");
                UserPanel_BorrowBookButton.setDisable(false);
                buttonStateAlert.setContentText("Borrowing Period is Open Now.");
                buttonStateAlert.showAndWait();
            }
        });

        // ---------------------------------------------------------------------------------------------- //
        // User Dashboard - User Info Page Buttons :

        UserInfoPanel_BackButton.setOnAction(e-> {
            primaryStage.setScene(UserPanelScene);
        });

        // ---------------------------------------------------------------------------------------------- //
        // User Dashboard - Show My Books Page Buttons :

        UserViewMyBooks_BackButton.setOnAction(e-> {
            primaryStage.setScene(UserPanelScene);
        });

        // ---------------------------------------------------------------------------------------------- //
        // User Dashboard - Borrow Book Page Buttons :

        BorrowBook_BackButton.setOnAction(e-> {

            BorrowBookIDField.clear();
            BorrowBookNameField.clear();
            BorrowBookReturnDateField.setValue(null);
            primaryStage.setScene(UserPanelScene);

        });

        BorrowBook_BorrowButton.setOnAction(e-> {

            String BorrowBookIDField_String = BorrowBookIDField.getText();
            String BorrowBookNameField_String = BorrowBookNameField.getText();
            LocalDate BorrowBookReturnDateField_Date = BorrowBookReturnDateField.getValue();

            try {
                try {
                    int BorrowBookIDField_Int = Integer.parseInt(BorrowBookIDField_String);
                    if(!BorrowBookIDField_String.isEmpty() && !BorrowBookNameField_String.isEmpty() && BorrowBookReturnDateField_Date != null){
                        if(isFutureDate(BorrowBookReturnDateField_Date)){
                            User.performBooking(UserUsername_String, BorrowBookIDField_Int, BorrowBookNameField_String, BorrowBookReturnDateField_Date);
                            BorrowBookIDField.clear();
                            BorrowBookNameField.clear();
                            BorrowBookReturnDateField.setValue(null);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("# Invalid Return Date.\n# Try Again.");
                            alert.showAndWait();}
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# Empty Fields.\n# Try Again.");
                    alert.showAndWait();
                }
                } catch (NumberFormatException e2) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# Invalid Input.\n# Try Again.");
                    alert.showAndWait();                
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        });

        BorrowBook_BrowseButton.setOnAction(e-> {

            try {
                User user = new User();
                ObservableList<Book> bookList = user.User_getBooksFromDatabase();
                UserShowAvailableBooks_Table.setItems(bookList);
                BorrowBookIDField.clear();
                BorrowBookNameField.clear();
                BorrowBookReturnDateField.setValue(null);
                primaryStage.setScene(UserShowAvailableBooks_Scene);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // ---------------------------------------------------------------------------------------------- //
        // User Dashboard - Return Book Page Buttons :

        ReturnBook_BackButton.setOnAction(e-> {

            ReturnBookIDField.clear();
            ReturnBookNameField.clear();
            primaryStage.setScene(UserPanelScene);

        });

        ReturnBook_ReturnButton.setOnAction(e-> {
            String ReturnBookIDField_String = ReturnBookIDField.getText();
            String ReturnBookNameField_String = ReturnBookNameField.getText();

            try {
                int BorrowBookIDField_Int = Integer.parseInt(ReturnBookIDField_String);
                User.cancelBooking(UserUsername_String, BorrowBookIDField_Int, ReturnBookNameField_String);
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Input for Booking ID. Please enter a valid number.");
                alert.showAndWait();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error cancelling booking. Please try again.");
                alert.showAndWait();
            }
        });

        // ---------------------------------------------------------------------------------------------- //
        // User Dashboard - Borrow Book - Show Available Books Window //

        UserShowAvailableBooks_BackButton.setOnAction(e -> {
            primaryStage.setScene(BorrowingBookScene);
        });

        // ---------------------------------------------------------------------------------------------- //


    }
       // End Of the Application Class //


    // Functions :

    private void vibrateTextField(TextField textField) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(textField.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(textField.translateXProperty(), -5)),
                new KeyFrame(Duration.millis(100), new KeyValue(textField.translateXProperty(), 5)),
                new KeyFrame(Duration.millis(150), new KeyValue(textField.translateXProperty(), -5)),
                new KeyFrame(Duration.millis(200), new KeyValue(textField.translateXProperty(), 5)),
                new KeyFrame(Duration.millis(250), new KeyValue(textField.translateXProperty(), 0))
        );
        timeline.play();
        textField.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-border-width: 0 0 2 0; -fx-border-color: #FF0000;");
    }

    private boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private void startCountdown(Button button) {
        stopCountdown();

        countdownTimer = new Timeline(
                new KeyFrame(Duration.seconds(31), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        button.setDisable(false);
                    }
                })
        );

        countdownTimer1 = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    int remainingTime = 30;

                    @Override
                    public void handle(ActionEvent event) {
                        button.setText("Re-Send OTP (" + remainingTime + " sec)");
                        remainingTime--;

                        if (remainingTime < 0) {
                            stopCountdown();
                            button.setText("Re-Send OTP");
                        }
                    }

                })
        );

        countdownTimer.play();
        countdownTimer1.setCycleCount(Timeline.INDEFINITE);
        countdownTimer1.play();
    }

    private void stopCountdown() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        if (countdownTimer1 != null) {
            countdownTimer1.stop();
        }
    }

    private String generateOTP() {
        int otpLength = 6;

        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    private String maskEmail(String email) {
        if (isValidEmail(email)) {
            int atIndex = email.indexOf('@');
            if (atIndex > 0) {
                String username = email.substring(0, atIndex);
                int lengthToMask = Math.min((2*username.length()) / 3, 8); // Mask half or up to 6 characters, whichever is smaller
                String maskedUsername = username.substring(0, username.length() - lengthToMask) +
                        "********".substring(0, lengthToMask);
                return maskedUsername + email.substring(atIndex);
            } else {
                return email;
            }
        } else {
            return "Invalid Email";
        }
    }

    private boolean isFutureDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return date != null && date.isAfter(currentDate);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

