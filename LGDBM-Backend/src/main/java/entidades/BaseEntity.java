/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author guilherme.moura
 */
@MappedSuperclass
public abstract class BaseEntity implements PojoBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long Id;
    
    @Column(columnDefinition = "boolean DEFAULT false", nullable = true)
    public boolean excluido;
    
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    public Date insercao;
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date exclusao;
    
    @UpdateTimestamp
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date modificao;

    public BaseEntity(long Id, boolean excluido, Date insercao, Date exclusao, Date modificao) {
        this.Id = Id;
        this.excluido = excluido;
        this.insercao = insercao;
        this.exclusao = exclusao;
        this.modificao = modificao;
    }

    public BaseEntity() {
    }
    
    public Long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public Date getInsercao() {
        return insercao;
    }

    public void setInsercao(Date insercao) {
        this.insercao = insercao;
    }

    public Date getExclusao() {
        return exclusao;
    }

    public void setExclusao(Date exclusao) {
        this.exclusao = exclusao;
    }

    public Date getModificao() {
        return modificao;
    }

    public void setModificao(Date modificao) {
        this.modificao = modificao;
    }
    
    
    
    
}
