package com.ejiahe.gradle.plugin.structure.dsl.impl

import com.ejiahe.gradle.plugin.structure.constant.PropertiesConstant
import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IInstallFileService
import org.gradle.api.Project

class InstallFileServiceImpl extends AbstractFileService implements IInstallFileService {

    InstallFileServiceImpl(Project project) {
        super(project)
    }

    @Override
    List<String> getDependencyTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.DEPENDENCY_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.INSTALL_DEPENDENCY_TEMPLATE
        }
        fileReplace(filePath)
    }

    @Override
    List<String> getInstallSqlTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.INSTALL_SQL_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.INSTALL_SQL_TEMPLATE
        }
        fileReplace(filePath)
    }

    @Override
    List<String> getMetaPropertiesTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.META_PROPERTIES_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.INSTALL_META_TEMPLATE
        }
        fileReplace(filePath)
    }

    @Override
    List<String> getUpdateTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.UPDATE_SH_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.INSTALL_UPDATE_TEMPLATE
        }
        fileReplace(filePath)
    }
}
