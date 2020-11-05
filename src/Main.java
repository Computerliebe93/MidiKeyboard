import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
               
                System.out.println(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();

                for (int j = 0; j < transmitters.size(); j++) {
                    transmitters.get(j).setReceiver(
                            new MidiInputReceiver(device.getDeviceInfo().toString())
                    );
                }

                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

                trans.getReceiver();
                System.out.println();

                device.open();

                System.out.println(device.getDeviceInfo() + " Was Opened");


            }
            catch (MidiUnavailableException e) {
            }
        }
    }

    public static class MidiInputReceiver implements Receiver {
        public String name;

        public MidiInputReceiver(String name) {
            this.name = name;
        }

        public void send(MidiMessage msg, long timeStamp) {
            System.out.println("midi received");
            System.out.println();

            byte[] aMsg = msg.getMessage();

            for (int i = 0; i < msg.getLength(); i++){
                System.out.println(aMsg[i]);

            }
            System.out.println();
        }

        public void close() {
        }
    }
}