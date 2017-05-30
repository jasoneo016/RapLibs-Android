package cs499.cpp.edu.raplibs.data;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by admin on 5/29/17.
 */

public class ArtistSuggestion implements SearchSuggestion {

    private String artistName;
    private boolean mIsHistory = false;

    public ArtistSuggestion(String suggestion) {
        this.artistName = suggestion;
    }

    public ArtistSuggestion(Parcel source) {
        this.artistName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return artistName;
    }

    public static final Creator<ArtistSuggestion> CREATOR = new Creator<ArtistSuggestion>() {
        @Override
        public ArtistSuggestion createFromParcel(Parcel in) {
            return new ArtistSuggestion(in);
        }

        @Override
        public ArtistSuggestion[] newArray(int size) {
            return new ArtistSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artistName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}