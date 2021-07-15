/*
 * To change this license header, choose License Headers in Projeto Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicesimpl;

import entidades.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorios.LinguaRepository;
import repositorios.ProjetoRepository;
import services.ProjetoService;

/**
 *
 * @author guilherme.moura
 */
@Service
public class ProjetoServiceImpl extends BaseServiceImpl<Projeto> implements ProjetoService{
 
    private final ProjetoRepository repository;
    @Autowired
    public ProjetoServiceImpl(ProjetoRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
