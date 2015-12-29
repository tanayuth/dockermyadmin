package com.abc.dkadmin.service;

import com.abc.dkadmin.exception.DockerMyAdminException;
import com.abc.dkadmin.properties.DockerMyAdminProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

@Component
public class UnixProcessor {

    private static final Logger log = LoggerFactory.getLogger(UnixProcessor.class);

    private static final String INCORRECT_PASSWORD = "Sorry, try again.";

    private static final String PASSWORD_FORM = "[sudo] password for";

    @Autowired
    private DockerMyAdminProperties dockerMyAdminProperties;

    public String executeCommand(String command) {

        log.info("Execute : " + command);

        Process process = runProcess(command);

        //Authorize super user
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
        writer.println(dockerMyAdminProperties.getSuperUserPassword());
        writer.flush();

        //Read console output
        try (InputStreamReader in = new InputStreamReader(process.getInputStream());
             BufferedReader reader = new BufferedReader(in)) {
            StringBuilder stringBuilder = new StringBuilder();
            reader.lines()
                    .forEach(line -> {

                        //Terminate command when got incorrect password
                        if (line.contains(INCORRECT_PASSWORD)) {
                            process.destroy();
                            throw new DockerMyAdminException("super user password is incorrect, terminated command: \""
                                                                     + command + "\"");
                        }

                        //Remove password form input from console
                        if (line.contains(PASSWORD_FORM)) {
                            line = line.substring(line.indexOf(": ") + 1).trim();
                        }
                        stringBuilder.append(line).append("\n");
                    });
            process.destroy();
            return stringBuilder.toString();
        } catch (IOException e) {
            process.destroy();
            throw new DockerMyAdminException("error while execute command line", e);
        }
    }

    private Process runProcess(String command) {
        ProcessBuilder builder = new ProcessBuilder("sudo", "-S", "bash", "-c", command).redirectErrorStream(true);
        try {
            return builder.start();
        } catch (IOException e) {
            throw new DockerMyAdminException("cannot start processor builder", e);
        }
    }
}
