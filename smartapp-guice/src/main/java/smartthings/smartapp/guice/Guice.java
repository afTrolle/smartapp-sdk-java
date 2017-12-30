package smartthings.smartapp.guice;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.internal.InternalInjectorCreator;
import smartthings.smartapp.core.SmartAppDefinition;
import smartthings.smartapp.guice.internal.DefaultBindingsSpec;
import smartthings.smartapp.guice.internal.GuiceSmartAppDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface Guice {

    static SmartAppDefinition smartapp(Consumer<BindingsSpec> consumer) {
        List<Module> modules = new ArrayList<>();
        DefaultBindingsSpec bindingsSpec = new DefaultBindingsSpec(modules);
        consumer.accept(bindingsSpec);
        Injector injector = createInjector(Stage.PRODUCTION, modules);
        return new GuiceSmartAppDefinition(injector);
    }

    static SmartAppDefinition smartapp(Injector injector) {
        return new GuiceSmartAppDefinition(injector);
    }

    static Injector createInjector(Stage stage, Iterable<? extends Module> modules) {
        return new InternalInjectorCreator()
            .stage(stage)
            .addModules(modules)
            .build();
    }

}
