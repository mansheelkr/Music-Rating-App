import java.util.ArrayList;
import java.util.Scanner;

public class Artist {
    private String name;
    private ArrayList<Album> albums;

    // Store all artists
    private static ArrayList<Artist> allArtists = new ArrayList<>();

    public Artist(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
        allArtists.add(this); 
    }

    public String getName() {
        return name;
    }
    
    public static ArrayList<Artist> getAllArtists() {
        return allArtists;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
    
 // Method to return the album title that contains the song
    public String getAlbumTitle(Song song) {
        for (Album album : albums) {
            if (album.getSongs().contains(song)) {
                return album.getTitle(); 
            }
        }
        return "Unknown Album"; 
    }
    
    // Method to create an album
    public void createAlbum(String title) {
        Album album = new Album(title, this);
        albums.add(album);

        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name, and genre of the songs?");
        while (true) {
            System.out.print("Enter song title (or 'done' to finish adding songs): ");
            String songTitle = scanner.nextLine();
            if (songTitle.equalsIgnoreCase("done")) break;

            System.out.print("Enter genre for song " + songTitle + ": ");
            String genre = scanner.nextLine();
            System.out.println();

            Song song = new Song(songTitle, genre, this);
            album.addSong(song);
        }

        System.out.println();
        album.displayAlbumInfo();
    }

    // Method to display the artist's music catalog
    public void displayArtistCatalog() {
        if (albums.isEmpty()) {
            System.out.println("No albums found.");
            return;
        }

        System.out.println();
        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", "Artist", "Album", "Song", "Genre", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Album album : albums) {
            for (Song song : album.getSongs()) {
                System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", 
                    name, 
                    album.getTitle(),
                    song.getTitle(), 
                    song.getGenre(), 
                    song.getAverageRating()
                );
            }
        }
    }
    
    // Method to search the catalog by album or song
    public void searchMusicCatalog(String query) {
        boolean found = false;

        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", "Artist", "Album", "Song", "Genre", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Album album : albums) {
            if (album.getTitle().equalsIgnoreCase(query)) {
                for (Song song : album.getSongs()) {
                    System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", 
                        name,
                        album.getTitle(), 
                        song.getTitle(), 
                        song.getGenre(), 
                        song.getAverageRating() 
                    );
                }
                found = true;
                break;  
            }
        }

        // If no album match, check for song match
        if (!found) {
            for (Album album : albums) {
                for (Song song : album.getSongs()) {
                    if (song.getTitle().equalsIgnoreCase(query)) {        
                        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", 
                            name, 
                            album.getTitle(),
                            song.getTitle(),
                            song.getGenre(), 
                            song.getAverageRating() 
                        );
                        found = true;
                        break;  
                    }
                }
                if (found) break;
            }
        }

        if (!found) {
        	System.out.println();
            System.out.println("No matching album or song found for: " + query);
        }
    }
    
    // Method to view an album and edit album
    public void viewAlbum() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println();
        System.out.println("Which album would you like to view?");
        if (albums.size() == 0) {
            System.out.println("No albums available!");
            return;
        }
        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + ") " + albums.get(i).getTitle());
        }

        int albumChoice = scanner.nextInt();
        scanner.nextLine(); 
        if (albumChoice < 1 || albumChoice > albums.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Album selectedAlbum = albums.get(albumChoice - 1);
        System.out.println();
        System.out.println("You are viewing the album: " + selectedAlbum.getTitle());
        selectedAlbum.displayAlbumInfo();

        // Allow the artist to edit album
        System.out.println();
        System.out.println("Do you want to edit this album? (yes/no)");
        String editChoice = scanner.nextLine().toLowerCase();

        if (editChoice.equals("yes")) {
        	System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("a) Edit album name");
            System.out.println("b) Add a song to the album");
            System.out.println("c) Remove a song from the album");
            System.out.println("d) Edit a song on the album");

            String option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "a":
                	System.out.println();
                    System.out.print("Enter the new album name: ");
                    String newAlbumName = scanner.nextLine();
                    selectedAlbum.setTitle(newAlbumName);  
                    System.out.println();
                    selectedAlbum.displayAlbumInfo();
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
                        System.out.print("Enter the genre for the song: ");
                        String newSongGenre = scanner.nextLine();

                        selectedAlbum.addSong(new Song(newSongTitle, newSongGenre, this));
                        System.out.println("Song added: " + newSongTitle);
                        System.out.println();
                    }
                    System.out.println();
                    selectedAlbum.displayAlbumInfo();
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
                        selectedAlbum.removeSong(songToRemove);  
                        System.out.println("Song removed: " + songToRemove);
                        System.out.println();
                    }
                    System.out.println();
                    selectedAlbum.displayAlbumInfo();
                    break;
                case "d":
                	System.out.println();
                    System.out.print("Enter the song name to edit: ");
                    String songToEdit = scanner.nextLine();
                    Song song = selectedAlbum.getSongByTitle(songToEdit);
                    if (song != null) {
                        System.out.print("Enter the new song name: ");
                        String newSongName = scanner.nextLine();
                        System.out.print("Enter the new genre: ");
                        String newGenre = scanner.nextLine();
                        song.setTitle(newSongName);
                        song.setGenre(newGenre);  
                        
                        System.out.println();
                        selectedAlbum.displayAlbumInfo();
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

            
        }
    }

}
