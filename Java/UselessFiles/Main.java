package com.company;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args)  {
        try {

            Player player = new Player();
            EventListener eventListener = new EventListener();
            Integer number = new Integer(127);
            eventListener.addEvents(number);
            player.addEventListener(eventListener);

            for (int i = 5; i < 61; i++) {
                player.addEvent(144, 1, i, 100, i);
                player.addEvent(176, 1, 127, 0, i);
                player.addEvent(128, 1, i, 100, i + 2);
            }

            MainView mainView = new MainView(player);
            mainView.start();
        }
        catch (Exception exc) {}
    }

}

class Player {

    private Sequencer sequencer = null;
    private Sequence sequence = null;
    private Track track = null;

    private EventListener eventListener = null;

    public Player() throws Exception {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequence = new Sequence(Sequence.PPQ, 4);
        track = sequence.createTrack();
    }

    public void addEventListener(EventListener current) {
        eventListener = current;
    }

    public void setLink(MainView mainView) {
        eventListener.setLink(mainView);
    }

    public void addEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        }
        catch (Exception ex) {}
        track.add(event);
    }

    public void play() throws Exception{
        sequencer.addControllerEventListener(eventListener, eventListener.getEventsIWant());
        sequencer.setSequence(sequence);
        sequencer.setTempoInBPM(220);
        sequencer.start();
    }
}


class EventListener implements ControllerEventListener {

    private List<Integer> eventsIWant = new ArrayList<Integer>(0);

    MainView mainView = null;

    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("BOO");
        mainView.repaint();
    }

    public void setLink(MainView mainView) {
        this.mainView = mainView;
    }

    public void addEvents(Integer number) {
        eventsIWant.add(number);
    }

    public int[] getEventsIWant() {
        int[] result = new int[eventsIWant.size()];
        for (int i = 0; i < eventsIWant.size(); i++) {
            result[i] = (int) eventsIWant.get(i);
        }
        return result;
    }

}


class MainView {

    private JFrame frame = null;
    private MyPanel myPanel = null;

    private Player player = null;

    private boolean flag = false;

    public MainView(Player player) {
        frame = new JFrame("Мой первый музыкальный клип");
        this.player = player;
        this.player.setLink(this);
        this.myPanel = new MyPanel();
    }

    public void start() throws Exception{
        frame.setContentPane(myPanel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(30, 30, 600, 600);
        player.play();
    }

    public void repaint() {
        flag = true;
        myPanel.repaint();
    }

    private class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            if (flag) {
                Graphics2D g2d = (Graphics2D) g;

                int r = (int) (Math.random() * 255);
                int gr = (int) (Math.random() * 255);
                int b = (int) (Math.random() * 255);

                g2d.setColor(new Color(r, gr, b));

                int height = (int) (Math.random() * 120) + 10;
                int width = (int) (Math.random() * 120) + 10;

                int x = (int) (Math.random() * 40) + 10;
                int y = (int) (Math.random() * 40) + 10;

                g2d.fillRect(x, y, width, height);
                flag = false;
            }
        }
    }

}