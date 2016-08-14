package com.gcplot.log_processor.parser;

import com.gcplot.log_processor.survivor.AgesState;
import com.gcplot.log_processor.LogMetadata;
import com.gcplot.model.notifications.GCNotification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:art.dm.ser@gmail.com">Artem Dmitriev</a>
 *         7/30/16
 */
public class ParseResult {
    private final boolean isSuccessful;
    public boolean isSuccessful() {
        return isSuccessful;
    }

    private final Throwable exception;
    public Optional<Throwable> getException() {
        return Optional.ofNullable(exception);
    }

    private final List<GCNotification> notifications;
    public List<GCNotification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    private final List<AgesState> agesStates;
    public List<AgesState> getAgesStates() {
        return agesStates;
    }

    private final LogMetadata logMetadata;
    public Optional<LogMetadata> getLogMetadata() {
        return Optional.ofNullable(logMetadata);
    }

    private ParseResult(List<GCNotification> notifications, List<AgesState> agesState, LogMetadata logMetadata) {
        this(true, null, notifications, agesState, logMetadata);
    }

    private ParseResult(boolean isSuccessful, Throwable exception,
                        List<GCNotification> notifications, List<AgesState> agesStates, LogMetadata logMetadata) {
        this.isSuccessful = isSuccessful;
        this.notifications = notifications;
        this.agesStates = agesStates;
        this.exception = exception;
        this.logMetadata = logMetadata;
    }

    public static ParseResult success(List<GCNotification> notifications, List<AgesState> agesStates,
                                      LogMetadata logMetadata) {
        return new ParseResult(notifications, agesStates, logMetadata);
    }

    public static ParseResult failure(Throwable t) {
        return new ParseResult(false, t, Collections.emptyList(), Collections.emptyList(), null);
    }

}