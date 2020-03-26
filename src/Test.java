public class Test {
    public static void lab1tests(){
        Person[] persons = new Person[]{
                new Person("Максим","Картамышев"),
                new Person("Никита","Чекрыгин"),
                new Person("Даниил","Джашин")
        };

        Vehicle[] vehicles = new Vehicle[]{
                new Vehicle("1lab23","Maksimum","jam"),
                new Vehicle("1lab45","Nikus","turboXXL"),
                new Vehicle("1lab67","Denius","fullTermos")
        };

        Space[] spaces = new Space[]{
                new Space(persons[0],vehicles[0]),
                new Space(persons[1],vehicles[1]),
                new Space(persons[2],vehicles[2]),
        };
        for (Person person:persons){
            System.out.println(person.toString());
        }
        for (Vehicle vehicle:vehicles){
            System.out.println(vehicle.toString());
        }
        for(Space space: spaces){
            System.out.println(space.toString());
        }
    }
}
