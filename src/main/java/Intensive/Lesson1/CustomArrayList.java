package Intensive.Lesson1;

public class CustomArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private T[] objects;

    public int size() {
        return size;
    }

    public CustomArrayList() {
        objects = (T[])new Object[DEFAULT_CAPACITY];
    }

    public void add(T object) {
        if (size == objects.length) {
            expandCapacity();
        }
        objects[size++] = object;
    }

    private void expandCapacity() {
        int oldCapacity = objects.length;
        int newCapacity;

        if (oldCapacity == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = oldCapacity + (oldCapacity / 2) + 1;
        }

        T[] newArray = (T[])new Object[newCapacity];
        System.arraycopy(objects, 0, newArray, 0, size);
        objects = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public T get(int index) {
        checkIndex(index);
        return (T) objects[index];
    }

    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) objects[index];
        objects[index] = element;

        return oldValue;
    }

}