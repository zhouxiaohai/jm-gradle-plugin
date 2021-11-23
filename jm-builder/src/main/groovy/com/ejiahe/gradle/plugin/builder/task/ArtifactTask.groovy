package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import org.gradle.api.DefaultTask
import org.gradle.api.file.CopySpec
import org.gradle.api.tasks.TaskAction

class ArtifactTask extends DefaultTask {
    IApplicationTemplateService applicationTemplateService

    @TaskAction
    void executor() {
        def appName = applicationTemplateService.get().getAppName()
        println "will build artifact: " + appName

        project.copy { CopySpec copySpec ->
            String buildInstallPath = "build" + File.separator + "install" + File.separator + project.name

            copySpec.from(buildInstallPath) {
                copySpec.include("lib" + File.separator + "**")
            }

            copySpec.from(".") {
                copySpec.include "config" + File.separator + "**"
                copySpec.include "install" + File.separator + "**"
                copySpec.include "bin" + File.separator + "**"
                copySpec.include "static" + File.separator + "**"
                copySpec.include "static-frontend" + File.separator + "**"
                copySpec.include BuilderConstant.CHANGELOG
                copySpec.include BuilderConstant.README
            }

            copySpec.into BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH + File.separator + appName

            println "build artifact to: " + BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH + File.separator + appName
        }
    }


}
