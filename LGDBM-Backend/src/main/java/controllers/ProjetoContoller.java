/*
 * To change this license header, choose License Headers in Projeto Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entidades.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.ProjetoService;

/**
 *
 * @author guilherme.moura
 */
@Controller
@RequestMapping("/project")
public class ProjetoContoller extends BaseController<Projeto> {
    
    private final ProjetoService service;
    
    @Autowired
    public ProjetoContoller(ProjetoService service){
        super(service, Projeto.class, Projeto[].class);
            this.service = service;
    }
}
