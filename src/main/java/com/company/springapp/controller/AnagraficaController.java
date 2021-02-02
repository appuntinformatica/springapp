package com.company.springapp.controller;

import java.util.List;

import com.company.springapp.controller.dto.AuthenticationRequest;
import com.company.springapp.model.Anagrafica;
import com.company.springapp.service.AnagraficaService;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/anagrafica")
@RestController
public class AnagraficaController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public AnagraficaService anagraficaService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Anagrafica>> list(@RequestParam(required = false) String q,
            @RequestParam(required = false) String sortBy, @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
		log.info("q = {}", q);
        log.info("sortBy = {}, orderBy = {}", sortBy, orderBy);
        log.info("limit = {}, offset = {}", limit, offset);
        //http://localhost:8080/restapi-faker/vehicles?limit=10&offset=0
        
        if (offset == null) {
            offset = 0;                    
        }
        if (limit == null) {
            limit = 10;                    
        }
        List<Anagrafica> results = anagraficaService.findAll(q, offset, limit, sortBy, orderBy);
        
        return  ResponseEntity.ok(results);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@RequestBody Anagrafica anagrafica) {
		anagraficaService.createAnagrafica(anagrafica);
		ResponseEntity.ok(anagrafica);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
