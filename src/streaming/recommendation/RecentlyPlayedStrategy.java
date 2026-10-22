package streaming.recommendation;

import java.util.ArrayList;
import java.util.List;

public class RecentlyPlayedStrategy implements RecommendationStrategy {
    @Override
    public List<Track> recommend(List<Track> listeningHistory, List<Track> catalog, int limit) {
        List<Track> recent = new ArrayList<>();
        for (int i = listeningHistory.size() - 1; i >= 0 && recent.size() < limit; i--) {
            Track track = listeningHistory.get(i);
            if (!recent.contains(track)) {
                recent.add(track);
            }
        }
        return recent;
    }
}