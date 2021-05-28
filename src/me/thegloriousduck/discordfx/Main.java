package me.thegloriousduck.discordfx;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Application.launch(DiscordFXApplication.class, args);
    }

    public static void info(String msg) {
        System.out.println("[DiscordFX] " + msg);
    }

    public static void debug(String msg) {
    	System.out.println("[DiscordFX] " + msg);
    }

    public static void warn(String msg) {
    	System.out.println("[DiscordFX] " + msg);
    }

    public static void error(String msg) {
    	System.out.println("[DiscordFX] " + msg);
    }
}
