package streaming.recommendation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Client {
    private static final int RECOMMENDATIONS_PER_REQUEST = 3;

    public static void main(String[] args) {
        List<Track> catalog = List.of(
                new Track("Blinding Lights", "pop", 9500),
                new Track("Levitating", "pop", 8700),
                new Track("Billie Jean", "pop", 9000),
                new Track("Bohemian Rhapsody", "rock", 9900),
                new Track("Smells Like Teen Spirit", "rock", 7200),
                new Track("Take Five", "jazz", 3100),
                new Track("So What", "jazz", 2800),
                new Track("Blue in Green", "jazz", 2500),
                new Track("Autumn Leaves", "jazz", 2200),
                new Track("Lose Yourself", "hip-hop", 8100)
        );

        List<Track> history = List.of(
                catalog.get(3),   // Bohemian Rhapsody
                catalog.get(1),   // Levitating
                catalog.get(5),   // Take Five
                catalog.get(6)    // So What
        );

        // The client picks strategies by name — no if/switch anywhere.
        Map<String, RecommendationStrategy> strategiesByMode = new LinkedHashMap<>();
        strategiesByMode.put("Familiar (recently played)", new RecentlyPlayedStrategy());
        strategiesByMode.put("My taste (by genre)", new GenreBasedStrategy());
        strategiesByMode.put("Discover (popular)", new PopularityBasedStrategy());

        RecommendationEngine engine = new RecommendationEngine(
                catalog, RECOMMENDATIONS_PER_REQUEST, new RecentlyPlayedStrategy());

        // Same engine object, strategy switched at runtime
        for (Map.Entry<String, RecommendationStrategy> mode : strategiesByMode.entrySet()) {
            engine.setStrategy(mode.getValue());
            System.out.println("Mode: " + mode.getKey());
            engine.recommendFor(history).forEach(track -> System.out.println("  " + track));
        }

        // A brand-new user has no history, so a taste-based strategy has nothing to work with
        engine.setStrategy(new PopularityBasedStrategy());
        System.out.println("New user with empty history (popular)");
        engine.recommendFor(List.of()).forEach(track -> System.out.println("  " + track));
    }
}