package com.ejiahe.gradle.plugin.structure.dsl.impl

import com.ejiahe.gradle.plugin.structure.constant.PropertiesConstant
import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IBinFileService
import org.gradle.api.Project

class BinFileServiceImpl extends AbstractFileService implements IBinFileService {

    BinFileServiceImpl(Project project) {
        super(project)
    }


    @Override
    List<String> getBinTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.BIN_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.BIN_TEMPLATE
        }
        fileReplace(filePath)
    }

    @Override
    List<String> getBinServiceTemplate() {
        def filePath = loadProperties().get(PropertiesConstant.BIN_SERVICE_PATH)
        if ("" == filePath || null == filePath) {
            filePath = getBaseFilePath() + StructureConstant.BIN_SERVICE_TEMPLATE
        }
        fileReplace(filePath)
    }

}
