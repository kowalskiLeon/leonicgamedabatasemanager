/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import entidades.BaseEntity;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.BaseService;

/**
 *
 * @author guilherme.moura
 */
public class BaseController<T extends BaseEntity> {
    
    protected BaseService<T> baseService;
    protected Class classe;
    protected Class<T[]> classes;

    public BaseController(BaseService<T> service, Class classe, Class<T[]> classes) {
        this.baseService = service;
        this.classe = classe;
        this.classes = classes;
    }
    
    @RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity list(Model model, @RequestHeader HttpHeaders httpHeaders) {
        List<T> listaObjetos = (List<T>) baseService.listAllObjetos();

        String json = GsonUtils.getInstanceWithStringDateAdapter().toJson(listaObjetos.toArray(), this.classes);

        return this.formatarResposta(200, json, "Executado com sucesso", null);
    }

    @RequestMapping(value = "/listarPorObjetoPaginado", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity listarPorObjetoPaginado(
            @RequestHeader(value = "filtro") String objJson,
            @RequestHeader(value = "pagination") String pagJson) {

        T obj = (T) getGson().fromJson("{}", classe);
        if (objJson != null && !objJson.isEmpty()) {
            obj = (T) GsonUtils.getInstanceWithStringDateAdapter().fromJson(objJson, classe);
        }

        Pageable paginacao = null;
        if (pagJson != null && !pagJson.isEmpty()) {
            PaginacaoDTO pg = GsonUtils.getInstanceWithStringDateAdapter().fromJson(pagJson, PaginacaoDTO.class);
            Sort sort = pg.getOrdem().equalsIgnoreCase("asc") ? Sort.by(pg.getCampo()).ascending() : Sort.by(pg.getCampo()).descending();
            paginacao = PageRequest.of(pg.getPagina(), pg.getTotalItens(), sort);
        } else {
            paginacao = PageRequest.of(0, 1000, Sort.by("id").ascending());
        }
        Page lista = this.baseService.findAll(obj, paginacao);
        RetornoPaginadoDTO ret = new RetornoPaginadoDTO(lista.getContent(), lista.getTotalElements(), lista.getTotalPages(), lista.getNumberOfElements(), lista.getNumber());
        return this.formatarResposta(200, GsonUtils.getInstanceWithStringDateAdapter().toJson(ret, RetornoPaginadoDTO.class),
                "Executado com sucesso", null);
    }

    @RequestMapping(value = "/consultarPorId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity showObjeto(@PathVariable String id, Model model) {
        T objeto = baseService.getObjetoById(Long.parseLong(id));
        return this.formatarResposta(200, GsonUtils.getInstanceWithStringDateAdapter().toJson(objeto, classe),
                "Executado com sucesso", null);
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveObjeto(@RequestBody String json) {
        T obj = (T) GsonUtils.getInstanceWithStringDateAdapter().fromJson(json, classe);
        baseService.saveObjeto(obj);
        HttpHeaders headers = new HttpHeaders();
        headers.add("mensagem", "Salvo com sucesso!");

        return new ResponseEntity(GsonUtils.getInstanceWithStringDateAdapter().toJson(obj, classe), headers,
                HttpStatus.OK);
    }

    // @RequestMapping(value = "/update", method = RequestMethod.PUT, produces =
    // "application/json")
    public ResponseEntity updateObjeto(@RequestBody String json) {
        T objeto = (T) GsonUtils.getInstanceWithStringDateAdapter().fromJson(json, classe);
        baseService.saveObjeto(objeto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("mensagem", "Alterado com sucesso!");

        return new ResponseEntity(criarDtoDefault(objeto), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable String id) {
        T obj = baseService.deleteObjeto(Long.parseLong(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("mensagem", "Removido com sucesso!");
        return new ResponseEntity(GsonUtils.getInstanceWithStringDateAdapter().toJson(obj, classe), headers, HttpStatus.OK);

    }

    public Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement je, java.lang.reflect.Type type, JsonDeserializationContext jdc)
                    throws JsonParseException {
                return new Date(je.getAsJsonPrimitive().getAsLong());
            }
        });
        return gsonBuilder.create();
    }

    public T criarDtoDefault(T objeto) {
        if (objeto != null) {
            objeto.setExcluido(false);
            objeto.setModificao(null);
        }
        return objeto;
    }

    protected Pageable getPageable(String campo, Integer size) {
        Sort sort = Sort.by(campo).descending();
        return PageRequest.of(0, size, sort);
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

    protected String getBaseDir() {
        File pasta = new File("./arquivos");
        if (!pasta.exists() && !pasta.mkdirs()) {
            return null;
        }
        return pasta.getAbsolutePath();
    }

    protected String getBasePdfDir() {
        File pasta = new File(getBaseDir(), "pdf");
        if (!pasta.exists() && !pasta.mkdirs()) {
            return null;
        }
        return pasta.getAbsolutePath();
    }
    
}
