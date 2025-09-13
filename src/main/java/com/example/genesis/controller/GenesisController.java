package com.example.genesis.controller;

import com.example.genesis.dto.GenesisResponse;
import com.example.genesis.service.GenesisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genesis")
public class GenesisController {
    private final GenesisService service;

    public GenesisController(GenesisService service) {
        this.service = service;
    }

    @GetMapping("/{capitulo}")
    public GenesisResponse getGenesis(@PathVariable("capitulo") int capitulo) {
        return service.getGenesisUpTo(capitulo);
    }
}
