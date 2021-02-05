package com.hxh.basic.project.config.plugin.japidocs;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * 接口文档生成
 *
 * @author yomu
 * @version 1.0
 * @date 2020/12/15 13:21
 * <p>
 * Copyright 2021 yomu Inc.
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class JApiDocsMain {

    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("E:\\code\\basic_project");
        // 项目名称
        config.setProjectName("project");
        // 声明该API的版本
        config.setApiVersion("V1.0");
        // 生成API 文档所在目录
        config.setDocsPath("E:\\code\\basic_project\\src\\main\\resources\\static");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}
