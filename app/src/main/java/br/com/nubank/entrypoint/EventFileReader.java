package br.com.nubank.entrypoint;

import javax.inject.Inject;

import br.com.nubank.application.JsonParser;
import br.com.nubank.core.EventHandlerManager;
import br.com.nubank.core.entity.AccountEvent;
import br.com.nubank.core.entity.Event;
import br.com.nubank.core.entity.TransactionEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class EventFileReader {

    private final EventHandlerManager eventHandlerManager;

    @Inject
    public EventFileReader(EventHandlerManager eventHandlerManager) {
        this.eventHandlerManager = eventHandlerManager;
    }

    public void read(String file) throws Exception {
        var lines = new ArrayList<String>();
        
        try (var stream = Files.lines(Paths.get(file))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            throw new Exception("invalid argument");
        }

        var iterator = lines.iterator();
        while (iterator.hasNext()) {
            var event = parseToEvent(iterator.next());

            if (Objects.nonNull(event)) {
                eventHandlerManager.setEventHandler(event.getType());
                var accountState = eventHandlerManager.execute(event);

                System.out.println(JsonParser.parseObjectToJson(accountState));
            }
        }
    }

    private Event<?> parseToEvent(String stream) {
        var eventTypes = new ArrayList<Event<?>>();
        
        eventTypes.add(new AccountEvent());
        eventTypes.add(new TransactionEvent());

        var iterator = eventTypes.iterator();

        Event<?> event = null;
        while (iterator.hasNext()) {
            event = JsonParser.parseJsonToObject(stream, iterator.next().getClass());

            if (Objects.nonNull(event))
                break;
        }

        return event;
    }
}
