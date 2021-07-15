/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicesimpl;

import entidades.BaseEntity;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repositorios.BaseRepository;
import services.BaseService;

/**
 *
 * @author guilherme.moura
 */

@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>  {
    protected BaseRepository crudRepository;

    public BaseServiceImpl(BaseRepository<T> crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Iterable<T> listAllObjetos() {
        return crudRepository.findAll();
    }

    @Override
    public Page findAll(T objeto, Pageable pageable) {
        objeto.setExcluido(false);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues().withIgnoreCase();

        Example<T> example = Example.of(objeto, matcher);

        return crudRepository.findAll(example, pageable);
    }
    @Override
    public Page findAllWithoutRestriction(T objeto, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues().withIgnoreCase();

        Example<T> example = Example.of(objeto, matcher);

        return crudRepository.findAll(example, pageable);
    }

    @Override
    public Page findAll(T objeto) {
        return this.findAll(objeto, PageRequest.of(0, 1000, Sort.by(Sort.Direction.ASC, "id")));
    }

    @Override
    public T getObjetoById(Long id) {
        return (T) crudRepository.findById(id).orElse(null);
    }

    @Override
    public T saveObject(T obj, boolean updateVersion) {
        if (obj.getExclusao()== null) {
            obj.setExcluido(false);
        }
        if (obj.getInsercao()== null) {
            obj.setInsercao(new Date());
        }
        obj.setModificao(new Date());
        return (T) crudRepository.save(obj);
    }

    @Override
    public T saveObjeto(T obj) {
        return saveObject(obj, true);
    }

    @Override
    public T deleteObjeto(Long id) {
        BaseEntity pojo = (BaseEntity) crudRepository.findById(id).orElse(null);
        pojo.setExcluido(true);
        pojo.setModificao(new Date());
        pojo.setExclusao(new Date());
        return (T) crudRepository.save(pojo);
    }

    public JpaRepository getRepository() {
        return this.crudRepository;
    }

    public <U extends BaseEntity> U simplificarObjeto(U obj) {
        if (obj != null) {
            try {
                U aux = (U) obj.getClass().newInstance();
                aux.setId(obj.getId());
                return aux;
            } catch (Exception ex) {
                Logger.getLogger(BaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    
    public ResponseEntity formatarResposta(Integer status, String respostaGson, String mensagem, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", token);
        headers.add("mensagem", mensagem);
        headers.add("content-type", "application/json; charset=utf-8");

        HttpStatus erro = HttpStatus.OK;

        switch (status) {
            case 100:
                erro = HttpStatus.CONTINUE;
                break;
            case 101:
                erro = HttpStatus.SWITCHING_PROTOCOLS;
                break;
            case 200:
                erro = HttpStatus.OK;
                break;
            case 201:
                erro = HttpStatus.CREATED;
                break;
            case 202:
                erro = HttpStatus.ACCEPTED;
                break;
            case 203:
                erro = HttpStatus.NON_AUTHORITATIVE_INFORMATION;
                break;
            case 204:
                erro = HttpStatus.NO_CONTENT;
                break;
            case 205:
                erro = HttpStatus.RESET_CONTENT;
                break;
            case 206:
                erro = HttpStatus.PARTIAL_CONTENT;
                break;
            case 300:
                erro = HttpStatus.MULTIPLE_CHOICES;
                break;
            case 301:
                erro = HttpStatus.MOVED_PERMANENTLY;
                break;
            case 302:
                erro = HttpStatus.FOUND;
                break;
            case 303:
                erro = HttpStatus.SEE_OTHER;
                break;
            case 304:
                erro = HttpStatus.NOT_MODIFIED;
                break;
            case 305:
                erro = HttpStatus.USE_PROXY;
                break;
            case 306:
                erro = HttpStatus.UPGRADE_REQUIRED;
                break;
            case 307:
                erro = HttpStatus.TEMPORARY_REDIRECT;
                break;
            case 400:
                erro = HttpStatus.BAD_REQUEST;
                break;
            case 401:
                erro = HttpStatus.UNAUTHORIZED;
                break;
            case 402:
                erro = HttpStatus.PAYMENT_REQUIRED;
                break;
            case 403:
                erro = HttpStatus.FORBIDDEN;
                break;
            case 404:
                erro = HttpStatus.NOT_FOUND;
                break;
            case 405:
                erro = HttpStatus.METHOD_NOT_ALLOWED;
                break;
            case 406:
                erro = HttpStatus.NOT_ACCEPTABLE;
                break;
            case 407:
                erro = HttpStatus.PROXY_AUTHENTICATION_REQUIRED;
                break;
            case 408:
                erro = HttpStatus.REQUEST_TIMEOUT;
                break;
            case 409:
                erro = HttpStatus.CONFLICT;
                break;
            case 410:
                erro = HttpStatus.GONE;
                break;
            case 411:
                erro = HttpStatus.LENGTH_REQUIRED;
                break;
            case 412:
                erro = HttpStatus.PRECONDITION_FAILED;
                break;
            case 413:
                erro = HttpStatus.REQUEST_ENTITY_TOO_LARGE;
                break;
            case 414:
                erro = HttpStatus.REQUEST_URI_TOO_LONG;
                break;
            case 415:
                erro = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
                break;
            case 417:
                erro = HttpStatus.EXPECTATION_FAILED;
                break;
            case 500:
                erro = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case 501:
                erro = HttpStatus.NOT_IMPLEMENTED;
                break;
            case 502:
                erro = HttpStatus.BAD_GATEWAY;
                break;
            case 503:
                erro = HttpStatus.SERVICE_UNAVAILABLE;
                break;
            case 504:
                erro = HttpStatus.GATEWAY_TIMEOUT;
                break;
            case 505:
                erro = HttpStatus.HTTP_VERSION_NOT_SUPPORTED;
                break;
        }

        return new ResponseEntity(respostaGson, headers, erro);
    }
}
