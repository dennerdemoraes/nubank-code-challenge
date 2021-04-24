package br.com.nubank.core.usecase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import br.com.nubank.core.EventHandlerManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Event;
import br.com.nubank.core.entity.EventType;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorizerUseCaseTest {

    EventHandlerManager eventHandlerManager = mock(EventHandlerManager.class);
    AuthorizerUseCase authorizerUseCase = new AuthorizerUseCase(eventHandlerManager);

    @Test
    void when_execute_with_valid_account_event_should_call_event_handler_by_type() {
        var event = "{\"account\": {\"active-card\": true, \"available-limit\": 100}}";

        var expectedEvent = new Event();
        expectedEvent.setAccount(new Account(true, 100));

        
        authorizerUseCase.execute(event);


        ArgumentCaptor<EventType> eventTypeCaptor = ArgumentCaptor.forClass(EventType.class);
        verify(eventHandlerManager, times(1)).setEventHandler(eventTypeCaptor.capture());
        assertThat(eventTypeCaptor.getValue()).isEqualTo(EventType.ACCOUNT);

        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventHandlerManager, times(1)).execute(eventCaptor.capture());
        assertThat(eventCaptor.getValue()).usingRecursiveComparison().isEqualTo(expectedEvent);
    }
}
