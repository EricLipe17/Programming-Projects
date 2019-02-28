# Class for Node
class Node:

    def __init__(self, value):
        self.value = value
        self.next_node = None
        self.prev_node = None

    def getvalue(self):
        return self.value

    def setvalue(self, new_value):
        self.value = new_value

    def get_next_node(self):
        return self.next_node

    def set_next_node(self, new_node):
        self.next_node = new_node

    def get_prev_node(self):
        return self.prev_node

    def set_prev_node(self, new_node):
        self.prev_node = new_node


# Class for Linked Lists
class LinkedList(Node):

    def __init__(self):
        self.head = None

    def print(self):
        list = []
        current_node = self.head
        next_node = current_node.get_next_node()
        list.append(self.head.getvalue())
        while next_node is not None:
            list.append(next_node.getvalue())
            current_node = next_node
            next_node = current_node.get_next_node()
        print(list)

    def add(self, item):
        new_node = Node(item)
        if self.head is None:
            self.head = new_node
        else:
            new_node.next_node = self.head
            self.head = new_node

    def remove(self, item):
        current_node = self.head
        prev_node = current_node
        next_node = current_node.get_next_node()
        if self.head.getvalue() == item:
            self.head = next_node
            return
        while current_node is not None:
            if current_node.getvalue() == item and current_node.get_next_node is None:
                prev_node.next_node = None
                break
            elif current_node.getvalue() == item and current_node.get_next_node is not None:
                prev_node.next_node = next_node
                break
            if next_node is None:
                raise IndexError
                break
            prev_node = current_node
            current_node = next_node
            next_node = current_node.get_next_node()

    def search(self, item):
        current_node = self.head
        next_node = current_node.get_next_node()
        while current_node is not None:
            if current_node.getvalue() == item:
                return True
            if next_node is None:
                break
            current_node = next_node
            next_node = current_node.get_next_node()
        return False

    def isEmpty(self):
        if self.head is None:
            return True
        else:
            return False

    def size(self):
        size = 0
        if self.head is None:
            return size
        else:
            current_node = self.head
            next_node = current_node.get_next_node()
            size += 1
            while next_node is not None:
                size += 1
                current_node = next_node
                next_node = current_node.get_next_node()
            return size

    def append(self, item):
        new_node = Node(item)
        if self.head is None:
            self.head = new_node
        else:
            current_node = self.head
            next_node = current_node.get_next_node()
            while next_node is not None:
                current_node = next_node
                next_node = current_node.get_next_node()
            current_node.set_next_node(new_node)

    def index(self, item):
        position = 0
        current_node = self.head
        while current_node is not None:
            if current_node.getvalue() == item:
                return position
            if current_node.getvalue() is None:
                raise IndexError
            position += 1
            current_node = current_node.get_next_node()

    def insert(self, position, item):
        new_node = Node(item)
        index = 0
        current_node = self.head
        next_node = current_node.get_next_node()
        if position == 0:
            self.add(item)
        elif position == self.size() - 1:
            self.append(item)
        else:
            while current_node is not None:
                prev_node = current_node
                current_node = next_node
                next_node = current_node.get_next_node()
                if position - 1 == index:
                    prev_node.next_node = new_node
                    new_node.next_node = current_node
                    current_node.next_node = next_node
                    break
                if next_node is None:
                    raise IndexError
                    break
                index += 1

    def pop(self):
        current_node = self.head
        prev_node = current_node
        next_node = current_node.get_next_node()
        if self.head is None:
            raise IndexError
        else:
            while next_node is not None:
                prev_node = current_node
                current_node = next_node
                next_node = current_node.get_next_node()
            node = current_node
            prev_node.next_node = None
        return node.getvalue()

    def popi(self, position):
        current_node = self.head
        prev_node = current_node
        next_node = current_node.get_next_node()
        index = 0
        if position == self.size() - 1:
            return self.pop()
        if position == 0:
            node = self.head.getvalue()
            self.head = next_node
            return node
        else:
            while next_node is not None:
                if position == index:
                    prev_node.next_node = next_node
                    break
                prev_node = current_node
                current_node = next_node
                next_node = current_node.get_next_node()
                index += 1
            current_node.next_node = None
            return current_node.getvalue()


