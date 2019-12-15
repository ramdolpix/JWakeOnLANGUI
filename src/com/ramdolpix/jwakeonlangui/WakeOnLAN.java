package com.ramdolpix.jwakeonlangui;

import javafx.scene.control.Alert;

import java.net.*;

public class WakeOnLAN {

        public static final int PORT = 9;

        public static void wakeUp(String ip,String mac) {


            String ipStr = ip;
            String macStr = mac;

            try {
                byte[] macBytes = getMacBytes(macStr);
                byte[] bytes = new byte[6 + 16 * macBytes.length];
                for (int i = 0; i < 6; i++) {
                    bytes[i] = (byte) 0xff;
                }
                for (int i = 6; i < bytes.length; i += macBytes.length) {
                    System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
                }

                InetAddress address = InetAddress.getByName(ipStr);
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
                DatagramSocket socket = new DatagramSocket();
                socket.send(packet);
                socket.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Done!");
                alert.setContentText("Packet sent!");

                alert.showAndWait();
            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to send packet");
                alert.setHeaderText("Results:" + e);
                alert.setContentText("Check MAC Address");

                alert.showAndWait();
            }
            }


        private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
            byte[] bytes = new byte[6];
            String[] hex = macStr.split("(\\:|\\-)");
            if (hex.length != 6) {
                throw new IllegalArgumentException("Invalid MAC address.");
            }
            try {
                for (int i = 0; i < 6; i++) {
                    bytes[i] = (byte) Integer.parseInt(hex[i], 16);
                }
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid hex digit in MAC address.");
            }
            return bytes;
        }
    }
