package com.company;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class BeatBox {

    public static void main(String[] args) {
        GUI main = new GUI();
        main.buildGUI();
    }

}


class GUI {

    private JFrame frame = null;
    private JPanel panel = null;

    private JButton start = null;
    private JButton stop = null;
    private JButton upTempo = null;
    private JButton downTempo = null;
    private JButton clear = null;
    private JButton serialize = null;
    private JButton restore = null;

    private ArrayList<JCheckBox> checkBoxList = null;

    private Sequencer sequencer = null;
    private Sequence sequence = null;
    private Track track = null;

    private final String[] instrumentsNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare",
            "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas",
            "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"};
    private final int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};


    public void buildGUI() {

        frame = new JFrame("Cyber BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        JPanel background = new JPanel(borderLayout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        start = new JButton("Start");
        start.addActionListener(new MyStartActionListener());
        buttonBox.add(start);

        stop = new JButton("Stop");
        stop.addActionListener(new MyStopActionListener());
        buttonBox.add(stop);

        upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyTempUpActionListener());
        buttonBox.add(upTempo);

        downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyTempDownActionListener());
        buttonBox.add(downTempo);

        serialize = new JButton("Serialize");
        serialize.addActionListener(new ActionListenerForSerialize());
        buttonBox.add(serialize);

        restore = new JButton("Restore");
        restore.addActionListener(new ActionListenerForRestore());
        buttonBox.add(restore);

        clear = new JButton("Clear");
        clear.addActionListener(new MyClearActionListener());
        buttonBox.add(clear);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentsNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        panel = new JPanel(grid);
        background.add(BorderLayout.CENTER, panel);

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            panel.add(c);
        }

        setUpMidi();

        frame.setBounds(50, 50, 400, 400);
        frame.pack();
        frame.setVisible(true);
    }

    private void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void buildTrackAndStart() {

        int[] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];

            int key = instruments[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox c = (JCheckBox) checkBoxList.get(j + (16 * i));
                if (c.isSelected()) {
                    trackList[j] = key;
                }
                else {
                    trackList[j] = 0;
                }
            }

            makeTracks(trackList);
            track.add(makeEvent(176, 1, 127, 0, 16));
        }

        track.add(makeEvent(192, 9, 1, 0, 15));

        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void makeTracks(int[] list) {

        for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }

    }

    private MidiEvent makeEvent(int cmd, int chan, int one, int two, int tick) {

        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(cmd, chan, one, two);
            event = new MidiEvent(a, tick);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return event;
    }

    private class MyStartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    private class MyStopActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    private class MyTempUpActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    private class MyTempDownActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 0.97));
        }
    }

    private class MyClearActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    JCheckBox c = (JCheckBox) checkBoxList.get(j + i * 16);
                    c.setSelected(false);
                }
            }
        }
    }

    private class ActionListenerForSerialize implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] arrayToSerialize = new boolean[256];

            for (int i = 0; i < 256; i++) {
                JCheckBox c = (JCheckBox) checkBoxList.get(i);
                if (c.isSelected()) {
                    arrayToSerialize[i] = true;
                }
            }

            try {
                FileOutputStream fileStream = new FileOutputStream(new File("CheckBox.ser"));
                ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
                objectStream.writeObject(arrayToSerialize);

            }
            catch (Exception exc) {
                System.out.println("Error");
                exc.printStackTrace();
            }
        }
    }

    private class ActionListenerForRestore implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] arrayFromSerialize = new boolean[256];

            try {
                FileInputStream fileStream =  new FileInputStream("CheckBox.ser");
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                arrayFromSerialize = (boolean[]) objectStream.readObject();
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }

            for (int i = 0; i < 256; i++) {
                JCheckBox c = (JCheckBox) checkBoxList.get(i);
                c.setSelected(arrayFromSerialize[i]);
            }

            sequencer.stop();
            buildTrackAndStart();
        }
    }
}
