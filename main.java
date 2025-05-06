import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {  // Start an infinite loop to allow going back to the main prompt after any exit
            // Welcome message
            System.out.println("Welcome to Tune Vibes! Are you a listener or an artist?");
            System.out.print("Enter 'listener' or 'artist' (or 'exit' to quit): ");
            String userType = scanner.nextLine().toLowerCase();

            if (userType.equals("listener")) {
                // Listener functionality
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                Listener listener = new Listener(username);

                // Listener options
                while (true) {
                    System.out.println("Now we will look into the functionality of the listener.");
                    System.out.println("Hello, " + username + "! Choose an option:");
                    System.out.println("1) Browse music");
                    System.out.println("2) Search music");
                    System.out.println("3) Rate a song");
                    System.out.println("4) Create a new playlist");
                    System.out.println("5) View Playlists");
                    System.out.println("6) Exit");

                    int listenerChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline

                    switch (listenerChoice) {
                        case 1:
                            listener.browseMusic();
                            break;
                        case 2:
                            System.out.print("Enter artist, album, or song to search: ");
                            String query = scanner.nextLine();
                            listener.searchMusic(query);
                            break;
                        case 3:
                        	System.out.print("Enter a song title to rate: ");
                            String songToRate = scanner.nextLine();
                            listener.rateSong(songToRate); // Call rateSong function with user input
                            break;
                        case 4:
                            System.out.print("Enter playlist name: ");
                            String playlistName = scanner.nextLine();
                            listener.createPlaylist(playlistName);
                            break;
                        case 5:
                            listener.viewPlaylists();
                            break;
                        case 6:
                            System.out.println("Exiting listener menu...");
                            break; // Exit listener's menu
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }

                    // Return to menu after each option
                    System.out.print("Press 0 to return to the menu or any other key to exit: ");
                    String returnToMenu = scanner.nextLine();
                    if (returnToMenu.equals("0")) {
                        continue; // Go back to the main menu
                    } else {
                        System.out.println("Exiting listener functionality...");
                        break; // Exit if any other key is pressed
                    }
                }
            } else if (userType.equals("artist")) {
                // Artist functionality
                System.out.print("Enter your artist name: ");
                String artistName = scanner.nextLine();
                Artist artist = new Artist(artistName);

                // Artist options
                while (true) {
                    System.out.println("Now we will look into the functionality of the artist.");
                    System.out.println("Hello, " + artistName + "! Choose an option:");
                    System.out.println("1) Browse YOUR music catalog");
                    System.out.println("2) Search YOUR music catalog");
                    System.out.println("3) Create a new album");
                    System.out.println("4) View albums");
                    System.out.println("5) Exit");

                    int artistChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline

                    switch (artistChoice) {
                        case 1:
                            artist.displayArtistCatalog();
                            break;
                        case 2:
                            System.out.print("Enter album or song name to search: ");
                            String query = scanner.nextLine();
                            artist.searchMusicCatalog(query);
                            break;
                        case 3:
                            System.out.print("Enter album name: ");
                            String albumName = scanner.nextLine();
                            artist.createAlbum(albumName);  // Create a new album with songs
                            break;
                        case 4:
                            artist.viewAlbum(); // View all albums
                            break;
                        case 5:
                            System.out.println("Exiting artist menu...");
                            break; // Exit artist menu
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }

                    // Return to menu after each option
                    System.out.print("Press 0 to return to the menu or any other key to exit: ");
                    String returnToMenu = scanner.nextLine();
                    if (returnToMenu.equals("0")) {
                        continue; // Go back to the main menu
                    } else {
                        System.out.println("Exiting artist functionality...");
                        break; // Exit if any other key is pressed
                    }
                }
            } else if (userType.equals("exit")) {
                System.out.println("Thank you for using Tune Vibes! Goodbye!");
                break; // Exit the program entirely
            } else {
                System.out.println("Invalid input. Please enter 'listener', 'artist', or 'exit'.");
            }
        }

        scanner.close();  // Close the scanner
    }
}
