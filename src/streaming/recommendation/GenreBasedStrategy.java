package streaming.recommendation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenreBasedStrategy implements RecommendationStrategy {
    @Override
    public List<Track> recommend(List<Track> listeningHistory, List<Track> catalog, int limit) {
        String favouriteGenre = findFavouriteGenre(listeningHistory);
        if (favouriteGenre == null) {
            return List.of();
        }
        return catalog.stream()
                .filter(track -> track.getGenre().equals(favouriteGenre))
                .filter(track -> !listeningHistory.contains(track))
                .limit(limit)
                .collect(Collectors.toList());
    }
    private String findFavouriteGenre(List<Track> listeningHistory) {
        Map<String, Integer> listensPerGenre = new LinkedHashMap<>();
        for (Track track : listeningHistory) {
            listensPerGenre.merge(track.getGenre(), 1, Integer::sum);
        }
        String favouriteGenre = null;
        int highestCount = 0;
        for (Map.Entry<String, Integer> entry : listensPerGenre.entrySet()) {
            if (entry.getValue() > highestCount) {
                favouriteGenre = entry.getKey();
                highestCount = entry.getValue();
            }
        }
        return favouriteGenre;
    }
}