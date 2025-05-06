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

    public void displayPlaylistInfo() {
        System.out.println("Playlist: " + title);
        for (Song song : songs) {
            song.displaySongInfo();
        }
    }
}

