package br.com.tech4music.music.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4music.music.Model.Music;
import br.com.tech4music.music.Repository.MusicRepository;
import br.com.tech4music.music.Service.MusicService;
import br.com.tech4music.music.Shared.MusicDto;

@RestController
@RequestMapping("music")
//a criação de um CRUD é um elemento fundamental no BackEnd. Cada verbo da classe Controller
//faz uma determinada tarefa. Seja Created, Read, Update ou Delete
public class MusicController {
    @Autowired//serve para indicar que um um atributo ou constritor sera injetado por uma instancia
              //gerenciado pelo Spring

    private MusicService service;//utilizamos a classe Service para implementar nosso codigo deixando-o mais profissional
                                //e permitindo entao, que a classe Controller passe a utilizar a classe Service ao inves da Repository

    @GetMapping//coleta todos os resultados da "play List cadastrada"
    public ResponseEntity<List<MusicDto>> obterTodas(){//controla não apenas as respostas às requisições do programa
        return new ResponseEntity<>(service.obterTodas(), HttpStatus.OK);//como tambem seu conteudo da resposta e seu Status HTTP
    }

    @GetMapping("/{id}")//pegar um determinado dado pelo seu Id, de modo a não termos erros na coleta de um item não especificado
    public ResponseEntity<MusicDto> obterMusicPorId(@PathVariable String id){
       
       if (service.obterMusicPorId(id).isPresent()) {
        return new ResponseEntity<>(service.obterMusicPorId(id).get(),HttpStatus.OK);
       } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    @PostMapping//PostMapping é o que corresponse ao Created, servindo para adicionar elementos em nosso CRUD
    public ResponseEntity<MusicDto> cadastrarMusic(@RequestBody MusicDto music){
        return new ResponseEntity<>(service.cadastrarMusic(music), HttpStatus.CREATED);//o retorno desde status indica que o elemento foi cadastrado com sucesso

    }
    @DeleteMapping("/{id}")//excluir um determinado dado por Id
    public ResponseEntity<Void> deletarPorId(@PathVariable String id){
        service.deletarPorId(id);//coleta o dado Id para atraces do repositorio para ser excluido
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//apenas retorna a resposta de que o ite foi excluido
    }
    @PutMapping("/{id}")//PutMapping é o verbo que corresponse ao Update em nosso CRUD
    private ResponseEntity<MusicDto> atualizarMusicPorId(@PathVariable String id,
                                                     @RequestBody MusicDto music){
        Optional<MusicDto> musicAtualizar = service.atualizarMusicPorId(id, music);
        if (musicAtualizar.isPresent()) {
            return new ResponseEntity<>(musicAtualizar.get(),HttpStatus.OK);  

        }      
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);                      
    }


}
