package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import org.gradle.api.DefaultTask
import org.gradle.api.file.CopySpec
import org.gradle.api.tasks.TaskAction

class DockerImage extends DefaultTask {
    IApplicationTemplateService applicationTemplateService

    @TaskAction
    void executor() {
        def model = applicationTemplateService.get()

        def docker_image_tag = BuilderConstant.DOCKER_IMAGE_TAG + model.getAppName() + ":" + model.getAppVersion()

        project.copy {
            CopySpec copySpec ->
                copySpec.from BuilderConstant.DOCKER_ENTRYPOINT
                copySpec.from BuilderConstant.DOCKER_File
                copySpec.from BuilderConstant.DOCKER_LOGBACK
                copySpec.from BuilderConstant.OUT_PUT_TEMP_DIR_PATH + File.separator + model.getAppArtifactsName()
                copySpec.into BuilderConstant.DOCKER_OUTPUT_DIR
        }

        println "will build docker image: " + docker_image_tag
        String command = "docker build -t " + docker_image_tag + " " + BuilderConstant.DOCKER_OUTPUT_DIR
        println command
        execShell(command)

    }


    def execShell(cmd) {
        def proc =  cmd.execute()
        proc.waitFor()
        println "${proc.err.text}"
        println "${proc.text}"
        return proc
    }
}
