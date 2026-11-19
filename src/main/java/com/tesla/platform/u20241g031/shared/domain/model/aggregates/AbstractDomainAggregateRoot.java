package com.tesla.platform.u20241g031.shared.domain.model.aggregates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for pure domain Aggregate Roots, decoupled from persistence frameworks.
 *
 * @param <T> concrete aggregate root type
 * @author Piero Francisco Montes Chang
 */
public abstract class AbstractDomainAggregateRoot<T extends AbstractDomainAggregateRoot<T>> {

    private final List<Object> domainEvents = new ArrayList<>();

    /**
     * Registers a domain event to be dispatched after persistence.
     *
     * @param event domain event instance
     */
    protected void registerEvent(Object event) {
        if (event != null) {
            this.domainEvents.add(event);
        }
    }

    /**
     * Returns an unmodifiable view of currently registered domain events.
     *
     * @return list of domain events
     */
    public List<Object> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }

    /**
     * Clears all registered domain events.
     */
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}