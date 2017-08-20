package me.wuwenbin.tools.util.xml;

import me.wuwenbin.tools.util.exception.LangException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * XML工具类<br>
 * 此工具使用w3c dom工具，不需要依赖第三方包。<br>
 *
 * @author xiaoleilu
 */
public class W3cXml {

    /**
     * 在XML中无效的字符 正则
     */
    public final static String INVALID_REGEX = "[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]";

    // -------------------------------------------------------------------------------------- Read

    /**
     * 读取解析XML文件
     *
     * @param file XML文件
     * @return XML文档对象
     */
    public Document readXML(File file) {
        if (file == null) {
            throw new NullPointerException("Xml file is null !");
        }
        if (!file.exists()) {
            throw new LangException("Files [" + file.getAbsolutePath() + "] not a exist!");
        }
        if (!file.isFile()) {
            throw new LangException("[" + file.getAbsolutePath() + "] not a file!");
        }

        try {
            file = file.getCanonicalFile();
        } catch (IOException ignored) {
        }

        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = dbf.newDocumentBuilder();
            return builder.parse(file);
        } catch (Exception e) {
            throw new LangException("Parse xml file [" + file.getAbsolutePath() + "] error!", e);
        }
    }

    /**
     * 读取解析XML文件
     *
     * @param absoluteFilePath XML文件绝对路径
     * @return XML文档对象
     */
    public Document readXML(String absoluteFilePath) {
        return readXML(new File(absoluteFilePath));
    }


    /**
     * 根据节点名获得子节点列表
     *
     * @param element 节点
     * @param tagName 节点名
     * @return 节点列表
     */
    public List<Element> getElements(Element element, String tagName) {
        final NodeList nodeList = element.getElementsByTagName(tagName);
        return transElements(element, nodeList);
    }

    /**
     * 根据节点名获得第一个子节点
     *
     * @param element 节点
     * @param tagName 节点名
     * @return 节点
     */
    public Element getElement(Element element, String tagName) {
        final NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList == null || nodeList.getLength() < 1) {
            return null;
        }
        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            Element childEle = (Element) nodeList.item(i);
            if (childEle == null || childEle.getParentNode() == element) {
                return childEle;
            }
        }
        return null;
    }

    /**
     * 根据节点名获得第一个子节点
     *
     * @param element 节点
     * @param tagName 节点名
     * @return 节点中的值
     */
    public String elementText(Element element, String tagName) {
        Element child = getElement(element, tagName);
        return child == null ? null : child.getTextContent();
    }

    /**
     * 根据节点名获得第一个子节点
     *
     * @param element 节点
     * @param tagName 节点名
     * @return 节点中的值
     */
    public String elementText(Element element, String tagName, String defaultValue) {
        Element child = getElement(element, tagName);
        return child == null ? defaultValue : child.getTextContent();
    }

    /**
     * 将NodeList转换为Element列表
     *
     * @param nodeList NodeList
     * @return Element列表
     */
    public List<Element> transElements(NodeList nodeList) {
        return transElements(null, nodeList);
    }

    /**
     * 将NodeList转换为Element列表
     *
     * @param parentEle 父节点，如果指定将返回此节点的所有直接子节点，null返回所有节点
     * @param nodeList  NodeList
     * @return Element列表
     */
    public List<Element> transElements(Element parentEle, NodeList nodeList) {
        final ArrayList<Element> elements = new ArrayList<>();
        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            Element element = (Element) nodeList.item(i);
            if (parentEle == null || element.getParentNode() == parentEle) {
                elements.add(element);
            }
        }
        return elements;
    }

}
