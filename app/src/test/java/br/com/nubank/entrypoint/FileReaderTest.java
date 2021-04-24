package br.com.nubank.entrypoint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.nubank.core.usecase.AuthorizerUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FileReaderTest {

    AuthorizerUseCase authorizerUseCase = mock(AuthorizerUseCase.class);
    FileReader fileReader = new FileReader(authorizerUseCase);

    @Test
    @DisplayName("Scenario 1: Read args method should throws an exception when the args is empty")
    void read_args_should_throws_an_exception_when_args_is_empty() {
        var exception = assertThrows(Exception.class, () -> fileReader.read(null));

        assertThat(exception.getMessage()).isEqualTo("it's missing required argument");
    }

    @Test
    @DisplayName("Scenario 2: Read args method should read file and call authorizer use case")
    void read_args_should_read_file_and_call_authorizer_use_case() throws Exception {
        fileReader.read("src/test/resources/files/operations_two_lines");

        verify(authorizerUseCase, times(2)).execute(anyString());
    }

    @Test
    @DisplayName("Scenario 3: Read args method should throws an exception when the args is empty")
    void read_args_should_throws_an_exception_when_args_is_invalid() {
        var exception = assertThrows(Exception.class, () -> fileReader.read("invalid"));

        assertThat(exception.getMessage()).isEqualTo("invalid argument");
    }
}
