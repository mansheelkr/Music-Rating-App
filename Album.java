import java.util.ArrayList;

class Album implements Rateable {
    public String title;
    public String description;
    public Artist artist;
    public ArrayList<Song> songs;
    public ArrayList<Integer> ratings;

    public Album(String title, String description, Artist artist) {
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.songs = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    // Add song to an album
    public void addSong(Song song) {
        songs.add(song);
    }
    
    // Remove song from an album
    public void removeSong(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
            System.out.println("Song " + song.getTitle() + " has been removed from the album " + this.title);
        } else {
            System.out.println("Song not found in the album.");
        }
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    // Add rating to an album
    @Override
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
        } else {
            System.out.println("Rating must be between 1 and 5.");
        }
    }

    // Average rating of an album
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
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
    
    
}