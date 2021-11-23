package com.ejiahe.gradle.plugin.structure.task

import com.ejiahe.gradle.plugin.structure.constant.PropertiesConstant
import org.gradle.api.tasks.TaskAction

class GradlePropertiesFileTask extends AbstractFileTask {

    @TaskAction
    void executor() {
        def file = project.file("gradle.properties")
        if (file.exists()) {
            Properties properties = new Properties()
            file.withInputStream {
                properties.load(it)
            }
            def lines = existsProperties(properties)
            if (!lines.isEmpty()) {
                def readLines = file.readLines()
                readLines.addAll(lines)
                printWriter(file, readLines)
            }
        } else {
            def lines = createProperties(true)
            printWriter(file, lines)
        }
    }

    private List<String> existsProperties(Properties properties) {
        List<String> addList = new ArrayList<>()
        def keySet = properties.keySet()
        def addProperties = createProperties(false)
        for (String key : addProperties) {
            def contains = keySet.contains(key)
            if (!contains) {
                addList.add(join(key, true))
            }
        }
        return addList
    }

    private List<String> createProperties(boolean flag) {
        List<String> proList = new ArrayList<>()
        proList.add(join(PropertiesConstant.BIN_PATH, flag))
        proList.add(join(PropertiesConstant.BIN_SERVICE_PATH, flag))
        proList.add(join(PropertiesConstant.APPLICATION_TEMPLATE_PATH, flag))
        proList.add(join(PropertiesConstant.KEYSTORE_PATH, flag))
        proList.add(join(PropertiesConstant.LOGBACK_DOCKER_PATH, flag))
        proList.add(join(PropertiesConstant.LOGBACK_TEMPLATE_PATH, flag))
        proList.add(join(PropertiesConstant.LOGBACK_TEMPLATE_JSON_PATH, flag))
        proList.add(join(PropertiesConstant.DEPENDENCY_PATH, flag))
        proList.add(join(PropertiesConstant.INSTALL_SQL_PATH, flag))
        proList.add(join(PropertiesConstant.META_PROPERTIES_PATH, flag))
        proList.add(join(PropertiesConstant.UPDATE_SH_PATH, flag))
        return proList
    }

    private String join(String basePro, boolean  flag) {
        if (flag) {
            return basePro + "="
        } else {
            return basePro
        }

    }
}