class DoublyLinkedList(Node):

    def __init__(self):
        self.head = None
        self.tail = None

    def print(self):
        list = []
        current_node = self.head
        next_node = current_node.get_next_node()
        list.append(self.head.getvalue())
        while next_node is not None:
            list.append(next_node.getvalue())
            current_node = next_node
            next_node = current_node.get_next_node()
        print(list)

    def add(self, item):
        new_node = Node(item)
        if self.head is None:
            self.head = new_node
            self.tail = new_node
        else:
            new_node.next_node = self.head
            self.head.prev_node = new_node
            self.head = new_node

    def remove(self, item):
        current_node = self.head
        prev_node = current_node
        next_node = current_node.get_next_node()

        if self.head.getvalue() == item:
            self.head = next_node

        if self.tail.getvalue() == item:
            self.tail.prev_node.next_node = None

        else:
            while current_node is not None:
                if current_node.getvalue() == item:
                    prev_node.next_node = next_node
                    next_node.prev_node = prev_node
                    break

                elif current_node.get_next_node() is None:
                    raise IndexError

                else:
                    prev_node = current_node
                    current_node = next_node
                    next_node = current_node.get_next_node()

    def search(self, item):
        current_node = self.head
        next_node = current_node.get_next_node()
        while current_node is not None:
            if current_node.getvalue() == item:
                return True
            if next_node is None:
                break
            current_node = next_node
            next_node = current_node.get_next_node()
        return False

    def isEmpty(self):
        if self.head is None:
            return True
        else:
            return False

    def size(self):
        size = 0
        if self.head is None:
            return size
        else:
            current_node = self.head
            next_node = current_node.get_next_node()
            size += 1
            while next_node is not None:
                size += 1
                current_node = next_node
                next_node = current_node.get_next_node()
            return size

    def append(self, item):
        new_node = Node(item)
        if self.head is None:
            self.head = new_node
            self.tail = self.head
        else:
            self.tail.set_next_node(new_node)
            new_node.set_prev_node(self.tail)
            self.tail = new_node

    def index(self, item):
        position = 0
        current_node = self.head
        while current_node is not None:
            if current_node.getvalue() == item:
                return position
            if current_node.getvalue() is None:
                raise IndexError
            position += 1
            current_node = current_node.get_next_node()

    def insert(self, position, item):
        new_node = Node(item)
        index = 0
        current_node = self.head
        next_node = current_node.get_next_node()
        if position == 0:
            self.add(item)

        elif position == self.size() - 1:
            self.append(item)

        else:
            while current_node is not None:
                prev_node = current_node
                current_node = next_node
                next_node = current_node.get_next_node()
                if position - 1 == index:
                    prev_node.next_node = new_node
                    new_node.next_node = current_node
                    current_node.prev_node = new_node
                    current_node.next_node = next_node
                    break
                if next_node is None:
                    raise IndexError
                    break
                index += 1

    def pop(self):
        node = self.tail
        prev_node = self.tail.prev_node
        prev_node.next_node = None
        self.tail = prev_node
        return node.getvalue()

    def popi(self, position):
        current_node = self.head
        prev_node = current_node
        next_node = current_node.get_next_node()
        index = 0
        if position == self.size() - 1:
            return self.pop()
        if position == 0:
            node = self.head.getvalue()
            self.head = next_node
            next_node.prev_node = None
            return node
        else:
            while next_node is not None:
                if position == index:
                    prev_node.next_node = next_node
                    next_node.prev_node = prev_node
                    break
                prev_node = current_node
                current_node = next_node
                next_node = current_node.get_next_node()
                index += 1
            current_node.next_node = None
            return current_node.getvalue()


# Class for stack
class Stack:

    def __init__(self):
        self.stack = []

    def push(self, item):
        self.stack.append(item)

    def pop(self):
        return self.stack.pop()

    def peek(self):
        return self.stack[-1]

    def isEmpty(self):
        if len(self.stack) == 0:
            return True
        else:
            return False

    def size(self):
        return len(self.stack)

    def print(self):
        list = []
        for i in range(len(self.stack)):
            list.append(self.stack[i])
        print(list)


