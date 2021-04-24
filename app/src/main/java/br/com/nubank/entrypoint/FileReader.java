package br.com.nubank.entrypoint;

import javax.inject.Inject;

import br.com.nubank.core.usecase.AuthorizerUseCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    private final AuthorizerUseCase authorizerUseCase;

    @Inject
    public FileReader(AuthorizerUseCase authorizerUseCase) {
        this.authorizerUseCase = authorizerUseCase;
    }

    public void readArgs(String... args) throws Exception {
        if (args.length == 0)
            throw new Exception("it's missing required argument");

        try (var stream = Files.lines(Paths.get(args[0]))) {
            stream.forEach(authorizerUseCase::execute);
        } catch (IOException e) {
            throw new Exception("invalid argument");
        }
    }
}
