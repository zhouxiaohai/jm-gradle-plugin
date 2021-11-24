package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DeleteTask extends DefaultTask {

    @TaskAction
    void executor() {

        this.project.delete(BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH)
        println "clean: " + BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH

        this.project.delete(BuilderConstant.OUT_PUT_TEMP_DIR_PATH)
        println "clean: " + BuilderConstant.OUT_PUT_TEMP_DIR_PATH

        this.project.delete(BuilderConstant.OUT_PUT_RELEASE_DIR)
        println "clean: " + BuilderConstant.OUT_PUT_RELEASE_DIR

        this.project.delete(BuilderConstant.DOCKER_OUTPUT_DIR)
        println "clean: " + BuilderConstant.DOCKER_OUTPUT_DIR
    }
}
