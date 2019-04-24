package com.madrat.spaceshooter.apiserver.utils.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Logger() {
    }

    public static void i(String msg) {
        System.out.println("[+] [" + dtf.format(LocalDateTime.now()) + "] " + msg);
    }

    public static void w(String msg) {
        System.out.println("[!] [" + dtf.format(LocalDateTime.now()) + "] " + msg);
    }

    public static void e(String msg) {
        System.out.println("[-] [" + dtf.format(LocalDateTime.now()) + "] " + msg);
    }
}
