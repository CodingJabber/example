import java.io.Serializable;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 蟹老板 on 2017/3/23.
 */
public class CopyOnWriteHashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {
    private static final long serialVersionUID = 3140755708651297581L;
    private volatile HashSet<E> hashSet;
    private final transient ReentrantLock lock = new ReentrantLock();


    private CopyOnWriteArraySet copyOnWriteArraySet;
    private CopyOnWriteArrayList copyOnWriteArrayList;

    public CopyOnWriteHashSet() {
        hashSet = new HashSet();
    }

    public boolean add(E e) {
        lock.lock();
        try {
            HashSet<E> newHashSet = new HashSet(hashSet);
            boolean result = newHashSet.add(e);
            if (result)
                hashSet = newHashSet;
            return result;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return hashSet.isEmpty();
    }

    public boolean contains(Object e) {
        return hashSet.contains(e);
    }

    public boolean remove(Object e) {
        lock.lock();
        try {
            HashSet<E> newHashSet = new HashSet(hashSet);
            boolean result = newHashSet.remove(e);
            if (result)
                hashSet = newHashSet;
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        hashSet.clear();
    }

    public Object clone() {
        return hashSet.clone();
    }

    @Override
    public Iterator<E> iterator() {
        return hashSet.iterator();
    }

    @Override
    public int size() {
        return hashSet.size();
    }

}
