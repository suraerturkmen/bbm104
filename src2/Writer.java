class Writer extends Artist{
    private String writing_type;
    public Writer(String people_id, String name, String surname, String country, String writing_type) {
        super(people_id, name, surname, country);
        this.writing_type = writing_type;
    }
    public String getWriting_type() {
        return writing_type;
    }

}