class Actor extends Performer{
    private String height;

    public Actor(String people_id, String name, String surname, String country, String height) {
        super(people_id, name, surname, country);
        this.height = height;
    }

    public String getHeight() {
        return height;
    }
}
