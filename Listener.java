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

    // Method to browse all music
    public void browseMusic() {
        System.out.println();
        for (Artist artist : Artist.getAllArtists()) {
            artist.displayArtistCatalog();
        }
    }

    // Method to search music by artist, album, or song
    public void searchMusic(String query) {
        boolean found = false;
        
        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", "Artist", "Album", "Song", "Genre", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Artist artist : Artist.getAllArtists()) {
            for (Album album : artist.getAlbums()) {
                for (Song song : album.getSongs()) {
                    if (song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        album.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        artist.getName().toLowerCase().contains(query.toLowerCase())) {

                        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", 
                            artist.getName(), 
                            album.getTitle(), 
                            song.getTitle(),  
                            song.getGenre(),  
                            song.getAverageRating() 
                        );
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("No matching artist, album, or song found for: " + query);
        }
    }

    
    // Method to rate a song
    public void rateSong(String songTitle) {
        Scanner scanner = new Scanner(System.in);

        // Search for song
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

        if (songToRate == null) {
            System.out.println("Song not found.");
            return;
        }

        System.out.println();
        System.out.println("Current Song Information:");
        songToRate.displaySongInfo();

        System.out.println();
        System.out.print("What would you like to rate this song (1-10)? ");
        float rating = scanner.nextFloat();
        scanner.nextLine(); 

        if (rating < 1 || rating > 10) {
            System.out.println("Invalid rating. Please enter a rating between 1 and 10.");
            return;
        }

        songToRate.addRating(rating);

        System.out.println();
        System.out.println("Updated Song Information:");
        songToRate.displaySongInfo();
    }


    // Method to create a new playlist
    public void createPlaylist(String playlistName) {

        Scanner scanner = new Scanner(System.in);

        Playlist newPlaylist = new Playlist(playlistName);
        playlists.add(newPlaylist);

        System.out.println("Current songs: ");

        browseMusic();


        System.out.println("What songs would you like to add to this playlist? (Enter 'done' when finished)");

        while (true) {
            System.out.print("Enter song title: ");
            String songTitle = scanner.nextLine();

            if (songTitle.equalsIgnoreCase("done")) {
                break;
            }

            // Search for the song 
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
                System.out.println();
            } else {
                System.out.println("Song not found in the catalog. Please try again.");
                System.out.println();
            }
        }

        System.out.println();
        newPlaylist.displayPlaylistInfo();
    }


    // Method to view a playlist
    public void viewPlaylists() {
        Scanner scanner = new Scanner(System.in);

        if (playlists.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        System.out.println();
        System.out.println("Your Playlists:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ") " + playlists.get(i).getTitle());
        }

        System.out.println();
        System.out.print("Which playlist would you like to view? Enter the number: ");
        int playlistIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (playlistIndex < 1 || playlistIndex > playlists.size()) {
            System.out.println("Invalid playlist number.");
            return;
        }

        Playlist selectedPlaylist = playlists.get(playlistIndex - 1);

        System.out.println();
        selectedPlaylist.displayPlaylistInfo();

        // Ask if the listener wants to edit playlist
        System.out.println();
        System.out.println("Do you want to edit this playlist? (yes/no)");
        String editChoice = scanner.nextLine().toLowerCase();

        if (editChoice.equals("yes")) {
        	System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("a) Edit playlist name");
            System.out.println("b) Add a song to the playlist");
            System.out.println("c) Remove a song from the playlist");

            String option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "a":
                	System.out.println();
                    System.out.print("Enter the new playlist name: ");
                    String newName = scanner.nextLine();
                    selectedPlaylist.setTitle(newName); 
                    break;
                case "b":
                    System.out.println();
                    // Loop to add multiple songs
                    while (true) {
                        System.out.print("Enter the song name to add (or type 'done' to finish): ");
                        String newSongTitle = scanner.nextLine();
                        if (newSongTitle.equalsIgnoreCase("done")) {
                            break;  
                        }
                        Song songToAdd = findSongByTitle(newSongTitle);
                        if (songToAdd != null) {
                            selectedPlaylist.addSong(songToAdd); 
                            System.out.println("Song added: " + newSongTitle);
                            System.out.println();
                        } else {
                            System.out.println("Song not found.");
                        }
                    }
                    break;
                case "c":
                    System.out.println();
                    // Loop to remove multiple songs
                    while (true) {
                        System.out.print("Enter the song name to remove (or type 'done' to finish): ");
                        String songToRemove = scanner.nextLine();
                        if (songToRemove.equalsIgnoreCase("done")) {
                            break;  
                        }
                        selectedPlaylist.removeSong(songToRemove); 
                        System.out.println("Song removed: " + songToRemove);
                        System.out.println();
                    }
                    break;
                default:
                	System.out.println();
                    System.out.println("Invalid option.");
                    break;
            }

            System.out.println();
            selectedPlaylist.displayPlaylistInfo();
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
        return null; 
    }
    
}
