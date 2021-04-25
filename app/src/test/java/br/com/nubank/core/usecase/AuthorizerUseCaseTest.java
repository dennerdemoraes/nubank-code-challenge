// package br.com.nubank.core.usecase;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;

// import java.time.ZonedDateTime;

// import org.junit.jupiter.api.Test;
// import org.mockito.ArgumentCaptor;

// import br.com.nubank.core.EventHandlerManager;
// import br.com.nubank.core.entity.Account;
// import br.com.nubank.core.entity.Event;
// import br.com.nubank.core.entity.EventType;
// import br.com.nubank.core.entity.Transaction;

// import static org.assertj.core.api.Assertions.assertThat;

// class AuthorizerUseCaseTest {

//     EventHandlerManager eventHandlerManager = mock(EventHandlerManager.class);
//     AuthorizerUseCase authorizerUseCase = new AuthorizerUseCase(eventHandlerManager);

//     @Test
//     void when_execute_with_valid_account_event_should_call_event_handler_by_type() {
//         var event = "{\"account\": {\"active-card\": true, \"available-limit\": 100}}";

//         var expectedEvent = new Event();
//         expectedEvent.setAccount(new Account(true, 100));

        
//         authorizerUseCase.execute(event);


//         ArgumentCaptor<EventType> eventTypeCaptor = ArgumentCaptor.forClass(EventType.class);
//         verify(eventHandlerManager, times(1)).setEventHandler(eventTypeCaptor.capture());
//         assertThat(eventTypeCaptor.getValue()).isEqualTo(EventType.ACCOUNT);

//         ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
//         verify(eventHandlerManager, times(1)).execute(eventCaptor.capture());
//         assertThat(eventCaptor.getValue()).usingRecursiveComparison().isEqualTo(expectedEvent);
//     }

//     @Test
//     void when_execute_with_valid_transaction_event_should_call_event_handler_by_type() {
//         var event = "{\"transaction\": {\"merchant\": \"Burger King\", \"amount\": 20, \"time\": \"2019-02-13T10:00:00.000Z\"}}";

//         var expectedEvent = new Event();
//         expectedEvent.setTransaction(new Transaction("Burger King", 20, ZonedDateTime.parse("2019-02-13T10:00:00.000Z[UTC]")));


//         authorizerUseCase.execute(event);


//         ArgumentCaptor<EventType> eventTypeCaptor = ArgumentCaptor.forClass(EventType.class);
//         verify(eventHandlerManager, times(1)).setEventHandler(eventTypeCaptor.capture());
//         assertThat(eventTypeCaptor.getValue()).isEqualTo(EventType.TRANSACTION);

//         ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
//         verify(eventHandlerManager, times(1)).execute(eventCaptor.capture());
//         assertThat(eventCaptor.getValue()).usingRecursiveComparison().isEqualTo(expectedEvent);
//     }

//     @Test
//     void when_execute_with_missing_required_data_should_not_call_event_handler() {
//         authorizerUseCase.execute("{\"transaction\": {\"merchant\": \"Burguer King\"}}");

//         verify(eventHandlerManager, times(0)).setEventHandler(any());
//         verify(eventHandlerManager, times(0)).execute(any());
//     }

//     @Test
//     void when_execute_with_invalid_event_should_not_call_event_handler() {
//         authorizerUseCase.execute("");

//         verify(eventHandlerManager, times(0)).setEventHandler(any());
//         verify(eventHandlerManager, times(0)).execute(any());
//     }
// }
