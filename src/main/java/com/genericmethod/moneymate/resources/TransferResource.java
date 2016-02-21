package com.genericmethod.moneymate.resources;


import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.model.Transfer;
import com.genericmethod.moneymate.services.TransferService;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/transfers")
@Produces(MediaType.APPLICATION_JSON)
public class TransferResource {

    public TransferService transferService;

    public TransferResource(TransferService transferService) {
        this.transferService = transferService;
    }

    @POST
    @Timed
    public void transfer(@Valid Transfer transfer){
        transferService.transfer(transfer);
    }

}
