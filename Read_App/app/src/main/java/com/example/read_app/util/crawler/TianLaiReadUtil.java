package com.example.read_app.util.crawler;

import android.text.Html;

import com.example.read_app.callback.ResultCallback;
import com.example.read_app.common.URLCONST;
import com.example.read_app.entity.Item;
import com.example.read_app.greendao.entity.Book;
import com.example.read_app.greendao.entity.Chapter;
import com.example.read_app.util.StringHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.read_app.common.URLCONST.nameSpace_tianlai;
import static com.example.read_app.webapi.BaseApi.getCommonReturnHtmlStringApi;

/**
 * 天籁小说网html解析工具
 * Created by zhao on 2017/7/24.
 */

public class TianLaiReadUtil {


    /**
     * 从html中获取章节正文
     *
     * @param html
     * @return
     */
    public static String getContentFormHtml(String html) {

        Pattern pattern = Pattern.compile("<div id=\"content\">[\\s\\S]*?</div>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String content = Html.fromHtml(matcher.group(0)).toString();
            char c = 160;
            String spaec = "" + c;
            content = content.replace(spaec, "  ");
            return content;
        } else {
            return "";
        }
    }

    /**
     * 从html中获取章节列表
     *
     * @param html
     * @return
     */
    public static ArrayList<Chapter> getChaptersFromHtml(String html) {
        ArrayList<Chapter> chapters = new ArrayList<>();
        Pattern pattern = Pattern.compile("<dd><a href=\"([\\s\\S]*?)</a>");
//        Pattern pattern = Pattern.compile("<dd><a href=\"([\\s\\S]*?)\"");
        Matcher matcher = pattern.matcher(html);
        String lastTile = null;
        int i = 0;
        while (matcher.find()) {
            String content = matcher.group();
            String title = content.substring(content.indexOf("\">") + 2, content.lastIndexOf("<"));
            if (!StringHelper.isEmpty(lastTile) && title.equals(lastTile)) {
                continue;
            }
            Chapter chapter = new Chapter();
            chapter.setNumber(i++);
            chapter.setTitle(title);
            chapter.setUrl(content.substring(content.indexOf("\"") + 1, content.lastIndexOf("l\"") + 1));
            chapters.add(chapter);
            lastTile = title;
        }
        return chapters;
    }

    /**
     * 从搜索html中得到书列表
     *
     * @param html
     * @return
     */
    public static ArrayList<Book> getBooksFromSearchHtml(String html) {
        ArrayList<Book> books = new ArrayList<>();
         Document doc = Jsoup.parse(html);
//        Element node = doc.getElementById("results");
//        for (Element div : node.children()) {
        Elements divs = doc.getElementsByClass("result-list");
        Element div = divs.get(0);
//        if (!StringHelper.isEmpty(div.className()) && div.className().equals("result-list")) {
        for (Element element : div.children()) {
            Book book = new Book();
            Element img = element.child(0).child(0).child(0);
            book.setImgUrl(img.attr("src"));
            Element title = element.getElementsByClass("result-item-title result-game-item-title").get(0);
            book.setName(title.child(0).attr("title"));
            book.setChapterUrl(title.child(0).attr("href"));
            Element desc = element.getElementsByClass("result-game-item-desc").get(0);
            book.setDesc(desc.text());
            Element info = element.getElementsByClass("result-game-item-info").get(0);
            for (Element element1 : info.children()) {
                String infoStr = element1.text();
                if (infoStr.contains("作者：")) {
                    book.setAuthor(infoStr.replace("作者：", "").replace(" ", ""));
                } else if (infoStr.contains("类型：")) {
                    book.setType(infoStr.replace("类型：", "").replace(" ", ""));
                } else if (infoStr.contains("更新时间：")) {
                    book.setUpdateDate(infoStr.replace("更新时间：", "").replace(" ", ""));
                } else {
                    Element newChapter = element1.child(1);
                    book.setNewestChapterUrl(newChapter.attr("href"));
                    book.setNewestChapterTitle(newChapter.text());
                }
            }
            books.add(book);
//            }
//            }
        }

        return books;
    }
    /**
     * 从搜索html中得到书列表
     *
     * @param html
     * @return
     */
    public static ArrayList<Book> getBooksFromStoreHtml(String html,String key) {
        ArrayList<Book> books = new ArrayList<>();
         Document doc = Jsoup.parse(html);
        Elements divs = doc.getElementsByClass("item");
        for (int i=0;i<divs.size();i++){
            Book book = new Book();
            Element element=divs.get(i);
            Element img = element.child(0).child(0).child(0);
            book.setImgUrl(img.attr("src"));
            Elements trs = element.select("a");
            Element title = trs.get(1);
            book.setName(title.text());
            book.setChapterUrl(nameSpace_tianlai+"/"+title.attr("href"));
            Elements trs2 = element.select("span");
            Element setAuthor = trs2.get(0);
            book.setAuthor(setAuthor.text());
             Elements trs3 = element.select("dd");
            Element setAuthor3 = trs3.get(0);
            book.setDesc(setAuthor3.text());
            books.add(book);
        }
        Elements divsee = doc.select("ul");
        for (int i=divsee.size()-1;i>=divsee.size()-2;i--){
            Element element=divsee.get(i);
            Elements tds = element.select("li");
//        if (!StringHelper.isEmpty(div.className()) && div.className().equals("result-list")) {
            for (int j = 0; j < tds.size(); ++j) {
                final Book book = new Book();
                Elements trs = tds.get(j).select("a");
                book.setName(trs.get(0).text());
                book.setChapterUrl(nameSpace_tianlai+"/"+trs.get(0).attr("href"));
                if(key.equals("/")){
                    Element title = tds.get(j).getElementsByClass("s1").get(0);
                    book.setType(title.text());
                }
                books.add(book);
            }
            }
        return books;
    }
    /**
     * 从搜索html中得到分类
     *
     * @param html
     * @return
     */
    public static ArrayList<Item> getBooksClassHtml(String html) {
        ArrayList<Item> items = new ArrayList<>();
         Document doc = Jsoup.parse(html);
        Elements trs = doc.select("ul");

      Element div = trs.get(0);
       Elements tds = div.select("li");
//        if (!StringHelper.isEmpty(div.className()) && div.className().equals("result-list")) {
        for (int j = 0; j < tds.size()-1; ++j) {
            Element td = tds.get(j);
            Item item = new Item();
             item.setUrl(td.child(0).attr("href"));
             item.setName(td.child(0).text());
             if(j==0)item.setIsselect(1);
            items.add(item);
//            }
//            }
        }

        return items;
    }
}
