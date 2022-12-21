package Persistence;

import com.google.gson.Gson;
import com.google.gson.*;

public class AdventuresJsonDAO {
    private final Gson gson;

    public AdventuresJsonDAO(Gson gson) {
        this.gson = gson;
    }

    public AdventuresJsonDAO() {
    }
}
