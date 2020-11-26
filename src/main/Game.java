package main;

import javax.swing.*;

public class Game {

    private JPanel MainPanel;

    public Game() {
        MainPanel = new JPanel();

        MainPanel.setSize(1000, 1000);

        JLabel jl = new JLabel(new ImageIcon("D:/Pictures/FinishedProblems.PNG"));
        jl.setBounds(0, 0, 1920, 1160);
        MainPanel.add(jl);

    }

    public JPanel get() {
        return MainPanel;

    }

}
