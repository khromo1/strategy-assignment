package streaming.recommendation;

import java.util.List;
import java.util.Objects;

public class RecommendationEngine {
    private final List<Track> catalog;
    private final int limit;
    private RecommendationStrategy strategy;
    public RecommendationEngine(List<Track> catalog, int limit, RecommendationStrategy strategy) {
        this.catalog = catalog;
        this.limit = limit;
        setStrategy(strategy);
    }
    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy must not be null");
    }
    public List<Track> recommendFor(List<Track> listeningHistory) {
        return strategy.recommend(listeningHistory, catalog, limit);
    }
}