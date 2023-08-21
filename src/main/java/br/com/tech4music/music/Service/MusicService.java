package br.com.tech4music.music.Service;

import java.util.List;
import java.util.Optional;

import br.com.tech4music.music.Model.Music;
import br.com.tech4music.music.Shared.MusicDto;

public interface MusicService {
    List<MusicDto> obterTodas();
    Optional<MusicDto> obterMusicPorId(String id);
    MusicDto cadastrarMusic(MusicDto music);
    Optional <MusicDto> atualizarMusicPorId(String id, MusicDto music);    
    void deletarPorId(String id);
    
}
