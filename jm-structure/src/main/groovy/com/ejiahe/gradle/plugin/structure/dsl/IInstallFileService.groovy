package com.ejiahe.gradle.plugin.structure.dsl

interface IInstallFileService {
    List<String> getDependencyTemplate()

    List<String> getInstallSqlTemplate()

    List<String> getMetaPropertiesTemplate()

    List<String> getUpdateTemplate()
}