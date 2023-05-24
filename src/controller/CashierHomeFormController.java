package controller;

import javafx.scene.control.Label;

import java.sql.SQLException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;


import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class CashierHomeFormController {


    public Label lblCustomer;
    public Label lblPayed;
    public Label lblTime;
    public Label lblDate;

     public void initialize() {
         loadDateAndTime();
         try {
             lblCustomer.setText(new CustomerController().getCountCustomer());


             try {
                 lblPayed.setText(new OrderController().getCountPayed());
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
     }

         private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

}
