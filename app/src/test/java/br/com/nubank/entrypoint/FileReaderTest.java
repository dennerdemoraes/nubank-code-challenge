package br.com.nubank.entrypoint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import br.com.nubank.core.EventHandlerManager;
import br.com.nubank.core.entity.AccountEvent;
import br.com.nubank.core.entity.Event;
import br.com.nubank.core.entity.EventType;
import br.com.nubank.core.entity.TransactionEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {

    EventHandlerManager eventHandlerManager = mock(EventHandlerManager.class);
    EventFileReader eventFileReader = new EventFileReader(eventHandlerManager);
    
    @Test
    void account_event_should_call_correct_instance() throws Exception {
        eventFileReader.read("src/test/resources/files/operations_account");

        ArgumentCaptor<EventType> eventTypeCaptor = ArgumentCaptor.forClass(EventType.class);
        verify(eventHandlerManager, times(1)).setEventHandler(eventTypeCaptor.capture());
        assertThat(eventTypeCaptor.getValue()).isEqualTo(EventType.ACCOUNT);

        ArgumentCaptor<Event<?>> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventHandlerManager, times(1)).execute(eventCaptor.capture());
        assertThat(eventCaptor.getValue()).isInstanceOf(AccountEvent.class);
    }

    @Test
    void transaction_event_should_call_correct_instance() throws Exception {
        eventFileReader.read("src/test/resources/files/operations_transaction");

        ArgumentCaptor<EventType> eventTypeCaptor = ArgumentCaptor.forClass(EventType.class);
        verify(eventHandlerManager, times(1)).setEventHandler(eventTypeCaptor.capture());
        assertThat(eventTypeCaptor.getValue()).isEqualTo(EventType.TRANSACTION);

        ArgumentCaptor<Event<?>> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventHandlerManager, times(1)).execute(eventCaptor.capture());
        assertThat(eventCaptor.getValue()).isInstanceOf(TransactionEvent.class);
    }

    @Test
    void invalid_event_should_not_call_event_handler() throws Exception {
        eventFileReader.read("src/test/resources/files/operations_invalid");

        verify(eventHandlerManager, times(0)).setEventHandler(any());
        verify(eventHandlerManager, times(0)).execute(any());
    }

    @Test
    void invalid_file_should_thrown_an_exception() throws Exception {
        var exception = assertThrows(Exception.class, () -> eventFileReader.read("invalid"));

        assertThat(exception.getMessage()).isEqualTo("invalid argument");
    }
}
