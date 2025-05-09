import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = DataManager.getInstance();  // Manage data
        
        while (true) {  
        	System.out.println();
        	System.out.println("♫ ♪ ♫ ♪ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪");
            System.out.println("Welcome to Tune Vibes!");
            System.out.println("♫ ♪ ♫ ♪ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪");
            System.out.println("Are you a listener or an artist?");
            System.out.print("Enter 'listener' or 'artist' (or 'exit' to quit): ");
            String userType = scanner.nextLine().toLowerCase();

            if (userType.equals("listener")) {
            	// Listener functionality
            	System.out.println();
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                // Check if listener exists, if not, create new listener
                Listener listener = dataManager.getListenerByUsername(username);
                if (listener == null) {
                    listener = new Listener(username);
                    dataManager.addListener(listener);  // Add listener to data manager
                    System.out.println("New listener created: " + username);
                } else {
                    System.out.println("Welcome back, " + username);
                }

                // Listener options
                while (true) {
                    System.out.println();
                    System.out.println("Hello, " + username + "! Choose an option:");
                    System.out.println("1) Browse Music");
                    System.out.println("2) Search Music");
                    System.out.println("3) Rate a Song");
                    System.out.println("4) Create a New Playlist");
                    System.out.println("5) View Playlists");
                    System.out.println("6) Exit");

                    int listenerChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (listenerChoice) {
                        case 1:
                            listener.browseMusic();
                            break;
                        case 2:
                        	System.out.println();
                            System.out.print("Enter artist, album, or song to search: ");
                            String query = scanner.nextLine();
                            listener.searchMusic(query);
                            break;
                        case 3:
                        	System.out.println();
                        	System.out.print("Enter a song title to rate: ");
                            String songToRate = scanner.nextLine();
                            listener.rateSong(songToRate);
                            break;
                        case 4:
                        	System.out.println();
                            System.out.print("Enter playlist name: ");
                            String playlistName = scanner.nextLine();
                            listener.createPlaylist(playlistName);
                            break;
                        case 5:
                            listener.viewPlaylists();
                            break;
                        case 6:
                            System.out.println("Exiting listener menu...");
                            break; 
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }

                    // Return to menu after each option
                    System.out.println();
                    System.out.println("Press 0 to return to the menu.");
                    String returnToMenu = scanner.nextLine();
                    if (returnToMenu.equals("0")) {
                        continue; // Go back to the main menu
                    } 
                    break; // Exit back to login if user presses any key other than 0
                }
            } else if (userType.equals("artist")) {
                // Artist functionality
            	System.out.println();
                System.out.print("Enter your artist name: ");
                String artistName = scanner.nextLine();

                // Check if artist exists, if not, create new artist
                Artist artist = dataManager.getArtistByName(artistName);
                if (artist == null) {
                    artist = new Artist(artistName);
                    dataManager.addArtist(artist);  // Add artist to data manager
                    System.out.println("New artist created: " + artistName);
                } else {
                    System.out.println("Welcome back, " + artistName);
                }

                // Artist options
                while (true) {
                    System.out.println();
                    System.out.println("Hello, " + artistName + "! Choose an option:");
                    System.out.println("1) Browse YOUR music catalog");
                    System.out.println("2) Search YOUR music catalog");
                    System.out.println("3) Create a new album");
                    System.out.println("4) View an album");
                    System.out.println("5) Exit");

                    int artistChoice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (artistChoice) {
                        case 1:
                        	System.out.println();
                            artist.displayArtistCatalog();
                            break;
                        case 2:
                        	System.out.println();
                            System.out.print("Enter album or song name to search: ");
                            String query = scanner.nextLine();
                            System.out.println();
                            artist.searchMusicCatalog(query);
                            break;
                        case 3:
                        	System.out.println();
                            System.out.print("Enter album name: ");
                            String albumName = scanner.nextLine();
                            artist.createAlbum(albumName);  
                            break;
                        case 4:
                            artist.viewAlbum(); 
                            break;
                        case 5:
                            System.out.println("Exiting artist menu...");
                            break; 
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }

                    System.out.println();
                    System.out.println("Press 0 to return to the menu or press any key to log out.");
                    String returnToMenu = scanner.nextLine();
                    if (returnToMenu.equals("0")) {
                        continue; // Go back to the main menu
                    } 
                    break; // Exit back to login if user presses any key other than 0
                }
            } else if (userType.equals("exit")) {
            	System.out.println();
            	System.out.println("♫ ♪ ♫ ♪ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪");
                System.out.println("Thank you for using Tune Vibes! Goodbye!");
                System.out.println("♫ ♪ ♫ ♪ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♪ ♫ ♪ ♫ ♪ ♫ ♪ ♫ ♪");
                break; // Exit the program entirely
            } else {
                System.out.println("Invalid input. Please enter 'listener', 'artist', or 'exit'.");
            }
        }

        scanner.close(); 
    }
}
