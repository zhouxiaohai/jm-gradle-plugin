package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class UpdateMetaPropertiesTask extends DefaultTask {

    IApplicationTemplateService applicationTemplateService

    @TaskAction
    void executor() {
        Properties properties = new Properties()
        File file = project.file(BuilderConstant.META)
        file.withInputStream {
            properties.load(it)
        }

        def model = applicationTemplateService.get()
        properties.put("service", model.getAppName())
        properties.put("version", model.getAppVersion())
        properties.put("displayName", model.getAppDisplayName())
        properties.put("tag", model.getAppTag())

        file.withOutputStream {
            properties.store(it, "update properties")
        }
    }

}
