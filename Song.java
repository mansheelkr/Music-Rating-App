import java.util.ArrayList;

class Song implements Rateable {
    public String title;
    public int duration;  // in seconds
    public String genre;
    public Artist artist;
    public ArrayList<Integer> ratings;

    public Song(String title, int duration, String genre, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.artist = artist;
        this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    // Add rating to song
    @Override
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
        } else {
            System.out.println("Rating must be between 1 and 5.");
        }
    }

    // Get average rating of song
    @Override
    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum / (double) ratings.size();
    }

    // Getters and setters
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public ArrayList<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Integer> ratings) {
		this.ratings = ratings;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    
}
