import java.util.ArrayList;
import java.util.Scanner;

class Listener {
    private String username;
    private ArrayList<Playlist> playlists;

    public Listener(String username) {
        this.username = username;
        this.playlists = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    // Browse all music
    public void browseMusic() {
        System.out.println("Browse Music");
        for (Artist artist : Artist.getAllArtists()) {
            artist.displayArtistCatalog();
        }
    }

    // Search music by artist, album, or song
    public void searchMusic(String query) {
        System.out.println("Search results for: " + query);
        for (Artist artist : Artist.getAllArtists()) {
            for (Album album : artist.getAlbums()) {
                for (Song song : album.getSongs()) {
                    if (song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        album.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        artist.getName().toLowerCase().contains(query.toLowerCase())) {
                        song.displaySongInfo();
                    }
                }
            }
        }
    }
    
    // Method to rate a song
    public void rateSong(String songTitle) {
        Scanner scanner = new Scanner(System.in);

        // Search for the song in all artists' catalogs
        Song songToRate = null;
        Artist artistWithSong = null;
        for (Artist artist : Artist.getAllArtists()) {
            for (Album album : artist.getAlbums()) {
                for (Song song : album.getSongs()) {
                    if (song.getTitle().equalsIgnoreCase(songTitle)) {
                        songToRate = song;
                        artistWithSong = artist;
                        break;
                    }
                }
                if (songToRate != null) break;
            }
            if (songToRate != null) break;
        }

        // If the song was not found
        if (songToRate == null) {
            System.out.println("Song not found.");
            return;
        }

        // Display the song details using displaySongInfo
        System.out.println("Current Song Information:");
        songToRate.displaySongInfo();

        // Ask for the new rating
        System.out.print("What would you like to rate this song (1-10)? ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Validate rating input
        if (rating < 1 || rating > 10) {
            System.out.println("Invalid rating. Please enter a rating between 1 and 10.");
            return;
        }

        // Add the rating to the song
        songToRate.addRating(rating);

        // Show the updated song details using displaySongInfo
        System.out.println("Updated Song Information:");
        songToRate.displaySongInfo();
    }


    // Method to create a new playlist
    public void createPlaylist(String playlistName) {
        Scanner scanner = new Scanner(System.in);

        // Create new playlist
        Playlist newPlaylist = new Playlist(playlistName);
        playlists.add(newPlaylist);

        // Ask for songs to add to the playlist
        System.out.println("What songs would you like to add to this playlist? (Enter 'done' when finished)");

        while (true) {
            System.out.print("Enter song title: ");
            String songTitle = scanner.nextLine();

            if (songTitle.equalsIgnoreCase("done")) {
                break;
            }

            // Search for the song in all artists' catalogs
            Song songToAdd = null;
            for (Artist artist : Artist.getAllArtists()) {
                for (Album album : artist.getAlbums()) {
                    for (Song song : album.getSongs()) {
                        if (song.getTitle().equalsIgnoreCase(songTitle)) {
                            songToAdd = song;
                            break;
                        }
                    }
                    if (songToAdd != null) break;
                }
                if (songToAdd != null) break;
            }

            if (songToAdd != null) {
                newPlaylist.addSong(songToAdd);
                System.out.println("Song added to playlist: " + songToAdd.getTitle());
            } else {
                System.out.println("Song not found in the catalog. Please try again.");
            }
        }

        // After finishing, display the created playlist
        newPlaylist.displayPlaylistInfo();
    }


    // Method to view the playlists
    public void viewPlaylists() {
        Scanner scanner = new Scanner(System.in);

        if (playlists.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        System.out.println("Your Playlists:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ") " + playlists.get(i).getTitle());
        }

        System.out.print("Which playlist would you like to view? ");
        String playlistName = scanner.nextLine();

        Playlist selectedPlaylist = null;
        for (Playlist playlist : playlists) {
            if (playlist.getTitle().equalsIgnoreCase(playlistName)) {
                selectedPlaylist = playlist;
                break;
            }
        }

        if (selectedPlaylist != null) {
            selectedPlaylist.displayPlaylistInfo();

            // Ask if the listener wants to edit the playlist
            System.out.println("Do you want to edit this playlist? (yes/no)");
            String editChoice = scanner.nextLine().toLowerCase();

            if (editChoice.equals("yes")) {
                System.out.println("What would you like to do?");
                System.out.println("a) Edit playlist name");
                System.out.println("b) Add a song to the playlist");
                System.out.println("c) Remove a song from the playlist");

                String option = scanner.nextLine().toLowerCase();

                switch (option) {
                    case "a":
                        System.out.print("Enter the new playlist name: ");
                        String newName = scanner.nextLine();
                        selectedPlaylist.setTitle(newName); // Update playlist name
                        break;
                    case "b":
                        System.out.print("Enter the song name to add: ");
                        String newSongTitle = scanner.nextLine();
                        Song songToAdd = findSongByTitle(newSongTitle);
                        if (songToAdd != null) {
                            selectedPlaylist.addSong(songToAdd); // Add song to playlist
                        } else {
                            System.out.println("Song not found.");
                        }
                        break;
                    case "c":
                        System.out.print("Enter the song name to remove: ");
                        String songToRemove = scanner.nextLine();
                        selectedPlaylist.removeSong(songToRemove); // Remove song from playlist
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }

                // After editing, display updated playlist info
                selectedPlaylist.displayPlaylistInfo();
            }
        } else {
            System.out.println("Playlist not found.");
        }
    }
    
    // Method to find a song by title in the catalog
    private Song findSongByTitle(String songTitle) {
        for (Artist artist : Artist.getAllArtists()) {
            for (Album album : artist.getAlbums()) {
                for (Song song : album.getSongs()) {
                    if (song.getTitle().equalsIgnoreCase(songTitle)) {
                        return song;
                    }
                }
            }
        }
        return null; // Song not found
    }

    
}
