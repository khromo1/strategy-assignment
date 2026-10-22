package streaming.recommendation;

import java.util.Objects;

public class Track {
    private final String title;
    private final String genre;
    private final int playCount;
    public Track(String title, String genre, int playCount) {
        this.title = title;
        this.genre = genre;
        this.playCount = playCount;
    }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getPlayCount() { return playCount; }
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Track)) return false;
        return title.equals(((Track) other).title);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
    @Override
    public String toString() {
        return title + " (" + genre + ")";
    }
}