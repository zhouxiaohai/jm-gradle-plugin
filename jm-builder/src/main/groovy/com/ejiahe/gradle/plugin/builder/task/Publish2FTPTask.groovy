package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import org.gradle.api.DefaultTask
import org.gradle.api.file.CopySpec
import org.gradle.api.tasks.TaskAction

class Publish2FTPTask extends DefaultTask {
    IApplicationTemplateService applicationTemplateService

    @TaskAction
    void executor() {
        project.copy { CopySpec copySpec ->
            copySpec.from BuilderConstant.OUT_PUT_RELEASE_DIR
            copySpec.into BuilderConstant.FTP_DIR + applicationTemplateService.get().getAppName()
        }

        println "publish distribution to ftp"

    }


}
