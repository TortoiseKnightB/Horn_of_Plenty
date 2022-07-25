package com.knight.design;

import com.knight.design.factory.Factory;
import com.knight.design.factory.Link;
import com.knight.design.factory.Page;
import com.knight.design.factory.Tray;

/**
 * @author TortoiseKnightB
 * @date 2022/07/22
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = Factory.getFactory("com.knight.design.listfactory.ListFactory");

        Link people_daily = factory.createLink("People Daily", "http://www.people.com.cn");
        Link light_daily = factory.createLink("Light Daily", "http://www.gmw.cn");
        Link baidu = factory.createLink("Baidu", "http://www.baidu.com");
        Link google = factory.createLink("Google", "http://www.google.com");

        Tray trayNews = factory.createTray("Daily");
        trayNews.add(people_daily);
        trayNews.add(light_daily);

        Tray trayEngine = factory.createTray("SearchEngine");
        trayEngine.add(baidu);
        trayEngine.add(google);

        Page page = factory.createPage("MyPage", "Yang");
        page.add(trayNews);
        page.add(trayEngine);

        System.out.println(page.makeHTML());

        Factory factory2 = Factory.getFactory("com.knight.design.tablefactory.TableFactory");
        Link tLink = factory2.createLink("tLink", "aaa.com");
        Tray tTray = factory2.createTray("tTray");
        tTray.add(tLink);
        Page tPage = factory2.createPage("tPage", "tathor");
        System.out.println(tPage.makeHTML());

    }
}
