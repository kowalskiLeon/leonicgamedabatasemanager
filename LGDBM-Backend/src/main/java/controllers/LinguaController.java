/*
 * To change this license header, choose License Headers in Projeto Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entidades.Lingua;
import entidades.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.LinguaService;
import services.ProjetoService;

/**
 *
 * @author guilherme.moura
 */
@Controller
@RequestMapping("/lingua")
public class LinguaController extends BaseController<Lingua> {
    
    private final LinguaService service;
    
    @Autowired
    public LinguaController(LinguaService service){
        super(service, Lingua.class, Lingua[].class);
            this.service = service;
    }
}
