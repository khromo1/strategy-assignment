package streaming.recommendation;

import java.util.List;

public interface RecommendationStrategy {
    List<Track> recommend(List<Track> listeningHistory, List<Track> catalog, int limit);
}