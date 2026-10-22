package streaming.recommendation;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PopularityBasedStrategy implements RecommendationStrategy {
    @Override
    public List<Track> recommend(List<Track> listeningHistory, List<Track> catalog, int limit) {
        return catalog.stream()
                .filter(track -> !listeningHistory.contains(track))
                .sorted(Comparator.comparingInt(Track::getPlayCount).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}