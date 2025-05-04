import java.util.ArrayList;

public class Playlist {
    private String title;
    private ArrayList<Song> songs;

    public Playlist(String title) {
        this.title = title;
        this.songs = new ArrayList<>();
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
}
