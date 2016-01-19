package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PageRestaurantSpecialties implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Whether the restaurant serves breakfast
	 */
	@Column(name = "page_restaurant_specialties_breakfast")
	private boolean breakfast;
	/**
	 * Whether the restaurant serves coffee
	 */
	@Column(name = "page_restaurant_specialties_coffee")
	private boolean coffee;
	/**
	 * Whether the restaurant serves dinner
	 */
	@Column(name = "page_restaurant_specialties_dinner")
	private boolean dinner;
	/**
	 * Whether the restaurant serves drinks
	 */
	@Column(name = "page_restaurant_specialties_drinks")
	private boolean drinks;
	/**
	 * Whether the restaurant serves lunch
	 */
	@Column(name = "page_restaurant_specialties_lunch")
	private boolean lunch;

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public boolean isCoffee() {
		return coffee;
	}

	public void setCoffee(boolean coffee) {
		this.coffee = coffee;
	}

	public boolean isDinner() {
		return dinner;
	}

	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}

	public boolean isDrinks() {
		return drinks;
	}

	public void setDrinks(boolean drinks) {
		this.drinks = drinks;
	}

	public boolean isLunch() {
		return lunch;
	}

	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}

}
