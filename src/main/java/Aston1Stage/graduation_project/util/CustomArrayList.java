package Aston1Stage.graduation_project.util;

import java.io.Serializable;
import java.util.Comparator;

public class CustomArrayList<T> implements Serializable {

    private Object[] objects;

    private int size;

    public int size() {
        return size;
    }

    private static final int DEFAULT_CAPACITY = 10;

    public CustomArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
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

        Object[] newArray = new Object[newCapacity];
        System.arraycopy(objects, 0, newArray, 0, size);
        objects = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= objects.length) {
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

    public static <T> void selectionSort(CustomArrayList<T> list, Comparator<? super T> comparator) {
        for (int i = 0; i < list.size(); i++) {
            int minPos = i;
            T minValue = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                T current = list.get(j);

                if (comparator.compare(current, minValue) < 0) {
                    minPos = j;
                    minValue = current;
                }
            }
            if (minPos != i) {
                T temp = list.get(i);
                list.set(i, minValue);
                list.set(minPos, temp);
            }
        }
    }

}