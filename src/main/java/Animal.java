//Super class that is being inherited from to other Animal sub-classes
public class Animal {

    //Attributes that will be used to define the objects
    private String name;
    private String favoriteActivity;
    private String favoriteFood;

    //Constructor, used to initialize objects and is being called when an object is created of this class.
    public Animal(String name, String favoriteActivity, String favoriteFood) {
        this.name=name;
        this.favoriteActivity=favoriteActivity;
        this.favoriteFood=favoriteFood;
    }

    //Getters and setter that lets user change and set the attributes of each object of this class
    public void setName(String newName) {
        this.name=newName;
    }

    public String getName() {
        return name;
    }

    public void setFavoriteActivity(String newFavoriteActivity) {
        this.favoriteActivity=newFavoriteActivity;
    }

    public String getFavoriteActivity() {
        return favoriteActivity;
    }

    public void setFavoriteFood(String newFavoriteFood) {
        this.favoriteFood=newFavoriteFood;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

}