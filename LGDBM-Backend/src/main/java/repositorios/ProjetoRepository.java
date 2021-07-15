/*
 * To change this license header, choose License Headers in Projeto Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import entidades.Projeto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author guilherme.moura
 */
@Repository
@Transactional
public interface ProjetoRepository extends BaseRepository<Projeto> {

}
