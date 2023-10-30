package space.glowberry;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.*;
import java.util.logging.Logger;


public class NbMarkdownConverter {

    private final Logger logger = Logger.getLogger("NbMarkdownConverter");
    private final File notebook;
    private File markdown;

    private StringBuilder md_content;
    private StringBuilder original;

    public NbMarkdownConverter(File notebook) throws IOException {

        this.notebook = notebook;
        this.original = new StringBuilder();
        this.md_content = new StringBuilder();
        FileInputStream inputStream = new FileInputStream(this.notebook);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            original.append(line).append("\n");
        }
    }

    public void convert() {
        JSONObject nb = JSONObject.parseObject(this.original.toString());
//        System.out.println(nb.getJSONArray("cells"));
//         nb.getJSONArray("cells").getJSONObject(2).getJSONArray("source");

        logger.info("开始遍历所有单元……");
        for (Object o : nb.getJSONArray("cells")) {
            JSONObject a = (JSONObject) o;
            if(a.getString("cell_type").equals("markdown")){
                logger.info("当前单元类型为" + a.getString("cell_type") + "，继续操作");
                JSONArray source = a.getJSONArray("source");
                StringBuilder builder = new StringBuilder();
                for (Object object : source) {
                    logger.info("正在转换第" + source.indexOf(object) + "的内容到Markdown");
                    String s = (String) object;
                    builder.append(s);
                }
                md_content.append("\n").append(builder);
            }else {
                logger.info("当前单元类型为" + a.getString("cell_type") + "，跳过");
            }

        }
        logger.info("所有单元遍历结束，已将文件转换为Markdown格式，请等待下一步命令调用……");
        Main.emptyLine(2);
//        System.out.println(md_content);

    }

    public String getMarkdownContent(){
        return this.md_content.toString();
    }

    public void writeMarkdown() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(this.markdown);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(bos));
        writer.write(this.getMarkdownContent());
        writer.close();

    }

    public void writeMarkdown(File file) throws IOException{
        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(bos));
        writer.write(this.getMarkdownContent());
        writer.close();
    }

    public StringBuilder getMd_content() {
        return md_content;
    }


}
