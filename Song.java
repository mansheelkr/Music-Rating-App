import java.util.ArrayList;

public class Song implements Rateable {
    private String title;
    private String genre;
    private Artist artist;
    private ArrayList<Integer> ratings;

    public Song(String title, String genre, Artist artist) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.ratings = new ArrayList<>();
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

    @Override
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 10) {
            ratings.add(rating);
        } else {
            System.out.println("Rating must be between 1 and 10.");
        }
    }
    
    @Override
    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum / (double) ratings.size();
    }

    public void displaySongInfo() {
        System.out.println(artist.getName() + " | " + artist.getAlbumTitle(this) + " | " + title + " | " + genre + " | " + getAverageRating());
    }
}
