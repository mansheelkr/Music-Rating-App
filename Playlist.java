import java.util.ArrayList;

public class Playlist {
    private String title;
    private ArrayList<Song> songs;

    public Playlist(String title) {
        this.title = title;
        this.songs = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
    
    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(String songTitle) {
        songs.removeIf(song -> song.getTitle().equalsIgnoreCase(songTitle));
    }
    
    // Method to display songs in playlist
    public void displayPlaylistInfo() {
        System.out.println("Playlist: " + title);
        
        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", "Artist", "Album", "Song", "Genre", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Song song : songs) {
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", 
                song.getArtist().getName(), 
                song.getArtist().getAlbumTitle(song), 
                song.getTitle(),
                song.getGenre(),
                song.getAverageRating() 
            );
        }
    }

}

