package com.ejiahe.gradle.plugin.builder.dsl.impl

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import com.ejiahe.gradle.plugin.builder.dsl.TemplateModel
import org.gradle.api.Project

class ApplicationTemplateServiceImpl implements IApplicationTemplateService {
    private final Project project

    ApplicationTemplateServiceImpl(Project project) {
        this.project = project
    }

    @Override
    TemplateModel get() {
        TemplateModel templateModel = new TemplateModel()

        File file = new File(BuilderConstant.CONFIG_APPLICATION_TEMPLATE)
        if (!file.exists()) {
            println("Please after jm structure createConfigFile...")
        } else {
            Properties properties = new Properties()
            FileInputStream fis
            try {
                fis = new FileInputStream(file)
                // 将属性文件流装载到Properties对象中
                properties.load(fis)
                // 关闭流
                fis.close()
            } catch (FileNotFoundException e) {
                println(e.getMessage())
            } catch (IOException e) {
                println(e.getMessage())
            }

            def appName = properties.getProperty("application.name")
            def appDisplayName = properties.getProperty("application.displayName")
            def appVersion = properties.getProperty("application.version")
            def appReleaseName = appDisplayName + "_V" + appVersion + ".tar.gz"
            def appArtifactsName = appName + ".tar.gz"
            def appTag = properties.getProperty("application.tag")

            templateModel.setAppName(appName)
            templateModel.setAppDisplayName(appDisplayName)
            templateModel.setAppVersion(appVersion)
            templateModel.setAppReleaseName(appReleaseName)
            templateModel.setAppArtifactsName(appArtifactsName)
            templateModel.setAppTag(appTag)
        }

        return templateModel
    }
}
