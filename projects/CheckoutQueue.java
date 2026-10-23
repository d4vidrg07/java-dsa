public class CheckoutQueue extends ContainerBase {

    private double dailySales;

    // Constructor implícito innecesario eliminado; dailySales = 0.0 por defecto en Java

    public void enqueue(Client client) {
        Node newNode = new Node(client);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public Client dequeue() {
        if (first == null) return null;

        Client served = first.value;
        first = first.next;
        if (first == null) last = null;

        dailySales += served.totalCost;
        return served;
    }

    public double getDailySales() {
        return dailySales;
    }

    public CheckoutQueue split() {
        CheckoutQueue odd = new CheckoutQueue();
        Node current = first;
        first = last = null;

        for (int i = 0; current != null; i++, current = current.next) {
            if (i % 2 == 0) enqueue(current.value);
            else            odd.enqueue(current.value);
        }

        return odd;
    }

    public int size() {
        int count = 0;
        for (Node n = first; n != null; n = n.next) count++;
        return count;
    }
}
