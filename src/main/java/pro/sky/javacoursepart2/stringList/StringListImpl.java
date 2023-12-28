package pro.sky.javacoursepart2.stringList;

import com.sun.jdi.connect.Connector;
import pro.sky.javacoursepart2.exceptions.*;

import java.util.ArrayList;

public class StringListImpl implements StringList {
    private int capacity = 10; // storage value
    private final String[] STORAGE = new String[capacity];
    private int size = 0; // actual quantity of elements in storage


    public StringListImpl() {
    }

    public StringListImpl(int capacity) {
        if (capacity < 1) {
            throw new IllegalCapacityException("Capacity " + capacity + " cannot be lower than 1");
        }
        this.capacity = capacity;
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(String item) {
        if (item == null) {
            throw new NothingToAddException("String argument is null");
        }
        if (size < capacity) {
            STORAGE[size] = item;
            size++;
        }
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    // нужно сдвинуть массив???????? в том числе если окружен пустыми
    public String add(int index, String item) {
        indexChecker(index);
        if (index < size) {
            System.arraycopy(STORAGE, index, STORAGE, index + 1, size - index);
            STORAGE[index] = item;
            size++;
        }
        if (index >= size) {
            this.add(item);
        }
        return item;
    }

    @Override
    public String[] add(String... items) {
        if (items.length == 0) {
            throw new NothingToAddException("String argument is null");
        }
        if (items.length > capacity - size) {
            throw new InsufficientCapacityException("Insufficient storage");
        } else {
            System.arraycopy(items, 0, STORAGE, size, items.length);
            size += items.length;
        }
        return items;
    }

    @Override
    public String[] add(int index, String... items) {
        if (index < 0) throw new IllegalIndexException("Destination index " + index + " cannot be negative");
        if (items.length == 0) {
            throw new NothingToAddException("String argument is null");
        }
        if (index > capacity - items.length) {
            throw new IllegalIndexException("Insuffisient storage to add items from index " + index);
        } else {
            if (index > size) {
                index = size;
            }
            System.arraycopy(STORAGE, index, STORAGE, index + items.length,
                    size - index);
            System.arraycopy(items, 0, STORAGE, index, items.length);
            size += items.length;
        }
        return items;
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    public String set(int index, String item) {
        indexChecker(index);
        if (index < size) {
            STORAGE[index] = item;
        } else {
            this.add(item);
        }
        return item;
    }

    public String[] set(int index, String... items) {
        indexChecker(index);
        if (items.length > capacity - index) throw new InsufficientCapacityException("Insufficient storage");
        if (index >= size) {
            this.add(items);
        } else {
            System.arraycopy(items, 0, STORAGE, index, items.length);
            if (size < index + items.length) size = index + items.length;
        }
        return items;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    public String remove(String item) {
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (item.equals(STORAGE[i])) {
                System.arraycopy(STORAGE, i + 1, STORAGE, i, size - 1 - i);
                size--;
                flag = true;
            }
        }
        if (flag) return item;
        throw new ItemNotFoundException(item + " not found");
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    public String remove(int index) {
        indexChecker(index);
        if (index > size - 1) {
            throw new IllegalIndexException("item with index " + index + " does not exist");
        }
        String output = STORAGE[index];
        System.arraycopy(STORAGE, index + 1, STORAGE, index, size - 1 - index);
        size--;
        return output;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(STORAGE[i])) return true;
        }
        return false;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(STORAGE[i])) return i;
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(STORAGE[i])) return i;
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public String get(int index) {
        if (index < 0) throw new IllegalIndexException("index " + index + " cannot be negative");
        if (index > size - 1) throw new IllegalIndexException("item with index " + index + " does not exist");
        return STORAGE[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringList otherList) { // Метод выполняет ту же функцию, что и метод в java.lang, но не сравнивает по классу, т.к. аргумент должен быть того же класса
        if (this.size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!STORAGE[i].equals(otherList.get(i))) return false;
        }
        return true;
    }


    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        return size;
    }


    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        if (this.size != 0) {
            return false;
        }
        return true;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        this.size = 0;
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray() {
        String[] output = new String[size];
        System.arraycopy(STORAGE, 0, output, 0, size);
        return output;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(STORAGE[i]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void indexChecker(int index) {
        if (index < 0) throw new IllegalIndexException("Argument index " + index + " cannot be negative");
        if (index > capacity - 1) throw new IllegalIndexException("Destination index " + index + " is beyond capacity");
    }

}
