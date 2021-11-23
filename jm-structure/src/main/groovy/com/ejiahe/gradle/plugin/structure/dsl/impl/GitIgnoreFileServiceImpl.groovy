package com.ejiahe.gradle.plugin.structure.dsl.impl

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IGitIgnoreFileService
import org.gradle.api.Project

class GitIgnoreFileServiceImpl extends AbstractFileService implements IGitIgnoreFileService {

    GitIgnoreFileServiceImpl(Project project) {
        super(project)
    }

    @Override
    InputStream getIgnoreServiceTemplate() {
        String filePath = getBaseFilePath() + StructureConstant.IGNORE_TEMPLATE
        getFile(filePath)
    }
}
