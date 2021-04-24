package br.com.nubank;

import br.com.nubank.core.AccountEventHandler;
import br.com.nubank.core.EventHandler;
import br.com.nubank.core.TransactionEventHandler;
import br.com.nubank.core.entity.EventType;
import br.com.nubank.entrypoint.FileReader;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.name.Names;

import static com.google.inject.Guice.createInjector;

public class App {

    private static final Injector injector = createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            bind(EventHandler.class)
                .annotatedWith(Names.named(EventType.ACCOUNT.getEventHandler()))
                .to(AccountEventHandler.class);

            bind(EventHandler.class)
                .annotatedWith(Names.named(EventType.TRANSACTION.getEventHandler()))
                .to(TransactionEventHandler.class);
        }
    });

    public static void main(String... args) throws Exception {
        var reader = injector.getInstance(FileReader.class);

        if (args.length == 0)
            throw new Exception("it's missing required argument");

        reader.read(args[0]);
    }
}
