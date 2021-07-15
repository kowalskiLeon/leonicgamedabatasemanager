/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;

/**
 *
 * @author breno
 */
public class RetornoPaginadoDTO {

    private List lista;
    private Long totalElements;
    private Integer totalPages;
    private Integer numeroIntens;
    private Integer paginaAtual;

    public RetornoPaginadoDTO(List lista, Long totalElements, Integer totalPages, Integer numeroIntens, Integer paginaAtual) {
        this.lista = lista;
        this.totalElements = totalElements;
        this.numeroIntens = numeroIntens;
        this.paginaAtual = paginaAtual;
        this.totalPages = totalPages;
    }
    
    

    public Iterable getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }


    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getNumeroIntens() {
        return numeroIntens;
    }

    public void setNumeroIntens(Integer numeroIntens) {
        this.numeroIntens = numeroIntens;
    }

    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }
}
