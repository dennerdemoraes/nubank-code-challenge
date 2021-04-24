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

    public void read(String file) throws Exception {
        try (var stream = Files.lines(Paths.get(file))) {
            stream.forEach(authorizerUseCase::execute);
        } catch (IOException e) {
            throw new Exception("invalid argument");
        }
    }
}
