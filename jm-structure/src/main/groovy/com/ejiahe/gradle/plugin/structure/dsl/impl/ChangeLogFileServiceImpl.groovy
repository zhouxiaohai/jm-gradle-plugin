package com.ejiahe.gradle.plugin.structure.dsl.impl

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IChangeLogFileService
import org.gradle.api.Project

class ChangeLogFileServiceImpl extends AbstractFileService implements IChangeLogFileService {

    ChangeLogFileServiceImpl(Project project) {
        super(project)
    }

    @Override
    InputStream getChangeLogTemplate() {
        String filePath = getBaseFilePath() + StructureConstant.CHANGELOG_TEMPLATE
        getFile(filePath)
    }
}
