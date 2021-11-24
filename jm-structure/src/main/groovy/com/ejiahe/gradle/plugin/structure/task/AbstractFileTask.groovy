package com.ejiahe.gradle.plugin.structure.task

import org.gradle.api.DefaultTask

abstract class AbstractFileTask extends DefaultTask {

    protected boolean existsFile(String fileName) {
        def file = project.file(fileName)
        return file.exists()
    }

    protected String checkDir(String path) {
        def parentFile = project.file(path)
        if (!parentFile.parentFile.exists()) {
            parentFile.parentFile.mkdir()
        }
        return path
    }

    protected void writeFile(List<String> readLines, String fileDistPath) {
        writeFile(readLines, fileDistPath, null)
    }

    protected void writeFile(List<String> readLines, String fileDistPath, String backFileName) {
        checkDir(fileDistPath)

        def file = project.file(fileDistPath)
        if (file.exists()) {
            if (backFileName != null) {
                file = project.file(backFileName)
            }
        }
        printWriter(file, readLines)
    }


    protected void printWriter(File file, List<String> lines) {
        def writer = file.newPrintWriter()
        for (String x : lines) {
            writer.write(x)
            writer.write("\n")
        }
        writer.flush()
        writer.close()
    }

    protected void copy(InputStream inputStream, String newPath) {
        try {
            int byteSum = 0
            int byteRead = 0
            FileOutputStream fs = new FileOutputStream(newPath)
            byte[] buffer = new byte[1444]
            int length
            while ((byteRead = inputStream.read(buffer)) != -1) {
                //字节数 文件大小
                byteSum += byteRead
                fs.write(buffer, 0, byteRead)
            }
            inputStream.close()
        } catch (Exception e) {
            println("message=" + e.getMessage() + "e=" + e)
        }
    }

    protected void copyFile(InputStream oldFile, String newPath) {
        checkDir(newPath)
        copy(oldFile, newPath)
    }


}
