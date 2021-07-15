/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author guilherme.moura
 */
public interface BaseService<T extends BaseEntity> {
    
    Iterable<T> listAllObjetos();

    Page findAll(T objeto, Pageable pageable);

    Page findAllWithoutRestriction(T objeto, Pageable pageable);

    Page findAll(T objeto);

    T getObjetoById(Long id);

    T saveObjeto(T obj);

    T saveObject(T obj, boolean updateVersion);

    T deleteObjeto(Long id);
    
}
