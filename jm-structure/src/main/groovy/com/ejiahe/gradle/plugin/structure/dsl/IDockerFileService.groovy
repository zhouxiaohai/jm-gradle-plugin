package com.ejiahe.gradle.plugin.structure.dsl

interface IDockerFileService {
    List<String> getDockerEntrypointTemplate()

    List<String> getDockerFileTemplate()

    List<String> getDockerLogBackTemplate()
}