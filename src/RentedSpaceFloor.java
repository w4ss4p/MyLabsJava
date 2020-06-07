import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RentedSpaceFloor implements Floor,Cloneable {
    private Node head;
    private int size;

    public RentedSpaceFloor() {
        head = new Node(null, null);
        size = 0;
    }

    public RentedSpaceFloor(Space[] spaces) {
        for (Space space : spaces) {
            add(space);
        }
    }

    @Override
    public void clear() {
        head = new Node(null, null);
        size = 0;
    }

    @Override
    public boolean add(Space space) {
        if (size == 0) {
            head.next = new Node(head, space);
            head.next.next = head;
            head.prev = head.next;
        } else {
            head.prev.next = new Node(head.prev.next, space);
            head.prev.next.next = head;
            head.prev = head.prev.next;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(Space space, int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node buf = head.next;
        for (int i = 0; i < index; i++) {
            buf = buf.next;
        }
        Node prev = buf.prev;
        Node next = buf;
        prev.next = new Node(prev, space);
        prev.next.next = next;
        next.prev = prev.next;
        size++;
        return true;
    }

    @Override
    public Space get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return getTargetNode(index).value;
    }

    @Override
    public Space set(Space space, int index) {
        if(space == null) return remove(index);
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node buf = getTargetNode(index);
        Space tmp = buf.value;
        buf.value = Objects.requireNonNull(space);
        return tmp;
    }

    @Override
    public Space remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node buf = head.next;
        for (int i = 0; i < index; i++) {
            buf = buf.next;
        }
        Space tmp = buf.value;
        Node prev = buf.prev;
        Node next = buf.next;
        prev.next = next;
        next.prev = prev;
        size--;
        return tmp;
    }

    @Override
    public Space remove(String stateNumber) {
        Utils.checkRegNumberFormat(stateNumber);
        Node buf = head.next;
        while (buf.value != null) {
            if (buf.value.stringEquals(stateNumber)){
                Space tmp = buf.value;
                Node prev = buf.prev;
                Node next = buf.next;
                prev.next = next;
                next.prev = prev;
                size--;
                return tmp;
            }
            buf = buf.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Space[] toArray() {
        Space[] spaces = new Space[size];
        int index = 0;
        Node buf = head.next;
        while (buf.value != null) {
            spaces[index++] = buf.value;
            buf = buf.next;
        }
        return spaces;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] buf = a;
        int index = 0;
        Iterator iterator = iterator();
        while(iterator.hasNext()){
            buf[index++] = iterator.next();
        }
        return (T[]) buf;
    }

    @Override
    public Vehicle[] toVehicleArray() {
        Vehicle[] vehicles = new Vehicle[size];
        int index = 0;
        Node buf = head.next;
        while (buf.value != null) {
            vehicles[index++] = buf.value.getVehicle();
            buf = buf.next;
        }
        return vehicles;
    }

    private Node getTargetNode(int index){
        Node buf = head.next;
        for (int i = 0; i < index; i++) {
            buf = buf.next;
        }
        return buf;
    }

    @Override
    public Space[] getTypedSpaces(VehicleTypes type) {
        int count = 0;
        Node buf = head.next;
        while (buf.value != null) {
            if (buf.value.getVehicle().getType() == type) count++;
            buf = buf.next;
        }
        Space[] forReturn = new Space[count];
        count = 0;
        buf = head.next;
        while (buf.value != null) {
            if (buf.value.getVehicle().getType() == type) forReturn[count++] = buf.value;
            buf = buf.next;
        }
        return forReturn;
    }

    @Override
    public Space[] getFreeSpaces() {
        int count = 0;
        Node buf = head.next;
        while (buf.value != null) {
            if (buf.value.isEmpty()) count++;
            buf = buf.next;
        }
        Space[] forReturn = new Space[count];
        count = 0;
        buf = head.next;
        while (buf.value != null) {
            if (buf.value.isEmpty()) forReturn[count++] = buf.value;
            buf = buf.next;
        }
        return forReturn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rented spaces: \n");
        Node buf = head.next;
        while (buf.value != null) {
            sb.append(buf.value.toString()).append('\n');
            buf = buf.next;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RentedSpaceFloor that = (RentedSpaceFloor) obj;
        boolean answer = that.size() == this.size;
        if(answer){
            for(int i = 0; i<size;i++){
                answer &= (that.toArray()[i].equals(this.toArray()[i]));
            }
        }
        return answer;
    }

    @Override
    public int hashCode() {
        int hash = 53 *size;
        Node buf = head.next;
        while (buf.value != null) {
            hash*=buf.value.hashCode();
            buf = buf.next;
        }
        return hash;
    }

    public Object clone() throws CloneNotSupportedException{
        return new RentedSpaceFloor(toArray().clone());
    }

    @Override
    public boolean remove(Space space) {
        Node buf = head.next;
        while (buf.value != null) {
            if (buf.value.equals(space)){
                Space tmp = buf.value;
                Node prev = buf.prev;
                Node next = buf.next;
                prev.next = next;
                next.prev = prev;
                size--;
                return true;
            }
            buf = buf.next;
        }
        return false;
    }

    @Override
    public int indexOf(Space space) {
        Node buf = head.next;
        int index = 0;
        while (buf.value != null) {
            if (buf.value.equals(space)) return index;
            index++;
            buf = buf.next;
        }
        return -1;
    }

    @Override
    public Space spaceWithNearestRentEndsDate() throws NoRentedSpaceException{
        LocalDate date = nearestRentEndsDate();
        Node buf = head.next;
        while(buf.value!=null){
            if(buf.value instanceof RentedSpace){
                RentedSpace rs = (RentedSpace) buf.value;
                if(rs.getRentEndsDate().equals(date)) return rs;
            }
        }
        return null;
    }

    public void checkRentedSpaces() throws NoRentedSpaceException{
        int rentedSpaceCount = 0;
        Node buf = head.next;
        while(buf.value!=null){
            if(buf.value instanceof RentedSpace) rentedSpaceCount++;
        }
        if(rentedSpaceCount==0) throw new NoRentedSpaceException();
    }

    @Override
    public int compareTo(Floor o) {
        RentedSpaceFloor rsf = (RentedSpaceFloor) o;
        return this.size-rsf.size();
    }

    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator(head);
    }

    private class SpaceIterator implements Iterator<Space>{
        private Node head;

        public SpaceIterator(Node head){
            this.head = head.next;
        }

        @Override
        public boolean hasNext() {
            return head.value!=null;
        }

        @Override
        public Space next() {
            Space space = head.value;
            head = head.next;
            return space;
        }
    }
}

class Node {
    Node next;
    Node prev;
    Space value;

    Node(Node prev, Space value) {
        this.prev = prev;
        if (prev != null) prev.next = this;
        this.value = value;
    }
}
