package hu.nye.progkor.musiccatalog.web.controller;

import hu.nye.progkor.musiccatalog.data.model.Song;
import hu.nye.progkor.musiccatalog.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/song")
public class MusicCatalogRestController {

    private final SongService songService;

    @Autowired
    public MusicCatalogRestController(SongService songService) {
        this.songService = songService;
    }

    /**
     * Returns a song with the given id.
     *
     * @param id the id of the song to retrieve
     * @return the song object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> song = songService.retrieveSongById(id);
        return song.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Returns a list of all the songs in the catalog.
     *
     * @return the list of songs
     */
    @GetMapping
    public List<Song> getAllSongs() {
        return songService.retrieveAllSongs();
    }

    /**
     * Creates a new song and returns it.
     *
     * @param song the song object to create
     * @return the created song object
     */
    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.createSong(song);
    }

    /**
     * Updates an existing song and returns it.
     *
     * @param song the song object to update
     * @return the updated song object
     */
    @PutMapping
    public Song updateSong(@RequestBody Song song) {
        return songService.updateSong(song);
    }

    /**
     * Deletes a song by its id.
     *
     * @param id the id of the song to delete
     */
    @DeleteMapping("/{id}")
    public void deleteSongById(@PathVariable Long id) {
        songService.deleteSongById(id);
    }
}
