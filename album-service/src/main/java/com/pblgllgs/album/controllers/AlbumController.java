package com.pblgllgs.album.controllers;

import com.pblgllgs.album.entities.AlbumEntity;
import com.pblgllgs.album.services.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/albums")
@RequiredArgsConstructor
@Slf4j
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping()
    public List<AlbumEntity> findAllByUserId(@PathVariable String id) {
        List<AlbumEntity> albums = albumService.findAllAlbumsByUserId(id);
        log.info("Returning " + albums.size() + " albums");
        return albums;
    }

    @PostMapping
    public AlbumEntity saveAlbum(@RequestBody AlbumEntity album) {
        return albumService.save(album);
    }
}