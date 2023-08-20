package br.com.tech4music.music.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4music.music.Model.Music;

public interface MusicRepository extends MongoRepository<Music,String>{
    
}
