public final class Person implements Cloneable {
    private String firstName;
    private String secondName;

    public static final Person NOT_NAMED_PERSON = new Person("","");

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
        final StringBuilder sb = new StringBuilder("Person: ");
        sb.append(secondName).append(" ").append(firstName);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() ^ secondName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return firstName.equals(person.getFirstName()) && secondName.equals(person.getSecondName());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
