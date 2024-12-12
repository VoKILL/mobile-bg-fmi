package ads;

import java.util.List;
import search.Filter;

public interface AdService {
    Ad createAd(Ad ad);
    Ad getAdById(Long id);
    List<Ad> getAllAds();
    Ad updateAd(Long id, Ad updatedAd);
    boolean deleteAd(Long id);
    List<Ad> findAds(Filter<Ad> filter);
}
