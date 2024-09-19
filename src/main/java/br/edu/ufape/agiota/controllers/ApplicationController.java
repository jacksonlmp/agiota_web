package br.edu.ufape.agiota.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class ApplicationController {

    @GetMapping("/version")
    public String retornarVersao() {
        return "1.0";
    }

    @GetMapping("/health")
    public String health() {
        return "On-line";
    }

}
