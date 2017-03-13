package me.enmanuel.eatnear.domain;

/**
 * Created by enmanuelreyes on 3/12/17.
 */
public class Restaurant extends me.enmanuel.eatnear.entity.Restaurant {
    private byte vote;

    public Restaurant(me.enmanuel.eatnear.entity.Restaurant r, byte vote) {
        setId(r.getId());
        setAddress(r.getAddress());
        setName(r.getName());
        setPhoneNumber(r.getPhoneNumber());
        setCity(r.getCity());
        setFoodType(r.getFoodType());
        setMinPrice(r.getMinPrice());
        setMaxPrice(r.getMaxPrice());
        setLatitude(r.getLatitude());
        setLongitude(r.getLongitude());
        this.vote = vote;
    }

    public byte getVote() {
        return vote;
    }

    public void setVote(byte vote) {
        this.vote = vote;
    }
}
