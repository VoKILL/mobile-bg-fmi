package ads;

import java.util.ArrayList;
import java.util.List;
import search.Filter;

public class AdServiceImpl implements AdService {
    private List<Ad> ads = new ArrayList<>();

    @Override
    public Ad createAd(Ad ad) {
        if (ad.getId() == null) {
            ad.setId(generateId());
        }
        ads.add(ad);
        return ad;
    }

    @Override
    public Ad getAdById(Long id) {
        for (Ad ad : ads) {
            if (ad.getId().equals(id)) {
                return ad;
            }
        }
        return null;
    }

    @Override
    public List<Ad> getAllAds() {
        return ads;
    }

    @Override
    public Ad updateAd(Long id, Ad updatedAd) {
        for (int i = 0; i < ads.size(); i++) {
            if (ads.get(i).getId().equals(id)) {
                updatedAd.setId(id);
                ads.set(i, updatedAd);
                return updatedAd;
            }
        }
        return null;
    }

    @Override
    public boolean deleteAd(Long id) {
        return ads.removeIf(a -> a.getId().equals(id));
    }

    @Override
    public List<Ad> findAds(Filter<Ad> filter) {
        List<Ad> result = new ArrayList<>();
        for (Ad ad : ads) {
            if (filter.matches(ad)) {
                result.add(ad);
            }
        }
        return result;
    }

    private Long generateId() {
        long maxId = 0;
        for (Ad ad : ads) {
            if (ad.getId() != null && ad.getId() > maxId) {
                maxId = ad.getId();
            }
        }
        return maxId + 1;
    }
}
