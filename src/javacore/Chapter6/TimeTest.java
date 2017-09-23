package javacore.Chapter6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by tsf on 17-9-18.
 */

public class TimeTest {

    public static void main(String[] args) {

        ActionListener listener = new InterTimeListener();  // listen to the event and action accordingly

        Timer timer = new Timer(2000, listener); // every 2s
        timer.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);

    }
}


class InterTimeListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        Date now = new Date();
        System.out.println("At the tone, the time is " + now);
        Toolkit.getDefaultToolkit().beep();
    }
}