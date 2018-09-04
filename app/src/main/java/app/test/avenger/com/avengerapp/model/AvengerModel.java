package app.test.avenger.com.avengerapp.model;

import java.io.Serializable;

/**
 * Created by dinesh on 4/27/18.
 */

public class AvengerModel implements Serializable{
    private int avengerImage = 0;
    private String avengerName = "";
    private int avengerRating = 0;
    private int listPosition=-1;

    public AvengerModel(int avengerImage, String avengerName) {
        this.avengerImage = avengerImage;
        this.avengerName = avengerName;
    }

    public int getAvengerImage() {
        return avengerImage;
    }

    public void setAvengerImage(int avengerImage) {
        this.avengerImage = avengerImage;
    }

    public String getAvengerName() {
        return avengerName;
    }

    public void setAvengerName(String avengerName) {
        this.avengerName = avengerName;
    }

    public int getAvengerRating() {
        return avengerRating;
    }

    public void setAvengerRating(int avengerRating) {
        this.avengerRating = avengerRating;
    }

    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }

    public String getAverageRatingTxt() {
        switch (avengerRating) {
            case 1:
                return "Normal";
            case 2:
                return "Very Good";
            case 3:
                return "Awesome";
            default:
                return "";
        }
    }


}
