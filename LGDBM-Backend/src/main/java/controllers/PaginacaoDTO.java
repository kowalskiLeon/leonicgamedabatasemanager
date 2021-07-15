/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author guilherme.moura
 */
public class PaginacaoDTO {
    
    private Integer pagina;
    private Integer totalItens;
    private Integer totalPaginas;
    private String ordem;// ASC ou DESC
    private String campo;

    public PaginacaoDTO() {
    }

    public PaginacaoDTO(Integer pagina, Integer totalItens, Integer totalPaginas, String ordem, String campo) {
        this.pagina = pagina;
        this.totalItens = totalItens;
        this.totalPaginas = totalPaginas;
        this.ordem = ordem;
        this.campo = campo;
    }
    

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
   
}
