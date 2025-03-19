package esprit.javafxesprit.controllers.chatMessages;


import esprit.javafxesprit.models.Data;
import esprit.javafxesprit.models.DataBase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChatController {
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField messageField;

    private ChatClient chatClient;
    public void initialize() {
        try {
            chatClient = new ChatClient("localhost", 12345);
            new Thread(this::receiveMessages).start();
        } catch (IOException e) {
            chatArea.appendText("Error: Unable to connect to server.\n");
        }
    }

    @FXML
    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            // Display the message locally with [Me]: before sending
            appendMessage("Me", message, true);
            // Send the raw message to the server
            chatClient.sendMessage(message);
            messageField.clear();
        }
    }

    private void receiveMessages() {
        try {
            String message;
            while ((message = chatClient.receiveMessage()) != null) {
                String finalMessage = message;
                Platform.runLater(() -> {
                    // Only append received messages with [Friend]: if they weren't sent by this client
                    // (Note: This is a simplification; for a true fix, you'd need to track sent messages)
                    if (!chatArea.getText().contains("[Me]: " + finalMessage)) {
                        appendMessage("Friend", finalMessage, false);
                    }
                });
            }
        } catch (IOException e) {
            Platform.runLater(() -> chatArea.appendText("Connection lost.\n"));
        }
    }



    private void appendMessage(String sender, String message, boolean isMe) {
        String formattedMessage = (isMe ? "[Me]: " : "[Friend]: ") + message + "\n";
        chatArea.appendText(formattedMessage);
   }
}