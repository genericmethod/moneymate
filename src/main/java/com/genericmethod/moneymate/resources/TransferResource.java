package com.genericmethod.moneymate.resources;


import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.model.Transfer;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/transfers")
@Produces(MediaType.APPLICATION_JSON)
public class TransferResource {



    @POST
    @Timed
    public void transfer(@Valid Transfer transfer){


    }

}
