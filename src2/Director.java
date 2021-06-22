class Director extends Artist{
    private String agent;
    public Director(String people_id, String name, String surname, String country, String agent) {
        super(people_id, name, surname, country);
        this.agent = agent;
    }
    public String getAgent() {
        return agent;
    }
}
