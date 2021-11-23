package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DockerImagePush extends DefaultTask {
    IApplicationTemplateService applicationTemplateService

    @TaskAction
    void executor() {
        def model = applicationTemplateService.get()
        def docker_image_tag = BuilderConstant.DOCKER_IMAGE_TAG + model.getAppName() + ":" + model.getAppVersion()

        println "will push docker image: " + docker_image_tag
        String command = "docker push " + docker_image_tag
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
