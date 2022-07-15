package com.knight.design;

/**
 * 遍历书架的迭代器
 *
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class BookShelfIterator implements Iterator {

    private BookShelf bookShelf;

    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    /**
     * 判断是否存在下一个元素
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    /**
     * 获取下一个元素
     *
     * @return
     */
    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
