import java.util.ArrayList;

public class Artist extends User {
    public ArrayList<Album> albums;
    public static ArrayList<Song> allSongs = new ArrayList<>();

    public Artist(String name) {
        super(name);
        this.albums = new ArrayList<>();
    }

    // Create an album
    public Album createAlbum(String title, String description) {
        Album album = new Album(title, description, this);
        albums.add(album);
        return album;
    }

    // Upload songs to an album
    public Song uploadSong(Album album, String title, int duration, String genre) {
        Song song = new Song(title, duration, genre, this);
        album.addSong(song);
        return song;
    }
    
    // Edit song details
    public void editSongDetails(Song song, String newTitle, String newGenre) {
        song.setTitle(newTitle);
        song.setGenre(newGenre);
        System.out.println("Song details updated: " + song.getTitle());
    }
    
    // Edit album details
    public void editAlbumDetails(Album album, String newTitle, String newDescription) {
        album.setTitle(newTitle);
        album.setDescription(newDescription);
        System.out.println("Album details updated: " + album.getTitle());
    }
    
    // Delete a song from an album and the global song list
    public void deleteSong(Album album, Song song) {
        if (album.getSongs().contains(song)) {
            album.removeSong(song);  // Remove from the album
            allSongs.remove(song);   // Remove from the global list
            System.out.println("Song " + song.getTitle() + " deleted from album " + album.getTitle());
        } 
        else {
            System.out.println("Song not found in album.");
        }
    }
    
    // Delete an album and all its songs from the global list
    public void deleteAlbum(Album album) {
        if (albums.contains(album)) {
            // Remove all songs from the global song list
            for (Song song : album.getSongs()) {
                allSongs.remove(song);
            }
            albums.remove(album);
            System.out.println("Album " + album.getTitle() + " deleted.");
        } 
        else {
            System.out.println("Album not found.");
        }
    }

    //  View song ratings
    @Override
    public void viewSongRatings() {
        for (Album album : albums) {
            for (Song song : album.getSongs()) {
                System.out.println("Song: " + song.getTitle() + ", Rating: " + song.getAverageRating());
            }
            System.out.println("Album: " + album.title + ", Rating: " + album.getAverageRating());
        }
    }

    // View album ratings, needs to be implemented

    
    public static ArrayList<Song> getAllSongs() {
        return allSongs;
    }
}
