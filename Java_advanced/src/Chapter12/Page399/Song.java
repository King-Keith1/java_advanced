package Chapter12.Page399;

public class Song {
    private final String title;
    private final String artist;
    private final String genre;
    private final int year;
    private final int timesPlayed;

    // Constructor - automatically initialize an object when it's created
    public Song(String title, String artist, String genre, int year, int timesPlayed) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.timesPlayed = timesPlayed;
    }

    // Getters - provide controlled read-only access to an object's private properties by returning their values
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    // toString method for easy printing
    @Override       //builds a text string using a template and placeholder values
    public String toString() {//String by String (String,  integer) - played by integer times
        return String.format("%s by %s (%s, %d) - played %d times",
                title, artist, genre, year, timesPlayed);
    }
}
