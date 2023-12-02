package com.abiola.project3.UserDemo.controller;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
public class UserDemoController {

    File messagesFile = new File("messages.txt");
    File logFile = new File("log.txt");

    @GetMapping("message")
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
//        String x;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(messagesFile))) {
            bufferedReader.lines().forEach(line -> sb.append(String.format("%s%n", line.trim())));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    @GetMapping("message/count")
    public String messageCount() {
        StringBuilder sb = new StringBuilder();
//        String x;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(messagesFile))) {
            bufferedReader.lines().forEach(line -> sb.append(String.format("%s%n", line.trim())));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "The number of messages is " + sb.toString().split("\n").length;
    }

    @PostMapping("message")
    public String postMessage(@RequestBody String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(messagesFile, true))) {
            bufferedWriter.write(message + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true))) {
            bufferedWriter.write("New message created " + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "message successful";
    }

}
