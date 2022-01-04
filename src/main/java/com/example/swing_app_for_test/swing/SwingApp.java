package com.example.swing_app_for_test.swing;

import com.example.swing_app_for_test.service.DataService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Component
public class SwingApp extends JFrame {

    @Autowired
    private DataService dataService;

    public SwingApp() {
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Main Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Data url");
        JTextArea textArea = new JTextArea("");
        textArea.setLineWrap(true);

        JTextField textField = new JTextField(20);
        JButton getDataButton = new JButton("Get Data");
        panel.add(label);
        panel.add(textField);
        panel.add(getDataButton);

        frame.getContentPane().add(BorderLayout.CENTER, textArea);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);

        getDataButton.addActionListener((ActionEvent event) -> {
            String apiHost = textField.getText();
            if(apiHost.isEmpty() && apiHost.isBlank()){
                JOptionPane.showMessageDialog(frame, "data source address cannot be empty");
            }
            else if(!new UrlValidator().isValid(apiHost)){
                JOptionPane.showMessageDialog(frame, "data source address not valid");
            }
            else {
                ResponseEntity<String> dataFromURL = dataService.getDataFromURL(apiHost);
                if(dataFromURL.getStatusCodeValue() == 200){
                    textArea.setText(null);
                    textArea.append(dataFromURL.getBody());
                    JScrollPane scroll = new JScrollPane (textArea
                            , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    frame.getContentPane().remove(scroll);
                    frame.setVisible(true);
                    frame.getContentPane().add(BorderLayout.EAST, scroll);
                    frame.setVisible(true);

                }
                else{
                    JOptionPane.showMessageDialog(frame, "data retrieval error - "
                            + "error code "
                            + dataFromURL.getStatusCodeValue()
                            +", error body "
                            + dataFromURL.getBody());
                }
            }
        });

    }
}
