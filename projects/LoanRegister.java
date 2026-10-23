public class LoanRegister extends ContainerBase {

    private int penaltiesCollected;

    public LoanRegister() {
        penaltiesCollected = 0;
    }

    public LoanRegister(Loan[] loans) {
        penaltiesCollected = 0;
        for (Loan loan : loans) {
            appendLoan(loan);
        }
    }

    public void appendLoan(Loan loan) {
        Node newNode = new Node(loan);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public Loan[] getLoans() {
        int count = 0;
        Node current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        Loan[] result = new Loan[count];
        current = first;
        for (int i = 0; i < count; i++) {
            result[i] = current.value;
            current = current.next;
        }
        return result;
    }

    public Loan attendReturn() {
        if (first == null) {
            return null;
        }
        Loan loan = first.value;
        penaltiesCollected += loan.daysOverdue * 2;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return loan;
    }

    public int getPenaltiesCollected() {
        return penaltiesCollected;
    }

    public Loan[] getOverdue() {
        int count = 0;
        Node current = first;
        while (current != null) {
            if (current.value.daysOverdue > 0) {
                count++;
            }
            current = current.next;
        }
        Loan[] result = new Loan[count];
        current = first;
        int i = 0;
        while (current != null) {
            if (current.value.daysOverdue > 0) {
                result[i++] = current.value;
            }
            current = current.next;
        }
        return result;
    }

    public LoanRegister separateOverdue() {
        LoanRegister overdueRegister = new LoanRegister();
        Node current = first;
        first = null;
        last = null;
        while (current != null) {
            Node next = current.next;
            current.next = null;
            if (current.value.daysOverdue > 0) {
                overdueRegister.appendLoan(current.value);
            } else {
                if (first == null) {
                    first = current;
                    last = current;
                } else {
                    last.next = current;
                    last = current;
                }
            }
            current = next;
        }
        return overdueRegister;
    }
}
