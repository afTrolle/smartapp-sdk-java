package smartthings.smartapp.core.internal.handlers;

import smartthings.smartapp.core.Action;
import v1.smartapps.*;
import java.util.function.Predicate;

public final class PredicateSingleEventHandler {

    private final Predicate<Event> predicate;
    private final Action<Event> action;

    public PredicateSingleEventHandler(Predicate<Event> predicate, Action<Event> action) {
        this.predicate = predicate;
        this.action = action;
    }

    public boolean test(Event event) {
        return predicate.test(event);
    }

    public void execute(Event event) throws Exception {
        action.execute(event);
    }
}
