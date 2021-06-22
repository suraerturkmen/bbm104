class StuntPerformer extends Performer{
    private String height;
    private String[] real_actors_Id;

    public StuntPerformer(String people_id, String name, String surname, String country, String height, String[] real_actors_Id) {
        super(people_id, name, surname, country);
        this.height = height;
        this.real_actors_Id = real_actors_Id;
    }

    public String getHeight() {
        return height;
    }

}
