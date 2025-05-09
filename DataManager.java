import java.util.ArrayList;

public class DataManager {

    private static DataManager instance;
    private ArrayList<Artist> artists;
    private ArrayList<Listener> listeners;

    private DataManager() {
        artists = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Get all artists
    public ArrayList<Artist> getAllArtists() {
        return artists;
    }

    // Get all listeners
    public ArrayList<Listener> getAllListeners() {
        return listeners;
    }

    // Method to add an artist
    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    // Method to add a listener
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    // Get an artist by name
    public Artist getArtistByName(String name) {
        for (Artist artist : artists) {
            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;
            }
        }
        return null;
    }

    // Get a listener by username
    public Listener getListenerByUsername(String username) {
        for (Listener listener : listeners) {
            if (listener.getUsername().equalsIgnoreCase(username)) {
                return listener;
            }
        }
        return null;
    }
    
}
