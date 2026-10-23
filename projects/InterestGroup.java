public class InterestGroup extends ContainerBase {

    public InterestGroup() {
        super();
    }

    public boolean isMember(String member) {
        Node cabeza = first;
        while (cabeza != null) {                        
            if (cabeza.value.equals(member)) {
                return true;
            }
            cabeza = cabeza.next;
        }
        return false;
    }

    public boolean addMember(String member) {
        if (isMember(member)) return false;

        Node node_member = new Node(member);
        if (first == null) {
            first = node_member;
            return true;
        }
        Node cabeza = first;
        while (cabeza.next != null) {
            cabeza = cabeza.next;
        }
        cabeza.next = node_member;
        return true;
    }

    public int getSize() {
        int size = 0;
        Node cabeza = first;
        while (cabeza != null) {
            size++;
            cabeza = cabeza.next;
        }
        return size;
    }

    public InterestGroup union(InterestGroup grupo2) {
        InterestGroup unidos = new InterestGroup();

        Node cabeza = first;
        while (cabeza != null) {                       
            unidos.addMember(cabeza.value);
            cabeza = cabeza.next;
        }

        if (grupo2.first != null) {                     
            Node cabeza2 = grupo2.first;
            while (cabeza2 != null) {                  
                unidos.addMember(cabeza2.value);
                cabeza2 = cabeza2.next;
            }
        }
        return unidos;
    }

    public boolean removeMember(String member) {
        if (first == null) return false;

        // FIX: caso especial — el miembro es el primero
        if (first.value.equals(member)) {
            first = first.next;
            return true;
        }

        Node cabeza = first;
        while (cabeza.next != null) {
            if (cabeza.next.value.equals(member)) {     
                cabeza.next = cabeza.next.next;
                return true;                           
            }
            cabeza = cabeza.next;
        }
        return false;
    }
}
