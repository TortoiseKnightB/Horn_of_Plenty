package com.knight.design;

/**
 * 书架，用来存放Book的集合
 *
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class BookShelf implements Aggregate {

    private Book[] books;

    private int last = 0;

    public BookShelf(int maxSize) {
        this.books = new Book[maxSize];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public int getLength() {
        return last;
    }

    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    /**
     * 生成一个用于遍历集合的迭代器
     *
     * @return
     */
    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
