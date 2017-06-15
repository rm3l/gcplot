package com.gcplot.services.logs.disruptor;

import com.gcplot.commons.LazyVal;
import com.gcplot.logs.ParserContext;
import com.gcplot.model.gc.GCEvent;
import com.gcplot.repository.GCEventRepository;

/**
 * @author <a href="mailto:art.dm.ser@gmail.com">Artem Dmitriev</a>
 *         6/14/17
 */
public class ParsingState {
    private final LazyVal<GCEvent> lastPersistedEvent;
    private GCEvent firstEvent;
    private GCEvent lastEvent;

    public ParsingState(ParserContext ctx, GCEventRepository repository, String checksum) {
        this.lastPersistedEvent = LazyVal.ofOpt(() ->
                repository.lastEvent(ctx.analysisId(), ctx.jvmId(), checksum, getFirstEvent().occurred().minusDays(1)));
    }

    public LazyVal<GCEvent> getLastPersistedEvent() {
        return lastPersistedEvent;
    }

    public GCEvent getFirstEvent() {
        return firstEvent;
    }

    public void setFirstEvent(GCEvent firstEvent) {
        this.firstEvent = firstEvent;
    }

    public GCEvent getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(GCEvent lastEvent) {
        this.lastEvent = lastEvent;
    }
}
