package com.ejiahe.gradle.plugin.structure.dsl.impl

import com.ejiahe.gradle.plugin.structure.constant.PropertiesConstant
import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IConfigFileService
import org.gradle.api.Project

class ConfigFileServiceImpl extends AbstractFileService implements IConfigFileService {

    ConfigFileServiceImpl(Project project) {
        super(project)

    }

    @Override
    List<String> getApplicationTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.APPLICATION_TEMPLATE_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.CONFIG_APPLICATION_TEMPLATE
        }
        fileReplace(filePath)
    }

    @Override
    InputStream getKeystoreTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.KEYSTORE_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.CONFIG_KEYSTORE_TEMPLATE
        }
        getFile(filePath)
    }

    @Override
    Map<String, File> getLogBackTemplate() {
        Map<String, File> maps = new HashMap<>(3)
        def properties = loadProperties()
        def config_logback_docker = properties.get(PropertiesConstant.LOGBACK_DOCKER_PATH)
        if ("" == config_logback_docker || null == config_logback_docker) {
            config_logback_docker = getBaseFilePath() + StructureConstant.CONFIG_LOGBACK_DOCKER
        }
        def logBackDockerFile = getFile(config_logback_docker)
        maps.put(StructureConstant.CONFIG_LOGBACK_DOCKER, logBackDockerFile)

        def config_logback_template = properties.get(PropertiesConstant.LOGBACK_TEMPLATE_PATH)
        if ("" == config_logback_template || null == config_logback_template) {
            config_logback_template = getBaseFilePath() + StructureConstant.CONFIG_LOGBACK_TEMPLATE
        }
        def logBackTemplateFile = getFile(config_logback_template)
        maps.put(StructureConstant.CONFIG_LOGBACK_TEMPLATE, logBackTemplateFile)

        def config_logback_template_json = properties.get(PropertiesConstant.LOGBACK_TEMPLATE_JSON_PATH)
        if ("" == config_logback_template_json || null == config_logback_template_json) {
            config_logback_template_json = getBaseFilePath() + StructureConstant.CONFIG_LOGBACK_TEMPLATE_JSON
        }
        def logBackTemplateJsonFile = getFile(config_logback_template_json)
        maps.put(StructureConstant.CONFIG_LOGBACK_TEMPLATE_JSON, logBackTemplateJsonFile)

        return maps
    }
}
