package com.ejiahe.gradle.plugin.structure.dsl.impl

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import org.gradle.api.Project

class AbstractFileService {
    protected final Project project
    private final Properties properties

    AbstractFileService(Project project) {
        this.project = project
    }

    protected Properties loadProperties() {
        Properties properties = new Properties()
        def file = project.file("gradle.properties")
        if (file.exists()) {
            file.withInputStream {
                properties.load(it)
            }
        }
        return properties
    }

    protected String getBaseFilePath() {
        String baseFilePath = loadProperties().get("jm.structure.base.path")
        if (baseFilePath == null) {
            baseFilePath = StructureConstant.JM_STRUCTURE_BASE_PATH
        }

        return baseFilePath
    }

    protected List<String> fileReplace(String path) {
        List<String> list = new ArrayList<>()
        def url = new URL(path)
        def connection = url.openConnection()
        def inputStream = connection.getInputStream()
        def lines = inputStream.readLines()

        for (String x : lines) {
            def replace = x.replace("{APP_NAME_FLAG}", project.name).replace("{APP_DISPLAY_NAME_FLAG}", project.name.toUpperCase())
            list.add(replace)
        }

        inputStream.close()

        return list
    }

    protected InputStream getFile(String path) {
        def url = new URL(path)
        def connection = url.openConnection()
        return connection.getInputStream()
    }
}
