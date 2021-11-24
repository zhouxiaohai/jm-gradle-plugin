package com.ejiahe.gradle.plugin.structure.dsl.impl

import com.ejiahe.gradle.plugin.structure.constant.PropertiesConstant
import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IDockerFileService
import org.gradle.api.Project

class DockerFileServiceImpl extends AbstractFileService implements IDockerFileService {

    DockerFileServiceImpl(Project project) {
        super(project)
    }

    @Override
    List<String> getDockerEntrypointTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.DOCKER_ENTRYPOINT_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.DOCKER_ENTRYPOINT
        }
        fileReplace(filePath)
    }

    @Override
    List<String> getDockerFileTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.DOCKER_FILE_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.DOCKER_FILE
        }
        fileReplace(filePath)
    }

    @Override
    List<String> getDockerLogBackTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.DOCKER_LOGBACK_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.DOCKER_LOGBACK
        }
        fileReplace(filePath)
    }
}
