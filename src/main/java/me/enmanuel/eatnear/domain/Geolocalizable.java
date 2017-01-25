package me.enmanuel.eatnear.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 25-Jan-17
 * Time: 2:53 PM
 */
public interface Geolocalizable {

    Double getLatitude();
    Double getLongitude();
    default boolean isValid(){
        return getLatitude() != null && getLongitude() != null;
    }
}
