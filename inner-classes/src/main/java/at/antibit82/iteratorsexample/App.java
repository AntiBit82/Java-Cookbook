package at.antibit82.iteratorsexample;

import java.util.Iterator;

/**
 * Nested Iterator Pattern example with inner classes.
 * All the iterators have access to the outer Playlist class members.
 */
public class App {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong(new Playlist.Song("Renewable energy container", "Mellatica", 90000));
        playlist.addSong(new Playlist.Song("Song 3", "Blursed", 180));
        playlist.addSong(new Playlist.Song("Heyyyy Makkaroni", "2 old dudes", 240));
        playlist.addSong(new Playlist.Song("I Got Flattened By A Pig Farmer", "Stagefright", 270));
        playlist.addSong(new Playlist.Song("Doginabag", "Catpunx", 280));
        playlist.addSong(new Playlist.Song("The Ocean (Is Bleeding Salt)", "PopKoRn", 300));

        System.out.println("\nOriginal Playlist:");
        playlist.printSongs();

        Iterator<Playlist.Song> reverser = playlist.getReverseIterator();
        System.out.println("\nReversed Playlist:");
        while (reverser.hasNext()) {
            System.out.println(reverser.next());
        }

        Iterator<Playlist.Song> shuffler = playlist.getShuffleIterator();
        System.out.println("\nShuffled Playlist:");
        while (shuffler.hasNext()) {
            System.out.println(shuffler.next());
        }
    }
}
