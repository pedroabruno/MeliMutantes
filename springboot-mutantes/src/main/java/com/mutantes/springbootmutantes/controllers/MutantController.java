package com.mutantes.springbootmutantes.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutantes.springbootmutantes.services.MutantService;

import exceptions.BusinessException;

@RestController
public class MutantController {

	@Autowired
	private MutantService mutantService;
	
	@POST
	@RequestMapping("/isMutant")
    @Consumes("application/json")
    public Response isMutant(@RequestBody String cadenaAdn[]) {
		try {
			if (mutantService.isMutant(cadenaAdn)) {
				System.out.println("Ok");
				return Response.ok().build();
			} else {
				System.out.println("mal");
				return Response.status(403).build();
			}
		}catch(BusinessException e) {
			System.out.println(e.getMessage());
		}
		return Response.status(400).build();
    }
	
	@GET
	@RequestMapping("/stats")
	public String getMutantStats() {
		return mutantService.getMutantStats();
	}
	
}
