class ListNode {
    String val;
    ListNode next;

    ListNode(String x) {
        val = x;
        next = null;
    }
}

public class SymbolTable {
    //1


    public ListNode[] values = new ListNode[101];

    public int hashing(String var) {
        int sum = 0;
        for (int i = 0; i < var.length(); i++) {
            int asciiValue = var.charAt(i);
            sum = sum + asciiValue;
            //System.out.println(str.charAt(i) + "=" + asciiValue);
        }

        return sum % values.length;
    }

    public void add(String identifier) {
        int location = hashing(identifier);

        ListNode pos = values[location];
        if (pos == null) {
            values[location] = new ListNode(identifier);
            return;
        }
        while (pos.next != null) {
            pos = pos.next;
        }
        pos.val = identifier;
    }

    public boolean identify(String identifier) {
        int location = hashing(identifier);
        if(values[location] == null ) return false;
        if (values[location].val.equals(identifier) ) return true;
        return false;
    }


}
