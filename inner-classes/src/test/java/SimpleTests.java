import at.antibit82.iteratorsexample.Playlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTests {

    static Playlist playlist = new Playlist();

    @BeforeAll
    static void setup() {
        playlist.addSong(new Playlist.Song("Song A", "Artist A", 200));
        playlist.addSong(new Playlist.Song("Song B", "Artist B", 250));
        playlist.addSong(new Playlist.Song("Song C", "Artist C", 300));
    }

    @Test
    public void test_reverse_iterator() {
        Iterator<Playlist.Song> rit = playlist.getReverseIterator();

        for(int i=playlist.getSongs().size()-1; i>=0; i--) {
            assertEquals(playlist.getSongs().get(i), rit.next());
        }
        assertFalse(rit.hasNext());
    }

    @Test
    public void test_shuffle_iterator() {
        Set<Playlist.Song> playedSongs = new HashSet<>(playlist.getSongs());
        Iterator<Playlist.Song> sit = playlist.getShuffleIterator();

        while(sit.hasNext()) {
            Playlist.Song song = sit.next();
            Assertions.assertTrue(playedSongs.remove(song));
        }
        assertTrue(playedSongs.isEmpty());
    }
}
