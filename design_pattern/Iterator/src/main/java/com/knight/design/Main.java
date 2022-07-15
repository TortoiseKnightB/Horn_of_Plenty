package com.knight.design;

/**
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Animal Farm"));
        bookShelf.appendBook(new Book("Hamlet"));
        bookShelf.appendBook(new Book("King Lear"));
        bookShelf.appendBook(new Book("Romeo and Juliet"));

        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getName());
        }
    }
}