# Class for queue
class Queue:

    def __init__(self):
        self.queue = DoublyLinkedList()

    def enqueue(self, item):
        self.queue.append(item)

    def dequeue(self):
        return self.queue.popi(0)

    def isEmpty(self):
        if len(self.queue) == 0:
            return True
        else:
            return False

    def size(self):
        return len(self.queue)

    def print(self):
        list = []
        current_node = self.queue.head
        next_node = current_node.get_next_node()
        list.append(self.queue.head.getvalue())
        while next_node is not None:
            list.append(next_node.getvalue())
            current_node = next_node
            next_node = current_node.get_next_node()
        print(list)


class Deque:

    def __init__(self):
        self.deque = DoublyLinkedList()

    def addFront(self, item):
        self.deque.add(item)

    def addRear(self, item):
        self.deque.append(item)

    def removeFront(self):
        return self.deque.popi(0)

    def removeRear(self):
        return self.deque.pop()

    def isEmpty(self):
        if len(self.queue) == 0:
            return True
        else:
            return False

    def size(self):
        return len(self.queue)

    def print(self):
        list = []
        current_node = self.deque.head
        next_node = current_node.get_next_node()
        list.append(self.deque.head.getvalue())
        while next_node is not None:
            list.append(next_node.getvalue())
            current_node = next_node
            next_node = current_node.get_next_node()
        print(list)


def reversePolish(expression):
    list = expression.split()
    if len(list) == 1:
        print("Please put spaces in between every character in your input.")
        raise SyntaxError
    stack = Stack()
    for item in list:
        if item is '+':
            n2 = stack.pop()
            n1 = stack.pop()
            stack.push(n1 + n2)
        elif item is '-':
            n2 = stack.pop()
            n1 = stack.pop()
            stack.push(n1 - n2)
        elif item is '*':
            n2 = stack.pop()
            n1 = stack.pop()
            stack.push(n1 * n2)
        elif item is '/':
            n2 = stack.pop()
            n1 = stack.pop()
            stack.push(n1 // n2)
        else:
            stack.push(int(item))

    return stack.pop()


print("Testing Linked List")
linked = LinkedList()
linked.add(1)
linked.add(2)
linked.add(3)
linked.add(4)
linked.add(5)
linked.add(6)
linked.print()
print()
print(linked.search(3))
print()
print(linked.pop())
linked.print()
print()
linked.insert(3, 7)
linked.print()
print()
linked.remove(3)
linked.print()
print()
print(linked.popi(4))
linked.print()
print()
linked.append(8)
linked.print()
print()
print(linked.index(8))
print()


print("Testing Doubly Linked List")
dlinked = DoublyLinkedList()
dlinked.add(1)
dlinked.add(2)
dlinked.add(3)
dlinked.add(4)
dlinked.add(5)
dlinked.add(6)
dlinked.print()
print()
dlinked.remove(3)
dlinked.print()
print()
print(dlinked.search(1))
print()
dlinked.append(7)
dlinked.print()
print()
print(dlinked.index(7))
print()
dlinked.insert(4, 8)
dlinked.print()
print()
print(dlinked.pop())
print()
print(dlinked.popi(2))
print()

print("Testing Stack")
stack = Stack()
stack.push(1)
stack.push(2)
stack.push(3)
stack.push(4)
stack.push(5)
stack.push(6)
stack.print()
print()
print(stack.pop())
stack.print()
print()
print(stack.peek())
print()

print("Testing Queue")
queue = Queue()
queue.enqueue(1)
queue.enqueue(2)
queue.enqueue(3)
queue.enqueue(4)
queue.enqueue(5)
queue.enqueue(6)
queue.print()
print()
print(queue.dequeue())
queue.print()
print()

print("Testing Deque")
deque = Deque()
deque.addFront(1)
deque.addFront(2)
deque.addFront(3)
deque.addFront(4)
deque.addFront(5)
deque.addFront(6)
deque.print()
print()
deque.addRear(7)
deque.print()
print()
print(deque.removeFront())
deque.print()
print()
print(deque.removeRear())
deque.print()

print(reversePolish("1 1 + 5 * 5 *"))
