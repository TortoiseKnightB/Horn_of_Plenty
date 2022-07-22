package com.knight.design;

/**
 * 使用纯文本编写文档
 *
 * @author TortoiseKnightB
 * @date 2022/07/21
 */
public class TextBuilder extends Builder {

    private StringBuffer buffer = new StringBuffer();

    /**
     * 编写标题
     *
     * @param title
     */
    @Override
    public void makeTitle(String title) {
        buffer.append("================================\n");
        buffer.append("[" + title + "]\n");
        buffer.append("\n");
    }

    /**
     * 编写字符串
     *
     * @param str
     */
    @Override
    public void makeString(String str) {
        buffer.append(" # " + str + "\n");
        buffer.append("\n");
    }

    /**
     * 编写条目
     *
     * @param items
     */
    @Override
    public void makeItems(String[] items) {
        for (String item : items) {
            buffer.append("   · " + item + "\n");
        }
        buffer.append("\n");
    }

    /**
     * 完成文档编写
     */
    @Override
    public void close() {
        buffer.append("==========================\n");
    }

    public String getResult() {
        return buffer.toString();
    }
}
