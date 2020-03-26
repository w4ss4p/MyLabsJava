public class Person {
    private String firstName;
    private String secondName;

    public static final Person NON_NAMED_PERSON = new Person("","");

    public Person(String firstName, String secondName) {
        setFirstName(firstName);
        setSecondName(secondName);
    }



    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    private void setSecondName(String secondName){
            this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Person: " + secondName + " " + firstName;
    }
}
