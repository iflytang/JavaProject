package src.interview;

import java.util.Iterator;
import java.util.LinkedList;

public class myHashTable {

    int BASE = 567;
    transient LinkedList [] entry;

    public myHashTable () {
        entry = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            entry[i] = new LinkedList<Table>();
        }
    }

    public int hashCode (int key) {
        return key % BASE;
    }

    public synchronized void put (int key, int value) {
        int idx = hashCode(key);
        Iterator<Table> iterator = entry[idx].iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();

            if (table.key == key) {
                table.value = value;
                return;
            }
        }

        // add new table
        entry[idx].addLast(new Table(key, value));
    }

    public synchronized int get (int key) {
        int idx = hashCode(key);
        Iterator<Table> iterator = entry[idx].iterator();

        while (iterator.hasNext()) {
            Table table = iterator.next();
            if (table.key == key) {
                return table.value;
            }
        }

        return -1;

    }

    public synchronized void remove (int key) {
        int idx = hashCode(key);
        Iterator<Table> iterator = entry[idx].iterator();

        while (iterator.hasNext()) {
            Table table = iterator.next();

            if (table.key == key) {
                entry[idx].remove(table);
                return;
            }

        }

    }
}

class Table {
    int key;
    int value;

    public Table (int key, int value) {
        this.key = key;
        this.value = value;
    }
}