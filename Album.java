import java.util.ArrayList;

public class Album {
    private String title;
    private Artist artist;
    private ArrayList<Song> songs;

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    // Method to remove songs from an album
    public void removeSong(String songTitle) {
        Song songToRemove = null;
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(songTitle)) {
                songToRemove = song;
                break;
            }
        }
        if (songToRemove != null) {
            songs.remove(songToRemove);
            System.out.println("Song '" + songTitle + "' removed from the album.");
        } 
        else {
            System.out.println("Song not found.");
        }
    }

    // Method to get a song by its title
    public Song getSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null; 
    }
    
    // Method to display album info
    public void displayAlbumInfo() {
        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", "Artist", "Album", "Song", "Genre", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Song song : songs) {
            System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", 
                artist.getName(), 
                this.getTitle(), 
                song.getTitle(), 
                song.getGenre(), 
                song.getAverageRating() 
            );
        }
    }

}
