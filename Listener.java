import java.util.ArrayList;

public class Listener extends User {
    private ArrayList<Playlist> playlists;

    public Listener(String name) {
        super(name);
        this.playlists = new ArrayList<>();
    }
    
    // Browse all songs, albums, and artists
    public void browseCatalog() {
        System.out.println("Song | Album | Artist");
        System.out.println("-----------------------");

        // Display all songs with their album and artist information
        for (Song song : Artist.getAllSongs()) {
            // Display the song, album, and artist
            System.out.println(song.getTitle() + " | " + song.artist.albums.get(0).getTitle() + " | " + song.artist.name);
        }
    }

    // Create a new playlist
    public Playlist createPlaylist(String title) {
        Playlist playlist = new Playlist(title);
        playlists.add(playlist);
        return playlist;
    }

    // Select playlist, needs to be implemented
        // Add song to playlist, needs to be implemented
        // Remove song from playlist, needs to be implemented
        // Edit playlist, needs to be implemented

    // Rate a song
    public void rateSong(Song song, int rating) {
        song.addRating(rating);
    }

    // Rate an album
    public void rateAlbum(Album album, int rating) {
        album.addRating(rating);
    }

    // View song ratings
    @Override
    public void viewSongRatings() {
        for (Playlist playlist : playlists) {
            for (Song song : playlist.getSongs()) {
                System.out.println("Song: " + song.getTitle() + ", Rating: " + song.getAverageRating());
            }
        }
    }

    // View album ratings, needs to be implemented
}