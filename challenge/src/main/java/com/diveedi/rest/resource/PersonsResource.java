package com.diveedi.rest.resource;

import com.diveedi.rest.service.PersonsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonsResource {
    @Inject
    private PersonsService personsService;

    @GET
    public Response getPersons(@QueryParam("p") int page) {
        return Response.ok(personsService.getPersons(page)).build();
    }
}
