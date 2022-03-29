package uz.elmurodov.services.search;

/**
 * @author Narzullayev Husan, ср 10:36. 29.12.2021
 */
public class SearchService {
    private static final SearchService instance=new SearchService();




    public static SearchService getInstance() {
        return instance;
    }
}
