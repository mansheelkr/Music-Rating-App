import java.util.ArrayList;

public class Song {
    private String title;
    private String genre;
    private Artist artist;
    private ArrayList<Float> ratings;

    public Song(String title, String genre, Artist artist) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.ratings = new ArrayList<>();
    }
    
    // Getters and setters
    public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public ArrayList<Float> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Float> ratings) {
		this.ratings = ratings;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String newGenre) {
        this.genre = newGenre;
    }
    
    // Method to add song rating
    public void addRating(float rating) {
        if (rating >= 1 && rating <= 10) {
            ratings.add(rating);
        } else {
            System.out.println("Rating must be between 1 and 10.");
        }
    }
    
    // Method to calculate average song rating
    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        float sum = 0;
        for (float rating : ratings) {
            sum += rating;
        }
        double average = sum / ratings.size();
        
        return Double.parseDouble(String.format("%.2f", average));
    }
    
    // Method to display song info
    public void displaySongInfo() {
        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", "Artist", "Album", "Song", "Genre", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        System.out.printf("%-20s| %-20s| %-20s| %-20s| %-20s%n", 
            artist.getName(), 
            artist.getAlbumTitle(this), 
            title, 
            genre, 
            getAverageRating() 
        );
    }
}
