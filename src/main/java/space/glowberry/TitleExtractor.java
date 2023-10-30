package space.glowberry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class TitleExtractor {
    private final String markdownText;

    private final List<Object[]> metadata;
    private final Logger logger = Logger.getLogger("TitleExtractor");

    public TitleExtractor(String markdownText) {
        this.markdownText = markdownText;
        this.metadata = new LinkedList<>();
    }

    public void extract(){
        int maxLevel = 6;
        for (String line : this.markdownText.split("\n")) {
            if(line.trim().isEmpty()) continue;
            if(line.split("\\s+")[0].contains("#")){
                int level = line.split("\\s+")[0].length();
                if(level < maxLevel) maxLevel = level;
                logger.info("提取到" + level + "级标题：" + line);
                metadata.add(new Object[]{level, line});
                logger.info("成功识别并载入标题 " + line);
            }
        }
    }

    public List<Object[]> getMetadata(){
        return this.metadata;
    }
}
