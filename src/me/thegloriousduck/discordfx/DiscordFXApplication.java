package me.thegloriousduck.discordfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DiscordFXApplication extends Application {

    @Override
    public void init() throws Exception {
        Main.info("Initializing DiscordFX");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.info("Starting DiscordFX");
        stage.setTitle("DiscordFX");
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.centerOnScreen();
        
        VBox parent = new VBox();
        Scene loginScene = new Scene(parent);
        
        parent.setAlignment(Pos.CENTER);
        
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        
        Label l = new Label();
        l.setTranslateY(50);
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0)  {
				String a = null;
				try {
					a = login(usernameField.getText(), passwordField.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				l.setText(a);
			}
        });
        
        usernameField.setMaxWidth(500);
        passwordField.setMaxWidth(500);
        
        usernameField.setTranslateY(-10);
        passwordField.setTranslateY(10);
        loginButton.setTranslateY(30);
        
        parent.getChildren().add(usernameField);
        parent.getChildren().add(passwordField);
        parent.getChildren().add(loginButton);
        parent.getChildren().add(l);
        
        stage.setScene(loginScene);
        stage.show();
    }
    
    public String login(String email, String password) throws IOException {
    	String payload = "{\"login\":\"" + email + "\",\"password\":\"" + password + "\",\"undelete\":false,\"captcha_key\":null,\"login_source\":null,\"gift_code_sku_id\":null}";

    	URL url = new URL("https://discord.com/api/v9/auth/login");
    	URLConnection connection = url.openConnection();
    	connection.setDoInput(true);
    	connection.setDoOutput(true);
    	
    	connection.setRequestProperty("accept", "*/*");
    	connection.setRequestProperty("accept-encoding", "gzip, deflate, br");
    	connection.setRequestProperty("accept-language", "en-US");
    	connection.setRequestProperty("content-length", "150");
    	connection.setRequestProperty("content-type", "application/json");
    	
    	connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
    	connection.connect();

    	OutputStream os = connection.getOutputStream();
    	PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
    	pw.write(payload);
    	pw.close();

    	InputStream is = connection.getInputStream();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	String line = null;
    	StringBuffer sb = new StringBuffer();
    	while ((line = reader.readLine()) != null) {
    	    sb.append(line);
    	}
    	is.close();
    	return sb.toString();
    }

}
