package br.com.nubank;

import br.com.nubank.core.entity.EventType;
import br.com.nubank.core.usecase.AccountEventHandlerUseCase;
import br.com.nubank.core.usecase.EventHandlerUseCase;
import br.com.nubank.core.usecase.TransactionEventHandlerUseCase;
import br.com.nubank.entrypoint.EventFileReader;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.name.Names;

import static com.google.inject.Guice.createInjector;

public class App {

    private static final Injector injector = createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            bind(EventHandlerUseCase.class)
                .annotatedWith(Names.named(EventType.ACCOUNT.getEventHandler()))
                .to(AccountEventHandlerUseCase.class);

            bind(EventHandlerUseCase.class)
                .annotatedWith(Names.named(EventType.TRANSACTION.getEventHandler()))
                .to(TransactionEventHandlerUseCase.class);
        }
    });

    public static void main(String... args) throws Exception {
        var reader = injector.getInstance(EventFileReader.class);

        if (args.length == 0)
            throw new Exception("it's missing required argument");

        reader.read(args[0]);
    }
}
