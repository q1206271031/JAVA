import com.sun.org.apache.bcel.internal.generic.LNEG;

class LinkedNode {
    public int data = 0;
    public LinkedNode next = null;
	
	//构造方法
    public LinkedNode(int data) {
        this.data = data;
    }
}

public class LinkedList {
    // 链表的头结点(第一个节点). 有了这个头结点之后
    // 就可以根据 next 把所有的剩下的元素都获取到
    private LinkedNode head = null;

    public void addFirst(int elem) {
        // 先创建一个节点, 让这个节点的值就是 elem
        LinkedNode node = new LinkedNode(elem);
        if (this.head == null) {
            // 空链表的情况
            this.head = node;
            return;
        }
        // 如果不是空链表, 就需要把新的节点放到链表的开始位置上.
        node.next = head;
        this.head = node;
        return;
    }

    public void addLast(int elem) {
        LinkedNode node = new LinkedNode(elem);
        // 如果是空链表, 直接把这个节点放上去即可
        if (this.head == null) {
            this.head = node;
            return;
        }
        // 非空的情况, 需要先找到最后一个节点
        LinkedNode cur = this.head;
        // 这个循环结束之后, cur 势必就是最后一个节点
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }

    public void addIndex(int index, int elem) {
        LinkedNode node = new LinkedNode(elem);
        // 1. 先对 pos 进行一个合法性的校验, 需要知道链表的长度
        int len = size();
        if (index < 0 || index > len) {
            return;
        }
        // 2. 处理头插的情况
        if (index == 0) {
            addFirst(elem);
            return;
        }
        // 3. 尾插
        if (index == len) {
            addLast(elem);
            return;
        }
        // 4. 处理插入到中间位置的情况
        // prev 这个引用就对应到 index - 1 的位置
        LinkedNode prev = getIndexPos(index - 1);
        // 5. 完成具体的插入过程
        node.next = prev.next;
        prev.next = node;
    }

    private LinkedNode getIndexPos(int index) {
        // 是否要判定一下 index 是在有效范围中呢?
        LinkedNode cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public int size() {
        int size = 0;
        for (LinkedNode cur = this.head;
            cur != null; cur = cur.next) {
            size++;
        }
        return size;
    }

    public boolean contains(int toFind) {
        // 直接遍历链表, 依次比较每个元素就行了
        for (LinkedNode cur = this.head;
                cur != null; cur = cur.next) {
            if (cur.data == toFind) {
                return true;
            }
        }
        return false;
    }

    public void remove(int toRemove) {
        // 1. 先单独处理下空链表的情况
        if (head == null) {
            return;
        }
        // 2. 先考虑是否要删除的是头结点
        if (head.data == toRemove) {
            this.head = this.head.next;
            return;
        }
        // 3. 删除中间的节点, 找到要删除元素的前一个元素
        LinkedNode prev = searchPrev(toRemove);
        // prev.next = prev.next.next;  // 这种写法虽然没错, 但是比较抽象
        LinkedNode nodeToRemove = prev.next;
        prev.next = nodeToRemove.next;
    }

    private LinkedNode searchPrev(int toRemove) {
        // 找到要删除元素的前一个位置
        if (this.head == null) {
            return null;
        }
        LinkedNode prev = this.head;
        while (prev.next != null) {
            if (prev.next.data == toRemove) {
                return prev;
            }
            prev = prev.next;
        }
        // 返回 null 表示没找到
        return null;
    }

    public void display() {
        // 打印链表中的所有元素
        System.out.print("[");
        for (LinkedNode node = this.head;
            node != null; node = node.next) {
            System.out.print(node.data);
            if (node.next != null) {
                // 如果不是最后一个元素就加上 , 否则不加
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
