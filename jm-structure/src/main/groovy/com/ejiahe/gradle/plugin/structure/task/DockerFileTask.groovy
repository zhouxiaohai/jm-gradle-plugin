package com.ejiahe.gradle.plugin.structure.task

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IDockerFileService
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class DockerFileTask extends AbstractFileTask {
    private final IDockerFileService dockerFileService

    @Inject
    DockerFileTask(IDockerFileService dockerFileService) {
        this.dockerFileService = dockerFileService
    }

    @TaskAction
    void executor() {
        writeDockerEntrypoint()
        writeDockerFile()
        writeDockerLogBack()
    }

    void writeDockerEntrypoint() {
        if (!existsFile(StructureConstant.DOCKER_ENTRYPOINT)) {
            def entrypointTemplate = dockerFileService.getDockerEntrypointTemplate()
            writeFile(entrypointTemplate, StructureConstant.DOCKER_ENTRYPOINT)
        }
    }

    void writeDockerFile() {
        if (!existsFile(StructureConstant.DOCKER_FILE)) {
            def dockerFileTemplate = dockerFileService.getDockerFileTemplate()
            writeFile(dockerFileTemplate, StructureConstant.DOCKER_FILE)
        }
    }

    void writeDockerLogBack() {
        if (!existsFile(StructureConstant.DOCKER_LOGBACK)) {
            def dockerLogBackTemplate = dockerFileService.getDockerLogBackTemplate()
            writeFile(dockerLogBackTemplate, StructureConstant.DOCKER_LOGBACK)
        }
    }
}
