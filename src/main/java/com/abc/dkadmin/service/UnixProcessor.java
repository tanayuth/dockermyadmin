package com.abc.dkadmin.service;

import com.abc.dkadmin.exception.DockerMyAdminException;
import com.abc.dkadmin.properties.DockerMyAdminProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

@Component
public class UnixProcessor {

    @Autowired
    private DockerMyAdminProperties dockerMyAdminProperties;

    public String executeCommand(String command) {
        Process process = runProcess(command);

        //Authorize super user
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
//        writer.println(dockerMyAdminProperties.getSuperUserPassword());
        writer.println("natep'boat000");
        writer.flush();

        //Read console output
        try (InputStreamReader in = new InputStreamReader(process.getInputStream());
             BufferedReader reader = new BufferedReader(in)) {
            StringBuilder stringBuilder = new StringBuilder();
            reader.lines().forEach(line -> stringBuilder.append(line).append("\n"));
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new DockerMyAdminException("error while execute command line", e);
        }
    }

    private Process runProcess(String command) {
        ProcessBuilder builder = new ProcessBuilder("sudo", "-S", "bash", "-c", command);
        try {
            return builder.start();
        } catch (IOException e) {
            throw new DockerMyAdminException("cannot start processor builder", e);
        }
    }
}
