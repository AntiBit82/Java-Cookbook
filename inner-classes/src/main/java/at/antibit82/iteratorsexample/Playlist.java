package at.antibit82.iteratorsexample;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Nested Iterator Pattern example with inner classes.
 * All the iterators have access to the outer Playlist class members.
 */
public class Playlist {
    public record Song(String name, String artist, int duration) {}

    private ArrayList<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public void printSongs() {
        songs.forEach(System.out::println);
    }

    public class ShuffleIterator implements Iterator<Song> {
        private int index = 0;
        private ArrayList<Integer> shuffledIndices = new ArrayList<>();

        private ShuffleIterator() {
            for (int i = 0; i < songs.size(); i++) {
                shuffledIndices.add(i);
            }
            java.util.Collections.shuffle(shuffledIndices);
        }

        @Override
        public boolean hasNext() {
            return index < shuffledIndices.size();
        }

        @Override
        public Song next() {
            return songs.get(shuffledIndices.get(index++));
        }
    }

    public class ReverseIterator implements Iterator<Song> {
        private int index;

        private ReverseIterator() {
            this.index = songs.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public Song next() {
            return songs.get(index--);
        }
    }

    public Iterator<Song> getShuffleIterator() {
        return new ShuffleIterator();
    }

    public Iterator<Song> getReverseIterator() {
        return new ReverseIterator();
    }
}
