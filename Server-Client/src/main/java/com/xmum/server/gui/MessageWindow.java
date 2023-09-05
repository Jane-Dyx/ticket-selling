//package com.xmum.server.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import javax.swing.text.*;
//
//
//public class MessageWindow extends JFrame {
//
//    private JTextPane textPane;
//    private StyledDocument document;
//
//    public MessageWindow() {
//        setTitle("Message Window");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(800, 600));
//
//        textPane = new JTextPane();
//        textPane.setEditable(false);
//        document = textPane.getStyledDocument();
//
//        JScrollPane scrollPane = new JScrollPane(textPane);
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//
//        pack();
//        setVisible(true);
//    }
//
//    public void addMessage(String message, Color textColor) {
//        Style style = textPane.addStyle("MessageStyle", null);
//        StyleConstants.setForeground(style, textColor);
//
//        try {
//            document.insertString(document.getLength(), message + "\n", style);
//            textPane.setCaretPosition(document.getLength());
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }
//    }
//}
package com.xmum.server.gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.text.*;

public class MessageWindow {
    private static MessageWindow instance = null;
    private JFrame frame;
    private JTextPane textPane;
    private StyledDocument document;

    private MessageWindow() {
        frame = new JFrame();
        frame.setTitle("Server");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        textPane = new JTextPane();
        textPane.setEditable(false);
        document = textPane.getStyledDocument();

        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.pack();
    }

    public static MessageWindow getInstance() {
        if (instance == null) {
            instance = new MessageWindow();
        }
        return instance;
    }

    public void showWindow() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public void addMessage(String message, Color textColor) {
        Style style = textPane.addStyle("MessageStyle", null);
        StyleConstants.setForeground(style, textColor);

        try {
            document.insertString(document.getLength(), message + "\n", style);
            textPane.setCaretPosition(document.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void systemLog(String message){
        log(message, "SYSTEM", Color.BLACK);
    }
    public void ticketLog(String message){
        log(message, "TICKET", Color.ORANGE);
    }
    public void userLog(String message){
        log(message, "USER", Color.BLUE);
    }
    public void eventLog(String message){
        log(message, "EVENT", Color.MAGENTA);
    }

    public void log(String message, String label, Color color){
        Style style = textPane.addStyle("MessageStyle", null);
        StyleConstants.setForeground(style, color);
        StyleConstants.setBold(style, true);
        try {
            document.insertString(document.getLength(), LocalDateTime.now() + "\t[" + label + "]\t", style);
            StyleConstants.setBold(style, false);
            document.insertString(document.getLength(), message + "\n", style);
            textPane.setCaretPosition(document.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
