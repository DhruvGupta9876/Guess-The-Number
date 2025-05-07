package Guess_the_num;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GamePanel extends JPanel implements ActionListener {

    JPanel body = new JPanel();
    JPanel header = new JPanel();
    JPanel foot = new JPanel();
    JButton start = new JButton("Start");
    JButton diff = new JButton("Easy");
    JButton help = new JButton("Help");
    JButton submit = new JButton("Guess!");
    JButton cont = new JButton("Continue");
    JTextField input = new JTextField();
    JLabel result = new JLabel();
    JLabel atm = new JLabel();
    int rand;
    int attempts=0;
    GamePanel() {
        //Text Field
        input.setBounds(90, 230, 100, 40);
        input.setBackground(new Color(130, 6, 246));
        input.setForeground(Color.yellow);
        input.setFont(new Font("Serif", Font.BOLD, 20));
        input.setCaretColor(Color.YELLOW);
        input.setEnabled(false);

        //Buttons
        start.setBounds(204, 20, 90, 30);
        start.setFocusable(false);
        start.setHorizontalTextPosition(JButton.CENTER);
        start.setVerticalTextPosition(JButton.CENTER);
        start.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.BLACK));
        start.setFont(new Font("Sans",Font.BOLD,18));
        start.addActionListener(this);

        diff.setBounds(204, 70, 90, 30);
        diff.setFocusable(false);
        diff.setHorizontalTextPosition(JButton.CENTER);
        diff.setVerticalTextPosition(JButton.CENTER);
        diff.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.BLACK));
        diff.setFont(new Font("Sans",Font.BOLD,18));
        diff.addActionListener(this);

        help.setBounds(204, 120, 90, 30);
        help.setFocusable(false);
        help.setHorizontalTextPosition(JButton.CENTER);
        help.setVerticalTextPosition(JButton.CENTER);
        help.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.BLACK));
        help.setFont(new Font("Sans",Font.BOLD,18));
        help.addActionListener(this);

        submit.setBounds(325, 230, 85, 40);
        submit.setFocusable(false);
        submit.setHorizontalTextPosition(JButton.CENTER);
        submit.setVerticalTextPosition(JButton.CENTER);
        submit.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.BLACK));
        submit.setFont(new Font("Sans",Font.BOLD,18));
        submit.setEnabled(false);
        submit.addActionListener(this);

        cont.setBounds(195, 350, 120, 35);
        cont.setFocusable(false);
        cont.setHorizontalTextPosition(JButton.CENTER);
        cont.setVerticalTextPosition(JButton.CENTER);
        cont.setBorder(BorderFactory.createBevelBorder(0, Color.GRAY, Color.BLACK));
        cont.setFont(new Font("Sans",Font.BOLD,18));
        cont.addActionListener(this);
        cont.setEnabled(false);

        //Labels
        JLabel label1 = new JLabel();
        label1.setText("Guess The Number");
        label1.setFont(new Font("Serif", Font.BOLD, 40));
        label1.setForeground(new Color(40, 37, 55));

        JLabel label2 = new JLabel();
        label2.setText("Type a number and Press Submit");
        label2.setFont(new Font("Serif",Font.BOLD,25));
        label2.setForeground(Color.BLACK);
        label2.setBounds(80, 155, 400, 30);

        JLabel footer = new JLabel();
        footer.setText("Created by Dhruv Gupta as Internship Project for Afame Technologies");
        footer.setFont(new Font("Serif",Font.BOLD,16));

        result.setBounds(20,290,460,50);
        result.setFont(new Font("Monospaced",Font.BOLD,20));
        result.setOpaque(false);
        result.setHorizontalAlignment(JLabel.CENTER);

        atm.setBounds(170,175,200,50);
        atm.setFont(new Font("Serif",Font.BOLD,15));
        atm.setForeground(new Color(69, 25, 82));

        //Panels
        header.setBackground(new Color(176, 202, 171));
        header.setPreferredSize(new Dimension(500, 90));
        header.add(label1);

        body.setLayout(null);
        //body.setBorder(BorderFactory.createLineBorder(Color.CYAN,4));
        body.setBackground(new Color(158, 175, 246));
        body.setPreferredSize(new Dimension(500, 400));
        body.add(start);
        body.add(diff);
        body.add(help);
        body.add(submit);
        body.add(label2);
        body.add(input);
        body.add(result);
        body.add(atm);
        body.add(cont);

        foot.setBackground(Color.WHITE);
        foot.add(footer);

        this.add(header, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER);
        this.add(foot, BorderLayout.SOUTH);
    }

    public void gameStart() {
        start.setEnabled(false);
        diff.setEnabled(false);
        input.setEnabled(true);
        submit.setEnabled(true);
        input.grabFocus();
        result.setBackground(Color.WHITE);
        result.setBorder(BorderFactory.createLineBorder(Color.GRAY,4,true));
        result.setOpaque(true);
        rand = (int) (Math.random() * 100);
        if(Objects.equals(diff.getText(), "Easy")){
            attempts=10;
        }else if(Objects.equals(diff.getText(), "Hard")){
            attempts=7;
        }
        atm.setText("Attempts Remaining : "+ attempts);
    }
    public void loop(int attempts, int value){
        if(attempts<=0){
            result.setBorder(BorderFactory.createLineBorder(Color.RED,4,true));
            result.setForeground(Color.RED);
            result.setText("Game Over");
            cont.setEnabled(true);
            return;
        }
        if(value==rand){
            result.setBorder(BorderFactory.createLineBorder(Color.GREEN,4,true));
            result.setForeground(Color.GREEN);
            result.setText("Yay! You guessed the number");
            cont.setEnabled(true);
        }else if(value>rand){
            result.setText("Target Number is Lower than "+value);
        }else {
            result.setText("Target Number is Higher than "+value);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            gameStart();
        } else if (e.getSource() == submit) {
            int value ;
            try {
                value = Integer.parseInt(input.getText());
                loop(attempts,value);
            }catch (NumberFormatException nfe){
                result.setText("Please provide an Input");
                return;
            }
            attempts--;
            input.setText("");
            atm.setText("Attempts Remaining : "+ attempts);
        }else if(e.getSource()== diff){
            if(Objects.equals(diff.getText(), "Easy")){
                diff.setText("Hard");
            }else diff.setText("Easy");
        } else if (e.getSource()==cont) {
            start.setEnabled(true);
            diff.setEnabled(true);
            input.setText("");
            result.setText("");
        } else if (e.getSource()==help) {
            new HelpWindow();
        }
    }
}

