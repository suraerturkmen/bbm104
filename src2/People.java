class People {
    private String people_id;
    private String name;
    private String surname;
    private String country;

    public People(String people_id, String name, String surname, String country) {
        this.people_id = people_id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public String getPeople_id() {
        return people_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }
}
