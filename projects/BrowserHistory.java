public class BrowserHistory extends ContainerBase {

    public BrowserHistory() {
        super();
    }

    public void visit(String url) {
        Node newNode = new Node(url);
        if (first == null) {
            first = newNode;
            current = newNode;
            return;
        }
        if (current.next != null) {
            current.next = null;
        }
        newNode.prev = current;
        current.next = newNode;
        current = newNode;
    }

    public boolean back() {
        if (current == null || current.prev == null) {
            return false;
        }
        current = current.prev;
        return true;
    }

    public boolean forward() {
        if (current == null || current.next == null) {
            return false;
        }
        current = current.next;
        return true;
    }

    public String getCurrentUrl() {
        if (current == null) {
            return null;
        }
        return current.url;
    }

    public boolean revisited(String url) {
        int count = 0;
        Node temp = first;
        while (temp != null) {
            if (temp.url.equals(url)) {
                count++;
            }
            temp = temp.next;
        }
        return count > 1;
    }

    public String[] getHistory() {
        String[] urls = new String[getSize()];
        Node temp = first;
        int i = 0;
        while (temp != null) {
            urls[i++] = temp.url;
            temp = temp.next;
        }
        return urls;
    }

    public int getSize() {
        int size = 0;
        Node temp = first;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }
}
