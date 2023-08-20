package br.com.tech4music.music.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4music.music.Model.Music;
import br.com.tech4music.music.Repository.MusicRepository;
import br.com.tech4music.music.Shared.MusicDto;

@Service

public class MusicServiceImpl implements MusicService{
@Autowired
private MusicRepository repositorio;
    @Override
    public List<MusicDto> obterTodas() {
       return repositorio.findAll().stream().map(p -> new MusicDto(p.getId(),
       p.getTitulo(), p.getArtista(), p.getAlbum(), p.getGenero(), p.getAnoLancamento(), p.getCompositor())).toList();
    }

    @Override
    public Optional<MusicDto> obterMusicPorId(String id) {
       Optional<Music> music = repositorio.findById(id);
        
       if (music.isPresent()) {
        return Optional.of(new MusicDto(music.get().getId(),
        music.get().getTitulo(),music.get().getArtista(),
        music.get().getAlbum(),music.get().getAnoLancamento(), music.get().getCompositor(), music.get().getGenero()));
       } else {
        return Optional.empty();
       }
    }

    @Override
    public MusicDto cadastrarMusic(MusicDto musicDto) {
       Music n = new Music(musicDto);
       repositorio.save(n);
       return new MusicDto(n.getId(),n.getTitulo(),
       n.getArtista(),n.getAlbum(),n.getGenero(),
       n.getAnoLancamento(),n.getCompositor());
    }

    @Override
    public Optional<MusicDto> atualizarMusicPorId(String id, MusicDto dto) {
        Optional<Music>music = repositorio.findById(id);
       if (music.isPresent()) {
        Music musicAtualizar = new Music(dto);
        musicAtualizar.setId(id);
        repositorio.save(musicAtualizar);
        return Optional.of(new MusicDto(musicAtualizar.getId(),
        musicAtualizar.getTitulo(),musicAtualizar.getArtista(),
        musicAtualizar.getAlbum(),musicAtualizar.getGenero(),
        musicAtualizar.getAnoLancamento(),musicAtualizar.getCompositor()));
       } else {
        return Optional.empty();
       }
    }

    @Override
    public void deletarPorId(String id) {
       repositorio.deleteById(id);
    }
    
}
