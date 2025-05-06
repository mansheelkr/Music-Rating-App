import java.util.ArrayList;
import java.util.Scanner;

public class Artist {
    private String name;
    private ArrayList<Album> albums;

    // Static list to store all artists
    private static ArrayList<Artist> allArtists = new ArrayList<>();

    public Artist(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
        allArtists.add(this); // Add the current artist to the global list
    }

    public String getName() {
        return name;
    }
    
    // Method to return the album title that contains the song
    public String getAlbumTitle(Song song) {
        for (Album album : albums) {
            if (album.getSongs().contains(song)) {
                return album.getTitle(); // Return the title of the album that contains the song
            }
        }
        return "Unknown Album"; // If no album contains the song, return this
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void createAlbum(String title) {
        // Create an album
        Album album = new Album(title, this);
        albums.add(album);

        // Ask for songs and add them to the album
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name, and genre of the songs?");
        while (true) {
            System.out.print("Enter song title (or 'done' to finish): ");
            String songTitle = scanner.nextLine();
            if (songTitle.equalsIgnoreCase("done")) break;

            System.out.print("Enter genre for song " + songTitle + ": ");
            String genre = scanner.nextLine();

            // Create and add the song to the album
            Song song = new Song(songTitle, genre, this);
            album.addSong(song);
        }

        // After adding songs, display the album
        displayArtistCatalog(); // Display the updated catalog
    }

    // Method to display the artist's catalog
    public void displayArtistCatalog() {
        if (albums.isEmpty()) {
            System.out.println(name + " has no albums yet.");
            return;
        }

        System.out.println("Artist: " + name);
        System.out.println("Albums:");

        // Display information for each album
        for (Album album : albums) {
            System.out.println("Album: " + album.getTitle());
            
            // Display all songs in the album
            if (album.getSongs().isEmpty()) {
                System.out.println("  No songs in this album.");
            } else {
                for (Song song : album.getSongs()) {
                    System.out.println("  Song: " + song.getTitle() + " | Genre: " + song.getGenre() + " | Rating: " + song.getAverageRating());
                }
            }
        }
    }
    
    // Method to search the catalog by album or song
    public void searchMusicCatalog(String query) {
        boolean found = false;

        // Check for album match first
        for (Album album : albums) {
            if (album.getTitle().equalsIgnoreCase(query)) {
                System.out.println("Album found: " + album.getTitle());
                for (Song song : album.getSongs()) {
                    System.out.println("  Song: " + song.getTitle() + " | Genre: " + song.getGenre() + " | Rating: N/A");
                }
                found = true;
                break;  // No need to continue searching for albums if one is found
            }
        }

        // If no album match, check for song match
        if (!found) {
            for (Album album : albums) {
                for (Song song : album.getSongs()) {
                    if (song.getTitle().equalsIgnoreCase(query)) {
                        System.out.println("Song found: " + song.getTitle());
                        System.out.println("Artist: " + name + " | Album: " + album.getTitle() + " | Genre: " + song.getGenre() + " | Rating: N/A");
                        found = true;
                        break;  // Exit the loop once the song is found
                    }
                }
                if (found) break; // Exit the outer loop if the song is found
            }
        }

        if (!found) {
            System.out.println("No matching album or song found for: " + query);
        }
    }
    
    // Method to view an album and allow edits
    public void viewAlbum() {
        Scanner scanner = new Scanner(System.in);
        // Display available albums
        System.out.println("Which album would you like to view?");
        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + ") " + albums.get(i).getTitle());
        }

        int albumChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        if (albumChoice < 1 || albumChoice > albums.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Album selectedAlbum = albums.get(albumChoice - 1);
        System.out.println("You are viewing the album: " + selectedAlbum.getTitle());

        // Display the songs in the album
        selectedAlbum.displayAlbumInfo();

        // Allow the artist to edit the album
        System.out.println("Do you want to edit this album? (yes/no)");
        String editChoice = scanner.nextLine().toLowerCase();

        if (editChoice.equals("yes")) {
            System.out.println("What would you like to do?");
            System.out.println("a) Edit album name");
            System.out.println("b) Add a song to the album");
            System.out.println("c) Remove a song from the album");
            System.out.println("d) Edit a song on the album");

            String option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "a":
                    System.out.print("Enter the new album name: ");
                    String newAlbumName = scanner.nextLine();
                    selectedAlbum.setTitle(newAlbumName);  // Update album name
                    break;
                case "b":
                    System.out.print("Enter the song name: ");
                    String newSongTitle = scanner.nextLine();
                    System.out.print("Enter the genre for the song: ");
                    String newSongGenre = scanner.nextLine();
                    selectedAlbum.addSong(new Song(newSongTitle, newSongGenre, this));  // Add new song
                    break;
                case "c":
                    System.out.print("Enter the song name to remove: ");
                    String songToRemove = scanner.nextLine();
                    selectedAlbum.removeSong(songToRemove);  // Remove song from album
                    break;
                case "d":
                    System.out.print("Enter the song name to edit: ");
                    String songToEdit = scanner.nextLine();
                    Song song = selectedAlbum.getSongByTitle(songToEdit);
                    if (song != null) {
                        System.out.print("Enter the new song name: ");
                        String newSongName = scanner.nextLine();
                        System.out.print("Enter the new genre: ");
                        String newGenre = scanner.nextLine();
                        song.setTitle(newSongName);
                        song.setGenre(newGenre);  // Edit song details
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

            // After editing, display updated album info
            selectedAlbum.displayAlbumInfo();
        }
    }

    // Static method to get all artists
    public static ArrayList<Artist> getAllArtists() {
        return allArtists;
    }
}
