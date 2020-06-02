import java.util.NoSuchElementException;

public class RentedSpaceFloor implements Floor {
    private Node head;
    int size;

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
    public Space get(String stateNumber) {
        Node buf = head.next;
        while (buf.value != null) {
            if (buf.value.stringEquals(stateNumber)) return buf.value;
            buf = buf.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean contains(String stateNumber) {
        Node buf = head.next;
        while (buf.value != null) {
            if (buf.value.stringEquals(stateNumber)) return true;
            buf = buf.next;
        }
        return false;
    }

    @Override
    public Space set(Space space, int index) {
        if(space == null) return remove(index);
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node buf = getTargetNode(index);
        Space tmp = buf.value;
        buf.value = space;
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