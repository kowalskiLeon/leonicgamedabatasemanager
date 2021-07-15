/*
 * To change this license header, choose License Headers in Projeto Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 *
 * @author guilherme.moura
 */

@Entity
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@Table(name = "projeto", schema = PojoBase.DB)
public class Projeto extends BaseEntity{
    
    public String nome;
    public String autor;
    public Lingua linguaPadrao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Lingua getLinguaPadrao() {
        return linguaPadrao;
    }

    public void setLinguaPadrao(Lingua linguaPadrao) {
        this.linguaPadrao = linguaPadrao;
    }

    public Projeto() {
    }

    public Projeto(String nome, String autor, Lingua linguaPadrao) {
        this.nome = nome;
        this.autor = autor;
        this.linguaPadrao = linguaPadrao;
    }

    @Override
    public void setId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getExclusaoLogica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setExclusaoLogica(Boolean ativo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getInclusao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInclusao(Date inclusao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getModificacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModificacao(Date modificacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
