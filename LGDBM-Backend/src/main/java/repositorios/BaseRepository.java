/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import entidades.BaseEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author guilherme.moura
 */

@NoRepositoryBean
@Transactional
public interface BaseRepository <T extends BaseEntity> extends JpaRepository<T, Long>{
    
}
