package com.ejiahe.gradle.plugin.structure.dsl

interface IConfigFileService {
    List<String> getApplicationTemplate()

    InputStream getKeystoreTemplate()

    Map<String, File> getLogBackTemplate()
}