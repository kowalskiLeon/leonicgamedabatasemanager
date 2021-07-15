/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author guilherme.moura
 */
public interface PojoBase extends Serializable, Cloneable {

    public static final String DB = "lgdbm";

    public Long getId();

    public void setId(Long id);

    public Boolean getExclusaoLogica();

    public void setExclusaoLogica(Boolean ativo);

    public Date getInclusao();

    public void setInclusao(Date inclusao);

    public Date getModificacao();

    public void setModificacao(Date modificacao);

    public Date getExclusao();

    public void setExclusao(Date exclusao);

}
