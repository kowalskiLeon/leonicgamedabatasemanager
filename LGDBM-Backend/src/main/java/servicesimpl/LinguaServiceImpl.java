/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicesimpl;

import entidades.Lingua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.LinguaService;
import repositorios.LinguaRepository;

/**
 *
 * @author guilherme.moura
 */
@Service
public class LinguaServiceImpl extends BaseServiceImpl<Lingua> implements LinguaService{
    
    private final LinguaRepository repository;
    @Autowired
    public LinguaServiceImpl(LinguaRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
}
