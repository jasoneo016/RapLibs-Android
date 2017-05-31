package cs499.cpp.edu.raplibs.data;

import java.util.List;

import cs499.cpp.edu.raplibs.model.Music;


/**
 * Created by admin on 5/30/17.
 */

public interface Helper {

    interface OnFindListener {
        void onResults(List<Music> results);
    }

    void populateList();


}
