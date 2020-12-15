package com.hxh.basic.project.config.japidocs;

import cn.hutool.core.bean.BeanUtil;
import com.hxh.basic.project.util.resource.YamlUtil;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

import java.io.IOException;

public class JApiDocsMain {

    public static void main(String[] args) throws IOException {
        DocsConfig config = new DocsConfig();
        BeanUtil.copyProperties(YamlUtil.readToObject("japidocs.yml"), config, Boolean.TRUE);
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
