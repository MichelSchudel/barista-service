package nl.craftsmen.coffeehouse;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.craftsmen.coffeehouse.models.Delivery;

@Path("/")
public class DeliveryCounterResource {

    Logger logger = LoggerFactory.getLogger(DeliveryCounterResource.class);

    @Inject
    @Channel("in-counter")
    Publisher<Delivery> deliveries;

    @GET
    @Path("/outcounter")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("application/json")
    public Publisher<Delivery> stream() {
        return deliveries;
    }
}
